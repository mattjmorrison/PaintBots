import org.junit.Test;
import paintbots.BoardSquare;
import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

public class PositionTests {
    Color color = Color.RED;

    @Test
    public void shouldGetBoardSquareInFrontOfCurrentPosition(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.rock, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };
        Position position = new Position(scan, null);

        assertEquals(TestUtils.rock, position.inFront().getSquare());
    }

    @Test
    public void shouldGetBoardSquareBehindCurrentPosition(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.rock, TestUtils.normal}
        };
        Position position = new Position(scan, null);

        assertEquals(TestUtils.rock, position.behind().getSquare());
    }

    @Test
    public void shouldGetBoardSquareToRightOfCurrentPosition(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.rock},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertEquals(TestUtils.rock, new Position(scan, null).onRight().getSquare());
    }

    @Test
    public void shouldGetBoardSquareToLeftOfCurrentPosition(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.rock, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertEquals(TestUtils.rock, new Position(scan, null).onLeft().getSquare());
    }

    @Test
    public void shouldGetBoardSquareInFrontOfCurrentPositionWithLargerGrid(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.rock, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
        };

        assertEquals(TestUtils.rock, new Position(scan, null).inFront().getSquare());
    }

    @Test
    public void shouldReturnTrueWhenSquareHasOpponentsColor(){
        assertTrue(new Position.Square(TestUtils.opponents, Color.RED).isOpponentsSquare());
    }

    @Test
    public void shouldReturnFalseWhenSquareHasMyColor(){
        assertFalse(new Position.Square(TestUtils.mine, color).isOpponentsSquare());
    }

    @Test
    public void shouldGetOpponentsColor(){
        assertEquals(Color.BLUE, new Position.Square(null, color).getOpponentsColor());
    }

    @Test
    public void shouldReturnTrueWhenSquareHasMyColor(){
        assertTrue(new Position.Square(TestUtils.mine, color).isMySquare());
    }

    @Test
    public void shouldReturnFalseWhenSquareHasOpponentsColor(){
        assertFalse(new Position.Square(TestUtils.opponents, color).isMySquare());
    }

    @Test
    public void shouldReturnTrueWhenRedRobotIsPresent(){
        assertTrue(new Position.Square(TestUtils.redRobot, color).isBlocked());
    }

    @Test
    public void shouldReturnTrueWhenBlueRobotIsPresent(){
        assertTrue(new Position.Square(TestUtils.blueRobot, color).isBlocked());
    }

    @Test
    public void shouldReturnSquaresObjectWithBorderingSquares(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.inFront, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.onLeft, TestUtils.normal, TestUtils.onRight, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.behind, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
        };

        Position.SurroundingSquares squares = new Position(scan, null).allSurrounding();
        List<BoardSquare> squareList = squares.getSquares();

        assertTrue(squareList.contains(TestUtils.inFront));
        assertTrue(squareList.contains(TestUtils.onRight));
        assertTrue(squareList.contains(TestUtils.behind));
        assertTrue(squareList.contains(TestUtils.onLeft));
    }

    @Test
    public void shouldReturnTrueWhenAllAreMyColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.mine, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.mine, TestUtils.normal, TestUtils.mine, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.mine, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
        };
        assertTrue(new Position(scan, Color.RED).allSurrounding().areMine());
    }

    @Test
    public void shouldReturnFalseWhenAllAreNotMyColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.mine, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.mine, TestUtils.normal, TestUtils.mine, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal, TestUtils.normal},
        };
        assertFalse(new Position(scan, Color.RED).allSurrounding().areMine());
    }
}
