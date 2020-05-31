package api.clue.repository.util;

import java.nio.file.Paths;
import java.sql.Connection;
import org.flywaydb.core.Flyway;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class DataSetExecutorListener extends AbstractTestExecutionListener {

  private static String DB_URL = "jdbc:h2:" + Paths.get("target").toAbsolutePath() + "/flyway-test";

  private Flyway flyway;

  protected Connection conn;

  @Override
  public void beforeTestMethod(TestContext testContext) throws Exception {
    this.flyway = Flyway.configure()
        .dataSource(DB_URL, "user", "")
        .locations("filesystem:src/main/resources/db/migration")
        .load();
    this.flyway.clean();
    this.flyway.migrate();

    this.conn =  flyway.getConfiguration().getDataSource().getConnection();
  }

  @Override
  public void afterTestMethod(TestContext testContext) throws Exception {
    this.flyway.clean();
    if (!conn.isClosed()) {
      conn.close();
    }
  }
}
