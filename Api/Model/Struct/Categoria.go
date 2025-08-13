package Model

import (
	conexao "meuapp/db"
)

type Categoria struct {
	ID_CATEGORIA_LIVRO  int    `json:"ID_CATEGORIA_LIVRO"`
	DESCRICAO_CATEGORIA string `json:"DESCRICAO_CATEGORIA"`
	ATIVO_CATEGORIA     bool   `json:"ATIVO_CATEGORIA"`
}

func (categoria Categoria) RegistrarCategoria() (Categoria, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return categoria, err
	}

	var _, erro = transacao.Exec(
		`
		INSERT INTO CATEGORIA_LIVRO (DESCRICAO_CATEGORIA,ATIVO_CATEGORIA)
							 VALUES	(?,?)`,
		categoria.DESCRICAO_CATEGORIA,
		categoria.ATIVO_CATEGORIA)

	if erro != nil {
		transacao.Rollback()
		return categoria, erro
	}

	transacao.Commit()
	return categoria, nil
}

func (categoria Categoria) EditarCategoria() (Categoria, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return categoria, err
	}

	var _, erro = transacao.Exec(
		`UPDATE CATEGORIA_LIVRO 
		   SET DESCRICAO_CATEGORIA = ?,
		       ATIVO_CATEGORIA = ?
		 WHERE ID_CATEGORIA_LIVRO = ?`,
		categoria.DESCRICAO_CATEGORIA,
		categoria.ATIVO_CATEGORIA,
		categoria.ID_CATEGORIA_LIVRO)

	if erro != nil {
		transacao.Rollback()
		return categoria, erro
	}

	transacao.Commit()
	return categoria, nil
}

func (categoria Categoria) ExcluirCategoria() (Categoria, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var transacao, err = conexao_geral.Begin()

	if err != nil {
		transacao.Rollback()
		return categoria, err
	}

	var _, erro = transacao.Exec(
		`DELETE FROM CATEGORIA_LIVRO 
		  WHERE ID_CATEGORIA_LIVRO = ?`,
		categoria.ID_CATEGORIA_LIVRO)

	if erro != nil {
		transacao.Rollback()
		return categoria, erro
	}

	transacao.Commit()
	return categoria, nil
}

func (categoria Categoria) RetornaDadosCategoria() ([]Categoria, error) {
	var conexao_geral = conexao.Conexao_DataBase()
	var categoria_ []Categoria
	var resultado, erro = conexao_geral.Query(
		`SELECT ID_CATEGORIA_LIVRO,
				DESCRICAO_CATEGORIA,
				ATIVO_CATEGORIA
			FROM CATEGORIA_LIVRO
		`)

	if erro != nil {
		return nil, erro
	}

	for resultado.Next() {
		var errr = resultado.Scan(&categoria.ID_CATEGORIA_LIVRO,
			&categoria.DESCRICAO_CATEGORIA,
			&categoria.ATIVO_CATEGORIA)

		if errr != nil {
			return nil, errr
		}
		categoria_ = append(categoria_, categoria)
	}

	return categoria_, nil
}
