import javax.swing.*;
//import java.awt.List;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

class Kulka extends Ellipse2D.Float
{
    Plansza p;
    int dx,dy;

    Kulka(Plansza p,int x,int y,int dx,int dy)
    {
        this.x=x;
        this.y=y;
        this.width=10;
        this.height=10;

        this.p=p;
        this.dx=dx;
        this.dy=dy;
    }

    void nextKrok()
    {
        x+=dx;
        y+=dy;

        if(getMinX()<0 || getMaxX()>p.getWidth())  dx=-dx;
        if(getMinY()<0 || getMaxY()>p.getHeight()) dy=-dy;

        p.repaint();
    }
}
class SilnikKulki extends Thread
{
    Kulka a;

    SilnikKulki(Kulka a)
    {
        this.a=a;
        start();
    }

    public void run()
    {
        try
        {
            while(true)
            {
                a.nextKrok();
                sleep(15);
            }
        }
        catch(InterruptedException e){}
    }
}
class Cegielka extends Rectangle2D.Float
{
    Boolean active;

    Cegielka(int x, int y, int w, int h){
        this.x=x;
        this.y=y;
        this.active = true;
        this.width=w;
        this.height=h;
    }
}

class Belka extends Rectangle2D.Float
{
    Plansza p;

    Belka(Plansza p, int x)
    {
        this.p = p;
        this.x=x;
        this.y=0;
        this.width=60;
        this.height=10;
    }

    void setX(int x)
    {
        this.x=x;
        this.y = p.getHeight()-20;
    }
}
class Plansza extends JPanel implements MouseMotionListener
{
    Belka b;
    Kulka a;
    SilnikKulki s;
    List<Cegielka> cegly_na_planszy = new ArrayList<Cegielka>();

    Plansza()
    {
        super();
        addMouseMotionListener(this);
        b=new Belka(this, 100);
        a=new Kulka(this,100,100,1,1);
        s=new SilnikKulki(a);
    }

    void post_init(){
        populate_cegly();
    }

    void populate_cegly(){
        int hpad = 10;
        int vpad = 10;
        int brick_w = 50;
        int brick_h = 10;
        int populating_x = 0;
        int populating_y = 0;
        for(int _y = 0; _y < 5; _y++){
            populating_y += vpad;
            for(int _x =0; _x< 5; _x++){
                populating_x += hpad;
                this.cegly_na_planszy.add(new Cegielka(populating_x, populating_y, brick_w, brick_h));
                populating_x += brick_w;
            }
            populating_x = 0;
            populating_y += brick_h;
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;

        g2d.fill(a);
        g2d.fill(b);
        for(Cegielka to_draw : cegly_na_planszy){
            if(to_draw.active) g2d.fill(to_draw);
        }
    }

    public void mouseMoved(MouseEvent e)
    {
        b.setX(e.getX()-50);
        repaint();
    }

    public void mouseDragged(MouseEvent e)
    {

    }
}
public class Program
{
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                Plansza p;
                p=new Plansza();

                JFrame jf=new JFrame();
                jf.add(p);

                jf.setTitle("Test grafiki");
                jf.setSize(400,370);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setVisible(true);
            }
        });
    }
}