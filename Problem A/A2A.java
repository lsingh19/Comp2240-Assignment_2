/**
 *  FileName: A2A
 *  Assessment: Assignment 2
 *  Course: COMP2240
 */

public class A2A
{
    public static void main (String [] args)
    {
        int n = Integer.parseInt(args[0].substring(2));
        int s = Integer.parseInt(args[1].substring(2));

        int total = n+s;        // a variable to store the total number of farmer

        Farmer[] farmers = new Farmer[n+s];     // creating an array of farmers with size of the total number of Farmers
        Thread[] farmer = new Thread[n+s];      // creating an array of Threads with size of the total number of Farmers
        Bridge bridge =  new Bridge();          // creating the bridge object

        int northFarmerNumber = 1;      // a variable used to assign each north farmer with an id
        int southFarmerNumber = 1;      // a variable used to assign each north farmer with an id
        int z = 0;                      // a variable used to alternate between north and south farmer when creating the farmer objects
        for (int i = 0; i <  total; i++)
        {
            if (z == 0)
            {
                if (n > 0)
                {
                    farmers[i] = new Farmer("N_Farmer" + northFarmerNumber, "North", "South", bridge);
                    farmer[i] = new Thread(farmers[i]);
                    n--;
                    z = 1;
                    northFarmerNumber++;
                }
                else
                {
                    farmers[i] = new Farmer("S_Farmer" + southFarmerNumber, "South", "North", bridge);
                    farmer[i] = new Thread(farmers[i]);
                    s--;
                    z = 1;
                    southFarmerNumber++;
                }
            }
            else
            {
                if (s > 0)
                {
                    farmers[i] = new Farmer("S_Farmer" + southFarmerNumber, "South", "North", bridge);
                    farmer[i] = new Thread(farmers[i]);
                    s--;
                    z = 0;
                    southFarmerNumber++;
                }
                else
                {
                    farmers[i] = new Farmer("N_Farmer" + northFarmerNumber, "North", "South", bridge);
                    farmer[i] = new Thread(farmers[i]);
                    n--;
                    z = 0;
                    northFarmerNumber++;
                }
            }
        }

        // starting the farmer threads
        for (int i = 0; i < farmers.length; i++)
        {
            farmer[i].start();
        }
    }
}
