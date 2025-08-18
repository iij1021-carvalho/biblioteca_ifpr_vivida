package db

import (
	"database/sql"
	"fmt"
	"log"
	_ "net/http"
	"os"
	"time"

	_ "github.com/go-sql-driver/mysql"
	"github.com/joho/godotenv"
)

func RetornaDadosConexao() string {
	var err = godotenv.Load("arquivo.env")

	if err != nil {
		log.Fatal("Não foi possivel ler o arquivo de dados" + err.Error())
	}

	configuracao := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s",
		os.Getenv("DB_USER"),
		os.Getenv("DB_PASSWORD"),
		os.Getenv("DB_HOST"),
		os.Getenv("DB_PORT"),
		os.Getenv("DB_DATABASE"),
	)
	return configuracao
}

func Conexao_DataBase() *sql.DB {
	var db, erro = sql.Open("mysql", RetornaDadosConexao()+"?multiStatements=true")

	if erro != nil {
		log.Fatal("Falha ao conectar a base de dados" + erro.Error())
	}

	var err = db.Ping()

	if err != nil {
		log.Fatal("Falha ao pingar na base de dados")
	}

	db.SetMaxOpenConns(25)
	db.SetConnMaxLifetime(5 * time.Hour)
	db.SetMaxIdleConns(25)

	return db
}

func CriarTabelas() bool {
	var conexao = Conexao_DataBase()
	var transacao, err = conexao.Begin()

	if err != nil {
		return false
	}

	var _, erro = transacao.Exec(`USE IFPR_BIBLIOTECA`)

	if erro != nil {
		return false
	}

	var _, errr = transacao.Exec(`USE IFPR_BIBLIOTECA;

		CREATE TABLE BOOKS (
		ID int(10) unsigned NOT NULL AUTO_INCREMENT,
		TITULO varchar(50) DEFAULT NULL,
		AUTOR varchar(50) DEFAULT NULL,
		ANO varchar(50) DEFAULT NULL,
		QRCODE varchar(500) DEFAULT NULL,
		STATUS char(1) DEFAULT NULL,
		ID_CATEGORIA int(11) DEFAULT NULL,
		ID_LOCALIZACAO int(11) DEFAULT NULL,
		PASTA_CAPA varchar(500) DEFAULT NULL,
		PRIMARY KEY (ID)
		);

		CREATE TABLE CATEGORIA_LIVRO (
		ID_CATEGORIA_LIVRO int(11) NOT NULL AUTO_INCREMENT,
		DESCRICAO_CATEGORIA varchar(200) NOT NULL,
		ATIVO_CATEGORIA tinyint(1) DEFAULT 0,
		PRIMARY KEY (ID_CATEGORIA_LIVRO)
		);

		CREATE TABLE EMPRESTADO (
		ID_EMPRESTADO int(11) NOT NULL AUTO_INCREMENT,
		ID_LIVRO int(11) DEFAULT 0,
		ID_USUARIO int(11) DEFAULT 0,
		DATA_EMPRESTADO date NOT NULL,
		DATA_DEVOLUCAO_EMPRESTADO date NOT NULL,
		STATUS_EMPRESTIMO enum('EMPRESTADO','DEVOLVIDO') DEFAULT 'EMPRESTADO',
		PRIMARY KEY (ID_EMPRESTADO)
		);

		CREATE TABLE LOCALIZACAO_LIVRO (
		ID_LOCALIZACAO int(11) NOT NULL AUTO_INCREMENT,
		DESCRICAO_LOCALIZACAO varchar(200) NOT NULL,
		FOTO_LOCALIZACAO varchar(500) NOT NULL,
		PRIMARY KEY (ID_LOCALIZACAO)
		);

		CREATE TABLE USUARIO (
		ID_USUARIO int(11) NOT NULL AUTO_INCREMENT,
		NOME_USUARIO varchar(100) NOT NULL,
		SENHA_USUARIO varchar(100) NOT NULL,
		EMAIL_USUARIO varchar(100) NOT NULL,
		DATANASCIMENTO_USUARIO date NOT NULL,
		IDADE_USUARIO int(11) NOT NULL,
		ENDERECO_USUARIO varchar(150) DEFAULT NULL,
		PRIMARY KEY (ID_USUARIO)
		);`)

	if errr != nil {
		return false
	}
	return true
}
