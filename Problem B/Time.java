/**
 * File Name: Time
 * Assessment: Assignment 2
 * Course: COMP2240
 */

import java.util.concurrent.*;
public class Time implements Runnable
{
    private int time;           // a variable to store the time for the program
    private int numberOfOrders;

    /*
        Purpose: a constructor for the Time class
        Pre-Condition: nil
        Post-Condition: an instance of Time is created and the time variable is set to 0
     */
    public Time()
    {
        time = 0;
    }

    /*
        Purpose: return the value of the 'time'
        Pre-Condition: an instance of Time exists
        Post-Condition: the value of the 'time' is returned
     */
    public int getTime()
    {
        return time;
    }

    /*
        Purpose: set the number of orders that need to be fulfilled
        Pre-Condition: an instance of Time exists and a valid input is provided
        Post-Condition: the value of numberOfOrders is set to the input parameter
     */
    public void setNumberOfOrders(int numberOfOrders)
    {
        this.numberOfOrders = numberOfOrders;
    }

    /*
        Purpose: decrease the value of 'numberOfOrders'
        Pre-Condition: an instance of Time exists
        Post-Condition: the value of 'numberOfOrders' is decreased
     */
    public void decreaseOrders()
    {
        numberOfOrders--;
    }

    /*
        Purpose: to simulate the time going by in the program
        Pre-Condition: an instance of Time exists
        Post-Condition: the time is stopped when all orders are completed
     */
    public void run()
    {
        // keep increasing the time variable until all orders are complete
        while (numberOfOrders != 0)
        {
            // Waiting for 1 second before updating the time variable
            try
            {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {}
            time++;       // incrementing the time variable
        }

    }
}
