import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @since 20. 11. 2015
 */
public class Sorter {
    private static class Item {
        private String name = "";
        private int priority = 0;

        public Item(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("xyz", 1));
        items.add(new Item("xyz", 2));
        items.add(new Item("xyz", 1));
        items.add(new Item("abc", 2));
        items.add(new Item("abc", 3));
        items.add(new Item("abc", 1));

        Comparator<Item> itemComparator = new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                if (i1.getName().equals(i2.getName())) {
                    return ((Integer)i1.getPriority()).compareTo(i2.getPriority());
                } else {
                    return i1.getName().compareTo(i2.getName());
                }
            }
        };

        debug(items);
        Collections.sort(items, itemComparator);
        System.out.println("===========");
        debug(items);
    }

    private static void debug(List items) {
        for (Object object : items) {
            System.out.println(object.toString());
        }
    }
}
