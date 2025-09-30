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
