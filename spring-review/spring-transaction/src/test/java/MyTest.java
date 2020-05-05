import com.gyy.dao.UserMapper;
import com.gyy.dao.impl.UserMapperImpl;
import com.gyy.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    public static void main(String[] args) {

        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<User> users = userMapper.selectUser();
        System.out.println(users);
    }
}
