import java.awt.image.BufferedImage;

public class Assety {
    public static final int w=16,h=22,x=4,y=1;
    public static BufferedImage player1,player2,player3,player4;

    public static void init(){
        SpriteSheet sprite=new SpriteSheet(LoadImage.loadIMG("src/res/rpg-pack/chars/gabe/gabe-idle-run.png"));
        player1=sprite.getSheet(x,y,w,h);
        player2=sprite.getSheet(w+10,y,w,h);
        player3=sprite.getSheet(2*w+10,y,w,h);
        player4=sprite.getSheet(3*w+10,y,w,h);
    }
}
