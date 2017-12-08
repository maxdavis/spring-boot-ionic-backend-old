package com.maxdavis.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.maxdavis.cursomc.domain.Cliente;
import com.maxdavis.cursomc.dto.ClienteDTO;
import com.maxdavis.cursomc.repositories.ClienteRepository;
import com.maxdavis.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) throws ObjectNotFoundException {
		Cliente cliente = clienteRepository.findOne(id);

		if (cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Cliente.class.getName());
		}

		return cliente;
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);

	}

	public Cliente update(Cliente obj) throws ObjectNotFoundException {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);

	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			clienteRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidade relacionadas.");

		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

}
