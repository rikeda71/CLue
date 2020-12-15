package api.clue.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class ClueConfig {

  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
    factory.setDataSource(dataSource);

    return factory;
  }
}
