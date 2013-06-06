/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geom;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author toddbodnar
 */
public class rectangle implements geom{

    public rectangle()
    {
        this((float)Math.random(),(float)Math.random(),(float)Math.random()/10,(float)Math.random()/10);
    }
    public rectangle(float x, float y, float w, float h)
    {
        X=x;
        Y=y;
        W=w;
        H=h;
    }
    @Override
    public void draw(Graphics g, float scale) {
        g.fillRect((int)(scale*X), (int)(scale*Y), (int)(scale*W), (int)(scale*H));
        
    }
    float X,Y,W,H;
}
