import com.sun.deploy.ui.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class LoadImage {

    public static BufferedImage loadIMG(String path){
        try{
            return ImageIO.read(new FileInputStream(path));
        }
        catch (Exception e){
            System.out.println(e);
            System.exit(1);
        }
        return null;
    }
}
