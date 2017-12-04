package com.maxdavis.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxdavis.cursomc.domain.Pedido;
import com.maxdavis.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Pedido pedido = pedidoRepository.findOne(id);

		if (pedido == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id:" + id + ", Tipo:" + Pedido.class.getName());
		}

		return pedido;
	}

}
