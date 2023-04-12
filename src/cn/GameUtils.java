package cn;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtils {

    private GameUtils(){

    }

    /**
     *
     *  利用路径返回图片对象
     * @return
     */
    public static Image getImage(String path){
        BufferedImage bi=null;
        try {
            URL u =GameUtils.class.getClassLoader().getResource(path);
            bi=ImageIO.read(u);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bi;
    }

}
