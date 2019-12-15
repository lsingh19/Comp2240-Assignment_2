/*
    File Name: Timer.java
    Assessment: Assignment 2
    Course: COMP2240 - Operating Systems
*/
import java.util.concurrent.TimeUnit;

public class Timer implements Runnable
{
    private int clock;              // a variable to store the increase in time
    private int numOfOrders;        // the number of order that need to be completed
    private int completedOrders;    // the number of completed orders

//    Purpose: constructor of the Timer class
//    Pre-Condition: valid input is provided to the function
//    Post-Condition: an instance of Timer is created
    public Timer()
    {
        completedOrders = 0;
    }

    public void setNumOfOrders(int numOfOrders)
    {
        this.numOfOrders = numOfOrders;
    }

    //    Purpose: return the time
//    Pre-Condition: an instance of Timer exists
//    Post-Condition: the time is returned
    public int getClock()
    {
        return clock;
    }

//    Purpose: increase the number of order completed
//    Pre-Condition: an instance of Timer exists
//    Post-Condition: the number of orders is incremented by 1
    public synchronized void increaseCompletedOrders()
    {
        completedOrders++;
    }

//    Purpose: simulate the time
//    Pre-condition: an instance of Timer exists
//    Post-Condition: the time is increased until all orders are completed.
    @Override
    public void run()
    {
//        a while loop to keep executing until all orders are done
        while (completedOrders < numOfOrders)
        {
            clock++;        // incrementing clock variable
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // outputting the total time taken to complete all teh orders
        System.out.println("(" + clock + ") DONE" );
    }
}
