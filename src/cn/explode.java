package cn;
import java.awt.Graphics;
import java.awt.Image;
import java.net.*;
/**
 * 爆炸类编写
 */
public class explode {
    double x,y;
    static Image[] imgs=new Image[16];

    static {
        for(int i=0;i<=15;i++){
            imgs[i]=GameUtils.getImage("images/e"+(i+1)+".gif");
            imgs[i].getWidth(null);
        }
    }
    int count;
    public void draw(Graphics g){
        if (count<15){
            g.drawImage(imgs[count],(int)x,(int) y,null);
            count++;
        }
    }

    public explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
