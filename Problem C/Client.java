/*
    File Name: Client.java
    Assessment: Assignment 2
    Course: COMP2240 - Operating Systems
*/
public class Client implements Runnable
{
    private Character type;     // the type of coffee the Client wants
    private int ID;             // the ID of the Client
    private int brewTime;       // the brew time of the Client
    private CoffeeMachine machine;  // a variable to reference the CoffeeMachine instance

    /*
        Purpose: Create an instance of Client
        Pre-Condition: valid inputs are provided
        Post-Condition: an instance of Client is created and the inputs are set to their respected member variables in the instance
     */
    public Client(Character type, int ID, int brewTime, CoffeeMachine shop)
    {
        this.type = type;           // setting the member variable 'type' to the input parameter type
        this.ID = ID;               // setting the member variable 'ID' to the input parameter ID
        this.brewTime = brewTime;   // setting the member variable 'brewTime' to the input parameter brewTime
        machine = shop;             // assigning the 'machine' member variable to the 'shop' input parameter
    }

    /*
        Purpose: return the type of coffee the Client wants
        Pre-Condition: an instance of Client exists
        Post-Condition: the coffee type of the Client is returned
     */
    public Character getType() {
        return type;
    }

    /*
        Purpose: Return the ID of the Client
        Pre-Condition: an instance of Client exists
        Post-Condition: the ID of the Client is returned
     */
    public int getID() {
        return ID;
    }

    /*
        Purpose: Return the brew Time of the Client
        Pre-Condition: an instance of Client exists
        Post-Condition: the brew time of the Client is returned
     */
    public int getBrewTime() {
        return brewTime;
    }

    /*
        Purpose: Simulate the Client getting their coffee
        Pre-Condition: an instance of Client exists, the member variable 'machine' references
                       an instance of CoffeeMachine
        Post-Condition: the Client has received their coffee from the coffee machine
     */
    @Override
    public void run()
    {
        machine.useCoffeeMachine(this);
    }
}