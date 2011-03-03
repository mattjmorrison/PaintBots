import paintbots.BoardSquare;
import paintbots.InternalBoardSquare;
import java.awt.*;

public class TestUtils {
    public static BoardSquare normal = new TestBoardSquare(InternalBoardSquare.SquareType.NORMAL);
    public static BoardSquare rock = new TestBoardSquare(InternalBoardSquare.SquareType.ROCK);
    public static BoardSquare wall = new TestBoardSquare(InternalBoardSquare.SquareType.WALL);
    public static BoardSquare fogRock = new TestBoardSquare(InternalBoardSquare.SquareType.FOGROCK);
    public static BoardSquare opponents = new TestBoardSquare(Color.BLUE);
    public static BoardSquare mine = new TestBoardSquare(Color.RED);

    public static BoardSquare inFront = new TestBoardSquare(Color.RED);
    public static BoardSquare onRight = new TestBoardSquare(Color.RED);
    public static BoardSquare onLeft = new TestBoardSquare(Color.RED);
    public static BoardSquare behind = new TestBoardSquare(Color.RED);

    public static BoardSquare redRobot = new TestBoardSquare(true, false);
    public static BoardSquare blueRobot = new TestBoardSquare(false, true);

}
