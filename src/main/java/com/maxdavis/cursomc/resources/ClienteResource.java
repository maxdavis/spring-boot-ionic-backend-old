package com.maxdavis.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maxdavis.cursomc.domain.Cliente;
import com.maxdavis.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
		
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Cliente Cliente = clienteService.buscar(id);
		
		return ResponseEntity.ok().body(Cliente);
		
	}
}
