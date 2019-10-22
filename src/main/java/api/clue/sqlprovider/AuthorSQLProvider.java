package api.clue.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

public class AuthorSQLProvider extends DynamicSQLProvider{

    public String select(String name) {
        return new SQL() {
            {
                SELECT("*");
                FROM("authors");
                if (name != null) {
                    WHERE("name LIKE CONCAT('%', #{name}, '%')");
                }
                ORDER_BY("id ASC");
            }
        }.toString();
    }

}
