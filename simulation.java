
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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
        this(agents,genes,mutation,iteration,normalSplit,draw,prefix,rounds, false);
    }
    
    /**
     * 
     * @param agents The population size
     * @param genes The number of genes (shapes) each individual has
     * @param mutation mutation rate
     * @param iteration for output purposes
     * @param normalSplit cross over split by a normal distribution ( false = uniform)
     * @param draw how often to output an image
     * @param prefix for output purposes
     * @param rounds how many generations to do
     * @param makeGrid make a 3x4 grid? (back of tshirt)
     */
    public simulation(int agents, int genes, double mutation, int iteration, boolean normalSplit, int draw, String prefix,int rounds, boolean makeGrid)
    {
        AgentCount = agents;
        GeneCount = genes;
        MutationRate = mutation;
        iter = iteration;
        normalCrossOver = normalSplit;
        this.prefix = prefix;
        drawMod = draw;
        this.rounds = rounds;
        this.makeGrid = makeGrid;
    }
    public simulation(int agents, int genes, double mutation, int iteration, boolean normalSplit)
    {
        this(agents,genes,mutation,iteration,normalSplit,100,"",2500);
    }
    int AgentCount, GeneCount, iter;
    boolean normalCrossOver;
    boolean makeGrid;
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
        
        agent agentGrid[] = null;
        int whenToDraw[] = null;
        if(makeGrid)
        {
            whenToDraw = roundsToDraw(rounds);
            agentGrid = new agent[12];
        }
        agent current[] = new agent[AgentCount];
        for(int ct=0;ct<current.length;ct++)
            current[ct]=new agent(GeneCount, MutationRate, normalCrossOver);
        for(int round=0;round<rounds+1;round++)
        {
            agent agents[] = new agent[current.length];
            
            for(int ct=0;ct<current.length;ct++)
            {
                agent parents[] = new agent[2];
                
                for(int c=0;c<2;c++) //of course, we could have > 2 parents if we wanted ;)
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
            if (makeGrid) {
                for (int ct = 0; ct < 12; ct++) {
                    if (round == whenToDraw[ct]) {
                        agentGrid[ct] = current[0];
                    }
                }
            }
        }
        
        BufferedImage output = new BufferedImage(2500,2500,BufferedImage.TYPE_BYTE_BINARY);
        current[0].draw(output.getGraphics(), 1250);
        ImageIO.write(output, "GIF", new File(directory.toString()+"/final.gif"));
        
        if(!makeGrid)
            return;
        output = new BufferedImage(2800,3600,BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = (Graphics2D) output.getGraphics();
        for(int ct=0;ct<12;ct++)
        {
            g2d.translate(100+900*(ct%3), 100+900*(ct/3));
           
        agentGrid[ct].draw(g2d, 400);
        
         g2d.setColor(Color.white);
         g2d.setFont(new Font("SansSerif",Font.PLAIN,100));
            g2d.drawString(whenToDraw[ct]+"", -50, 25);
         g2d.translate(-100-900*(ct%3), -100-900*(ct/3));
        }
        ImageIO.write(output, "GIF", new File(directory.toString()+"/grid.gif"));
        
    }
    
    public String toString()
    {
        return AgentCount+"_"+GeneCount+"_"+MutationRate+"_"+normalCrossOver+"_"+iter;
    }
    
    /**
     * Gets the 12 rounds when you should draw to the back of the shirt
     * Based on a logramithic growth
     * @param rounds
     * @return 
     */
    public static int[] roundsToDraw(int rounds)
    {
        int output[] = new int[12];
        double base = 2;
        double x = -(rounds*base - Math.pow(base,12))/(rounds+1);
        double k = 1.5 - (Math.pow(base,12) + x ) / rounds;
        k = k*-1;
      //  System.out.println(x+","+k);
        for(int ct=0;ct<11;ct++)
        {
            for(int i=1;i<=rounds;i++)
            {
                if(Math.log(k*(i-1)+x)/Math.log(base) < ct+1 && Math.log(k*i+x)/Math.log(base) >= ct+1)
                {
                    output[ct+1] = i;
                    continue;
                }
            }
          //  output[ct] = ct;
        }
        output[11] = rounds;
        output[0] = 1;
        return output;
    }
    
    public static void main(String args[]) throws Exception
        {
            //new simulation(4,1,.1,1,false).run();
            for(int val: roundsToDraw(2013))
            {
                System.out.println(val);
            }
        }
}
