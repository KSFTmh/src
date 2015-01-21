package koth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import static java.util.stream.IntStream.*;

class Contest {

    public static final int NUM_ROUNDS = 100;

    private final Player[] players;
    private final int[][] results;
    private int round;

    public Contest() throws FileNotFoundException {
        players = Player.getPlayers();
        results = new int[NUM_ROUNDS][players.length];
    }

    public double[] run() throws IOException {
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
        return scores();
    }

    private String[] args() {
        return range(0, round).mapToObj(rnd -> Arrays.stream(results[rnd]).sorted().mapToObj(Integer::toString).collect(Collectors.joining(" "))).toArray(String[]::new);
    }

    private double[] scores() {
        if (2 > 1) {
            return range(0, players.length).mapToDouble(thisPlayer -> range(0, NUM_ROUNDS).mapToDouble(rnd -> range(0, players.length).mapToDouble(otherPlayer -> Math.sqrt(Math.abs(results[rnd][otherPlayer] - results[rnd][thisPlayer]))).sum()).sum()).toArray();
        }

        double[] scores = new double[players.length];
        for (int thisPlayer = 0; thisPlayer < players.length; thisPlayer++) {
            double score = 0;
            for (int rnd = 0; rnd < NUM_ROUNDS; rnd++) {
                for (int otherPlayer = 0; otherPlayer < players.length; otherPlayer++) {
                    int dif = results[rnd][otherPlayer] - results[rnd][thisPlayer];
                    score += Math.sqrt(Math.abs(dif));
                }
            }
            scores[thisPlayer] = (int) score;
        }
        return scores;
    }

    @Override
    public String toString() {
        String[] outputs = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            outputs[i] = players[i].getName() + " - " + (int) scores()[i];
        }
        Arrays.sort(outputs, (s, t)
                    -> (int) (Double.parseDouble(t.replaceAll(".*- ", ""))
                              - Double.parseDouble(s.replaceAll(".*- ", ""))));
        return String.join("\n", outputs);
    }

    public Player[] players() {
        return Arrays.copyOf(players, players.length);

    }
}
