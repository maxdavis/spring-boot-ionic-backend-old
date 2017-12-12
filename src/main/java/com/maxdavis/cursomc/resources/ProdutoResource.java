package com.maxdavis.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxdavis.cursomc.domain.Produto;
import com.maxdavis.cursomc.dto.ProdutoDTO;
import com.maxdavis.cursomc.resources.utils.URL;
import com.maxdavis.cursomc.services.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Produto produto = produtoService.find(id);
		
		return ResponseEntity.ok().body(produto);
			
	}
	
	/**
	 * 
	 * @param nome
	 * @param categorias
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 * @throws ObjectNotFoundException
	 * Ex.: http://localhost:8080/produtos/?nome=or&categorias=1,4
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findAll(
				@RequestParam(value="nome", defaultValue="") String nome,
				@RequestParam(value="categorias", defaultValue="") String categorias,
			    @RequestParam(value="page", defaultValue="0") Integer page, 
			    @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			    @RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			    @RequestParam(value="direction", defaultValue="ASC") String direction) 
			   throws ObjectNotFoundException {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
			
		Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
			
		return ResponseEntity.ok().body(listDto);

	}
	
}
