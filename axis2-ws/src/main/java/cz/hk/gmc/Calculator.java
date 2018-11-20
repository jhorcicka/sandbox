package cz.hk.gmc;

public class Calculator {
    private static Calculator instance;
    private int x = 1;
    private int y = 2;
    private int result;

    private Calculator() {
    }

    public static Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSum() {
        this.result = x + y;
        return result;
    }
}
