package Model

import (
	conexao "meuapp/db"
)

type Localizacao struct {
	ID_LOCALIZACAO        int    `json:"ID_LOCALIZACAO"`
	DESCRICAO_LOCALIZACAO string `json:"DESCRICAO_LOCALIZACAO"`
	FOTO_LOCALIZACAO      string `json:"FOTO_LOCALIZACAO"`
}

func (localizacao Localizacao) RegistrarLocalizacao() (Localizacao, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return localizacao, err
	}

	var _, erro = transacao.Exec(`
		INSERT INTO LOCALIZACAO (DESCRICAO_LOCALIZACAO,FOTO_LOCALIZACAO)
		VALUES(?,?)`, localizacao.DESCRICAO_LOCALIZACAO,
		localizacao.FOTO_LOCALIZACAO)

	if erro != nil {
		transacao.Rollback()
		return localizacao, erro
	}

	transacao.Commit()
	return localizacao, nil
}

func (localizacao Localizacao) EditarLocalizacao() (Localizacao, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return localizacao, err
	}

	var _, erro = transacao.Exec(`
		UPDATE LOCALIZACAO 
		   SET DESCRICAO_LOCALIZACAO = ?,
			   FOTO_LOCALIZACAO =      ?
		 WHERE ID_LOCALIZACAO = ?	   `,
		localizacao.DESCRICAO_LOCALIZACAO,
		localizacao.FOTO_LOCALIZACAO,
		localizacao.ID_LOCALIZACAO)

	if erro != nil {
		transacao.Rollback()
		return localizacao, erro
	}

	transacao.Commit()
	return localizacao, nil
}

func (localizacao Localizacao) ExcluirLocalizacao() (Localizacao, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return localizacao, err
	}

	var _, erro = transacao.Exec(`
		DELETE FROM LOCALIZACAO
		 WHERE ID_LOCALIZACAO = ? 
	`, localizacao.ID_LOCALIZACAO)

	if erro != nil {
		transacao.Rollback()
		return localizacao, erro
	}

	transacao.Commit()
	return localizacao, nil
}

func (localizacao Localizacao) RetornaTodasLocalizacoesLivros() ([]Localizacao, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var localizacao_ []Localizacao
	var resultado, erro = conexao_geral.Query(
		`
			SELECT ID_LOCALIZACAO,
				   DESCRICAO_LOCALIZACAO,
				   FOTO_LOCALIZACAO
			 FROM LOCALIZACAO	   		
		`)

	if erro != nil {
		return nil, erro
	}

	for resultado.Next() {
		var errr = resultado.Scan(&localizacao.ID_LOCALIZACAO,
			&localizacao.DESCRICAO_LOCALIZACAO,
			&localizacao.FOTO_LOCALIZACAO)

		if errr != nil {
			return nil, errr
		}
		localizacao_ = append(localizacao_, localizacao)
	}

	return localizacao_, nil
}
