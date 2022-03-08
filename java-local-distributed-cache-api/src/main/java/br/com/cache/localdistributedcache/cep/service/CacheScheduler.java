package br.com.cache.localdistributedcache.cep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheScheduler {

    @Autowired
    private LocalCacheService localCacheService;

    @Scheduled(cron = "${app-config.cache.scheduler}")
    public void removeExpiredKeys() {
        localCacheService.removeExpiredKeys();
    }
}
