package com.example.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnClass({Pool.class,Jedis.class,Cluster.class})
public class JedisPoolConfiguation {
private final RedisProperties redisProperties;
private JedisPool jedisPool;
public JedisPoolConfiguation(RedisProperties redisProperties) {
	super();
	this.redisProperties = redisProperties;
}
@Bean
public JedisPool jedisPool() {
	this.jedisPool = createJedisPool();
	return this.jedisPool;
}

private JedisPool createJedisPool() {
	
		return new JedisPool(new GenericObjectPoolConfig() ,this.redisProperties.getHost());
	
}
}
