package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId){
        try{
            estadoRepository.deleteById(estadoId);
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois está em uso.", estadoId));
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d.", estadoId));
        }
    }

}
