package Services

import (
	reservado "meuapp/Model/Struct"

	"github.com/gofiber/fiber/v2"
)

func RegistrarReserva(c *fiber.Ctx) error {
	var reservado reservado.Livro_Reservado
	var falha = c.BodyParser(&reservado)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar dados",
			"details": falha.Error(),
		})
	}

	var erro = reservado.RegistrarReserva()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar dados",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados registrados com sucesso",
		"data":    reservado,
	})
}

func ListaLivroReservado(c *fiber.Ctx) error {
	var reservado reservado.Livro_Reservado
	var falha = c.BodyParser(&reservado)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados",
			"details": falha.Error(),
		})
	}

	var resultado, erro = reservado.ListaLivroReservado()

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

func EditarReserva(c *fiber.Ctx) error {
	var reservado reservado.Livro_Reservado
	var falha = c.BodyParser(&reservado)

	if falha != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar dados",
			"details": falha.Error(),
		})
	}

	var erro = reservado.EditarReserva()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar dados",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados editados com sucesso",
		"data":    reservado,
	})
}
