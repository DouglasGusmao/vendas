package testes;

import java.util.List;
import org.junit.Test;
import basicas.Produto;
import dao.ProdutoDAO;
import org.junit.Assert;

public class TesteProduto {

	private void prepararCenario() {

		new ProdutoDAO().limparTabela(); // remove todos os registros da tabela
		this.inserir();// insere um registro na tabela
	}

	@Test
	public void inserir() {

		Produto produto = new Produto();

		produto.setTitulo("Sitío do Pica-Pau Amarelo");
		produto.setDescricao("Obra de monteiro lobato");
		produto.setPreco(22);
		produto.setNomeImagem("cuca.png");

		ProdutoDAO dao = new ProdutoDAO();
		dao.inserir(produto);
	}

	@Test
	public void testeInserir() {

		prepararCenario();

		inserir();

		int quantidadeRegistroTabela = new ProdutoDAO().listar().size();

		Assert.assertEquals(2, quantidadeRegistroTabela);
	}

	@Test
	public void testeAlterar() {

		Produto produto;
		List<Produto> listar;
		String tituloAnterior;
		String tituloPosterior;

		prepararCenario();

		listar = new ProdutoDAO().listar();
		produto = listar.get(0);
		tituloAnterior = produto.getTitulo();

		produto.setTitulo("Novo Titulo");
		new ProdutoDAO().alterar(produto);

		listar = new ProdutoDAO().listar();
		produto = listar.get(0);
		tituloPosterior = produto.getTitulo();

		Assert.assertNotEquals(tituloAnterior, tituloPosterior);
	}

	@Test
	public void testeRemover() {

		prepararCenario(); // irá inserir um contato da tabela com o nome
							// Roberto

		new ProdutoDAO().remover(null, "Sitío do Pica-Pau Amarelo"); // remove o
																		// único
																		// registro
																		// existente
																		// na
																		// tabela

		int quantidadeRegistroTabela = new ProdutoDAO().listar().size();

		Assert.assertEquals(0, quantidadeRegistroTabela);
	}

}