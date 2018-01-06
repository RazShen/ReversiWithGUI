import java.awt.*;

public class GamePiece {
    private Color color;

    public boolean isEmpty() {
        return isEmpty;
    }

    private boolean isEmpty;

    GamePiece() {
        this.isEmpty = true;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.isEmpty = false;
    }
}
