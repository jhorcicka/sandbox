package cz.hk.gmc.various;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo {
    public static void main(String[] arguments) {
        final String matchString = "TEST B.V.";
        final String masterTerm = "XXX";
        final String synonym = "B.V.";

        /*
        A word boundary is a position that is either
          preceded by a word character and not followed by one,
        or
          not preceded by a word character and followed by one.
          //followed by a word character and not preceded by one.
        */
        final Matcher matcher = Pattern.compile("\\b" + synonym + "\\b?").matcher(matchString);
        while (matcher.find()) {
            final String group = matcher.group(0);
            final String result =
                    matchString.substring(0, matcher.start()) + masterTerm + matchString.substring(matcher.end());
            System.err.println("MYTODO: group=" + group + ", result=" + result);
        }
    }
}
