
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
        JFrame jf = new JFrame();
        jf.add(new agentPanel(new agent(),400));
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
