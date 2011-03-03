import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import paintbots.BoardSquare;
import paintbots.InternalBoardSquare;
import paintbots.MoveRequest;

import java.awt.*;
import java.lang.reflect.Method;

public class KyleTests {
    private Kyle kyle;
    private Color color = Color.RED;

    private MoveRequest.MoveType getMoveType(MoveRequest request){
        try {
            Method m = request.getClass().getDeclaredMethod("getMovetype");
            m.setAccessible(true);
            return (MoveRequest.MoveType)m.invoke(request);
        }
        catch (Throwable t){
            return null;
        }
    }

    @Before
    public void setUp(){
        kyle = new Kyle();
        kyle.reset(Color.RED);
    }

    @Test
    public void shouldMoveForwardWhenOpponentsColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.opponents, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertEquals(MoveRequest.MoveType.FORWARD, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveForwardWhenBlocked(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.rock, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.FORWARD, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveForwardWhenNotOpponentsColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };
        assertNotSame(MoveRequest.MoveType.FORWARD, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldRotateLeftWhenNotBlockedAndOpponentsColorForwardBlocked(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.opponents, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };
 
        assertEquals(MoveRequest.MoveType.ROTATE_LEFT, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotRotateLeftWhenBlockedAndForwardBlocked(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.wall, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_LEFT, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotRotateLeftWhenNotOpponentsColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_LEFT, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldRotateRightWhenFrontAndLeftAreBlockedAndNotBlockedAndOpponentsColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.wall, TestUtils.normal, TestUtils.opponents},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertEquals(MoveRequest.MoveType.ROTATE_RIGHT, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotRotateRightWhenFrontAndLeftAreBlockedAndBlocked(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.wall, TestUtils.normal, TestUtils.wall},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_RIGHT, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotRotateRightWhenFrontAndLeftAreBlockedAndNotOpponentsColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.wall, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_RIGHT, kyle.getLevelOneMove(new Position(scan, color)));
    }

    @Test
    public void shouldMoveFowardWhenNotOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertEquals(MoveRequest.MoveType.FORWARD, kyle.getLevelTwoMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveFowardWhenBlocked(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.FORWARD, kyle.getLevelTwoMove(new Position(scan, color)));
    }


    @Test
    public void shouldNotMoveFowardWhenOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.mine, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.FORWARD, kyle.getLevelTwoMove(new Position(scan, color)));
    }

    @Test
    public void shouldMoveLeftWhenNotOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertEquals(MoveRequest.MoveType.ROTATE_LEFT, kyle.getLevelTwoMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveLeftWhenBlocked(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.wall, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_LEFT, kyle.getLevelTwoMove(new Position(scan, color)));
    }


    @Test
    public void shouldNotMoveLeftWhenOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.mine, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_LEFT, kyle.getLevelTwoMove(new Position(scan, color)));
    }

    @Test
    public void shouldMoveRightWhenNotOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.wall, TestUtils.normal, TestUtils.normal},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertEquals(MoveRequest.MoveType.ROTATE_RIGHT, kyle.getLevelTwoMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveRightWhenBlocked(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.wall, TestUtils.normal, TestUtils.wall},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_RIGHT, kyle.getLevelTwoMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveRightWhenOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.wall, TestUtils.normal},
            {TestUtils.wall, TestUtils.normal, TestUtils.mine},
            {TestUtils.normal, TestUtils.normal, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_RIGHT, kyle.getLevelTwoMove(new Position(scan, color)));
    }

    @Test
    public void shouldMoveForwardWhenNotBlockedAndSurroundedByOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.mine, TestUtils.normal},
            {TestUtils.mine, TestUtils.normal, TestUtils.mine},
            {TestUtils.normal, TestUtils.mine, TestUtils.normal}
        };

        assertEquals(MoveRequest.MoveType.FORWARD, kyle.getLevelThreeMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveForwardWhenBlockedAndSurroundedByOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.rock, TestUtils.normal},
            {TestUtils.mine, TestUtils.normal, TestUtils.mine},
            {TestUtils.normal, TestUtils.mine, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.FORWARD, kyle.getLevelThreeMove(new Position(scan, color)));
    }

    @Test
    public void shouldMoveRightWhenNotBlockedAndSurroundedByOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.rock, TestUtils.normal},
            {TestUtils.mine, TestUtils.normal, TestUtils.mine},
            {TestUtils.normal, TestUtils.mine, TestUtils.normal}
        };

        assertEquals(MoveRequest.MoveType.ROTATE_RIGHT, kyle.getLevelThreeMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveRightWhenBlockedAndSurroundedByOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.rock, TestUtils.normal},
            {TestUtils.mine, TestUtils.normal, TestUtils.rock},
            {TestUtils.normal, TestUtils.mine, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_RIGHT, kyle.getLevelThreeMove(new Position(scan, color)));
    }

    @Test
    public void shouldMoveLeftWhenNotBlockedAndSurroundedByOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.rock, TestUtils.normal},
            {TestUtils.mine, TestUtils.normal, TestUtils.rock},
            {TestUtils.normal, TestUtils.mine, TestUtils.normal}
        };

        assertEquals(MoveRequest.MoveType.ROTATE_LEFT, kyle.getLevelThreeMove(new Position(scan, color)));
    }

    @Test
    public void shouldNotMoveLeftWhenBlockedAndSurroundedByOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {TestUtils.normal, TestUtils.rock, TestUtils.normal},
            {TestUtils.rock, TestUtils.normal, TestUtils.rock},
            {TestUtils.normal, TestUtils.mine, TestUtils.normal}
        };

        assertNotSame(MoveRequest.MoveType.ROTATE_LEFT, kyle.getLevelThreeMove(new Position(scan, color)));
    }
}
