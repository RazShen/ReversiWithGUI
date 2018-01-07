import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Menu {
    private boolean blackStart;
    private Color blackPlayerGameColor;
    private Color whitePlayerGameColor;
    private int boardSize;

    public Menu() {
        this.parseMenuFromText();
    }
    public void parseMenuFromText() {
        try {
            File file = new File("test.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            for (int i = 0; i < 4; i++) {
                line = bufferedReader.readLine();
                if (i == 0) {
                    this.boardSize = Integer.parseInt(line);
                } else if (i == 1) {
                   if (line.contains("t")) {
                       this.blackStart = true;
                   } else {
                       this.blackStart = false;
                   }
                } else if (i == 2) {
                    this.blackPlayerGameColor = this.parseColor(line);
                } else {
                    this.whitePlayerGameColor = this.parseColor(line);
                }

            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't parse setting file, using the default settings");
        }
    }

    private Color parseColor(String s) {
        switch (s) {
            case "White":
                return Color.WHITE;
            case "Black":
                return Color.BLACK;
            case "Orange":
                return Color.ORANGE;
            case "Blue":
                return Color.BLUE;
            case "Yellow":
                return Color.YELLOW;
            case "Cyan":
                return Color.CYAN;
            case "Green":
                return Color.green;
            case "Red":
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }
}
