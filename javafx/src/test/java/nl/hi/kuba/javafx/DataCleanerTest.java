package nl.hi.kuba.javafx;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.datacleaner.Main;
import org.junit.Before;
import org.junit.Test;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.cli.AutomatonScriptRunner;

public class DataCleanerTest {
    static class ScriptOutputCapturer {
        List<String> strings = new ArrayList<>();

        public void write(String s) {
            strings.add(s);
        }

        public String lastLine() {
            return strings.get(strings.size() - 2); // last line is just new-line
        }
    }

    @Test
    public void test() {
        System.out.println("in test...");
    }

    @Before
    public void setup() {
        FXApp.startApp(new Main());
    }

    @Test
    public void firstTest() {
        //runScript("src/test/groovy/test01.groovy");
    }

    public void runScript(String path) {
        SimpleFxAppTest.ScriptOutputCapturer writer = new SimpleFxAppTest.ScriptOutputCapturer();
        AutomatonScriptRunner.getInstance().run(path, writer);

        assertThat(writer.strings.isEmpty(), is(false));
        assertThat(writer.lastLine(), is("Test PASSED!"));
    }
}
