package dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import basicas.Pedido;
import util.ConnectionFactory;


public class PedidoDao {


	private Connection connection;

	public PedidoDao() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	public void inserir(Pedido pedido) {

		String sql = "INSERT INTO contato (id, dataPedido, valorTotal ) VALUES (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setLong(1, pedido.getId());
			stmt.setDate(2, (Date) pedido.getDataPedido());
			stmt.setDouble(3, pedido.getValorTotal());
			
			stmt.execute();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void removerPedido(long id){
		try{
			
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM pedido WHERE id = ?");
			
			
			stmt.execute();
			connection.close();
			
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	} 
}
