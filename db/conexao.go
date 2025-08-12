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
	var db, erro = sql.Open("mysql", RetornaDadosConexao())

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
