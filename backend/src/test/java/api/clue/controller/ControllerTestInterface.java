package api.clue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ControllerTestInterface {

  ObjectMapper mapper = new ObjectMapper();

  default String entity2String(Object object) throws Exception {
    return mapper.writeValueAsString(object);
  }

}
