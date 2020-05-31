package api.clue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

// mybatis-spring-boot-starterを使っている場合は，@MapperScanは入らない
// http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ClueApplication {
  public static void main(String[] args) {
    SpringApplication.run(ClueApplication.class, args);
  }
}
