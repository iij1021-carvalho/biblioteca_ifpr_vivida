package Services

import (
	localizacao "meuapp/Model/Struct"

	"github.com/gofiber/fiber/v2"
)

func RegistrarLocalizacao(c *fiber.Ctx) error {
	var localizacao localizacao.Localizacao
	var erro = c.BodyParser(&localizacao)

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar localizacao",
			"details": erro.Error(),
		})
	}

	var _, err = localizacao.RegistrarLocalizacao()

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar localizacao",
			"details": err.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"statuts": "sucesso",
		"message": "dados registrado com sucesso",
		"data":    localizacao,
	})
}

func EditarLocalizacao(c *fiber.Ctx) error {
	var localizacao localizacao.Localizacao
	var erro = c.BodyParser(&localizacao)

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar localizacao",
			"details": erro.Error(),
		})
	}

	var _, err = localizacao.EditarLocalizacao()

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar localizacao",
			"details": err.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"statuts": "sucesso",
		"message": "dados editados com sucesso",
		"data":    localizacao,
	})
}

func ExcluirLocalizacao(c *fiber.Ctx) error {
	var localizacao localizacao.Localizacao
	var erro = c.BodyParser(&localizacao)

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao excluir localizacao",
			"details": erro.Error(),
		})
	}

	var _, err = localizacao.EditarLocalizacao()

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao excluir localizacao",
			"details": err.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"statuts": "sucesso",
		"message": "dados deletados com sucesso",
		"data":    localizacao,
	})
}

func RetornaDadosLocalizacao(c *fiber.Ctx) error {
	var localizacao localizacao.Localizacao
	var erro = c.BodyParser(&localizacao)

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados da localizacao",
			"details": erro.Error(),
		})
	}

	var _, err = localizacao.EditarLocalizacao()

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao excluir localizacao",
			"details": err.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"statuts": "sucesso",
		"message": "dados obtidos com sucesso",
		"data":    localizacao,
	})
}
