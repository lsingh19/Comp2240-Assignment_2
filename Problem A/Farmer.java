/**
 *  FileName: Farmer
 *  Assessment: Assignment 2
 *  Course: COMP2240
 */
import java.util.concurrent.*;

public class Farmer implements Runnable
{
    private String destination;             // a variable to store the island that the farmer wants to get too
    private String currentLocation;         // a variable to store the current location of the farmer
    private String id;                      // a variable to store the identification of the farmer
    private Bridge bridge;                  // a variable that references the bridge object

//    Purpose: Constructor for Farmer
//    Pre-Condition: valid inputs are provided to the function
//    Post-Condition: an instance of Farmer is created with the provided inputs.
    public Farmer(String identification, String home, String dest, Bridge x)
    {
        id = identification;            // assigning the identification to the 'id' variable
        currentLocation = home;         // assigning the home to the 'currentLocation' variable
        destination = dest;             // assigning the dest to the 'destination' variable
        bridge =  x;
        System.out.println(id + ": Waiting for bridge. Going towards " + dest);

        // the farmer is added to the queue of farmers that want to use the bridge
        bridge.addFarmer(this);
    }

    /*
        Purpose: to return the id of the farmer
        Pre-Condition: an instance of the farmer exists
        Post-Condition: the id of the farmer is returned
     */
    public String getId()
    {
        return id;
    }

    /*
        Purpose: To simulate the farmer attempting the bridge constantly
        Pre-Condition: an instance of farmer and bridge exists
        Post-Condition: the farmer constantly attempts to access the bridge
     */
    public void run()
    {
        while (true)
        {
            // the farmer attempts to cross the bridge
            bridge.cross(this);
            try {
                TimeUnit.MILLISECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(id + ": Waiting for bridge. Going towards " + destination);
        }
    }

//    Purpose: sets the new destination for the farmer
//    Pre-Condition: an instance of Farmer exists
//    Post-Condition: a new destination is set for the farmer
    public void setDestination ()
    {
        // if the destination is "South" then the destination is set to "North"
        if (destination == "South")
        {
            destination = "North";
        }
        // the destination is "North then the destination is set to "South"
        else
        {
            destination = "South";
        }
    }
}
