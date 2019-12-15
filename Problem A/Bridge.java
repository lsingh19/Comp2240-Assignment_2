/**
 *  FileName: Bridge
 *  Assessment: Assignment 2
 *  Course: COMP2240
 */
import java.util.concurrent.*;
import java.util.LinkedList;
import java.util.Queue;

public class Bridge
{
    private int neon;                                                 // a variable to keep track of the number of farmers that have crossed the bridge
    private Semaphore hi = new Semaphore(1, true);       // semaphore for the bridge object
    private Queue<Farmer> farmerQueue;                                // A queue variable that stores a queue of farmer that want to use the bridge

    /*
        Purpose: constructor for the Bridge class
        Pre-Condition: None
        Post-Condition: an instance of Bridge is created. The neon variable is set to 0 and the farmerQueue is initialised
     */
    public Bridge ()
    {
        neon = 0;                                    // assigning the value of 0 to neon
        farmerQueue = new LinkedList<Farmer>();      // initialising farmerQueue
    }

    /*
        Purpose: To the add a farmer to the queue for crossing the bridge
        Pre-Condition: an instance of bridge exists and a valid input is provided
        Post-Condition: the input farmer is added to the farmerQueue
     */
    public void addFarmer(Farmer farmer)
    {
        farmerQueue.add(farmer);
    }

    /*
        Purpose: To allow a farmer to cross the bridge
        Pre-Condition: an instance of Bridge exists and a valid input is provided
        Post-Condition: if the farmer is next inline to cross then it is allowed to cross the bridge
     */
    public void cross(Farmer farmer)
    {
        try {
            // acquiring the lock on the bridge
            hi.acquire();
            // if the farmer is the next farmer in line to cross the bridge then the farmer is allowed to cross the bridge
            if (farmerQueue.peek().getId().equals(farmer.getId()))
            {
                System.out.println(farmer.getId() + ": Crossing the bridge Step 5");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(farmer.getId() + ": Crossing the bridge Step 10");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(farmer.getId() + ": Across the bridge");

                neon++;     // incrementing the neon variable

                System.out.println("NEON: " + neon);
                farmerQueue.add(farmerQueue.remove());      // the farmer is put at the back of the waiting list
                farmer.setDestination();                         // setting the new destination for the farmer
            }
            hi.release();       // releasing the lock on the bridge
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
