package Services

import (
	usuario "meuapp/Model/Struct"

	"github.com/gofiber/fiber/v2"
)

func EfectuarEntradaUsuario(c *fiber.Ctx) error {
	var usuario usuario.Usuario
	var err = c.BodyParser(&usuario)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao efectuar entrada do usuario",
			"details": err.Error(),
		})
	}

	var resposta, erro = usuario.EfectuarEntradaUsuario()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao obter dados do usuario",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados obtidos com sucesso",
		"data":    resposta,
	})
}

func RegistrarUsuario(c *fiber.Ctx) error {
	var usuario usuario.Usuario
	var err = c.BodyParser(&usuario)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar usuario",
			"details": err.Error(),
		})
	}

	var _, erro = usuario.RegistrarUsuario()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao registrar usuario",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados registrado com sucesso",
		"data":    usuario,
	})
}

func EditarUsuario(c *fiber.Ctx) error {
	var usuario usuario.Usuario
	var err = c.BodyParser(&usuario)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar usuario",
			"details": err.Error(),
		})
	}

	var _, erro = usuario.EditarUsuario()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao editar usuario",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados atualizado com sucesso",
		"data":    usuario,
	})
}

func ExcluirUsuario(c *fiber.Ctx) error {
	var usuario usuario.Usuario
	var err = c.BodyParser(&usuario)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao excluir usuario",
			"details": err.Error(),
		})
	}

	var _, erro = usuario.ExcluirUsuario()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao excluir usuario",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados deletado com sucesso",
		"data":    usuario,
	})
}

func RetornaTodosUsuarios(c *fiber.Ctx) error {
	var usuario usuario.Usuario
	var err = c.BodyParser(&usuario)

	if err != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao receber usuario",
			"details": err.Error(),
		})
	}

	var _, erro = usuario.RetornaTodosUsuarios()

	if erro != nil {
		return c.Status(400).JSON(fiber.Map{
			"status":  "falha",
			"message": "falha ao receber usuario",
			"details": erro.Error(),
		})
	}

	return c.Status(200).JSON(fiber.Map{
		"status":  "sucesso",
		"message": "dados obtidos com sucesso",
		"data":    usuario,
	})
}
