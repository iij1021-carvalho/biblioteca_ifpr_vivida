package Model

import (
	conexao "meuapp/db"
)

type Books struct {
	ID          int    `json:"ID"`
	TITULLO     string `json:"TITULO"`
	AUTOR       string `json:"AUTOR"`
	ANO         string `json:"ANO"`
	QRCODE      string `json:"QRCODE"`
	LOCALIZACAO string `json:"LOCALIZACAO"`
	STATUS      string `json:"STATUS"`
}

func (book Books) RegistrarLivro() (Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, erro = conexaogeral.Begin()

	if erro != nil {
		transacao.Rollback()
		return book, erro
	}

	var resultado, err = transacao.Exec(
		"	 INSERT INTO BOOKS  							   "+
			"(TITULO, AUTOR, ANO, QRCODE, LOCALIZACAO, STATUS) "+
			" VALUES(?, ?, ?, ?, ?, ?)                         ",
		book.TITULLO,
		book.AUTOR,
		book.ANO,
		book.QRCODE,
		book.LOCALIZACAO,
		book.STATUS)

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
		book.ID = int(row)
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
		"	  UPDATE BOOKS SET TITULO = ?, 		"+
			"         		   AUTOR  = ?,	    "+
			"         		   ANO    = ?, 		"+
			"         		   QRCODE = ?,		"+
			"         		   LOCALIZACAO = ?, "+
			"         		   STATUS = ?       "+
			" 			 WHERE ID = ?           ",
		book.TITULLO,
		book.AUTOR,
		book.ANO,
		book.QRCODE,
		book.LOCALIZACAO,
		book.STATUS,
		book.ID)

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

	_, erro := transacao.Exec(
		"DELETE FROM BOOKS WHERE ID = ?", book.ID)

	if erro != nil {
		return book, erro
	}
	return book, nil
}

func (book Books) RetornaTodosLivros() ([]Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, err = conexaogeral.Begin()
	var book_ []Books

	if err != nil {
		return book_, err
	}

	var resultado, erro = transacao.Query(
		"		SELECT ID,          " +
			"	       TITULO,      " +
			"	       AUTOR,       " +
			"	       ANO,         " +
			"	       QRCODE,      " +
			"	       LOCALIZACAO, " +
			"          STATUS       " +
			"     FROM BOOKS 		")

	if erro != nil {
		return book_, erro
	}

	for resultado.Next() {
		erro = resultado.Scan(
			&book.ID, &book.TITULLO, &book.AUTOR,
			&book.ANO, &book.QRCODE, &book.LOCALIZACAO, &book.STATUS)

		if erro != nil {
			return book_, erro
		}

		book_ = append(book_, book)
	}

	return book_, erro
}
