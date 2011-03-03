import paintbots.*;
import paintbots.PaintbotControl;
import paintbots.PaintbotSimulation;

public class Run {
    public static void main(String[] args){
        PaintbotControl robot1 = new Kyle();
        PaintbotControl robot2 = new CS207Paintbot1();
        PaintbotSimulation s = new PaintbotSimulation( robot1, robot2 );
        s.simulateGame( true );
    }
}
