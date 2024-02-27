package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.api.model.CozinhasXmlWapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		if (cozinha!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha){
		Cozinha novaCozinha = cozinhaRepository.salvar(cozinha);

			return ResponseEntity.status(HttpStatus.CREATED).body(novaCozinha);
		}

}
