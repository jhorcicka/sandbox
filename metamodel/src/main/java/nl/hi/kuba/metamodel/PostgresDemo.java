package nl.hi.kuba.metamodel;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PostgresDemo {
    private DataContext _dataContext;

    public PostgresDemo() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("postgres-context.xml");
        _dataContext = (DataContext) context.getBean("postgresContext");

        if (_dataContext == null) {
            System.out.println("NULL :-(");
            return;
        }
    }

    public void select() {
        final DataSet dataSet = _dataContext.query().from("people").select("name").where("id").eq(1).execute();

        for (final Row row : dataSet) {
            System.out.println(row.getValue(0));
        }
    }

    public static void main(String[] args) {
        final PostgresDemo main = new PostgresDemo();
        main.select();
    }
}
