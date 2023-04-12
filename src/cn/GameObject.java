package cn;
import java.awt.*;

/**
 * 游戏实体的父类
 */
public class GameObject {
     Image img;
     double x,y;
     int speed;
     int width,height;

    public GameObject(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        if(img!=null){
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);
        }
    }

    public GameObject(Image img, double x, double y, int speed, int width,
                      int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject() {

    }


    public void drawSelf(Graphics g){
        g.drawImage(img,(int)x,(int)y,null);
    }

    /**
     * 返回物体的矩阵，用于实现碰撞检测
     * judge
     */
    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,width,height);
    }
}
