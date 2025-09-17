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

var DB *sql.DB

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

func Conexao_DataBase() {
	var err error
	DB, err = sql.Open("mysql", RetornaDadosConexao())
	if err != nil {
		log.Fatal("Falha ao conectar ao banco de dados: ", err)
	}

	// Configura pool
	DB.SetMaxOpenConns(20)
	DB.SetConnMaxLifetime(30 * time.Hour)
	DB.SetMaxIdleConns(25)

	// Testa conexão
	if err = DB.Ping(); err != nil {
		log.Fatal("Falha ao pingar o banco de dados: ", err)
	}

	log.Println("Conexão com banco inicializada com sucesso")
}
