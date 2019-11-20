package top.djp.mby;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.djp.mby.mapper.TestMapper;

import java.util.List;

@SpringBootTest
class BootMbyAutowireApplicationTests {

    @Autowired
    private TestMapper testMapper;

    @Test
    void insertTest() {
        top.djp.mby.model.Test test=new top.djp.mby.model.Test();
        test.setId(2L).setUserId("2");
        testMapper.insertTest(test);
    }

    @Test
    void selectTest() {
        top.djp.mby.model.Test test = testMapper.selectTest(1L);
        System.err.println("test data:"+test);
    }

    @Test
    void selectTestList() {
        List<top.djp.mby.model.Test> testList = testMapper.selectTestList();
        System.err.println("testList data:"+testList);
    }


}
