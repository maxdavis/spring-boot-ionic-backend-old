package com.maxdavis.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maxdavis.cursomc.domain.Categoria;
import com.maxdavis.cursomc.domain.Cidade;
import com.maxdavis.cursomc.domain.Cliente;
import com.maxdavis.cursomc.domain.Endereco;
import com.maxdavis.cursomc.domain.Estado;
import com.maxdavis.cursomc.domain.Pagamento;
import com.maxdavis.cursomc.domain.PagamentoComBoleto;
import com.maxdavis.cursomc.domain.PagamentoComCartao;
import com.maxdavis.cursomc.domain.Pedido;
import com.maxdavis.cursomc.domain.Produto;
import com.maxdavis.cursomc.domain.Enums.EstadoPagamento;
import com.maxdavis.cursomc.domain.Enums.TipoCliente;
import com.maxdavis.cursomc.repositories.CategoriaRepository;
import com.maxdavis.cursomc.repositories.CidadeRepository;
import com.maxdavis.cursomc.repositories.ClienteRepository;
import com.maxdavis.cursomc.repositories.EnderecoRepository;
import com.maxdavis.cursomc.repositories.EstadoRepository;
import com.maxdavis.cursomc.repositories.PagamentoRepository;
import com.maxdavis.cursomc.repositories.PedidoRepository;
import com.maxdavis.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.setProdutos(Arrays.asList(p1, p2, p3));
		cat2.setProdutos(Arrays.asList(p2));

		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1, cat2));
		p3.setCategorias(Arrays.asList(cat1));

		categoriaRepository.save(Arrays.asList(cat1, cat2));

		produtoRepository.save(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerias");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));

		cidadeRepository.save(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "09898798788", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("83998889090", "83988096666"));

		Endereco end1 = new Endereco(null, "Av. Epitacio Pessoa", "90", "Ed.Monte Really, principal ", "Tambauzinho",
				"58098-090", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua Carneiro da Cunha", "1001", "", "Torre", "58000-088", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/10/2017 10:36"), cli1, end1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("30/11/2017 07:09"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("01/11/2017 07:09"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		

	}

}
