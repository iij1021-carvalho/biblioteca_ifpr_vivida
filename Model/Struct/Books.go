package Model

import (
	"errors"
	"log"
	conexao "meuapp/db"
)

type Books struct {
	ID_BOOK      int    `json:"ID_BOOK"`
	CODIGO_BARRA int    `json:"CODIGO_BARRA"`
	AUTOR        string `json:"AUTOR"`
	TITULO       string `json:"TITULO"`
	ISBN         string `json:"ISBN"`
}

type GoogleApi struct {
	Kind       string `json:"kind"`
	TotalItems int    `json:"totalItems"`
	Items      []struct {
		Kind       string `json:"kind"`
		ID         string `json:"id"`
		Etag       string `json:"etag"`
		SelfLink   string `json:"selfLink"`
		VolumeInfo struct {
			Title               string   `json:"title"`
			Authors             []string `json:"authors"`
			PublishedDate       string   `json:"publishedDate"`
			Description         string   `json:"description"`
			IndustryIdentifiers []struct {
				Type       string `json:"type"`
				Identifier string `json:"identifier"`
			} `json:"industryIdentifiers"`
			ReadingModes struct {
				Text  bool `json:"text"`
				Image bool `json:"image"`
			} `json:"readingModes"`
			PageCount           int    `json:"pageCount"`
			PrintType           string `json:"printType"`
			MaturityRating      string `json:"maturityRating"`
			AllowAnonLogging    bool   `json:"allowAnonLogging"`
			ContentVersion      string `json:"contentVersion"`
			PanelizationSummary struct {
				ContainsEpubBubbles  bool `json:"containsEpubBubbles"`
				ContainsImageBubbles bool `json:"containsImageBubbles"`
			} `json:"panelizationSummary"`
			Language            string `json:"language"`
			PreviewLink         string `json:"previewLink"`
			InfoLink            string `json:"infoLink"`
			CanonicalVolumeLink string `json:"canonicalVolumeLink"`
		} `json:"volumeInfo"`
		SaleInfo struct {
			Country     string `json:"country"`
			Saleability string `json:"saleability"`
			IsEbook     bool   `json:"isEbook"`
		} `json:"saleInfo"`
		AccessInfo struct {
			Country                string `json:"country"`
			Viewability            string `json:"viewability"`
			Embeddable             bool   `json:"embeddable"`
			PublicDomain           bool   `json:"publicDomain"`
			TextToSpeechPermission string `json:"textToSpeechPermission"`
			Epub                   struct {
				IsAvailable bool `json:"isAvailable"`
			} `json:"epub"`
			Pdf struct {
				IsAvailable bool `json:"isAvailable"`
			} `json:"pdf"`
			WebReaderLink       string `json:"webReaderLink"`
			AccessViewStatus    string `json:"accessViewStatus"`
			QuoteSharingAllowed bool   `json:"quoteSharingAllowed"`
		} `json:"accessInfo"`
		SearchInfo struct {
			TextSnippet string `json:"textSnippet"`
		} `json:"searchInfo"`
	} `json:"items"`
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
		`INSERT INTO BOOK (CODIGO_BARRA,AUTOR,TITULO,ISBN)
					 VALUES(?,?,?,?)`,
		book.CODIGO_BARRA,
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
		book.ID_BOOK = int(row)
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
			    ISBN = ?
		  WHERE ID_BOOK = ?`,
		book.CODIGO_BARRA,
		book.AUTOR,
		book.TITULO,
		book.ISBN,
		book.ID_BOOK)

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

	_, erro := transacao.Exec(`DELETE FROM BOOK WHERE ID_BOOK = ?`, book.ID_BOOK)

	if erro != nil {
		return book, erro
	}
	return book, nil
}

func (book Books) BuscaLivroCodigo() ([]Books, error) {
	db := conexao.DB
	var book_ []Books
	var resultado, erro = db.Query(`
				SELECT ID_BOOK,
					   CODIGO_BARRA,
					   AUTOR,
			           TITULO,
					   ISBN
				  FROM BOOK
				 WHERE CODIGO_BARRA = ?`, book.CODIGO_BARRA)

	if erro != nil {
		return book_, erro
	}

	for resultado.Next() {
		var errr = resultado.Scan(
			&book.ID_BOOK,
			&book.CODIGO_BARRA,
			&book.AUTOR,
			&book.TITULO,
			&book.ISBN)

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
				SELECT ID_BOOK,
					   CODIGO_BARRA,
					   AUTOR,
			           TITULO,
					   ISBN
				  FROM BOOK
				 WHERE TITULO LIKE ?`, pesquisa)

	if erro != nil {
		return book_, erro
	}

	for resultado.Next() {
		var errr = resultado.Scan(&book.ID_BOOK, &book.CODIGO_BARRA, &book.AUTOR, &book.TITULO, &book.ISBN)

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
					   ROW_NUMBER() OVER (PARTITION BY ISBN ORDER BY ID_BOOK ASC) AS rn
				  FROM BOOK
				 WHERE ID_BOOK > ?
			)
			SELECT ISBN,
			       ID_BOOK,
				   CODIGO_BARRA,
				   AUTOR,
				   TITULO
			  FROM livros_unicos
			 WHERE rn = 1
			ORDER BY ID_BOOK
			LIMIT ?`, book.INICIAL, book.FINAL)

	if erro != nil {
		return book_, erro
	}

	defer resultado.Close()

	for resultado.Next() {
		erro = resultado.Scan(&books.ISBN, &books.ID_BOOK, &books.CODIGO_BARRA, &books.AUTOR, &books.TITULO)
		if erro != nil {
			return book_, erro
		}

		book_ = append(book_, books)
	}

	log.Println(book.INICIAL)
	return book_, erro
}
