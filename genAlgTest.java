
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toddbodnar
 */
public class genAlgTest {
    public static void main(String args[])
    {
        agent current[] = new agent[10];
        for(int ct=0;ct<current.length;ct++)
            current[ct]=new agent();
        for(int round=0;round<10000;round++)
        {
            agent agents[] = new agent[current.length];
            
            for(int ct=0;ct<current.length;ct++)
            {
                agent parents[] = new agent[2];
                
                for(int c=0;c<2;c++)
                {
                    agent one = current[(int)(Math.random()*10)];
                    
                    agent two = current[(int)(Math.random()*10)];
                    
                    if(one.score() > two.score())
                        parents[c] = one;
                    else
                        parents[c] = two;
                }
                
                agents[ct] = new agent(parents);
            }
            
            current = agents;
            
            System.out.println(round+","+current[0].score());
            JFrame jf = new JFrame(round+"");
            jf.add(new agentPanel(current[0],200));
            jf.setVisible(round%100==0);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
