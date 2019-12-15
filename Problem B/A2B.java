/**
 * File Name: A2B
 * Assessment: Assignment 2
 * Course: COMP2240
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class A2B
{
    public static void main(String[] args) {
        String file = args[0];
        Queue<Customer> order = new LinkedList<Customer>();     // a queue of customer that will come to the shop based on their arrival time
        Time time = new Time();      // a variable to reference the newly created Time object
        Thread timer = new Thread(time);
        String[] output;            // an array to store the outputs from the program
        Ice_Cream_Shop shop = new Ice_Cream_Shop(time);      // a variable to reference the newly created Ice_Cream_Shop object
        Customer temp;              // a temporary variable used store newly created customer objects
        Thread execute;             // a thread variable used to start created customer
        int n = 0;                  // a variable to store the total number of customer
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            int arrivalTime = 0;        // a variable to store the arrival time read from the file
            int number = 0;             // a variable to store the customer id read from the file
            int eatingTime;             // a variable to store the eating time read from the file
            while (!currentLine.equals("END"))
            {
                String[] result = currentLine.split(" ", 3);
                arrivalTime = Integer.parseInt(result[0]);
                result[1] = result[1].replace('C', ' ');
                number = Integer.parseInt(result[1].replaceAll("\\s+", ""));
                eatingTime = Integer.parseInt(result[2].replaceAll("\\s+", ""));;
                temp = new Customer (arrivalTime, number, eatingTime, time, shop, order);           // creating the customer with the inputs from the file
                order.add(temp);
                execute = new Thread(temp);         // creating a new thread with the newly created customer
                execute.start();                    // starting the customer thread
                n++;                // incrementing the customer thread
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        output = new String[n];         // setting the size of the output array with the total number of customers
        shop.setOutput(output);         // setting the output array inside the shop object

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        time.setNumberOfOrders(n);
        timer.start();         // starting the time thread

        // printing out the results from the program
        System.out.println("Customer\tArrives\t\tSeats\t\tLeaves");
        for (int i=0; i < n; i++)
        {
            // check to see if the output for the customer has been produced
            while (output[i] == null)
            {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(output[i]);      // printing the output
        }

    }
}

