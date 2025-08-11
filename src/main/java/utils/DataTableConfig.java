package utils;

import io.cucumber.java.DataTableType;
import java.util.Map;
import models.User;

public class DataTableConfig {

  @DataTableType
  public User userEntry(Map<String, String> entry) {
    User user = new User();

    user.setNumero(entry.getOrDefault("numero", ""));
    user.setSaludo(entry.getOrDefault("saludo", ""));
    user.setValor(entry.getOrDefault("valor", ""));

    return user;
  }
}
