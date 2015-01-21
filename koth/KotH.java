package koth;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Yisrael Nechamkin
 */
public class KotH {

    public static void main(String[] args) throws FileNotFoundException {
        Contest contest = new Contest();
        long startTime = System.nanoTime();
        double[] scores = contest.run();
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        double max = 0;
        int win = -1;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > max) {
                win = i;
                max = scores[i];
            }
        }
        Player[] players = contest.players();
        System.out.println("The winner is " + players[win].getName() + ", by " + players[win].getAuthor() + ", with a score of " + (int) max + "!");
        System.out.println();
        System.out.println(contest);
        System.out.println();
        System.out.println("-- The contest took " + time/1e9/Contest.NUM_ROUNDS + " seconds per round. --");
    }
}
