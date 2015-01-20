package koth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private String name;
    private String author;
    private String language;
    private String fileName;

    abstract int process(int round, int players, String[] args);

    static Player[] getPlayers() throws FileNotFoundException {
        List<Player> players = new ArrayList<>();
        BufferedReader r = new BufferedReader(new FileReader("programs.txt"));
        r.lines().forEachOrdered(line -> {
            String[] info = line.split(", ");
            String type = info[2].trim().replaceFirst(".*\\.", "");
            Player player;
            switch (type) {
                case "java":
                    player = new JavaPlayer();
                    break;
                case "py":
                    player = new PythonPlayer();
                    break;
                case "rb":
                    player = new RubyPlayer();
                    break;
                default:
                    throw new RuntimeException("Invalid language: " + type);
            }
            player.name = info[0].trim();
            player.author = info[1].trim();
            player.fileName = info[2].trim();
            players.add(player);
        });
        return players.toArray(new Player[players.size()]);
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public String getFileName() {
        return fileName;
    }
}
