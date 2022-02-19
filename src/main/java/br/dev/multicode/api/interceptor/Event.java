package br.dev.multicode.api.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {

  String methodName;
  String params;

}
