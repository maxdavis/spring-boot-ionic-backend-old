package com.maxdavis.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxdavis.cursomc.domain.Cliente;
import com.maxdavis.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Cliente cliente = clienteRepository.findOne(id);

		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id:" + id + ", Tipo:" + Cliente.class.getName());
		}

		return cliente;
	}

}
