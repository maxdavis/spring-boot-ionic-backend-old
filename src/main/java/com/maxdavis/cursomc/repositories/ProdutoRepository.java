package com.maxdavis.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maxdavis.cursomc.domain.Categoria;
import com.maxdavis.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT distinct obj from Produto obj INNER JOIN obj.categorias cat where obj.nome like %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	
	//Page<Produto> findDistinctByNomeContainingAndCategorias(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	
}
