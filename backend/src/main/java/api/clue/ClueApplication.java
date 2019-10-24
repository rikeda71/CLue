package api.clue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// mybatis-spring-boot-starterを使っている場合は，@MapperScanは入らない
// http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
@SpringBootApplication
public class ClueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClueApplication.class, args);
	}

}
