package cn;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {

    boolean left, up, right, down;

    boolean live=true;
    boolean attack=false;
//    int attack2=0;

    public Plane(Image img, double x, double y) {
        super(img, x, y);
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed=3;
        this.width=40;
        this.height=40;
    }

    @Override
    public void drawSelf(Graphics g) {
        if (live) {
            super.drawSelf(g);
            //根据方向，计算飞机新的坐标
            if (left) {
                x -= speed;
            }
            if (right) {
                x += speed;
            }
            if (up) {
                y -= speed;
            }
            if (down) {
                y += speed;
            }
        }
    }

        //添加方向
    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case 38:
                up = true;
                break;
            case 39:
                right = true;
                break;
            case 40:
                down = true;
                break;
        }
    }
    //取消方向
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case 38:
                up = false;
                break;
            case 39:
                right = false;
                break;
            case 40:
                down = false;
                break;
        }
    }

    //添加攻击按钮
    public void addAttack(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            attack=true;
        }
    }

    public void minusAttack(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_SPACE){

        }
    }
}
