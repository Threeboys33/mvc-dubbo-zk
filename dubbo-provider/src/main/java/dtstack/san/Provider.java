package dtstack.san;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: Threeboys33
 * @created: 2019-11-06 15:00
 **/
public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo_provider.xml"});
        context.start();
        System.in.read();
    }
}
