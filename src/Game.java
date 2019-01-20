import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{
    private Board board;
    int w,h;
    String title;
    private Thread thread;
    private boolean running=false;
    private BufferStrategy bufferStrategy;
    private Graphics g;
    int x=0;

    private State GameState;

    public Game(int w,int h,String title){
        this.w=w;
        this.h=h;
        this.title=title;
    }
    public void init(){
        board=new Board(w,h,title);
        Assety.init();
        GameState=new GmaeState();
        State.setState(GameState);
    }
    private void tick(){
        if(State.getCurrState()!=null)
            State.getCurrState().tick();
    }
    private void render(){
        bufferStrategy=board.getCanvas().getBufferStrategy();
        if(bufferStrategy==null){
            board.getCanvas().createBufferStrategy(3);
            return;
        }
        g=bufferStrategy.getDrawGraphics();
        //___________CLEAR___________
        g.clearRect(0,0,w,h);
        //_________DRAWING___________
        if(State.getCurrState()!=null)
            State.getCurrState().render(g);
        //_________STOP DRAWING______
        bufferStrategy.show();
        g.dispose();
    }

    public void run() {
        init();
        int fps=60;
        double timePerTick=1000000000/fps;
        double delta=0;
        long now;
        long last=System.nanoTime();
        long timer=0;
        int tick=0;
        while (running){
            now=System.nanoTime();
            delta+=(now-last)/timePerTick;
            timer+=now-last;
            last=now;
            if(delta>=1) {
                tick();
                render();
                tick++;
                delta--;
            }
            if(timer>=1000000000){
                System.out.println("fps="+tick);
                timer=0;
                tick=0;
            }
        }
        stop();
    }
    public synchronized void start(){
        if (running)
            return;
        running=true;
        thread=new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        if(!running)
            return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
