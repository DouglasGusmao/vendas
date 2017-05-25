package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicas.Cliente;
import util.ConnectionFactory;

/**
 * @author Ruggery Gusmão
 */
public class ClienteDAO {

	private Connection connection;

	public ClienteDAO() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void inserir(Cliente cliente) {

		String sql = "INSERT INTO cliente (nome, email, senha) VALUES (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getSenha());

			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> listar() {

		try {
			List<Cliente> listaCliente = new ArrayList<Cliente>();

			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM cliente ORDER BY nome");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setSenha(rs.getString("senha"));

				listaCliente.add(cliente);
			}

			rs.close();
			stmt.close();
			connection.close();

			return listaCliente;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remover(Integer id, String nome) {

		try {

			String sql = null;

			if (id != null) {
				sql = "DELETE FROM cliente WHERE id = ?";
			} else {
				sql = "DELETE FROM cliente WHERE nome = ?";
			}

			PreparedStatement stmt = connection.prepareStatement(sql);

			if (id != null) {
				stmt.setInt(1, id);
			} else {
				stmt.setString(1, nome);
			}

			stmt.execute();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Cliente cliente) {

		String sql = "UPDATE cliente SET nome=?, email=?, senha=? WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getSenha());
			stmt.setInt(4, cliente.getId());

			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
