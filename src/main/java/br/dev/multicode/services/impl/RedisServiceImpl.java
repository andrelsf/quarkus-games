package br.dev.multicode.services.impl;

import br.dev.multicode.services.RedisService;
//import io.quarkus.cache.Cache;
//import io.quarkus.cache.CacheManager;
//import io.quarkus.cache.CacheName;
//import io.quarkus.redis.client.RedisClient;
//import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

//@ApplicationScoped
public class RedisServiceImpl { //implements RedisService {

  private static final Logger log = Logger.getLogger(RedisServiceImpl.class);

//  @Inject
//  RedisClient redisClient;

//  @Inject
//  ReactiveRedisClient reactiveRedisClient;

//  @Override
//  public String get(String key) {
//    return redisClient.get(key).toString();
//  }

//  @Override
//  public Uni<Void> del(String key) {
//    return reactiveRedisClient.del(List.of(key))
//        .map(response -> null);
//  }

//  @Override
//  public void set(String key, String value) {
//    Optional.ofNullable(this.get(key))
//        .ifPresentOrElse(k -> {
//          del(k);
//          redisClient.set(List.of(key, value));
//          log.info("RedisService - set registered KEY=".concat(key));
//        }, () ->
//          redisClient.set(List.of(key, value)));
//  }

}
