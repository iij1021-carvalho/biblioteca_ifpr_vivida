package Model

import (
	"errors"
	"log"
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
	INICIAL  int    `json:"INICIAL"`
	FINAL    int    `json:"FINAL"`
	PESQUISA string `json:"PESQUISA"`
}

func (book Books) RegistrarLivro() (Books, error) {
	db := conexao.DB
	var transacao, erro = db.Begin()

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
	db := conexao.DB
	var transacao, errro = db.Begin()

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
	db := conexao.DB
	var transacao, err = db.Begin()

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
	db := conexao.DB
	var book_ []Books
	var resultado, erro = db.Query(`
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

func (book Books) BuscaLivroTitulo() ([]Books, error) {
	db := conexao.DB
	var book_ []Books

	var pesquisa = book.TITULO + "%"
	var resultado, erro = db.Query(`
				SELECT IDBOOK,
					   CODIGO_BARRA,
					   AUTOR,
			           TITULO,
					   ISBN,
			           IDCATEGORIA,
					   QUANTIDADE
				  FROM BOOK
				 WHERE TITULO LIKE ?`, pesquisa)

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
	var book_ []Books
	var books Books

	db := conexao.DB

	if db == nil {
		return nil, errors.New("conexão com o banco não inicializada")
	}

	var resultado, erro = db.Query(
		`WITH livros_unicos AS (
    			SELECT *,
					   ROW_NUMBER() OVER (PARTITION BY ISBN ORDER BY IDBOOK ASC) AS rn
				  FROM BOOK
				 WHERE IDBOOK > ?
			)
			SELECT ISBN,
			       IDBOOK,
				   CODIGO_BARRA,
				   AUTOR,
				   TITULO,
				   QUANTIDADE
			  FROM livros_unicos
			 WHERE rn = 1
			ORDER BY IDBOOK
			LIMIT ?`, book.INICIAL, book.FINAL)

	if erro != nil {
		return book_, erro
	}

	defer resultado.Close()

	for resultado.Next() {
		erro = resultado.Scan(&books.ISBN, &books.IDBOOK, &books.CODIGO_BARRA, &books.AUTOR, &books.TITULO, &books.QUANTIDADE)
		if erro != nil {
			return book_, erro
		}

		book_ = append(book_, books)
	}

	log.Println(book.INICIAL)
	return book_, erro
}
