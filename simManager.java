
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toddbodnar
 */
public class simManager {
    public static void main(String args[])
    {
        for(int iterations = 0; iterations < 5; iterations++)
        {
            //for(int genes = 250; genes < 10000; genes *= 2)
            int genes = 1500;
            {
                for(double mutation = .5; mutation > 0.001; mutation/=5){
                for(int normal = 0; normal < 2; normal++)
                {
                    for(int pop = 10; pop < 100; pop*=2)
                    {
                        queue.add(new simulation(pop,genes,mutation,iterations,normal==1));
                    }
                }}
            }
        }
        System.out.println(queue.size());
        for(int ct=0;ct<8;ct++)
            new Thread(new process()).start();
    }
    static BlockingQueue<simulation> queue = new LinkedBlockingQueue();
    
    static protected class process implements Runnable
    {

        @Override
        public void run() {
            while(!queue.isEmpty())
            {
                simulation s = queue.poll();
                if (s == null)
                {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(simManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    continue;
                }
                try {
                    s.run();
                } catch (Exception ex) {
                    Logger.getLogger(simManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Completed "+s+" "+queue.size()+" remains.");
            }
            }
        
    }
}
