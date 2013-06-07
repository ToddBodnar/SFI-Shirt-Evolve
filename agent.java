
import geom.geom;
import geom.rectangle;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toddbodnar
 */
public class agent {
    public agent(agent p1, agent p2)
    {
        this(new agent[]{p1,p2});
    }
    public agent(agent parents[])
    {
        geometry = new geom[parents[0].geometry.length];
        int cut = (int) (Math.random()*geometry.length);
        
        if(normal)
            cut = (int) (r.nextGaussian()*geometry.length/2+geometry.length/4)%geometry.length;
        
        
        for(int ct=0;ct<geometry.length;ct++)
        {
            geometry[ct] = parents[ct<cut?0:1].geometry[ct];
            if(Math.random()<mutation)
                geometry[ct] = new rectangle();
        }
        
        mutation = parents[0].mutation;
        normal = parents[0].normal;
    }
    public agent()
    {
        geometry = new geom[10000/4];
        for(int ct=0;ct<geometry.length;ct++)
            geometry[ct] = new rectangle();
    }
    
    public agent(int genes, double mutation, boolean normal)
    {
        geometry = new geom[genes];
        for(int ct=0;ct<geometry.length;ct++)
            geometry[ct] = new rectangle();
        
        this.mutation = mutation;
        this.normal = normal;
    }
    
    public double score()
    {
        BufferedImage image = new BufferedImage(127,127,BufferedImage.TYPE_3BYTE_BGR);
        draw(image.getGraphics(),64);
        long count = 0;
        for(int i=0;i<127;i++)
            for(int j=0;j<127;j++)
            {
                if((image.getRGB(i, j) == -1)==sfiLogo.getColor(i, j)) //if...white?
                    count++;
                
            //    System.out.println(image.getRGB(i, j));
            }
        
        return count/100.0/100.0;  
    }
    public void draw(Graphics g, float scale)
    {
        
        scale = scale;
        Image i = new BufferedImage((int)scale,(int)scale,BufferedImage.TYPE_BYTE_BINARY);
        Graphics g2 = i.getGraphics();
        //g2 = g;
        g2.setColor(Color.black);
        g2.fillRect(0,0,(int)scale,(int)scale);
        g2.setColor(Color.white);
        for(int ct=0;ct<geometry.length;ct++)
        {
            geometry[ct].draw(g2,(int)scale/2);
        }
        
        //g.drawImage(i, 0, 0, null);
        
        Image rotated[] = new Image[4];
        rotated[0] = i;
        for(int ct=1;ct<4;ct++)
        {
            rotated[ct]= new BufferedImage((int)scale,(int)scale,BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g3 = (Graphics2D) rotated[ct].getGraphics();
            g3.rotate(Math.toRadians(90), (int)scale/2, (int)scale/2);
            g3.drawImage(rotated[ct-1], 0, 0,null);
        }
        //g.drawImage(i.scale(-1,1), i, i1, null);
        
        g.drawImage(rotated[0], 0, 0, null);
        g.drawImage(rotated[1], (int)scale, 0, null);
        g.drawImage(rotated[2], (int)scale, (int)scale, null);
        g.drawImage(rotated[3], 00, (int)scale,null);
        
        BufferedImage circleMask = new BufferedImage((int)scale*2,(int)scale*2, BufferedImage.TYPE_4BYTE_ABGR);
        g2 = circleMask.getGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, 99999, 9999);
        ((Graphics2D)g2).setComposite(AlphaComposite.Clear);
        g2.setColor(new Color(0,0,0,0));
        g2.fillOval(0, 0, (int)scale*2, (int)scale*2);
        g.drawImage(circleMask, 0, 0, null);
    }
    
    geom geometry[];
    public double mutation = .01;
    boolean normal;
    
    public static Random r = new Random();
}
