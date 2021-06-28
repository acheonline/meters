package ru.achernyavskiy0n.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@Import(value = {RedisConfiguration.class})
@EnableRedisRepositories(basePackages = "ru.achernyavskiy0n.repository")
@ComponentScan(basePackages = {"ru.achernyavskiy0n"})
public class MetersConfiguration {


}
