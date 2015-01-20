package koth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Contest {

    public static final int NUM_ROUNDS = 100;

    private final Player[] players;
    private final int[][] results;
    private int round;

    public Contest() throws FileNotFoundException {
        players = Player.getPlayers();
        results = new int[NUM_ROUNDS][players.length];
    }

    public int[] run() throws IOException, ReflectiveOperationException {
        for (round = 0; round < NUM_ROUNDS; round++) {
            String[] args = args();
            for (int player = 0; player < players.length; player++) {
                int x = players[player].process(round, players.length, args);
                if (x < 1 || x > 999) {
                    x = 1;
                }
                results[round][player] = x;
            }
        }

        for (int[] r : results) {
            for (int p : r) {
                System.out.print(p);
                System.out.print(", ");
            }
            System.out.println();
        }

        System.exit(0);
        return scores();
    }

    private String[] args() {
        return IntStream.range(0, round).mapToObj(rnd -> Arrays.stream(results[rnd]).sorted().mapToObj(Integer::toString).collect(Collectors.joining(" "))).toArray(String[]::new);
    }

    private int[] scores() {
        int[] scores = new int[players.length];
        for (int thisPlayer = 0; thisPlayer < players.length; thisPlayer++) {
            double score = 0;
            for (round = 0; round < NUM_ROUNDS; round++) {
                for (int otherPlayer = 0; otherPlayer < players.length; otherPlayer++) {
                    int dif = results[round][otherPlayer] - results[round][thisPlayer];
                    score += Math.sqrt(Math.abs(dif));
                }
            }
            scores[thisPlayer] = (int) score;
        }
        return scores;
    }
}
