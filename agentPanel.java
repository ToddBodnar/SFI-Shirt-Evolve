
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toddbodnar
 */
public class agentPanel extends JPanel{
    public agentPanel(agent ag, int scale)
    {
        this.scale = scale;
        a = ag;
    }
    public void paint(Graphics g)
    {
        
        a.draw(g,scale);
    }
    agent a;
    int scale;
    
    
    public static void main(String args[])
    {
        agent one,two,three;
        one = new agent();
        two = new agent();
        three = new agent(one,two);
        
        JFrame jf = new JFrame();
        jf.add(new agentPanel(one,200));
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFrame jf1 = new JFrame();
        jf1.add(new agentPanel(two,200));
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFrame jf2 = new JFrame("child");
        jf2.add(new agentPanel(three,200));
        jf2.setVisible(true);
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        System.out.println(one.score());
        System.out.println(two.score());
        System.out.println(three.score());
    }
}
