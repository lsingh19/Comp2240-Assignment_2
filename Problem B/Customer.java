/**
 * File Name: Customer
 * Assessment: Assignment 2
 * Course: COMP2240
 */

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Customer implements Runnable
{
    private int arrival_time;           // a variable to store the arrival time of the customer
    private int id;                     // a variable to store the id of the customer
    private int eating_time;            // a variable to store the eating time fo the customer
    private int seats;                  // a variable to store when the customer takes a seat in the shop
    private int leaves;                 // a variable to store when the customer leaves the shop
    private Time time;                  // a variable to reference the time object
    private Ice_Cream_Shop shop;        // a variable to reference the shop object
    private Queue<Customer> order;

    /*
        Purpose: Constructor for the Customer
        Pre-Condition: valid inputs are provided
        Post-Condition: an instance of customer is created with the provided inputs
     */
    public Customer(int arrives, int number, int eat, Time watch, Ice_Cream_Shop store, Queue<Customer> order)
    {
        arrival_time = arrives;     // assigning the arrives value to arrival_time
        id = number;                // assigning the number value to id
        eating_time = eat;          // assigning the eat value to eating_time
        time = watch;               // assigning the watch value to time
        shop = store;               // assigning the store value to shop
        this.order = order;
    }

    /*
        Purpose: return the id of the customer
        Pre-Condition: an instance of customer exists
        Post-Condition: the id of the customer is returned
     */
    public int getId() {
        return id;
    }

    /*
        Purpose: return the arrival time of the customer
        Pre-Condition: an instance of customer exists
        Post-Condition: the arrival time of the customer is returned
     */
    public int getArrival_time() {
        return arrival_time;
    }

    /*
        Purpose: return the seating time of the customer
        Pre-Condition: an instance of customer exists
        Post-Condition: the seat value of the customer is returned
     */
    public int getSeats() {
        return seats;
    }

    /*
        Purpose: return the leaving time of the customer
        Pre-Condition: an instance of customer exists
        Post-Condition: the leaves value of the customer is returned
     */
    public int getLeaves() {
        return leaves;
    }

    /*
        Purpose: return the eating time of the customer
        Pre-Condition: an instance of customer exists
        Post-Condition: the eating time of the customer is returned
     */
    public int getEating_time()
    {
        return eating_time;
    }

    /*
        Purpose: set the leaving time for the customer
        Pre-Condition: an instance of customer exists and a valid input is provided
        Post-Condition: the leaves member variable of the customer is set to the input parameter
     */
    public void setLeaves(int leaves) {
        this.leaves = leaves;
    }

    /*
        Purpose: set the seating time for the customer
        Pre-Condition: an instance of customer exists and a valid input is provided
        Post-Condition: the seats member variable of the customer is set to the input parameter
     */
    public void setSeats(int seats)
    {
        this.seats = seats;
    }

    /*
        Purpose: decrease the eating time of the customer
        Pre-Condition: an instance of customer exists
        Post-Condition: the eating_time of the customer is decreased
     */
    public void cutEating_time()
    {
        eating_time--;      // decreasing the eating_time variable
    }

    /*
        Purpose: to simulate the customer getting their ice cream from the shop
        Pre-Condition: an instance of Customer and Ice_Cream_Shop exists
        Post-Condition: the customer gets their ice cream from the shop
     */
    @Override
    public void run()
    {
        while (true)
        {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			
			// check to see if its the customer's turn to get their ice cream 
            if (order.peek().getId() == id)
            {
                while (true)
                {
                    // check to see if the customer has arrived at the shop
                    if (arrival_time <= time.getTime())
                    {
                        // waiting until the shop is ready for customer to come in
                        while (shop.isFull())
                        {
                            try {
                                TimeUnit.MILLISECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        shop.eat(order.remove());     // the customer eats their ice cream
                        break;
                    }
                    // waiting if the customer hasn't arrived at the shop
                    else
                    {
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
        }
    }
}
