package cz.hk.gmc.various;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
    public static void main(String[] args) {
        test(Locale.getDefault());
        test(new Locale("en", "US"));
        test(new Locale("cs", "CZ"));
    }

    private static void test(final Locale locale) {
        final ResourceBundle labels = ResourceBundle.getBundle("bundle", locale);
        //final ResourceBundle labels = ResourceBundle.getBundle("bundle___TEST");
        System.out.println(labels.getString("label3"));
    }
}
