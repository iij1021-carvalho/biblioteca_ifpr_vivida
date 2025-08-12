package Model

import (
	"database/sql"
	conexao "meuapp/db"
)

type Usuario struct {
	ID_USUARIO             int    `json:"ID_USUARIO"`
	NOME_USUARIO           string `json:"NOME_USUARIO"`
	SENHA_USUARIO          string `json:"SENHA_USUARIO"`
	EMAIL_USUARIO          string `json:"EMAIL_USUARIO"`
	DATANASCIMENTO_USUARIO string `json:"DATANASCIMENTO_USUARIO"`
	IDADE_USUARIO          int    `json:"IDADE_USUARIO"`
	ENDERECO_USUARIO       string `json:"ENDERECO_USUARIO"`
}

func (usuario Usuario) EfectuarEntradaUsuario() ([]Usuario, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var usuario_ []Usuario

	var resultado, erro = conexao_geral.Query(
		`SELECT NOME_USUARIO,
        	    SENHA_USUARIO,
                EMAIL_USUARIO,
                DATANASCIMENTO_USUARIO,
                IDADE_USUARIO,
                ENDERECO_USUARIO
		   FROM USUARIO
		  WHERE NOME_USUARIO = ?
		    AND SENHA_USUARIO = ?`,
		usuario.NOME_USUARIO,
		usuario.SENHA_USUARIO)

	if erro != nil {
		return nil, erro
	}

	for resultado.Next() {
		erro = resultado.Scan(
			&usuario.NOME_USUARIO,
			&usuario.SENHA_USUARIO,
			&usuario.EMAIL_USUARIO,
			&usuario.DATANASCIMENTO_USUARIO,
			&usuario.IDADE_USUARIO,
			&usuario.ENDERECO_USUARIO)

		if erro != nil {
			return nil, erro
		}

		usuario_ = append(usuario_, usuario)
	}

	if len(usuario_) <= 0 {
		return usuario_, sql.ErrNoRows
	}

	return usuario_, nil
}

func (usuario Usuario) RegistrarUsuario() (Usuario, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return usuario, err
	}

	var _, erro = transacao.Exec(`
	 	INSERT INTO USUARIO (NOME_USUARIO,
        	                 SENHA_USUARIO,
                 		     EMAIL_USUARIO,
                    		 DATANASCIMENTO_USUARIO,
                    		 IDADE_USUARIO,
                    		 ENDERECO_USUARIO)
                      VALUES(?,?,?,?,?,?)`,
		usuario.NOME_USUARIO,
		usuario.SENHA_USUARIO,
		usuario.EMAIL_USUARIO,
		usuario.DATANASCIMENTO_USUARIO,
		usuario.IDADE_USUARIO,
		usuario.ENDERECO_USUARIO)

	if erro != nil {
		transacao.Rollback()
		return usuario, erro
	}

	transacao.Commit()
	return usuario, nil
}

func (usuario Usuario) EditarUsuario() (Usuario, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return usuario, err
	}

	var _, erro = transacao.Exec(`
	 	UPDATE USUARIO SET NOME_USUARIO = ?,
        	               SENHA_USUARIO = ?,
                 		   EMAIL_USUARIO = ?,
                    	   DATANASCIMENTO_USUARIO = ?,
                    	   IDADE_USUARIO = ?,
                    	   ENDERECO_USUARIO = ?
					 WHERE ID_USUARIO = ?`,
		usuario.NOME_USUARIO,
		usuario.SENHA_USUARIO,
		usuario.EMAIL_USUARIO,
		usuario.DATANASCIMENTO_USUARIO,
		usuario.IDADE_USUARIO,
		usuario.ENDERECO_USUARIO,
		usuario.ID_USUARIO)

	if erro != nil {
		transacao.Rollback()
		return usuario, erro
	}

	transacao.Commit()
	return usuario, nil
}

func (usuario Usuario) ExcluirUsuario() (Usuario, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return usuario, err
	}

	var _, erro = transacao.Exec(`
		DELETE FROM USUARIO 
		 WHERE ID_USUARIO = ?`,
		usuario.ID_USUARIO)

	if erro != nil {
		transacao.Rollback()
		return usuario, erro
	}

	transacao.Commit()
	return usuario, err
}

func (usuario Usuario) RetornaTodosUsuarios() ([]Usuario, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var usuario_ []Usuario

	var resultado, erro = conexao_geral.Query(`
		SELECT ID_USUARIO,	
			   NOME_USUARIO,
			   SENHA_USUARIO,
			   EMAIL_USUARIO,
			   DATANASCIMENTO_USUARIO,
			   IDADE_USUARIO,
			   ENDERECO_USUARIO 
		  FROM USUARIO`)

	if erro != nil {
		return nil, erro
	}

	for resultado.Next() {
		var erro = resultado.Scan(
			&usuario.ID_USUARIO,
			&usuario.NOME_USUARIO,
			&usuario.SENHA_USUARIO,
			&usuario.EMAIL_USUARIO,
			&usuario.DATANASCIMENTO_USUARIO,
			&usuario.IDADE_USUARIO,
			&usuario.ENDERECO_USUARIO)

		if erro != nil {
			return nil, erro
		}

		usuario_ = append(usuario_, usuario)
	}

	if len(usuario_) <= 0 {
		return nil, sql.ErrNoRows
	}

	return usuario_, nil
}
