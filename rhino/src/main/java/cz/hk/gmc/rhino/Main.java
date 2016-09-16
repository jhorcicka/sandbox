package cz.hk.gmc.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String args[]) {
        //logger.debug("java: a={},b={}", 42, 'B');
        logger.debug("java: a={},b={},c={}", 42, 'B', new String[] { "1", "2" });
        final Context cx = Context.enter();
        
        try {
            final Scriptable scope = cx.initStandardObjects();
            ScriptableObject.putProperty(scope, "logger", logger);
            
            //final String js = "logger.debug('javascript: a={},b={}', 42, 'B')";
            final String js = "logger.debug('javascript: a={},b={},c={}', 42, 'B', ['1','2'])";
            
            final Object result = cx.evaluateString(scope, js, "<cmd>", 1, null);
            System.err.println(Context.toString(result));
        } finally {
            Context.exit();
        }
    }
}
