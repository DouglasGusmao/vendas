package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicas.ItemPedido;
import util.ConnectionFactory;

public class ItemPedidoDao {

	private Connection connection;

	public ItemPedidoDao() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//inserir
	public void inserir(ItemPedido itemPedido) {

		String sql = "INSERT INTO item_pedido (preco_unitario, quantidade, preco_total, pedido, produto) VALUES (?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setDouble(1,itemPedido.getPrecoUnitario());
			stmt.setInt(2,itemPedido.getQuantidade());
			stmt.setDouble(3,itemPedido.getPrecoTotal());
			stmt.setLong(4,itemPedido.getPedido().getId());
			stmt.setLong(5,itemPedido.getProduto().getId());
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//remover
	public void remover(ItemPedido itemPedido) {

		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM item_pedido WHERE id_pedido = ? end id_produto = ?");
			stmt.setLong(1, itemPedido.getPedido().getId());
			stmt.setLong(2, itemPedido.getProduto().getId());
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//listar
	public List<ItemPedido> listar() {

		try {
			List<ItemPedido> listarItemPedido = new ArrayList<ItemPedido>();

			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM item_pedido");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setPrecoUnitario(rs.getDouble("preco_unitario"));
				itemPedido.setQuantidade(rs.getInt("quantidade"));
				itemPedido.setPrecoTotal(rs.getDouble("preco_total"));
			}

			rs.close();
			stmt.close();
			connection.close();

			return listarItemPedido;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}//fim
