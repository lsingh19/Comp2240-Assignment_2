/**
 * File Name: Ice_Cream_Shop
 * Assessment: Assignment 2
 * Course: COMP2240
 */
import java.util.concurrent.*;

public class Ice_Cream_Shop
{
    private static Semaphore seats;         // a semaphore for the object
    private boolean full;                   // a boolean variable that indicates whether the shop is full or not
    private int[] people = new int[4];      // an array to represent the seats in the shop
    private String[] output;                // an array to store the outputs from the algorithm
    private Time time;                       // a variable that references the Time object

    /*
        Purpose: a constructor for Ice cream shop
        Pre-Condition: a valid input is provided
        Post-Condition: an instance of Ice_Cream_shop is created with the provided inputs
     */
    public Ice_Cream_Shop(Time timer)
    {
        seats = new Semaphore(5);       // initiating the seat semaphore
        full = false;                           // setting the 'full' variable to false
        time = timer;                            // setting the 'fun' variable to the input parameter

        // setting the array values to 0 (meaning that no customer is in using the seat
        for (int i = 0; i < 4; i++)
        {
            people[i] = 0;
        }
    }

    /*
        Purpose: set the 'output' member variable
        Pre-Condition: an instance of Ice_Cream_Shop exists and a valid input is provided
        Post-Condition: the 'output' member variable value is set to the input parameter
     */
    public void setOutput(String[] output)
    {
        this.output = output;
    }

    /*
        Purpose: return the value of full
        Pre-Condition: an instance of Ice_Cream_Shop exists
        Post-Condition: a value of full is returned
     */
    public boolean isFull()
    {
        return full;
    }

    /*
        Purpose: check if the shop is full or not
        Pre-Condition: an instance of Ice_Cream_Shop exists
        Post-Condition: the value for the 'full' member variable is set depending on whether the shop is fill or not
     */
    public void checkFull()
    {
        // the shop is already full
        if (full == true)
        {
            return;
        }
        // the shop is not full
        else
        {
            int x = 0;
            for (int i = 0; i < 4 ; i ++)
            {
                if (people[i] != 0)
                {
                    x++;       // there is someone in the array
                }
            }

            // check to see if all the seats are all taken up
            if (x == 4)
            {
                full = true;    // set 'full' to true if all the seats are taken up
            }
        }
    }

    /*
        Purpose: add the customer to one of the seats in the shop
        Pre-Condition: an instance of Ice_Cream_Shop exists and a valid input is provided
        Post-Condition: the customer is assigned a seat in the shop
     */
    public void addCustomer(Customer person)
    {
        // assigns the customer a seat within the ice cream shop
        for (int i = 0; i  < 4; i ++)
        {
            if (people[i] == 0)
            {
                people[i] = person.getId();
                break;
            }
        }
    }

    /*
        Purpose: remove the customer from the shop
        Pre-Condition: an instance of Ice_Cream_Shop exists and a valid input is provided
        Post-Condition: the customer is removed from the shop and their seat is freed up
     */
    public void removeCustomer(Customer person)
    {
        // finding and removing the customer from the shop seat
        for (int i = 0; i < 4; i++)
        {
            if (people[i] == person.getId())
            {
                people[i] = 0;
            }
        }

        // if the shop is full then we check to see if all the customer has left
        if (full)
        {
            int x = 0;      // a local variable to store the number of seats taken up
            for (int i = 0; i < 4; i++)
            {
                if (people[i] != 0)
                {
                    x++;
                }
            }
            // check to see if the shop is empty
            if (x == 0)
            {
                full = false;       // the shop is empty so we set the 'full' to false
            }
        }
    }

    /*
        Purpose: to simulate the customer eating their ice cream
        Pre-Condition: an instance of Ice_Cream_Shop exists and a valid input is provided
        Post-Condition: the customer eats their ice cream and the results are stored in the 'output' variable
     */
    public void eat(Customer person)
    {
        // assigning the customer a seat
        addCustomer(person);
        // check to see if all the seats are taken up
        checkFull();
        while (true)
        {
             try {
                // acquiring lock on the ice cream shop
                seats.acquire();

                // setting the time that the customer sits down
                 int lastTime = time.getTime();
                 person.setSeats(lastTime);     // setting the time that the customer sits down
                    while (person.getEating_time() > 0)
                    {
                        // if the time has not changed since it was last checked then we wait for a period of time
                        // before re-checking
                        if (lastTime == time.getTime())
                        {
                            try {
                                TimeUnit.MICROSECONDS.sleep(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            // updating the lastTime variable
                            lastTime = time.getTime();
                            // decreasing the eating time of the customer.
                            person.cutEating_time();
                        }
                    }
                    person.setLeaves(lastTime);     // setting the time that the customer finishes eating their ice cream
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        time.decreaseOrders();			// decreasing number of orders that need to done in the time variable
        // storing the results of the customer into the 'output' array
        output[person.getId() - 1] = "C" + person.getId() + "\t\t" + person.getArrival_time() + "\t\t" + person.getSeats() + "\t\t" + person.getLeaves();
        removeCustomer(person);         // removing the customer from shop and setting their seat are free
        seats.release();                // releasing lock on the shop
    }
}
