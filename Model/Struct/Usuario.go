package Model

import (
	"database/sql"
	conexao "meuapp/db"
)

type Usuario struct {
	ID_USUARIO    int    `json:"ID_USUARIO"`
	NOME_USUARIO  string `json:"NOME_USUARIO"`
	SENHA_USUARIO string `json:"SENHA_USUARIO"`
	EMAIL_USUARIO string `json:"EMAIL_USUARIO"`
}

func (usuario Usuario) EfectuarEntradaUsuario() ([]Usuario, error) {
	db := conexao.DB
	var usuario_ []Usuario

	var resultado, erro = db.Query(
		`SELECT NOME_USUARIO,
        	    SENHA_USUARIO,
                EMAIL_USUARIO
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
			&usuario.EMAIL_USUARIO)

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
	db := conexao.DB
	var transacao, err = db.Begin()

	if err != nil {
		transacao.Rollback()
		return usuario, err
	}

	var _, erro = transacao.Exec(`
	 	INSERT INTO USUARIO (NOME_USUARIO,
        	                 SENHA_USUARIO,
                 		     EMAIL_USUARIO)
                      VALUES(?,?,?)`,
		usuario.NOME_USUARIO,
		usuario.SENHA_USUARIO,
		usuario.EMAIL_USUARIO)

	if erro != nil {
		transacao.Rollback()
		return usuario, erro
	}

	transacao.Commit()
	return usuario, nil
}

func (usuario Usuario) EditarUsuario() (Usuario, error) {
	db := conexao.DB
	var transacao, err = db.Begin()

	if err != nil {
		transacao.Rollback()
		return usuario, err
	}

	var _, erro = transacao.Exec(`
	 	UPDATE USUARIO SET NOME_USUARIO = ?,
        	               SENHA_USUARIO = ?,
                 		   EMAIL_USUARIO = ?
					 WHERE ID_USUARIO = ?`,
		usuario.NOME_USUARIO,
		usuario.SENHA_USUARIO,
		usuario.EMAIL_USUARIO,
		usuario.ID_USUARIO)

	if erro != nil {
		transacao.Rollback()
		return usuario, erro
	}

	transacao.Commit()
	return usuario, nil
}

func (usuario Usuario) ExcluirUsuario() (Usuario, error) {
	db := conexao.DB
	var transacao, err = db.Begin()

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
	db := conexao.DB
	var usuario_ []Usuario

	var resultado, erro = db.Query(`
		SELECT ID_USUARIO,	
			   NOME_USUARIO,
			   SENHA_USUARIO,
			   EMAIL_USUARIO
		  FROM USUARIO`)

	if erro != nil {
		return nil, erro
	}

	for resultado.Next() {
		var erro = resultado.Scan(
			&usuario.ID_USUARIO,
			&usuario.NOME_USUARIO,
			&usuario.SENHA_USUARIO,
			&usuario.EMAIL_USUARIO)

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
