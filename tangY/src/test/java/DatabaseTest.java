import com.gyy.dao.PoetryMapper;
import com.gyy.pojo.Echart;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseTest {

    @Test
    public void testSpring(){
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取对象
        PoetryMapper poetryMapper = ac.getBean("poetryMapper", PoetryMapper.class);
        List<String> poetByName = poetryMapper.findPoetByName(2);
        System.out.println(poetByName);
    }

    @Test
    public void testSelectWords(){
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取对象
        PoetryMapper poetryMapper = ac.getBean("poetryMapper", PoetryMapper.class);
        List<String> wordsByName = poetryMapper.findWordsByName("李白");
        System.out.println(wordsByName);
    }

}
