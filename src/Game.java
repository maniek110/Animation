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


    public Game(int w,int h,String title){
        this.w=w;
        this.h=h;
        this.title=title;
    }
    public void init(){
        board=new Board(w,h,title);
        Assety.init();
    }
    private void tick(){
        x++;
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
        g.setColor(Color.blue);
        g.fillRect(0,0,w,h);
        g.drawImage(Assety.player1,x,0,null);
        g.drawOval(100,100,300,300);
        g.drawRect(10,10,20,20);
        //_________STOP DRAWING______
        bufferStrategy.show();
        g.dispose();
    }

    public void run() {
        init();
        int fps=30;
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
            System.out.println("NOW="+now);
            System.out.println("LAST="+last);
            System.out.println("DELTA="+delta);
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
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(tick),10,10);
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
