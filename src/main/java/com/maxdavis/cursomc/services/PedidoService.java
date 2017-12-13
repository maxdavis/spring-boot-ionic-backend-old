package com.maxdavis.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxdavis.cursomc.domain.ItemPedido;
import com.maxdavis.cursomc.domain.PagamentoComBoleto;
import com.maxdavis.cursomc.domain.Pedido;
import com.maxdavis.cursomc.domain.Enums.EstadoPagamento;
import com.maxdavis.cursomc.repositories.ItemPedidoRepository;
import com.maxdavis.cursomc.repositories.PagamentoRepository;
import com.maxdavis.cursomc.repositories.PedidoRepository;
import com.maxdavis.cursomc.repositories.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Pedido pedido = pedidoRepository.findOne(id);

		if (pedido == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id:" + id + ", Tipo:" + Pedido.class.getName());
		}

		return pedido;
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto ) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido item : obj.getItens()) {
			item.setDesconto(0.0);
			item.setPreco(produtoRepository.findOne(item.getProduto().getId()).getPreco());
		    item.setPedido(obj);
		}
		
		itemPedidoRepository.save(obj.getItens());
		
		return obj;
	}

}
