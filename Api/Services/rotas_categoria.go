package Services

import (
	categoria "meuapp/Model/Struct"

	"github.com/gofiber/fiber/v2"
)

func RegistrarCategoria(c *fiber.Ctx) error {
	var categoria categoria.Categoria
	var err = c.BodyParser(&categoria)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar categoria",
			"details": err.Error(),
		})
	}

	var _, erro = categoria.RegistrarCategoria()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar categoria",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "categoria registrada com sucesso",
		"data":    categoria,
	})
}

func EditarCategoria(c *fiber.Ctx) error {
	var categoria categoria.Categoria
	var err = c.BodyParser(&categoria)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar categoria",
			"details": err.Error(),
		})
	}

	var _, erro = categoria.EditarCategoria()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar categoria",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "categoria editada com sucesso",
		"data":    categoria,
	})
}

func ExcluirCategoria(c *fiber.Ctx) error {
	var categoria categoria.Categoria
	var err = c.BodyParser(&categoria)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao excluir categoria",
			"details": err.Error(),
		})
	}

	var _, erro = categoria.ExcluirCategoria()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao excluir categoria",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "categoria deletada com sucesso",
		"data":    categoria,
	})
}

func RetornaDadosCategoria(c *fiber.Ctx) error {
	var categoria categoria.Categoria
	var err = c.BodyParser(&categoria)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao retornar dados categoria",
			"details": err.Error(),
		})
	}

	var resultado, erro = categoria.RetornaDadosCategoria()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao retornar dados categoria",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados retornado com sucesso",
		"data":    resultado,
	})
}
