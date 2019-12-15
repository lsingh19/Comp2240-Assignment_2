/**
    File Name: A2C.java
    Assessment: Assignment 2
    Course: COMP2240 - Operating Systems
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class A2C
{
    public static void main(String[] args)
    {
        String file = args[0];
        Queue<Client> waitingList = new LinkedList<Client>();       // a queue of clients that want to use the coffee machine
        Timer clock = new Timer();                    // a timer variable that references a newly created timer object
        Thread startClock = new Thread(clock);      // a thread for the timer object

        // creating the coffee shop.
        CoffeeMachine shop = new CoffeeMachine(waitingList, clock);
        Thread execute;         // a thread used to start the newly created clients
        Client temp;            // a temporary client variable that is used to create clients objects

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            int totalNumberOfOrders = Integer.parseInt(currentLine);
            clock.setNumOfOrders(totalNumberOfOrders);

            currentLine = reader.readLine();
            while (currentLine != null)
            {
                String[] result = currentLine.split(" ", 2);
                Character id = result[0].charAt(0);
                result[0] = result[0].replace(id, ' ');
                int number = Integer.parseInt(result[0].replaceAll("\\s+", ""));

                result[1] = result[1].replaceAll("\\s+", "");
                int brewTime = Integer.parseInt(result[1]);

//                new Client(type[i], person[i], brewTime[i], shop);
                temp = new Client(id, number, brewTime, shop);
                waitingList.add(temp);
                execute = new Thread(temp);
                execute.start();
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // waiting for a period of time before commencing the Time
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startClock.start();     // starting the clock thread
    }
}
