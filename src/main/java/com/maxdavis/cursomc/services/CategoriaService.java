package com.maxdavis.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxdavis.cursomc.domain.Categoria;
import com.maxdavis.cursomc.repositories.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Categoria categoria = categoriaRepository.findOne(id);

		if (categoria == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id:" + id + ", Tipo:" + Categoria.class.getName());
		}

		return categoria;
	}

}
