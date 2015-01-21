package koth;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

class JavaPlayer extends Player {

    @Override
    int process(int round, int players, String[] args) {
        try {
            Method method = Class.forName("Players." + getFileName().replaceFirst("\\.java", "")).getMethod("choose", int.class, int.class, String[].class);
            Object result = method.invoke(null, round, players, args);
            return (Integer) result;
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            return 1;
        } catch (ReflectiveOperationException ex) {
            return 1;
        } catch (Exception ex) {
            return 1;
        }
    }
}
