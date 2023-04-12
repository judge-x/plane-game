package cn;

import java.awt.*;

public class EnmPlane extends GameObject{
    boolean live;

    public EnmPlane(Image img, double x, double y) {
        super(img, x, y);
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed=3;
        this.width=40;
        this.height=40;
    }
    public void drawMyself(Graphics g){
        g.drawImage(img, (int) x, (int) y, null);
        this.y++;
    }

}
