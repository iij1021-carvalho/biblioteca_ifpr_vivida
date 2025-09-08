package Services

import (
	livro "meuapp/Model/Struct"

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

	if resultado.IDBOOK > 0 {
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

	if resultado.IDBOOK > 0 {
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

func RetornaListaLivros(c *fiber.Ctx) error {
	var book livro.Books_Paginacao
	var falha = c.BodyParser(&book)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados",
			"details": falha.Error(),
		})
	}

	var resultado, erro = book.RetornaTodosLivros()

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
