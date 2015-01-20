package koth;

import java.io.IOException;

/**
 * @author Yisrael Nechamkin
 */
public class KotH {

    public static void main(String[] args) throws IOException, ReflectiveOperationException {
        Contest contest = new Contest();
        int[] scores = contest.run();
        int max = 0;
        int win = -1;
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
            if (scores[i] > max) {
                win = i;
                max = scores[i];
            }
        }
        System.out.println("The winner is player " + win + ", with a score of " + max + "!");
    }

    Class<?> f() {
        return String.class;
    }

    void g(Class<String> c) {

    }

    void h() {
        int x = 5, y;
        y = -~x;
    }
}
