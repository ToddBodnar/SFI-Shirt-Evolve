


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toddbodnar
 */
public class finalRender extends simManager{
    public static void main(String args[])
    {
        for(int iterations = 0; iterations < 5; iterations++)
        {
            
                        queue.add(new simulation(80,500,.5,iterations,true,1,"final_",10000));
                        queue.add(new simulation(80,250,.004,iterations,false,1,"final_",10000));
                        queue.add(new simulation(80,250,.1,iterations,true,1,"final_",10000));
                  
                        queue.add(new simulation(80,250,.5,iterations,true,1,"final_",10000));
                        queue.add(new simulation(80,1000,.02,iterations,false,1,"final_",10000));
                        queue.add(new simulation(80,1000,.1,iterations,true,1,"final_",10000));
                        
                        queue.add(new simulation(160,500,.5,iterations,true,1,"final_",10000));
                        queue.add(new simulation(160,250,.004,iterations,false,1,"final_",10000));
                        queue.add(new simulation(160,250,.1,iterations,true,1,"final_",10000));
                        
                        queue.add(new simulation(160,250,.5,iterations,true,1,"final_",10000));
                        queue.add(new simulation(160,1000,.02,iterations,false,1,"final_",10000));
                        queue.add(new simulation(160,1000,.1,iterations,true,1,"final_",10000));

        }
        System.out.println(queue.size());
        for(int ct=0;ct<8;ct++)
            new Thread(new simManager.process()).start();
    }
    
}
