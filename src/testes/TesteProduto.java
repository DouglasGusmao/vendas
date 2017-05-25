package testes;

import java.util.List;
import org.junit.Test;
import basicas.Produto;
import dao.ProdutoDAO;
import org.junit.Assert;

public class TesteProduto {

	@Test
	public void inserir() {
		
		new ProdutoDAO().limparTabela();
		
		Produto produto = new Produto();

		produto.setTitulo("Sitío do Pica-Pau Amarelo");
		produto.setDescricao("Obra de monteiro lobato");
		produto.setPreco(22);
		produto.setNomeImagem("cuca.png");

		ProdutoDAO dao = new ProdutoDAO();
		dao.inserir(produto);
		
		int quantidadeRegistroTabela = new ProdutoDAO().listar().size();

		Assert.assertEquals(1, quantidadeRegistroTabela);

	}

	@Test
	public void testeAlterar() {

		Produto produto;
		List<Produto> listar;
		String titulo;
		String tituloAlterado;

		listar = new ProdutoDAO().listar();
		produto = listar.get(0);
		
		produto.setTitulo("Novo Titulo");
		new ProdutoDAO().alterar(produto);
		
		titulo = "Novo Titulo";
		
		listar = new ProdutoDAO().listar();
		produto = listar.get(0);
		tituloAlterado = produto.getTitulo();

		Assert.assertEquals(titulo, tituloAlterado);
	}

	@Test
	public void testeRemover() {

		this.inserir();

		new ProdutoDAO().remover(null, "Sitío do Pica-Pau Amarelo"); 

		int quantidadeRegistroTabela = new ProdutoDAO().listar().size();

		Assert.assertEquals(0, quantidadeRegistroTabela);
	}
}