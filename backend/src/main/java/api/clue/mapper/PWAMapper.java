package api.clue.mapper;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.sqlprovider.PWASQLProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PWAMapper {

    @InsertProvider(value = PWASQLProvider.class, method = "insert")
    void insert(@Param("author") Author author, @Param("paper") Paper paper);
}
