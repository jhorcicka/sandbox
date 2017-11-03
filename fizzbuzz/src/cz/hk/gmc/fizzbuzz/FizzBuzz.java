package cz.hk.gmc.fizzbuzz;

public class FizzBuzz {
    static final String FIZZ = "fizz";
    static final String BUZZ = "buzz";
    static final String FIZZBUZZ= FIZZ + " " + BUZZ;

    public String response(final int input) {
        if (input % 3 == 0 && input % 5 == 0) {
            return FIZZBUZZ;
        } else if (input % 3 == 0) {
            return FIZZ;
        } else if (input % 5 == 0) {
            return BUZZ;
        } else {
            return ""+input;
        }
    }
}
