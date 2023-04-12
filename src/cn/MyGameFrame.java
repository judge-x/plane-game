package cn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * 飞机游戏的主窗口
 * judge
 */
public class MyGameFrame extends JFrame {
    //绘制图像
    Image planeImg =GameUtils.getImage("images/plane.png");
    public  Image enmplaneImge=GameUtils.getImage("images/enmplane.png");
    Image bg =GameUtils.getImage("images/bg.jpg");
    Plane plane=null;
    //用于控制发射子弹的变量，后期肯定要改，这个太粗糙
    int num=1;

    //生成随机位置的敌机
    Random ran=new Random();
    //EnmPlane[] enmplanes=new EnmPlane[5];
    ArrayList<EnmPlane> enmplanesArr=new ArrayList<>();

    //为敌机生成相应的攻击子弹
//    Shell[] shells = new Shell[5];
    ArrayList<Shell> enmShells=new ArrayList<>();

    //生成攻击的子弹
    Shell attShell;

    //实现爆炸动画
    explode bao;

    //生成时间
    Date startTime=new Date();
    Date endTime;
    int liveTime;

    //初始化窗口
    public void launchFrame(){
        this.setTitle("judge's PlaneGame");
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGH);
        this.setLocation(500,300);
        this.setVisible(true);
        //增加一个监听事件，窗口关闭是自动关闭程序
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();  //启动重画窗口的线程
        addKeyListener(new KeyMonitor());


        //初始化50个炮弹
  /**
        for (int i=0;i<50;i++){
            shells[i]=new Shell();
        }**/

        //初始化一个飞机
        plane = new Plane(planeImg,225,600);
        //初始化五架敌机
        for (int i=0;i<5;i++){
            enmplanesArr.add(new EnmPlane(enmplaneImge,ran.nextInt(450),50));

            enmShells.add(new Shell((enmplanesArr.get(i).x)+25,(enmplanesArr.get(i).y)+50));
        }
//        if(plane.attack&&num==1){
//            Shell attShell = new Shell(plane.x+15, plane.y+5);
//        }

    }

    //定义生成攻击子弹的方法
    public void emergeatt(){
        attShell= new Shell(plane.x+15, plane.y+5);
    }

    //绘制
    public void paint(Graphics g) {

        g.drawImage(bg,0,0,null);

        //绘制所有敌机
        for (int i=0;i<enmplanesArr.size();i++){
            enmplanesArr.get(i).drawMyself(g);
        }

        //绘制自己
        plane.drawSelf(g);

//        判断攻击是否命中敌机
        if(plane.attack){
//            判断生成一颗子弹
            if(num==1){
                emergeatt();
                num--;
            }
            attShell.attDraw(g);
//            判断是否命中敌机
            for (int i=0;i<enmplanesArr.size();i++) {
                boolean attSucc = attShell.getRect().intersects(enmplanesArr.get(i).getRect());
                if (attSucc) {
                    enmplanesArr.remove(i);

                }
            }
        }



        Color c=g.getColor();
        //画出多个炮弹
        for (int i=0;i<enmShells.size();i++)
        {
            enmShells.get(i).EnmDraw(g);

            boolean penzhuang =  enmShells.get(i).getRect().intersects(plane.getRect());
            if (penzhuang){
                plane.live=false;
                if(bao==null) {
                    bao = new explode(plane.x, plane.y);
                    endTime = new Date();
                    liveTime = (int) ((endTime.getTime() - startTime.getTime()) / 1000);
                }
                bao.draw(g);
                enmShells.remove(i);
            }
        }

//        绘制字体
        if(!plane.live){
            Font f=new Font("宋体",Font.BOLD,40);
            g.setFont(f);
            g.setColor(Color.MAGENTA);
            g.drawString("生存了："+liveTime+"秒",(int)(plane.x),(int)(plane.y));
        }
        g.setColor(c);
    }

    //键盘监听实现
    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
            plane.addAttack(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
            plane.minusAttack(e);
        }
    }


    //用于反复重画窗口
    class PaintThread extends Thread {
        public void run(){
            while(true){
                repaint();
                try {

                    Thread.sleep(40); //1s = 1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    方法主体
    public static void main(String[] args) {
        MyGameFrame f=new MyGameFrame();
        f.launchFrame();
    }

}
