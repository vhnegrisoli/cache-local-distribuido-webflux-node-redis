package br.com.cache.localdistributedcache.cep.service;

import br.com.cache.localdistributedcache.cep.repository.CacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RedisCacheService {

    @Autowired
    private CacheRepository cacheRepository;

    public Mono<String> save(String key, String value) {
        try {
            return cacheRepository
                .save(key, value)
                .flatMap(saved -> {
                    if (saved) {
                        log.info("Cache salvo para a chave {}", key);
                    } else {
                        log.info("Não foi possível salvar cache para a chave {}", key);
                    }
                    return Mono.just(value);
                });
        } catch (Exception ex) {
            log.error("Erro ao tentar salvar cache para chave {}", key, ex);
        }
        return Mono.just(value);
    }

    public Mono<String> get(String key) {
        try {
            return cacheRepository
                .get(key)
                .doOnNext(response -> log.info("Retornando cache para a chave {}", key));
        } catch (Exception ex) {
            log.error("Erro ao tentar recuperar cache para chave {}", key, ex);
        }
        return Mono.empty();
    }

    public Mono<Boolean> existsForKey(String key) {
        try {
            return cacheRepository
                .existsForKey(key)
                .doOnNext(exists -> {
                    if (exists) {
                        log.info("Cache existente para a chave {}", key);
                    } else {
                        log.info("Cache não existente para a chave {}", key);
                    }
                });
        } catch (Exception ex) {
            log.error("Erro ao tentar recuperar cache para chave {}", key, ex);
        }
        return Mono.just(false);
    }
}
