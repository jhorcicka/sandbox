package cz.hk.gmc.various;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
    public static void main(String[] args) {
        final Locale locale = new Locale("en", "US");
        final ResourceBundle labels = ResourceBundle.getBundle("bundle", locale);
        System.out.println(labels.getString("label1"));
    }
}
