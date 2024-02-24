package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@RestController
@RequestMapping(value="/formaPagamentos", produces= MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository FormaPagamentoRepository;
	
	@GetMapping
	public List<FormaPagamento> listar() {
		return FormaPagamentoRepository.listar();
	}
	
}
