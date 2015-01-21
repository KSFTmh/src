package koth;

import java.util.Arrays;
import org.python.core.PyArray;
import org.python.core.PyException;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

class PythonPlayer extends Player {

    @Override
    int process(int round, int players, String[] args) {
        try {
            // some code taken from http://stackoverflow.com/a/8899042/3148067
            PythonInterpreter interpreter = new PythonInterpreter();
            interpreter.execfile("src/Players/" + getFileName());
            PyObject function = interpreter.get("choose");
            PyArray results = PyArray.zeros(args.length, PyString.class);
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    results.set(i, new PyString(args[i]));
                } else {
                    System.out.println("Uh oh" + Arrays.toString(args));
                }
            }
            PyObject result = function.__call__(
                    new PyInteger(round),
                    new PyInteger(players),
                    results
            );
            int num = result.asInt();
            return num;
        } catch (PyException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            return 1;
        } catch (Exception ex) {
            return 1;
        }
    }
}
