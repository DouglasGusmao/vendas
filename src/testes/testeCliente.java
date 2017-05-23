package testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import basicas.Cliente;
import dao.ClienteDAO;
import dao.ProdutoDAO;

public class testeCliente {

	private void prepararCenario() {

		new ClienteDAO().limparTabela(); // remove todos os registros da tabela
		this.inserir();// insere um registro na tabela
	}

	@Test
	public void inserir() {

		Cliente cliente = new Cliente();

		cliente.setNome("Ruggery Gusmão");
		cliente.setEmail("ruggery.gusmao@hotmail.com");
		cliente.setSenha("12345678");

		ClienteDAO dao = new ClienteDAO();
		dao.inserir(cliente);
	}

	@Test
	public void testeAlterar() {

		Cliente cliente;
		List<Cliente> listar;
		String nomeAnterior;
		String nomePosterior;

		prepararCenario();

		listar = new ClienteDAO().listar();
		cliente = listar.get(0);
		nomeAnterior = cliente.getNome();

		cliente.setNome("Novo nome");
		new ClienteDAO().alterar(cliente);

		listar = new ClienteDAO().listar();
		cliente = listar.get(0);
		nomePosterior = cliente.getNome();

		Assert.assertNotEquals(nomeAnterior, nomePosterior);
	}

	@Test
	public void testeRemover() {

		prepararCenario(); // irá inserir um contato da tabela com o nome
							// Ruggery Gusmão

		new ClienteDAO().remover(null, "Ruggery Gusmão"); // remove o
																		// único
																		// registro
																		// existente
																		// na
																		// tabela

		int quantidadeRegistroTabela = new ClienteDAO().listar().size();

		Assert.assertEquals(0, quantidadeRegistroTabela);
	}
}