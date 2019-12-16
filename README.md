# Comp2240-Assignment 2
**_Mark_**: 100 / 100 

## Comments by Marker
**Marks**:       
I/O (valid input and output) : 5/5   
Code Clarity (code structure, code comments) : 10/10    
 
**_Problems_**:     
A: Sharing the Bridge : 30/30   
B: Ice-cream time : 25/25   
C: Hot or Iced Coffee : 20/20   
 
Report: 10/10 
 
**_Deductions_**:    
Late (-10 per day late) : /0   
Coversheet (-5 if missing) : /0   
Other (see Feedback) : /0   
 
Total: 100/100 
 
**_Notes_**:    
A : Good.    
B : Execution is quite slow - are you doing 1 time unit = 1 sec? Results are correct though.   
C : Once again, rather slow - but results are correct.   
Report : OK.    
Code : Good.     
I/O : As Expected. 

## Assignment Specifications
### Problem A : Sharing the Bridge
A new single lane bridge is constructed to connect the North Island of New Zealand to the South Island of New Zealand. Farmers from each island use the bridge to deliver produce to the other island, return back to their island and this is repeated indefinitely. It takes a farmer carrying produce 15 steps to cross the bridge. Once a farmer (say a North Island farmer identified as N_Farmer1) crosses the bridge (from North Island to South Island) he just attempts to cross the bridge in the opposite direction (from South Island to North Island) and so on. Note that the ID of the farmer does NOT change. 

The bridge can become deadlocked if a northbound and a southbound farmer are on the bridge at the same time (New Zealand farmers are stubborn and will not back up). The bridge has a large neon sign above it indicating the number of farmers that have crossed it in either direction. The neon sign counts multiple crossing by the same farmer.

Using **semaphores**, design and implement an algorithm that prevents deadlock. Use **threads** to simulate multiple/**concurrent** farmers and assume that the stream of farmers are constantly attempting to use the bridge from either direction. Your program should input parameters at runtime to initialise the number of farmers from each direction. For example [N=5, S=5] would indicate a constant stream of 5 farmers from each direction wanting to use the bridge. You also make sure that the solution is starvation-free (the situation in which northbound farmers prevent southbound farmers from using the bridge, or vice versa should not occur). 

### Problem B : Ice-cream time
A new ice-cream parlour has been opened at Student Hub. The parlour does not offer take away service but there is only four seats in the parlour to eat in. A customer may arrive to the parlour at any time and may take a certain time to finish his ice-cream. The manager chooses a peculiar rule to serve his customers. If a customer arrives when there is an empty seat, then the customer can immediately take a seat. However, if all the four seats are occupied (i.e. there are four customers enjoying their ice-creams at any instant) then all the arriving customers have to wait for the entire party (all current customers) to leave before they can get their seats.

Using **semaphores** design and implement an algorithm that manages the customers entering and leaving the ice-cream parlour in line with manager’s rules. Use threads to simulate multiple/**concurrent** customers. Your solution must be fair - starvation free. Assume no time is wasted in taking seat, serving/starting eating ice-cream and leaving the parlour. 

The inputs will be as follows:  
0 C1 4  
Where each line, except the last, contains information regarding a customer     
Arrival-time Customer-ID Ice-cream-eating-time  
Input ends with a line containing the word END. You can assume that in the input the customers will be sorted in order of their arrival times.  
Output shows information of each customer in a separate line. Each line contains Customer-ID, Arrival-time, time when the customer seats in the icecream parlour and time when the customer leaves the parlour. 

### Problem C : Hot or Iced Coffee ?? 
School of EEC, the University bought a coffee machine that can serve both iced and hot coffee for the staffs. The coffee machine has TWO dispensing heads which can serve TWO clients (staffs) in parallel. We call a staff Hot Client (H) if (s)he is looking for hot coffee and a Cold Client (C) if (s)he is looking for cold coffee. However, the machine can operate in either of its two modes (hot or cold drink) at a time. If a Hot Client has started to make coffee in the machine then the other vacant disperser can serve hot coffee only – a Cold Client must wait.  A client (Hot or Cold) can choose the brew strength by choosing the brew time at the beginning. So the assumptions in operating the coffee machine are 
- Hot and Cold clients cannot use the machine at the same time.   
- No more than TWO clients can use the machine simultaneously.   
- Each client can choose different time to brew his coffee.     
 
Using **monitor**, design and implement an algorithm that ensures the operation of the coffee machine according to the above characteristics. Use threads to simulate multiple/concurrent clients. Your solution should be fair – stream of Hot Clients should not prevent a Cold Client from using the coffee machine or vice versa. A Hot Client with ID y (i.e. Hy) should not be served before a HOT Client with ID x (i.e. Hx) where x < y. And the same for the cold clients.  The input will be as follows:      
- 9     
- H1 5     
- H2 2

Where the first line contains the number of clients looking for coffee and each line contains information about each client of the form   
Client-ID   Brew-Time    
Client-ID: The first character is H or C indicating hot (H) or cold (C) coffee client, a number (without no blank in-between) indicating the client ID in each group. Client-IDs are unique. Brew-Time: The time to brew his coffee. The order of clients in the input file indicates the order in which they arrived for coffee.    
The output should be as follows:  
- (0) H1 uses dispenser 1 (time: 5)    
- (0) H2 uses dispenser 2 (time: 2)   
- (2) H3 uses dispenser 2 (time: 3)   


Each line contains information about the usage of the coffee machine by a client. First, the time the client starts using the coffee machine in parenthesis. Then his Client-ID the disperser number in which he is operating and his brew time in parenthesis. Last line shows the time to serve all the clients. 
