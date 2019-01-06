import java.awt.image.BufferedImage;

public class SpriteSheet {
    public BufferedImage bufferedImage;

    public SpriteSheet(BufferedImage img){
        this.bufferedImage=img;
    }

    public BufferedImage getSheet(int x,int y,int w,int h) {
        return bufferedImage.getSubimage(x,y,w,h);
    }
}
