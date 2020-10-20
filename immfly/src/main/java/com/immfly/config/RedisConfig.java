package com.immfly.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

	private Environment environment;
	
	public RedisConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		String host = Arrays.stream(environment.getActiveProfiles()).anyMatch("prod"::equals) ? "redis" : "localhost";
		return new JedisConnectionFactory(new RedisStandaloneConfiguration(host, 6379));
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}

}
