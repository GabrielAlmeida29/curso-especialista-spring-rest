package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.api.model.CozinhasXmlWapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value="/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}

	@GetMapping(produces=MediaType.APPLICATION_XML_VALUE)
	public  CozinhasXmlWapper listarxml() {
		return new CozinhasXmlWapper(cozinhaRepository.listar());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar (@PathVariable Long cozinhaId){
		return cozinhaRepository.buscar(cozinhaId);
	}
	
}
