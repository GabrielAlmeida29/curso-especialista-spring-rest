package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(value="/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoRepository PermissaoRepository;
	
	@GetMapping
	public List<Permissao> listar() {
		return PermissaoRepository.findAll();
	}
	
}
