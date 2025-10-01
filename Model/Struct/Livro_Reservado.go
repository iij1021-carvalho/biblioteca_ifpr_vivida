package Model

import (
	conexao "meuapp/db"
)

type Livro_Reservado struct {
	IDRESERVA      int    `json:"IDRESERVA"`
	IDLIVRO        int    `json:"IDLIVRO"`
	IDUSUARIO      int    `json:"IDUSUARIO"`
	DATA_RESERVADO string `json:"DATA_RESERVADO"`
	DATA_DEVOLUCAO string `json:"DATA_DEVOLUCAO"`
	AUTOR          string `json:"AUTOR"`
	TITULO         string `json:"TITULO"`
	ISBN           string `json:"ISBN"`
	CODIGO_BARRA   string `json:"CODIGO_BARRA"`
}

func (reservado Livro_Reservado) ListaLivroReservado() ([]Livro_Reservado, error) {
	var livro_reservado_ []Livro_Reservado
	db := conexao.DB

	var resultado, erro = db.Query(`
		 SELECT IDRESERVA,
				IDUSUARIO,
				IDLIVRO,
				AUTOR,
				TITULO,
				ISBN,
				CODIGO_BARRA,
				DATA_RESERVADO,
				DATA_DEVOLUCAO
		   FROM LIVRO_RESERVADO
		   LEFT JOIN BOOK 
				  ON BOOK.ID_BOOK = LIVRO_RESERVADO.IDLIVRO
		  WHERE IDUSUARIO = ?`, reservado.IDUSUARIO)

	if erro != nil {
		return livro_reservado_, erro
	}

	for resultado.Next() {
		var erro = resultado.Scan(
			&reservado.IDRESERVA,
			&reservado.IDUSUARIO,
			&reservado.IDLIVRO,
			&reservado.AUTOR,
			&reservado.TITULO,
			&reservado.ISBN,
			&reservado.CODIGO_BARRA,
			&reservado.DATA_RESERVADO,
			&reservado.DATA_DEVOLUCAO)

		if erro != nil {
			return livro_reservado_, erro
		}

		livro_reservado_ = append(livro_reservado_, reservado)
	}
	return livro_reservado_, nil
}

func (reservado Livro_Reservado) RegistrarReserva() error {
	db := conexao.DB
	var transacao, erro = db.Begin()

	if erro != nil {
		transacao.Rollback()
		return erro
	}

	var _, err = transacao.Exec(`
		INSERT INTO LIVRO_RESERVADO(IDLIVRO,IDUSUARIO,DATA_RESERVADO,DATA_DEVOLUCAO) 
					VALUES(?,?,?,?)`,
		reservado.IDLIVRO,
		reservado.IDUSUARIO,
		reservado.DATA_RESERVADO,
		reservado.DATA_DEVOLUCAO)

	if err != nil {
		transacao.Rollback()
		return err
	}

	transacao.Commit()
	return nil
}

func (reservado Livro_Reservado) EditarReserva() error {
	db := conexao.DB
	var transacao, erro = db.Begin()

	if erro != nil {
		transacao.Rollback()
		return erro
	}

	var _, err = transacao.Exec(`
		UPDATE LIVRO_RESERVADO 
		   SET IDLIVRO = ?,
		       IDUSUARIO = ?,
			   DATA_RESERVADO = ?,
			   DATA_DEVOLUCAO = ?
		 WHERE IDRESERVA = ?`,
		reservado.IDLIVRO,
		reservado.IDUSUARIO,
		reservado.DATA_RESERVADO,
		reservado.DATA_DEVOLUCAO,
		reservado.IDRESERVA)

	if err != nil {
		transacao.Rollback()
		return err
	}

	transacao.Commit()
	return nil
}
