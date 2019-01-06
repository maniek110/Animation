import javax.swing.*;
import java.awt.*;

public class Board {
    private JFrame jFrame;
    private int w,h;
    private String title;
    private Canvas canvas;

    public Board(int w,int h,String title){
        this.title=title;
        this.w=w;
        this.h=h;
        makeBoard();
    }
    private void makeBoard(){
        jFrame=new JFrame(title);
        jFrame.setSize(w,h);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        //_______________________CANVAS_________________________
        canvas=new Canvas();
        canvas.setMinimumSize(new Dimension(w,h));
        canvas.setMaximumSize(new Dimension(w,h));
        canvas.setPreferredSize(new Dimension(w,h));
        jFrame.add(canvas);
        jFrame.pack();
    }
    public Canvas getCanvas(){
        return canvas;
    }
}
