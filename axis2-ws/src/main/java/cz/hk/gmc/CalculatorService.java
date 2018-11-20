package cz.hk.gmc;

public class CalculatorService {
    Calculator calculator;

    public CalculatorService() {
        calculator = Calculator.getInstance();
    }

    public String sayHi(final String name) {
        return "Hi, " + name;
    }
    
    public void setX(int x) {
        calculator.setX(x);
    }

    public void setY(int y) {
        calculator.setY(y);
    }

    public int getSum() {
        return calculator.getSum();
    }
}
