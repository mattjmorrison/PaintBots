import paintbots.BoardSquare;
import paintbots.InternalBoardSquare;
import java.awt.*;

public class TestBoardSquare implements BoardSquare {
    private InternalBoardSquare.SquareType squareType = InternalBoardSquare.SquareType.NORMAL;
    private Color color = Color.WHITE;
    private boolean redRobotPresent;
    private boolean blueRobotPresent;

    public TestBoardSquare(InternalBoardSquare.SquareType squareType) {
        this.squareType = squareType;
    }

    public TestBoardSquare(Color color) {
        this.color = color;
    }

    public TestBoardSquare(boolean redRobotPresent, boolean blueRobotPresent){
        this.redRobotPresent = redRobotPresent;
        this.blueRobotPresent = blueRobotPresent;
    }

    public Color getSquareColor() {
        return color;
    }

    public boolean redRobotPresent() {
        return redRobotPresent;
    }

    public boolean blueRobotPresent() {
        return blueRobotPresent;
    }

    public int robotDirection() {
        return 0;
    }

    public InternalBoardSquare.SquareType getSquareType() {
        return squareType;
    }
}
