package cz.hk.gmc.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String args[]) {
        Main m = new Main();
        m.run();
    }

    public void run() {
        System.out.println("java verison=" + System.getProperty("java.version"));

        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        Customer customer = (Customer) context.getBean("CustomerBean");

        if (customer == null) {
            System.out.println("NULL :-(");
        } else {
            System.out.println("Not NULL :-)\n" + customer.toString());
        }
    }
}
