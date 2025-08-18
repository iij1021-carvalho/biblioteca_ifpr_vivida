package main

import (
	"log"
	servicos "meuapp/Services"
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
	api := fiber.New()

	api.Get("/", servicos.CriarTabelas)
	api.Post("/registrarlivro", servicos.GravarLivro)
	api.Put("/editarlivro", servicos.EditarLivro)
	api.Post("/deletarlivro", servicos.ExcluirLivro)
	api.Get("/retornalivros", servicos.RetornaListaLivros)

	api.Post("/registrarusuario", servicos.RegistrarUsuario)
	api.Put("/editarusuario", servicos.EditarUsuario)
	api.Post("/deletarusuario", servicos.ExcluirUsuario)
	api.Post("/efectuarentradausuario", servicos.EfectuarEntradaUsuario)
	api.Get("/retornadadosusuario", servicos.RetornaTodosUsuarios)

	api.Post("/registrarlivroemprestado", servicos.RegistrarLivroEmprestado)
	api.Put("/editarlivroemprestado", servicos.EditarLivroEmprestado)
	api.Post("/deletarlivroemprestado", servicos.DevolverLivroEmprestado)

	api.Post("/registrarcategoria", servicos.RegistrarCategoria)
	api.Put("/editarcategoria", servicos.EditarCategoria)
	api.Post("/deletarcategoria", servicos.ExcluirCategoria)
	api.Get("/retornadadoscategoria", servicos.RetornaDadosCategoria)

	api.Post("/registrarlocalizacao", servicos.RegistrarLocalizacao)
	api.Put("/editarlocalizacao", servicos.EditarLocalizacao)
	api.Post("/deletarlocalizacao", servicos.ExcluirLocalizacao)
	api.Get("/retornadadoslocalizacao", servicos.RetornaDadosLocalizacao)

	var ip = GetIpMaquina()
	var erro = api.Listen(ip.String() + ":3000")

	if erro != nil {
		log.Fatal("Falha ao se conectar a Api" + erro.Error())
	}
}
