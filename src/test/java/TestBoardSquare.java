import paintbots.BoardSquare;
import paintbots.InternalBoardSquare;
import java.awt.*;

public class TestBoardSquare implements BoardSquare {
    private InternalBoardSquare.SquareType squareType = InternalBoardSquare.SquareType.NORMAL;
    private Color color = Color.WHITE;

    public TestBoardSquare(InternalBoardSquare.SquareType squareType) {
        this.squareType = squareType;
    }

    public TestBoardSquare(Color color) {
        this.color = color;
    }

    public Color getSquareColor() {
        return color;
    }

    public boolean redRobotPresent() {
        return false;
    }

    public boolean blueRobotPresent() {
        return false;
    }

    public int robotDirection() {
        return 0;
    }

    public InternalBoardSquare.SquareType getSquareType() {
        return squareType;
    }
}
