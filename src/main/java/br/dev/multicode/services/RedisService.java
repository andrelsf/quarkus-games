package br.dev.multicode.services;

import io.smallrye.mutiny.Uni;

public interface RedisService {

  String get(String key);

  Uni<Void> del(String key);

  void set(String key, String value);

}
