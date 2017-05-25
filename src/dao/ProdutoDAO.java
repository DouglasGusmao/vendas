package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicas.Produto;

import util.ConnectionFactory;

/**
 * @author Ruggery Gusmão
 */
public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void inserir(Produto produto) {

		String sql = "INSERT INTO produto (titulo, descricao, preco, nomeImagem) VALUES (?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, produto.getTitulo());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPreco());
			stmt.setString(4, produto.getNomeImagem());

			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> listar() {

		try {
			List<Produto> listaProduto = new ArrayList<Produto>();

			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM produto ORDER BY titulo");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setTitulo(rs.getString("titulo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setNomeImagem(rs.getString("nomeImagem"));

				listaProduto.add(produto);
			}

			rs.close();
			stmt.close();
			connection.close();

			return listaProduto;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remover(Integer id, String titulo) {

		try {

			String sql = null;

			if (id != null) {
				sql = "DELETE FROM produto WHERE id = ?";
			} else {
				sql = "DELETE FROM produto WHERE titulo = ?";
			}

			PreparedStatement stmt = connection.prepareStatement(sql);

			if (id != null) {
				stmt.setInt(1, id);
			} else {
				stmt.setString(1, titulo);
			}

			stmt.execute();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Produto produto) {

		String sql = "UPDATE produto SET titulo=?, descricao=?, preco=?, nomeImagem=? WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, produto.getTitulo());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPreco());
			stmt.setString(4, produto.getNomeImagem());
			stmt.setInt(5, produto.getId());

			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> pesquisar(Produto produto) {

		try {
			List<Produto> pesquisaProduto = new ArrayList<Produto>();

			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM produto WHERE titulo like ? and preco=?");

			stmt.setString(1, "%" + produto.getTitulo() + "%");
			stmt.setDouble(2, produto.getPreco());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Produto produto2 = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setTitulo(rs.getString("titulo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setNomeImagem(rs.getString("nomeImagem"));

				pesquisaProduto.add(produto2);
			}

			rs.close();
			stmt.close();
			connection.close();

			return pesquisaProduto;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void limparTabela() {

		try {

			PreparedStatement stmt = connection.prepareStatement("DELETE FROM produto");
			stmt.execute();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
