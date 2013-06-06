
import geom.geom;
import geom.rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toddbodnar
 */
public class agent {
    public agent(agent parents[])
    {
        geometry = new geom[parents[0].geometry.length];
        int cut = (int) (Math.random()*geometry.length);
        for(int ct=0;ct<geometry.length;ct++)
        {
            geometry[ct] = parents[ct<cut?0:1].geometry[ct];
            if(Math.random()<mutation)
                geometry[ct] = new rectangle();
        }
    }
    public agent()
    {
        geometry = new geom[500];
        for(int ct=0;ct<geometry.length;ct++)
            geometry[ct] = new rectangle();
    }
    public void draw(Graphics g, float scale)
    {
        Image i = new BufferedImage((int)scale,(int)scale,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g2 = i.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0,0,1,1);
        g2.setColor(Color.black);
        for(int ct=0;ct<geometry.length;ct++)
        {
            geometry[ct].draw(g2,(int)scale);
        }
        
        g.drawImage(i, 0, 0, null);
        
        Image rotated[] = new Image[4];
        rotated[0] = i;
        for(int ct=1;ct<4;ct++)
        {
            rotated[ct]= new BufferedImage((int)scale,(int)scale,BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g3 = (Graphics2D) rotated[ct].getGraphics();
            g3.rotate(Math.toRadians(90), (int)scale/2, (int)scale/2);
            g3.drawImage(rotated[ct-1], 0, 0,null);
        }
        //g.drawImage(i.scale(-1,1), i, i1, null);
        g.drawImage(rotated[0], 0, 0, null);
        g.drawImage(rotated[1], (int)scale, 0, null);
        g.drawImage(rotated[2], (int)scale, (int)scale, null);
        g.drawImage(rotated[3], 00, (int)scale,null);
    }
    
    geom geometry[];
    public static double mutation = .01;
}
