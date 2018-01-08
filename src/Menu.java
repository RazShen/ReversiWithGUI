import java.awt.*;
import java.io.*;

public class Menu {
    private boolean blackStart;
    private Color blackPlayerGameColor;
    private Color whitePlayerGameColor;
    private int boardSize;
    private static final int LINES = 4;
    private String settingsName;

    public Menu() {
        this.parseMenuFromText();
        this.settingsName = "settings.txt";
    }

    public void changeBoardSize(int size) {
        String oldStr = Integer.toString(size);
        String toWrite = Integer.toString(size);
        this.changeLineInText(oldStr, toWrite);
    }

    public void changeStartingPlayer(boolean blackStarts) {
        String oldStr;
        if (this.blackStart) {
            oldStr = "t";
        } else {
            oldStr = "f";
        }
        String toWrite;
        if (blackStarts) {
            toWrite = "t";
        } else {
            toWrite = "f";
        }
        this.changeLineInText(oldStr, toWrite);
    }
    public void changeBlackPlayerGameColor(Color color) {
        String oldStr;
        String toWrite = this.getStringFromColor(color);
        oldStr = this.getStringFromColor(this.blackPlayerGameColor);
        this.changeLineInText(oldStr, toWrite);
    }
    public void changeWhitePlayerGameColor(Color color) {
        String oldStr;
        String toWrite = this.getStringFromColor(color);
        oldStr = this.getStringFromColor(this.blackPlayerGameColor);
        this.changeLineInText(oldStr, toWrite);
    }

    public void changeLineInText(String oldString, String newString) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(this.settingsName));
            String line;
            StringBuffer inputBuffer = new StringBuffer();

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            String inputStr = inputBuffer.toString();

            file.close();

            System.out.println(inputStr); // check that it's inputted right

            // this if structure determines whether or not to replace "0" or "1"
            if (inputStr.contains(oldString)) {
                inputStr = inputStr.replace(oldString, newString);
            } else {
                System.out.println("The file doesn't contain the old setting to replace");
            }

            // check if the new input is right
            System.out.println("----------------------------------\n"  + inputStr);

            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(this.settingsName);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
    public void parseMenuFromText() {
        try {
            File file = new File(this.settingsName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            for (int i = 0; i < LINES; i++) {
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't parse setting file, using the default settings");
            // using the default values
            this.blackStart = true;
            this.boardSize = 8;
            this.blackPlayerGameColor = Color.BLACK;
            this.whitePlayerGameColor = Color.WHITE;
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
                return Color.GREEN;
            case "Red":
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }

    private String getStringFromColor(Color c) {
        if (c == Color.WHITE) {
            return "White";
        } else if (c == Color.BLACK) {
            return "Black";
        } else if (c == Color.ORANGE) {
            return "Orange";
        } else if (c == Color.BLUE) {
            return "Blue";
        } else if (c == Color.YELLOW) {
            return "Yellow";
        } else if (c == Color.GREEN) {
            return "Green";
        } else if (c == Color.RED) {
            return "Red";
        } else {
            return "Black";
        }
    }
}
