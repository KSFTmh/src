package koth;

import java.lang.reflect.Method;

class JavaPlayer extends Player {

    @Override
    int process(int round, int players, String[] args) {
        try {
            Method method = Class.forName("Players." + getFileName().replaceFirst("\\.java", "")).getMethod("choose", int.class, int.class, String[].class);
            Object result = method.invoke(null, round, players, args);
            return (Integer) result;
        } catch (ReflectiveOperationException ex) {
            return 1;
        }
    }
}
