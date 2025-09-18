package main

import (
	"log"
	servicos "meuapp/Services"
	conexao "meuapp/db"
	"net"

	_ "github.com/go-sql-driver/mysql"
	"github.com/gofiber/fiber/v2"
)

func GetIpMaquina() net.IP {
	con, erro := net.Dial("tcp", "www.google.com:80")

	if erro != nil {
		log.Fatal("Erro encontrado $erro")
	}

	defer con.Close()

	endereco := con.LocalAddr().(*net.TCPAddr)
	return endereco.IP
}

func main() {
	conexao.Conexao_DataBase()
	defer conexao.DB.Close()

	api := fiber.New()
	api.Post("/registrarlivro", servicos.GravarLivro)
	api.Put("/editarlivro", servicos.EditarLivro)
	api.Post("/deletarlivro", servicos.ExcluirLivro)
	api.Post("/retornalivrospaginacao", servicos.RetornaLivrosPaginacao)
	api.Post("/buscarlivro", servicos.BuscaLivroCodigoBarra)
	api.Post("/buscalivrotitulo", servicos.BuscaLivroTitulo)

	api.Post("/registrarusuario", servicos.RegistrarUsuario)
	api.Put("/editarusuario", servicos.EditarUsuario)
	api.Post("/deletarusuario", servicos.ExcluirUsuario)
	api.Post("/efectuarentradausuario", servicos.EfectuarEntradaUsuario)
	api.Get("/retornadadosusuario", servicos.RetornaTodosUsuarios)

	var ip = GetIpMaquina()
	var erro = api.Listen(ip.String() + ":3000")

	if erro != nil {
		log.Fatal("Falha ao se conectar a Api" + erro.Error())
	}
}
