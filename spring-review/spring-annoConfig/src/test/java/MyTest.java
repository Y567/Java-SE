import com.gyy.config.AppConfig;
import com.gyy.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        //使用另一个实现类，来导入配置
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = context.getBean("user", User.class);
        User user1 = context.getBean("user1", User.class);
        String getString = context.getBean("getString", String.class);
        System.out.println(user);
        System.out.println(user1);
        System.out.println(getString);
    }
}
