package top.djp.mby.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import top.djp.mby.model.Test;

import java.util.List;

public interface TestMapper {

    @Insert("insert into test(id,user_id) values (#{id},#{userId})")
    int insertTest(Test test);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id")
    })
    @Select("select id, user_id from test where id=#{id}")
    Test selectTest(long id);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id")
    })
    @Select("select id, user_id from test")
    List<Test> selectTestList();

}
