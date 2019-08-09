package nl.hi.kuba.nashorn;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");

        //logger.debug("java: a={},b={}", 42, 'B');
        logger.debug("java: a={},b={},c={}", 42, 'B', new String[]{"1", "2"});

        try {
            //final String js = "logger.debug('javascript: a={},b={}', 42, 'B')";
            final String js = "logger.debug('javascript: a={},b={},c={}', 42, 'B', ['1','2'])";

            engine.getContext().setAttribute("logger", logger, ScriptContext.GLOBAL_SCOPE);
            final Object result = engine.eval(js);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
