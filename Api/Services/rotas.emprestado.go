package Services

import (
	emprestado "meuapp/Model/Struct"

	"github.com/gofiber/fiber/v2"
)

func RegistrarLivroEmprestado(c *fiber.Ctx) error {
	var emprestado emprestado.Emprestado

	var err = c.BodyParser(&emprestado)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar livro emprestado",
			"details": err.Error(),
		})
	}

	var _, erro = emprestado.RegistrarLivroEmprestado()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar livro emprestado",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "registrado livro emprestado com sucesso",
		"data":    emprestado,
	})
}

func EditarLivroEmprestado(c *fiber.Ctx) error {
	var emprestado emprestado.Emprestado

	var err = c.BodyParser(&emprestado)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar livro emprestado",
			"details": err.Error(),
		})
	}

	var _, erro = emprestado.EditarLivroEmprestado()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar livro emprestado",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "editar livro emprestado com sucesso",
		"data":    emprestado,
	})
}

func ExcluirLivroEmprestado(c *fiber.Ctx) error {
	var emprestado emprestado.Emprestado

	var err = c.BodyParser(&emprestado)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao deletar livro emprestado",
			"details": err.Error(),
		})
	}

	var _, erro = emprestado.ExcluirLivroEmprestado()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao deletar livro emprestado",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "deletado livro emprestado com sucesso",
		"data":    emprestado,
	})
}
