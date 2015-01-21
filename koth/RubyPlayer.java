package koth;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyString;
import org.jruby.embed.ScriptingContainer;

class RubyPlayer extends Player {

    @Override
    int process(int round, int players, String[] args) {
        Object[] rubyArgs = new Object[3];
        rubyArgs[0] = round;
        rubyArgs[1] = players;
        Ruby ruby = Ruby.getGlobalRuntime();
        RubyArray array = RubyArray.newArray(ruby);
        for (String s : args) {
            array.add(RubyString.newString(ruby, s));
        }
        rubyArgs[2] = array;
        try {
            ScriptingContainer container = new ScriptingContainer();
            Object o = container.runScriptlet(new FileReader("src/Players/" + getFileName()), getFileName());
            return container.callMethod(o, "choose", rubyArgs, Integer.class);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            return 1;
        } catch (Exception ex) {
            return 1;
        }
    }
}
