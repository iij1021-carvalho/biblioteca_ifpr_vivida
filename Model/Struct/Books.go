package Model

import (
	conexao "meuapp/db"
)

type Books struct {
	ID_LIVRO     int    `json:"ID_LIVRO"`
	DATA         string `json:"DATA"`
	CODIGO_LIVRO int    `json:"CODIGO_LIVRO"`
	AUTOR        string `json:"AUTOR"`
	TITULO       string `json:"TITULO"`
	ISBN         string `json:"ISBN"`
	IDCATEGORIA  int    `json:"IDCATEGORIA"`
}

type Books_Paginacao struct {
	INICIAL int `json:"INICIAL"`
	FINAL   int `json:"FINAL"`
}

func (book Books) AtualizarRegistros() (Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, erro = conexaogeral.Begin()

	if erro != nil {
		return book, erro
	}

	var resultado, err = conexaogeral.Query(`SELECT ID_LIVRO FROM LIVRO`)

	if err != nil {
		return book, err
	}

	for resultado.Next() {
		var errr = resultado.Scan(&book.ID_LIVRO)

		if errr != nil {
			return book, errr
		}

		var _, erro = transacao.Exec(`UPDATE LIVRO SET IDCATEGORIA = ? WHERE ID_LIVRO = ?`, book.ID_LIVRO, book.ID_LIVRO)

		if erro != nil {
			return book, erro
		}
	}

	transacao.Commit()
	return book, nil

}

func (book Books) RegistrarLivro() (Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, erro = conexaogeral.Begin()

	if erro != nil {
		transacao.Rollback()
		return book, erro
	}

	var resultado, err = transacao.Exec(
		`INSERT INTO LIVRO (DATA,CODIGO_LIVRO,AUTOR,TITULO,ISBN)
					 VALUES(?,?,?,?,?)`,
		book.DATA,
		book.CODIGO_LIVRO,
		book.AUTOR,
		book.TITULO,
		book.ISBN)

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
		book.ID_LIVRO = int(row)
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
		`UPDATE LIVRO 
		    SET DATA = ?,
		        CODIGO_LIVRO = ?,
		  	    AUTOR = ?,
			    TITULO = ?,
			    ISBN = ?
		  WHERE ID_LIVRO = ?`,
		book.DATA,
		book.CODIGO_LIVRO,
		book.AUTOR,
		book.TITULO,
		book.ISBN,
		book.ID_LIVRO)

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

	_, erro := transacao.Exec(`DELETE FROM LIVRO WHERE ID_LIVRO = ?`, book.ID_LIVRO)

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
			SELECT ID_LIVRO,
				   DATE_FORMAT(DATA,'%d/%m/%Y') AS DATA,
				   CODIGO_LIVRO,
				   AUTOR,
				   TITULO,
				   ISBN
			  FROM LIVRO
			 WHERE ID_LIVRO > ?
			 LIMIT ?`, book.INICIAL, book.FINAL)

	if erro != nil {
		return book_, erro
	}

	for resultado.Next() {
		erro = resultado.Scan(&books.ID_LIVRO, &books.DATA, &books.CODIGO_LIVRO, &books.AUTOR, &books.TITULO, &books.ISBN)
		if erro != nil {
			return book_, erro
		}

		book_ = append(book_, books)
	}

	return book_, erro
}
