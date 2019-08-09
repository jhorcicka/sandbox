package cz.hk.gmc.jruby;
import org.jruby.*;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final String script ="puts 'hello'\n";
        final Ruby runtime = JavaEmbedUtils.initialize(new ArrayList());
        final RubyRuntimeAdapter adapter = JavaEmbedUtils.newRuntimeAdapter();
        final IRubyObject eval = adapter.eval(runtime, script);
        
        System.err.println("MYTODO: " + 
                eval.toString()
                + eval.asString().toString()
        );
    }
}
