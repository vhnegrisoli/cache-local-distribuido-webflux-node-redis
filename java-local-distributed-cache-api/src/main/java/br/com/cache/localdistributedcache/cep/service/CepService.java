package br.com.cache.localdistributedcache.cep.service;

import br.com.cache.localdistributedcache.cep.client.ViaCepClient;
import br.com.cache.localdistributedcache.cep.dto.CepResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
public class CepService {

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CacheServiceWrapper cacheServiceWrapper;

    public Mono<CepResponse> findByCep(String cep) {
        return cacheServiceWrapper
            .exists(cep)
            .flatMap(exists -> {
                if (exists) {
                    return cacheServiceWrapper.get(cep);
                } else {
                    return viaCepClient
                        .findByCep(cep)
                        .flatMap(response -> cacheServiceWrapper.save(cep, response));
                }
            })
            .flatMap(this::handleResponse);
    }

    private Mono<CepResponse> handleResponse(String response) {
        if (!isEmpty(response)) {
            try {
                return Mono.just(objectMapper.readValue(response, CepResponse.class));
            } catch (Exception ex) {
                log.error("Erro ao tentar converter resposta do CEP.", ex);
            }
        }
        return Mono.empty();
    }
}
