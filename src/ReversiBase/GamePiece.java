package ReversiBase;

import java.awt.*;

public class GamePiece {
    private Color color;
    private boolean isEmpty;

    public boolean isEmpty() {
        return isEmpty;
    }

    GamePiece() {
        this.isEmpty = true;
        this.color = Color.LIGHT_GRAY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.isEmpty = false;
    }
}
