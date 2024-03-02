package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.service.EstadoService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.listar();
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
		Estado estado = estadoRepository.buscar(estadoId);
		if (estado!=null){
			return ResponseEntity.status(HttpStatus.OK).body(estado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
		Estado novoEstado = estadoService.salvar(estado);

		return ResponseEntity.status(HttpStatus.CREATED).body(novoEstado);
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId,@RequestBody Estado estado){
		Estado estadoAtual = estadoRepository.buscar(estadoId);

		if(estadoAtual!=null){
			BeanUtils.copyProperties(estado, estadoAtual, "id");

			estadoService.salvar(estadoAtual);
			return ResponseEntity.status(HttpStatus.OK).body(estadoAtual);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> excluir(@PathVariable Long estadoId){
		try{
			estadoService.excluir(estadoId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} catch (EntidadeEmUsoException e){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
}
