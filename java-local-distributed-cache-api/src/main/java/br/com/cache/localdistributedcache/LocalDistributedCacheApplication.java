package br.com.cache.localdistributedcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LocalDistributedCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalDistributedCacheApplication.class, args);
	}
}
