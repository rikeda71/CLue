package api.clue.sqlprovider;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class PWASQLProvider extends DynamicSQLProvider {

   public String insert(@Param("author") Author author,
                        @Param("paper") Paper paper)
   {
      return new SQL() {
         {
            INSERT_INTO("paper_written_author");
            VALUES("author_id, paper_id", "#{author.id}, #{paper.id}");
         }
      }.toString();
   }
}
