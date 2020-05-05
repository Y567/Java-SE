import com.gyy.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        ApplicationContext con = new ClassPathXmlApplicationContext("beans.xml");
        People people = con.getBean("people", People.class);
        System.out.println(people);
    }
}
