package com.maxdavis.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.maxdavis.cursomc.domain.Categoria;
import com.maxdavis.cursomc.domain.Cliente;
import com.maxdavis.cursomc.dto.CategoriaDTO;
import com.maxdavis.cursomc.repositories.CategoriaRepository;
import com.maxdavis.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) throws ObjectNotFoundException {
		Categoria categoria = categoriaRepository.findOne(id);

		if (categoria == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id:" + id + ", Tipo:" + Categoria.class.getName());
		}

		return categoria;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);

	}

	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoriaRepository.save(newObj);

	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			categoriaRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria com dependência.");

		}
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto){
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
