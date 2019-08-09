package nl.hi.kuba.metamodel;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MongoDemo {
    private DataContext _dataContext;

    public MongoDemo() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("mongo-context.xml");
        _dataContext = (DataContext) context.getBean("mongoContext");

        if (_dataContext == null) {
            System.out.println("NULL :-(");
            return;
        }
    }

    public void select() {
        final DataSet dataSet = _dataContext.query().from("people").select("name").where("id").eq(2).execute();

        for (final Row row : dataSet) {
            System.out.println(row.getValue(0));
        }
    }

    public static void main(String[] args) {
        final MongoDemo main = new MongoDemo();
        main.select();
    }
}
