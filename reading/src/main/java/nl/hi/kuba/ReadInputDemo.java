package nl.hi.kuba;

import java.util.Scanner;

public class ReadInputDemo {
    public static void main(String[] arguments) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        final String name = scanner.next();
        System.out.println("Enter your company: ");
        final String company = scanner.next();
        System.out.println(String.format("%s@%s", name, company));
    }
}
