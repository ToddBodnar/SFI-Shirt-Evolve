


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toddbodnar
 */
public class finalFinalRender extends simManager{
    public static void main(String args[])
    {
        for(int iterations = 0; iterations < 5; iterations++)
        {
            for(int pop: new int[]{20,40,80,160})
            {
                for(int genes: new int[]{250,500,1000})
                {
                    for(double mu: new double[]{.5,.1,.02,.004})
                    {
                        queue.add(new simulation(pop,genes,mu,iterations,true,100000,"final_render_",2013,true));
                       
                    }
                }
            }
                        

        }
        System.out.println(queue.size());
        for(int ct=0;ct<8;ct++)
            new Thread(new simManager.process()).start();
    }
    
}
