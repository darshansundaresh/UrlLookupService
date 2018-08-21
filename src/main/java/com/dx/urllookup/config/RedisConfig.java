package com.dx.urllookup.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

@Configuration
public class RedisConfig {

	@Value("${redis.servername}")
	private String redisServerName;
	
	@Value("${redis.serverport}")
	private int redisServerPort;
	
	@Value("${redis.password:}")
	private String redisPassword;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		
		 RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(this.redisServerName, this.redisServerPort);
		    if(redisPassword!=null && redisPassword.length()>0) {
		    	
		    	redisStandaloneConfiguration.setPassword(RedisPassword.of(this.redisPassword));
		    }
		    return new JedisConnectionFactory(redisStandaloneConfiguration);
		
	}
	
	@Bean
	RedisTemplate<String, Object> redisTemplate(){
		final RedisTemplate<String, Object> template=new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}
}
