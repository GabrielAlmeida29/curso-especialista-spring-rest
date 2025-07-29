package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    public static final String MSG_ENTIDADE_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso.";
    public static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Não existe um cadastro de restaurante com código %d.";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscaOuFalha(cozinhaId);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public void excluir(Long restauranteId) {
        try {
            restauranteRepository.deleteById(restauranteId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException
                    (String.format(MSG_ENTIDADE_EM_USO, restauranteId));
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format(MSG_ENTIDADE_NAO_ENCONTRADA, restauranteId));
        }
    }

    public Restaurante buscaOuFalha(Long restauranteId) {
    return restauranteRepository.findById(restauranteId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ENTIDADE_NAO_ENCONTRADA, restauranteId)));
    }

}