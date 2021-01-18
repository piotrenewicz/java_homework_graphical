import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
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
class Belka extends Rectangle2D.Float
{
    Belka(int x)
    {
        this.x=x;
        this.y=170;
        this.width=60;
        this.height=10;
    }

    void setX(int x)
    {
        this.x=x;
    }
}
class Plansza extends JPanel implements MouseMotionListener
{
    Belka b;
    Kulka a;
    SilnikKulki s;

    Plansza()
    {
        super();
        addMouseMotionListener(this);

        b=new Belka(100);
        a=new Kulka(this,100,100,1,1);
        s=new SilnikKulki(a);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;

        g2d.fill(a);
        g2d.fill(b);
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