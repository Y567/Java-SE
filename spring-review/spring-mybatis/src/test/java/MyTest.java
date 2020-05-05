
import com.gyy.dao.UserMapper;
import com.gyy.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MyTest {
    public static void main(String[] args) throws IOException {
        //由于所有的对象都归Spring容器管了
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapperImpl", UserMapper.class);
        for (User user : userMapper.findUser()) {
            System.out.println(user);
        }

    }
}
