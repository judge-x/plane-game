package cn;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Shell extends GameObject{
    //定义为弧度
    double degree;

    public Shell(double x,double y) {
        super();
        this.x=x;
        this.y=y;
        width=10;
        height=10;
        speed=2;

        degree=Math.random()*2*Math.PI;
    }

    public void draw(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.GREEN);
        g.fillOval((int)x,(int)y,width,height);

        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);

        if(x<5||x>Constant.GAME_WIDTH-10){
            degree=Math.PI-degree;
        }
        if(y<30||y>Constant.GAME_HEIGH-10){
            degree=2*Math.PI-degree;
        }
        g.setColor(c);
    }

    public void EnmDraw(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.red);
        g.fillOval((int)x,(int)y,width,height);
        this.y+=speed;
    }

    public void attDraw(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.yellow);
        g.fillOval((int)x,(int)y,width,height);
        this.y-=speed;
    }
}
