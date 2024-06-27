package nl.hi.kuba.various.shelves;

public class Demo {
    private int[][] shelf;

    public static void main(String[] args) {
        final Demo demo = new Demo();
        demo.run();
    }

    private void run() {
        loadShelf();
    }

    private void loadShelf() {
        this.shelf = {
                {1, 0, 1},
                {0, 0, 0},
                {0, 0, 0},
        };
    }
}
