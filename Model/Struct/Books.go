package Model

import (
	conexao "meuapp/db"
)

type Books struct {
	IDBOOK      int    `json:"IDBOOK"`
	AUTOR       string `json:"AUTOR"`
	TITULO      string `json:"TITULO"`
	ISBN        string `json:"ISBN"`
	IDCATEGORIA int    `json:"IDCATEGORIA"`
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
		`INSERT INTO BOOK (AUTOR,TITULO,ISBN,IDCATEGORIA)
					 VALUES(?,?,?,?)`,
		book.AUTOR,
		book.TITULO,
		book.ISBN,
		book.IDCATEGORIA)

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
		    SET AUTOR = ?,
			    TITULO = ?,
			    ISBN = ?,
				IDCATEGORIA = ?
		  WHERE IDBOOK = ?`,
		book.AUTOR,
		book.TITULO,
		book.ISBN,
		book.IDBOOK,
		book.IDCATEGORIA)

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

func (book Books_Paginacao) RetornaTodosLivros() ([]Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, err = conexaogeral.Begin()
	var book_ []Books
	var books Books

	if err != nil {
		return book_, err
	}

	var resultado, erro = transacao.Query(`
			SELECT IDBOOK,
				   AUTOR,
				   TITULO,
				   ISBN,
				   IDCATEGORIA
			  FROM BOOK
			 WHERE IDBOOK > ?
			 LIMIT ?`, book.INICIAL, book.FINAL)

	if erro != nil {
		return book_, erro
	}

	for resultado.Next() {
		erro = resultado.Scan(&books.IDBOOK, &books.AUTOR, &books.TITULO, &books.ISBN, &books.IDCATEGORIA)
		if erro != nil {
			return book_, erro
		}

		book_ = append(book_, books)
	}

	return book_, erro
}
