package Model

import (
	conexao "meuapp/db"
)

type Emprestado struct {
	ID_EMPRESTADO             int    `json:"ID_EMPRESTADO"`
	ID_LIVRO                  int    `json:"ID_LIVRO"`
	ID_USUARIO                int    `json:"ID_USUARIO"`
	DATA_EMPRESTADO           string `json:"DATA_EMPRESTADO"`
	DATA_DEVOLUCAO_EMPRESTADO string `json:"DATA_DEVOLUCAO_EMPRESTADO"`
	STATUS_EMPRESTIMO         string `json:"STATUS_EMPRESTIMO"`
}

func (emprestado Emprestado) RegistrarLivroEmprestado() (Emprestado, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return emprestado, err
	}

	var _, erro = transacao.Exec(
		`INSERT INTO EMPRESTADO (ID_LIVRO,
							     ID_USUARIO,
								 DATA_EMPRESTADO,
								 DATA_DEVOLUCAO_EMPRESTADO,
								 STATUS_EMPRESTIMO)
					      VALUES(?,?,?,?,?)`,
		emprestado.ID_LIVRO,
		emprestado.ID_USUARIO,
		emprestado.DATA_EMPRESTADO,
		emprestado.DATA_DEVOLUCAO_EMPRESTADO,
		emprestado.STATUS_EMPRESTIMO)

	if erro != nil {
		transacao.Rollback()
		return emprestado, erro
	}

	transacao.Commit()
	return emprestado, nil
}

func (emprestado Emprestado) EditarLivroEmprestado() (Emprestado, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return emprestado, err
	}

	var _, erro = transacao.Exec(
		`UPDATE EMPRESTADO SET 
		        ID_LIVRO = ?,
			    ID_USUARIO = ?,
			    DATA_EMPRESTADO = ?,
			    DATA_DEVOLUCAO_EMPRESTADO = ?,
			    STATUS_EMPRESTIMO = ?
		  WHERE ID_EMPRESTADO = ?   `,
		emprestado.ID_LIVRO,
		emprestado.ID_USUARIO,
		emprestado.DATA_EMPRESTADO,
		emprestado.DATA_DEVOLUCAO_EMPRESTADO,
		emprestado.STATUS_EMPRESTIMO,
		emprestado.ID_EMPRESTADO)

	if erro != nil {
		transacao.Rollback()
		return emprestado, erro
	}

	transacao.Commit()
	return emprestado, nil
}

func (emprestado Emprestado) DevolverLivroEmprestado() (Emprestado, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return emprestado, err
	}

	var _, erro = transacao.Exec(
		`UPDATE EMPRESTADO 
			SET ID_LIVRO = ?,
				ID_USUARIO = ?,
				DATA_EMPRESTADO = ?,
				DATA_DEVOLUCAO_EMPRESTADO = ?,
				STATUS_EMPRESTIMO = ?
		  WHERE	ID_EMPRESTADO = ?`,
		emprestado.ID_LIVRO,
		emprestado.ID_USUARIO,
		emprestado.DATA_EMPRESTADO,
		emprestado.DATA_DEVOLUCAO_EMPRESTADO,
		emprestado.STATUS_EMPRESTIMO,
		emprestado.ID_EMPRESTADO)

	if erro != nil {
		transacao.Rollback()
		return emprestado, erro
	}

	transacao.Commit()
	return emprestado, nil
}
