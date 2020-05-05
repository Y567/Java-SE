import com.gyy.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //这里必须是接口类型，因为动态代理的是接口
        UserService userService = context.getBean("userService", UserService.class);
        userService.delete();
    }
}
