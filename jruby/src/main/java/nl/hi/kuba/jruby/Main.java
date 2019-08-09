package nl.hi.kuba.jruby;

import java.util.ArrayList;

import org.jruby.Ruby;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;

public class Main {
    public static void main(String[] args) {
        final String script = "puts 'hello'\n";
        final Ruby runtime = JavaEmbedUtils.initialize(new ArrayList());
        final RubyRuntimeAdapter adapter = JavaEmbedUtils.newRuntimeAdapter();
        final IRubyObject eval = adapter.eval(runtime, script);

        System.err.println("MYTODO: " + eval.toString() + eval.asString().toString());
    }
}
