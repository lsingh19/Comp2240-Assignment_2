/*
    File Name: CoffeeMachineHead.java
    Assessment: Assignment 2
    Course: COMP2240 - Operating Systems
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class CoffeeMachineHead
{
    private boolean beingUsed;      // a boolean to indicated whether the head is free for use or not
    private int headNumber;
    private int timeRemaining;      // the time remaining before the head is available for use again
    private Timer clock;            // a variable that references the timer
    private Queue<Client> list = new LinkedList<Client>();      // a list to keep track of Client waiting for the head to be free

    /*
        Purpose: create an instance of CoffeeMachineHead
        Pre-Condition: valid inputs are provided to the function
        Post-Condition: an instance of CoffeeMachineHead is created and the input parameter are set to their member variables
     */
    public CoffeeMachineHead(Timer clock, int headNumber)
    {
        this.clock = clock;     // assigning the clock parameter to the clock member variable
        beingUsed = false;      // setting the beingUsed variable to false
        timeRemaining = 0;      // setting the variable timeRemaining to 0
        this.headNumber = headNumber;
    }

    /*
        Purpose: check to see if the coffee machine head is being used
        Pre-Condition: an instance of CoffeeMachineHead exists
        Post-Condition: a boolean value is returned. (the value depends on whether the head is being used or not)
     */
    public boolean isBeingUsed() {
        // checking to see if the head is being used and whether there are Client waiting for the
        // head to be freed up
        if (beingUsed || list.size() > 0)
        {
            return true;
        }
            else
            {
                return false;
            }
    }

    /*
        Purpose: to return the time remaining until the head is free again
        Pre-Condition: am instance of CoffeeMachineHead exists
        Post-Condition: the time remaining until the head is free again is returned
     */
    public int getTimeRemaining()
    {
        int time = timeRemaining;
        for (int i = 0; i < list.size(); i++)
        {
            time += list.peek().getBrewTime();
        }
        return time;
    }

    /*
        Purpose: to simulate the Client getting coffee from the coffee machine head
        Pre-Condition: an instance of CoffeeMachineHead exists and a valid input is provided
        Post-Condition: the Client gets their coffee from the coffee machine head
     */
    public void useHead(Client person)
    {
        // adding the Client to the list queue
        list.add(person);
        synchronized (this) {
            // if the head is being used and the ID of the Client isn't larger the first person in the queue
                if (beingUsed || person.getID() > list.peek().getID()) {
                    try {
                        // waiting if the 'if' statement conditions are true.
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // removing the individual at the front of the queue and assigning it to person
                person = list.remove();
                beingUsed = true;           // setting the beingUsed variable to true
            timeRemaining = person.getBrewTime();       // setting the time remaining teh value of the person's brewing time
            System.out.println("(" + clock.getClock() + ") " + person.getType() + person.getID()+ " uses dispenser " + headNumber + " (time: " + timeRemaining + ")");
                // creating a local variable to store the current time
                int previousTime = clock.getClock();
                // the while loop will keep executing until the time remaining is greater than 0
                while (timeRemaining > 0) {
                    // if the time has not changed then we wait for a period of time before re-checking
                    if (previousTime == clock.getClock()) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        timeRemaining--;       // decrementing the time remaining variable
                        previousTime = clock.getClock();        // setting the previousTime variable the value of the current time
                    }
                }
                beingUsed = false;      // setting the beingUsed variable to false
                clock.increaseCompletedOrders();        // increasing the completed order in clock variable
                notify();       // notifying waiting threads that they can execute.
            }
    }
}

