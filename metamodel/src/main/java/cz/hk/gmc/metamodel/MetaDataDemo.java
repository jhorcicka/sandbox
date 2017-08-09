package cz.hk.gmc.metamodel;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.schema.Column;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MetaDataDemo {
    private DataContext _dataContext;

    public static void main(String args[]) {
        final MetaDataDemo demo = new MetaDataDemo();
        demo.init();
        //demo.select();
        demo.metadata();
    }

    public void init() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        _dataContext = (DataContext) context.getBean("dataContext");

        if (_dataContext == null) {
            System.out.println("NULL :-(");
            return;
        }
    }

    public void metadata() {
        for (final Column column : _dataContext.getDefaultSchema().getTableByName("test").getColumns()) {
            final String name = column.getName();
            final int size = column.getColumnSize();
            System.out.println(name + "(" + size + ")");
        }
    }

    public void select() {
        final DataSet dataSet = _dataContext.query()
                .from("test")
                .select("one, two, ten, big")
                .execute();

        for (final Row row : dataSet) {
            for (final Object value : row.getValues()) {
                System.out.println(value.toString());
            }
        }
    }
}
