package Services

import (
	"encoding/json"
	"fmt"
	livro "meuapp/Model/Struct"
	"net/http"

	"io"

	"github.com/gofiber/fiber/v2"
)

func GravarLivro(c *fiber.Ctx) error {
	var book livro.Books
	var falha = c.BodyParser(&book)

	if falha != nil {
		return c.Status(400).JSONP(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados",
			"details": falha.Error(),
		})
	}

	var resultado, erro = book.RegistrarLivro()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados",
			"details": erro.Error(),
		})
	}

	if resultado.ID_BOOK > 0 {
		return c.Status(200).JSON(fiber.Map{
			"status":  "sucesso",
			"message": "dados obtidos com sucesso",
			"data":    resultado,
		})
	}
	return nil
}

func EditarLivro(c *fiber.Ctx) error {
	var book livro.Books
	var falha = c.BodyParser(&book)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar dados",
			"details": falha.Error(),
		})
	}

	var resultado, erro = book.EditarLivro()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar livro",
			"details": erro.Error(),
		})
	}

	if resultado.ID_BOOK > 0 {
		return c.Status(200).JSON(fiber.Map{
			"status":  "sucesso",
			"message": "dados editados com sucesso",
			"data":    resultado,
		})
	}
	return nil
}

func ExcluirLivro(c *fiber.Ctx) error {
	var book livro.Books
	var falha = c.BodyParser(&book)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados",
			"details": falha.Error(),
		})
	}

	var _, erro = book.DeletarLivro()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao deletar dados",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados deletado com sucesso",
		"data":    book,
	})
}

func BuscaLivroCodigoBarra(c *fiber.Ctx) error {
	var book livro.Books
	var falha = c.BodyParser(&book)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao deletar dados",
			"details": falha.Error(),
		})
	}

	var res, erro = book.BuscaLivroCodigo()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao deletar dados",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados obtidos com sucesso",
		"data":    res,
	})
}

func GoogleLivroApi_Isbn(c *fiber.Ctx) error {
	var api livro.GoogleApi
	var book livro.Books
	var falha = c.BodyParser(&book)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao receber dados",
			"details": falha.Error(),
		})
	}

	var apikey = "AIzaSyD5BTeDQqB-7MJQGdOnZ7BLcZCg-rGJfGQ"

	var url = fmt.Sprintf("https://www.googleapis.com/books/v1/volumes?q=isbn:%s&key=%s", book.ISBN, apikey)

	var resposta, erro = http.Get(url)

	var dados, errr = io.ReadAll(resposta.Body)

	if errr != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao receber dados",
			"details": erro.Error(),
		})
	}

	var ert = json.Unmarshal(dados, &api)

	if ert != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao receber dados",
			"details": ert.Error(),
		})
	}

	if len(api.Items) > 0 {
		return c.Status(200).JSON(fiber.Map{
			"status":  "sucess",
			"message": "dados obtidos com sucesso",
			"data":    api,
		})
	} else {
		return c.Status(200).JSON(fiber.Map{
			"status":  "sucess",
			"message": "dados obtidos com sucesso",
			"data":    api,
		})
	}
}

func BuscaLivroTitulo(c *fiber.Ctx) error {
	var book livro.Books
	var falha = c.BodyParser(&book)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao deletar dados",
			"details": falha.Error(),
		})
	}

	var res, erro = book.BuscaLivroTitulo()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao deletar dados",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados obtidos com sucesso",
		"data":    res,
	})
}

func RetornaLivrosPaginacao(c *fiber.Ctx) error {
	var book livro.Books_Paginacao
	var falha = c.BodyParser(&book)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados",
			"details": falha.Error(),
		})
	}

	var resultado, erro = book.RetornaLivrosPaginacao()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados obtidos com sucesso",
		"data":    resultado,
	})

}
