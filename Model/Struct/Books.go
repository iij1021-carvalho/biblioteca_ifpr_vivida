package Model

import (
	conexao "meuapp/db"
)

type Books struct {
	IDBOOK       int    `json:"IDBOOK"`
	CODIGO_BARRA int    `json:"CODIGO_BARRA"`
	AUTOR        string `json:"AUTOR"`
	TITULO       string `json:"TITULO"`
	ISBN         string `json:"ISBN"`
	IDCATEGORIA  int    `json:"IDCATEGORIA"`
	QUANTIDADE   int    `json:"QUANTIDADE"`
}

type Books_Paginacao struct {
	INICIAL int `json:"INICIAL"`
	FINAL   int `json:"FINAL"`
}

func (book Books) RegistrarLivro() (Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, erro = conexaogeral.Begin()

	if erro != nil {
		transacao.Rollback()
		return book, erro
	}

	var resultado, err = transacao.Exec(
		`INSERT INTO BOOK (CODIGO_BARRA,AUTOR,TITULO,ISBN,IDCATEGORIA,QUANTIDADE)
					 VALUES(?,?,?,?,?,?)`,
		book.CODIGO_BARRA,
		book.AUTOR,
		book.TITULO,
		book.ISBN,
		book.IDCATEGORIA,
		book.QUANTIDADE)

	if err != nil {
		transacao.Rollback()
		return book, err
	}

	var row, errro = resultado.LastInsertId()

	if errro != nil {
		transacao.Rollback()
		return book, errro
	}

	if row > 0 {
		transacao.Commit()
		book.IDBOOK = int(row)
	}
	return book, nil
}

func (book Books) EditarLivro() (Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, errro = conexaogeral.Begin()

	if errro != nil {
		return book, errro
	}

	var resultado, erro = transacao.Exec(
		`UPDATE BOOK 
		    SET CODIGO_BARRA = ?,
				AUTOR = ?,
			    TITULO = ?,
			    ISBN = ?,
				IDCATEGORIA = ?,
				QUANTIDADE = ?
		  WHERE IDBOOK = ?`,
		book.CODIGO_BARRA,
		book.AUTOR,
		book.TITULO,
		book.ISBN,
		book.IDBOOK,
		book.IDCATEGORIA,
		book.QUANTIDADE)

	if erro != nil {
		return book, erro
	}

	linhas, erro := resultado.RowsAffected()

	if erro != nil {
		return book, erro
	}

	if linhas > 0 {
		return book, nil
	}

	return book, nil
}

func (book Books) DeletarLivro() (Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, err = conexaogeral.Begin()

	if err != nil {
		return book, err
	}

	_, erro := transacao.Exec(`DELETE FROM BOOK WHERE IDBOOK = ?`, book.IDBOOK)

	if erro != nil {
		return book, erro
	}
	return book, nil
}

func (book Books) BuscaLivroCodigo() ([]Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var book_ []Books
	var resultado, erro = conexaogeral.Query(`
				SELECT IDBOOK,
					   CODIGO_BARRA,
					   AUTOR,
			           TITULO,
					   ISBN,
			           IDCATEGORIA,
					   QUANTIDADE
				  FROM BOOK
				 WHERE CODIGO_BARRA = ?`, book.CODIGO_BARRA)

	if erro != nil {
		return book_, erro
	}

	for resultado.Next() {
		var errr = resultado.Scan(&book.IDBOOK, &book.CODIGO_BARRA, &book.AUTOR, &book.TITULO, &book.ISBN, &book.IDCATEGORIA, &book.QUANTIDADE)

		if errr != nil {
			return book_, errr
		}

		book_ = append(book_, book)
	}
	return book_, nil
}

func (book Books_Paginacao) RetornaLivrosPaginacao() ([]Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, err = conexaogeral.Begin()
	var book_ []Books
	var books Books

	if err != nil {
		return book_, err
	}

	var resultado, erro = transacao.Query(`
			SELECT DISTINCT ISBN,
	   			   IDBOOK,
	   			   CODIGO_BARRA,
       			   AUTOR,
       			   TITULO,
       			   QUANTIDADE
       		  FROM BOOK
   			 WHERE IDBOOK > ?
   			ORDER BY IDBOOK
   			LIMIT ?`,
		book.INICIAL, book.FINAL)

	if erro != nil {
		return book_, erro
	}

	for resultado.Next() {
		erro = resultado.Scan(&books.ISBN, &books.IDBOOK, &books.CODIGO_BARRA, &books.AUTOR, &books.TITULO, &books.QUANTIDADE)
		if erro != nil {
			return book_, erro
		}

		book_ = append(book_, books)
	}

	return book_, erro
}
