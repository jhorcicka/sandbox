package cz.hk.gmc.metamodel;

import java.util.Iterator;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String args[]) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DataContext dataContext = (DataContext) context.getBean("dataContext");

        if (dataContext == null) {
            System.out.println("NULL :-(");
            return;
        }

        DataSet dataSet = dataContext.query()
                .from("persons")
                .select("name")
                .where("id").eq(1)
                .execute();

        for (final Row row : dataSet) {
            System.out.println(row.getValue(0));
        }
    }
}
