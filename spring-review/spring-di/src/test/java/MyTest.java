import com.gyy.pojo.Address;
import com.gyy.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        ApplicationContext cpx = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) cpx.getBean("student");
        Address address2 = cpx.getBean("address2", Address.class);
        Address address3 = cpx.getBean("address3", Address.class);
        System.out.println("===================");
        System.out.println(address2);
        System.out.println("===================");
        System.out.println(address3);
        System.out.println("===================");
        System.out.println(student);
    }
}
