package testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import basicas.Cliente;
import dao.ClienteDAO;

public class testeCliente {

	@Test
	public void inserir() {

		Cliente cliente = new Cliente();

		cliente.setNome("Ruggery Gusmão");
		cliente.setEmail("ruggery.gusmao@hotmail.com");
		cliente.setSenha("12345678");

		ClienteDAO dao = new ClienteDAO();
		dao.inserir(cliente);

		int quantidadeRegistroTabela = new ClienteDAO().listar().size();

		Assert.assertEquals(1, quantidadeRegistroTabela);
	}

	@Test
	public void testeAlterar() {

		Cliente cliente;
		List<Cliente> listar;
		String nomeAnterior;
		String nomePosterior;

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

		this.inserir();

		new ClienteDAO().remover(null, "Ruggery Gusmão");

		int quantidadeRegistroTabela = new ClienteDAO().listar().size();

		Assert.assertEquals(0, quantidadeRegistroTabela);
	}
}