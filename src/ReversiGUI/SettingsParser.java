
package ReversiGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.scene.paint.Color;

public class SettingsParser {

    private static final int LINES = 4;
    String filePath = "settings.txt";
    private String Player1Color;
    private String Player2Color;
    private String startingPlayer;
    private Integer boardSize;

    public SettingsParser() {
    }

    public void parseSettingsFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                initializeDefaultValues();
                writeNewSettings(boardSize, startingPlayer, Player1Color, Player2Color);
                return;
            }

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            for (int i = 0; i < LINES; i++) {
                line = bufferedReader.readLine();
                if (i == 0)
                    this.boardSize = Integer.parseInt(line);

                if (i == 1)
                    this.startingPlayer = line;

                if (i == 2)
                    this.Player1Color = line;


                if (i == 3)
                    this.Player2Color = line;

            }

            fileReader.close();
        } catch (Exception e) {
            System.out.println("Can't parse settings from file, using the default settings");

            // using the default values
            initializeDefaultValues();
        }
    }

    public void writeNewSettings(Integer boardSize, String startingPlayer, String player1C, String player2C) {

        try {
            File file = new File(this.filePath);

            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(boardSize.toString());
            bufferedWriter.newLine();
            bufferedWriter.write(startingPlayer);
            bufferedWriter.newLine();
            bufferedWriter.write(player1C);
            bufferedWriter.newLine();
            bufferedWriter.write(player2C);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Can't write settings to file!");
        }
    }

    private void initializeDefaultValues() {
        this.boardSize = 8;
        this.startingPlayer = "player1";
        this.Player1Color = Color.BLACK.toString();
        this.Player2Color = Color.WHITE.toString();
    }


    public String getPlayer1Color() {
        return Player1Color;
    }

    public String getPlayer2Color() {
        return Player2Color;
    }

    public String getStartingPlayer() {
        return this.startingPlayer;
    }

    public Integer getBoardSize() {
        return this.boardSize;
    }
}