package Services

import (
	"github.com/gofiber/fiber/v2"

	conexao "meuapp/db"
)

func CriarTabelas(c *fiber.Ctx) error {
	var conexao = conexao.CriarTabelas()
	if conexao {
		return c.Status(200).JSON(fiber.Map{
			"status":  "sucesso",
			"message": "tabelas criadas com sucesso",
			"data":    true,
		})
	}

	return c.Status(400).JSON(fiber.Map{
		"status":  "falha",
		"message": "falha ao criar as tabelas",
		"data":    false,
	})
}
