package api.clue;

import api.clue.config.SecurityConfig;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(value = {SecurityConfig.class})
public class ClueApplicationTests {

	@Test
	public void contextLoads() {
	}

}
