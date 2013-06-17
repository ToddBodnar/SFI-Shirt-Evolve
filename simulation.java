
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toddbodnar
 */
public class simulation {
    public simulation(int agents, int genes, double mutation, int iteration, boolean normalSplit, int draw, String prefix,int rounds)
    {
        AgentCount = agents;
        GeneCount = genes;
        MutationRate = mutation;
        iter = iteration;
        normalCrossOver = normalSplit;
        this.prefix = prefix;
        drawMod = draw;
        this.rounds = rounds;
    }
    public simulation(int agents, int genes, double mutation, int iteration, boolean normalSplit)
    {
        this(agents,genes,mutation,iteration,normalSplit,100,"",2500);
    }
    int AgentCount, GeneCount, iter;
    boolean normalCrossOver;
    double MutationRate;
    String prefix;
    int drawMod;
    int rounds = 2500;
    public void run() throws Exception
    {
        File directory = new File("./results/"+prefix+AgentCount+"_"+GeneCount+"_"+MutationRate+"_"+normalCrossOver+"_"+iter+"/");
        
        directory.mkdirs();
        
        PrintStream out;
        
        out = new PrintStream(directory.getAbsoluteFile()+"/graph.csv");
        agent current[] = new agent[AgentCount];
        for(int ct=0;ct<current.length;ct++)
            current[ct]=new agent(GeneCount, MutationRate, normalCrossOver);
        for(int round=0;round<rounds+1;round++)
        {
            agent agents[] = new agent[current.length];
            
            for(int ct=0;ct<current.length;ct++)
            {
                agent parents[] = new agent[2];
                
                for(int c=0;c<2;c++)
                {
                    agent one = current[(int)(Math.random()*AgentCount)];
                    
                    agent two = current[(int)(Math.random()*AgentCount)];
                    
                    if(one.score() > two.score())
                        parents[c] = one;
                    else
                        parents[c] = two;
                }
                
                agents[ct] = new agent(parents);
            }
            
            current = agents;
            
            out.println(round+","+current[0].score());
           /* JFrame jf = new JFrame(round+"");
            jf.add(new agentPanel(current[0],200));
            jf.setVisible(round%100==0);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
            
            if(round%drawMod==0)
            {
                BufferedImage output = new BufferedImage(2500,2500,BufferedImage.TYPE_BYTE_BINARY);
                current[0].draw(output.getGraphics(), 1250);
                ImageIO.write(output, "GIF", new File(directory.toString()+"/"+round+".gif"));
            }
        }
        
        BufferedImage output = new BufferedImage(2500,2500,BufferedImage.TYPE_BYTE_BINARY);
        current[0].draw(output.getGraphics(), 1250);
        ImageIO.write(output, "GIF", new File(directory.toString()+"/final.gif"));
        
    }
    
    public String toString()
    {
        return AgentCount+"_"+GeneCount+"_"+MutationRate+"_"+normalCrossOver+"_"+iter;
    }
    
    
    public static void main(String args[]) throws Exception
        {
            new simulation(4,1,.1,1,false).run();
        }
}
