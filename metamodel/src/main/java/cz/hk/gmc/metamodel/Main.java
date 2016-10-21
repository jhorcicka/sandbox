package cz.hk.gmc.metamodel;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private DataContext _dataContext;
    
    public static void main(String args[]) {
        Main main = new Main();
        main.select();
    }
    
    public Main() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        _dataContext = (DataContext) context.getBean("dataContext");

        if (_dataContext == null) {
            System.out.println("NULL :-(");
            return;
        }
    }

    public void select() {
        DataSet dataSet = _dataContext.query()
                .from("persons")
                .select("name")
                .where("id").eq(1)
                .execute();

        for (final Row row : dataSet) {
            System.out.println(row.getValue(0));
        }
    }
}
