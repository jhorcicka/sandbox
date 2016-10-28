package cz.hk.gmc.javafx;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.cli.AutomatonScriptRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleFxAppTest {
    static class ScriptOutputCapturer {
        List<String> strings = new ArrayList<>();

        public void write(String s) {
            strings.add(s);
        }

        public String lastLine() {
            for (String s : strings) {
                System.out.println("s=" + s);
            }
            return strings.get(strings.size() - 2); // last line is just new-line
        }
    }

    @Before
    public void setup() {
        FXApp.startApp(new SimpleFxApp());
    }

    @Test
    public void firstTest() {
        runScript("src/test/groovy/test01.groovy");
    }

    @Test
    public void secondTest() {
        runScript("src/test/groovy/test02.groovy");
    }

    public void runScript(String path) {
        ScriptOutputCapturer writer = new ScriptOutputCapturer();
        AutomatonScriptRunner.getInstance().run(path, writer);

        System.out.println("Writer: " + writer.strings);

        assertThat(writer.strings.isEmpty(), is(false));
        assertThat(writer.lastLine(), is("Test PASSED!"));
    }
}