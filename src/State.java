import java.awt.*;

public abstract class State {

    public static State currState=null;

    public static void setState(State state){

    }

    public static State getCurrState() {
        return currState;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
