package testes;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import basicas.Cliente;
import basicas.Pedido;
import dao.PedidoDao;

public class TestePedido {

	@Test
	public void incluirPedido() {
		
		
		Cliente hewerton = new Cliente();
		hewerton.setNome("hewerton");
		hewerton.setEmail("hewerton@teste.com");
		hewerton.setId(1);
		hewerton.setSenha("12345");
		Date dataH = new Date();
		Pedido pedido = new Pedido(1,dataH, 50.20, hewerton);		
		
		
		PedidoDao dao = new PedidoDao();
		dao.inserir(pedido);
		
	}
	
	@Test
	public void removerPedido(){
		
	}
	

}
