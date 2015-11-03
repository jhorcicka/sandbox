/**
 * @author j.horcicka (GMC)
 * @since 25.5.15
 */
public class Greeter {

    public String sayHi(String name) {
        String greetingWord;

        if (name.startsWith("Mr.")) {
            greetingWord = "Hello, ";
        }
        else {
            greetingWord = "Hi, ";
        }

        return greetingWord + name;
    }
}
