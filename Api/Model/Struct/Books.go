package Model

import (
	conexao "meuapp/db"
)

type Books struct {
	ID             int    `json:"ID"`
	TITULLO        string `json:"TITULO"`
	AUTOR          string `json:"AUTOR"`
	ANO            string `json:"ANO"`
	QRCODE         string `json:"QRCODE"`
	STATUS         string `json:"STATUS"`
	ID_CATEGORIA   int    `json:"ID_CATEGORIA"`
	ID_LOCALIZACAO int    `json:"ID_LOCALIZACAO"`
	PASTA_CAPA     string `json:"PASTA_CAPA"`
}

func (book Books) RegistrarLivro() (Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, erro = conexaogeral.Begin()

	if erro != nil {
		transacao.Rollback()
		return book, erro
	}

	var resultado, err = transacao.Exec(`
			 INSERT INTO BOOKS (TITULO, AUTOR, ANO, QRCODE, STATUS,ID_CATEGORIA,ID_LOCALIZACAO,PASTA_CAPA)              
			                   VALUES(?,?,?,?,?,?,?,?)`,
		book.TITULLO,
		book.AUTOR,
		book.ANO,
		book.QRCODE,
		book.STATUS,
		book.ID_CATEGORIA,
		book.ID_LOCALIZACAO,
		book.PASTA_CAPA)

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

	var resultado, erro = transacao.Exec(`
		UPDATE BOOKS SET TITULO = ?, 		
		        		 AUTOR  = ?,	   
		        		 ANO    = ?, 		
		        		 QRCODE = ?,		
		        		 STATUS = ?,
						 ID_CATEGORIA = ?,
						 ID_LOCALIZACAO = ?,
						 PASTA_CAPA = ?      
				   WHERE ID =     ? `,
		book.TITULLO,
		book.AUTOR,
		book.ANO,
		book.QRCODE,
		book.STATUS,
		book.ID_CATEGORIA,
		book.ID_LOCALIZACAO,
		book.PASTA_CAPA,
		book.ID)

	if erro != nil {
		transacao.Rollback()
		return book, erro
	}

	linhas, erro := resultado.RowsAffected()

	if erro != nil {
		transacao.Rollback()
		return book, erro
	}

	if linhas > 0 {
		transacao.Commit()
		return book, nil
	}

	return book, nil
}

func (book Books) DeletarLivro() (Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, err = conexaogeral.Begin()

	if err != nil {
		transacao.Rollback()
		return book, err
	}

	_, erro := transacao.Exec(
		"DELETE FROM BOOKS WHERE ID = ?", book.ID)

	if erro != nil {
		transacao.Rollback()
		return book, erro
	}

	transacao.Commit()
	return book, nil
}

func (book Books) RetornaTodosLivros() ([]Books, error) {
	var conexaogeral = conexao.Conexao_DataBase()
	var transacao, err = conexaogeral.Begin()
	var book_ []Books

	if err != nil {
		return book_, err
	}

	var resultado, erro = transacao.Query(`
		SELECT ID,          
		       TITULO,      
		       AUTOR,       
		       ANO,         
		       QRCODE,      
	           STATUS, 
			   ID_CATEGORIA,
			   ID_LOCALIZACAO,
			   PASTA_CAPA      
	      FROM BOOKS `)

	if erro != nil {
		return book_, erro
	}

	for resultado.Next() {
		erro = resultado.Scan(
			&book.ID, &book.TITULLO, &book.AUTOR,
			&book.ANO, &book.QRCODE, &book.STATUS,
			&book.ID_CATEGORIA, &book.ID_LOCALIZACAO, &book.PASTA_CAPA)

		if erro != nil {
			return book_, erro
		}

		book_ = append(book_, book)
	}

	return book_, erro
}
