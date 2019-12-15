/*
    File Name: CoffeeMachine.java
    Assessment: Assignment 2
    Course: COMP2240 - Operating Systems
*/
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class CoffeeMachine {
    private Queue<Client> orders;         // a queue for incoming Client orders
    private Character typeOfCoffee;         // the type of coffee currently being served by the coffee machine
    private CoffeeMachineHead headOne;      // coffee machine head number one
    private CoffeeMachineHead headTwo;      // coffee machine head number two

    /*
        Purpose: Create an instance of CoffeeMachine
        Pre-Condition: valid inputs are provided to the function
        Post-Condition: an instance of CoffeeMachine is created and the inputs are set to their
                        respected member variables in the instance. Two heads are created for the Coffee Machine.
     */
    public CoffeeMachine(Queue<Client> waitingList, Timer clock) {
        orders = waitingList;       // assigning the incoming queue of orders to the 'orders' variable
        typeOfCoffee = null;        // setting the default coffee type to null

        // creating the coffee heads for the coffee machine
        headOne = new CoffeeMachineHead(clock, 1);
        headTwo = new CoffeeMachineHead(clock, 2);
    }

    /*
        Purpose: simulate the Client getting their coffee from the coffee machine
        Pre-Condition: an instance of CoffeeMachine exists and a valid input is provided
        Post-Condition: the Client gets their coffee from the machine.
     */
    public void useCoffeeMachine(Client person) {
        while (true)
        {
            // check to see if the current person is next in-line to use the coffee machine
            if (orders.peek().getType() == person.getType() && orders.peek().getID() == person.getID())
            {
                // the Client is the first Client to use the coffee machine
                if (typeOfCoffee == null)
                {
                    // setting the type of coffee for the machine to that of the customers coffee type
                    typeOfCoffee = person.getType();
                    // since the Client is the first to use the machine, it means that both heads are available for
                    // use. So we assign coffee head one to the Client.
                    headOne.useHead(orders.remove());
                    break;      // breaking the while loop
                }
                // the coffee machine is serving the type of coffee that the Client wants
                else if (typeOfCoffee == person.getType())
                {
                    try {
                        TimeUnit.MILLISECONDS.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // checking to see which head will be free for use first
                    if (headOne.getTimeRemaining() > headTwo.getTimeRemaining())
                    {
                        // head two will be free before head one so we assign head two to the Client
                        headTwo.useHead(orders.remove());
                        break;      // breaking the while loop
                    }
                    else
                    {
                        // head one will be free first or both the heads will be free at the same time so we assign
                        // head one to the Client.
                        headOne.useHead(orders.remove());
                        break;      // breaking the while loop
                    }
                }
                // the type of coffee being served by the machine is not the same as the Client coffee type order
                else
                {
                    // waiting the until both heads are free before we change the type of coffee being served
                    // by the machine
                    while (headOne.isBeingUsed() || headTwo.isBeingUsed())
                    {
                        try {
                            TimeUnit.MILLISECONDS.sleep(15);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // setting the type of coffee being served by the machine to the Client's coffee type
                    typeOfCoffee = person.getType();
                    // since the coffee type has been changed that means that both are free. so we assign head one
                    // to the Client
                    headOne.useHead(orders.remove());
                    break;          // breaking the while loop
                }
            }

            // it is not the Client turn to make coffee so we make the Client wait for a period of time before
            //  we check to see if its their turn.dfd
            else
            {
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
