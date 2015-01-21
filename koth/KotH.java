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
            if (scores[i] > max) {
                win = i;
                max = scores[i];
            }
        }
        System.out.println("The winner is player " + (win + 1) + ", with a score of " + max + "!");
        System.out.println();
        System.out.println(contest);
    }
}
