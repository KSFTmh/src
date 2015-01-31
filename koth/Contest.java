package koth;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import static java.util.Arrays.*;
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

    public double[] run() {
        for (round = 0; round < NUM_ROUNDS; round++) {
            String[] args = args();
            for (int player = 0; player < players.length; player++) {
                int x = players[player].process(round, players.length, args);
                if (x < 0 || x > 999) {
                    x = 0;
                }
                results[round][player] = x;
            }
        }
        return scores();
    }

    private String[] args() {
        return range(0, round).mapToObj(
                rnd -> stream(results[rnd]).sorted().mapToObj(Integer::toString).collect(Collectors.joining(" "))
        ).toArray(String[]::new);
    }

    private double[] scores() {
        return range(0, players.length).mapToDouble(
                thisPlayer -> range(0, NUM_ROUNDS).mapToDouble(
                        rnd -> range(0, players.length).mapToDouble(
                                otherPlayer -> Math.sqrt(Math.abs(results[rnd][otherPlayer] - results[rnd][thisPlayer])<500?Math.abs(results[rnd][otherPlayer] - results[rnd][thisPlayer]):1000-Math.abs(results[rnd][otherPlayer] - results[rnd][thisPlayer]))
                        ).sum()
                ).sum()
        ).toArray();
    }

    @Override
    public String toString() {
        String[] outputs = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            outputs[i] = players[i].getName() + " - " + (int) scores()[i];
        }
        sort(outputs, (s, t)
             -> (int) (Double.parseDouble(t.replaceAll(".*- ", ""))
                       - Double.parseDouble(s.replaceAll(".*- ", ""))));
        return String.join("\n", outputs);
    }

    public Player[] players() {
        return copyOf(players, players.length);

    }
}
