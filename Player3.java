package koth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class Player3 {

    private String command;
    private String name;
    private String author;

    int process(String[] args) throws ReflectiveOperationException, IOException {
        Process proc;
        /*  if (command.startsWith("java")) {
         String file = command.substring(5);
         Method main = Class.forName(file).getMethod("main", String[].class);
         main.invoke(null, (Object) args);
         } else {*/
        //some code from http://stackoverflow.com/a/8496537/3148067
        Runtime rt = Runtime.getRuntime();
        File dir = new File("C:/Users/Yisrael/Documents/NetBeansProjects/KotH/");
        proc = rt.exec(command + String.join(" ", args), new String[0], dir);
        //     }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player3.class.getName()).log(Level.SEVERE, null, ex);
        }
        // some code from http://codegolf.stackexchange.com/q/36515/16294
        String line = new BufferedReader(new InputStreamReader(proc.getInputStream())).readLine();
        if(line == null){
            System.out.println("line is null");
            return 1;
        }
        try {
            return Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    static Player3[] getPlayers() throws FileNotFoundException {
        List<Player3> players = new ArrayList<>();
        BufferedReader r = new BufferedReader(new FileReader("programs.txt"));
        r.lines().forEachOrdered(line -> {
            Player3 player = new Player3();
            String[] info = line.split(", ");
            player.name = info[0].trim();
            player.author = info[1].trim();
            player.command = info[2].trim();
            players.add(player);
        });
        return players.toArray(new Player3[players.size()]);
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
