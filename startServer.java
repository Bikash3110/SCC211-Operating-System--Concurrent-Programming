import java.util.*;
public class startServer
   {
  	Buffer b;										//Creation of buffer object  

    public startServer(int bufferSize, int num_users, int num_servers, int total_elements)
   {												//Creates execution scenario between user and webservers on buffer
        
        long startTime = System.currentTimeMillis();		
																
												//Instantiate all objects (webserver, users, buffer)
	b = new Buffer(bufferSize);								
	
	int thread_length =(num_users + num_servers);    // lenght of the thread array 
	user[] u = new user[num_users];                         
	webserver[] ws = new webserver[num_servers];
	Thread[] t = new Thread[thread_length];
	
												//Equally subdivide user inputted elements across all user objects
    for(int i=0; i<thread_length; i++)
	{
	  if(i<num_users){
		  
	  u[i]= new user(i+1,total_elements,num_users,b);	      // creates users 
      t[i] = new Thread(u[i]);                                // user threads created
	  }
	  
	 if(i>= num_users)
	  {
		int id = (i- num_users +1);   
		ws[id-1] = new webserver(id,total_elements,num_servers,b);         // Creates webserver
		t[i] = new Thread(ws[id-1]);                                       // server threads created
	  }
     
     t[i].start();	  // starts the threads
	}
	
	for(int i =0; i<thread_length; i++)
   {
	   try
	   {
		   t[i].join();                       // joins all threads so that the main thread ends last            
	   }
	   
	   catch(InterruptedException ie)
	   {
		   System.out.printf("Error : InterruptedException");
	   }
   }
	
	System.out.println("-----------------------");
	                                                     //Outputs the total number of elements added/removed from user and webserver
	for(int i=0; i<num_users; i++){
	    System.out.println("User "+ (i+1) +" created a total of "+ u[i].userEleCount()+" elements");												
        
	}
	
	for(int i=0; i<num_servers; i++){
	    System.out.println("Consumer "+ (i+1) +" removed a total of "+ ws[i].serverEleCount()+" elements");												
        
	}	

	System.out.println("-----------------------");
	
	System.out.println("Buffer has " + b.getSize() + " elements remaining");			//Check to see buffer if all elements produced from users have been successfully removed by webservers
	
	System.out.println("-----------------------");
												//Checks if all users and web servers successfully finished
	
    for(int i=0; i<num_users; i++){                                                       // checks all the user status
	    System.out.println("User thread "+ (i+1) +" is alive "+ u[i].isAlive());												
        
	}
	
	for(int i=0; i<num_servers; i++){
	    System.out.println("Server thread "+ (i+1) +" is alive "+ ws[i].isAlive());					// checks all server status							
        
	}	
	
	long endTime = System.currentTimeMillis();
	System.out.println("-----------------------");
     	System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");		//calculate and prints the total time taken by the code 

    }
  
public static void main(String[] args)
  {
	  int num_users = 0;
	  int num_servers = 0;
      int total_elements = 0;
      int bufferSize = 0;
	  

    System.out.println("Enter buffer capacity");					//Insert user inputted values for program execution
    Scanner scan = new Scanner(System.in);
    int size = scan.nextInt();			                            // Insert bufferSize
    bufferSize = size;	
	
    System.out.println("Enter number of users");                    // Insert num_users
    Scanner scan1 = new Scanner(System.in);
    int users = scan1.nextInt();			
    num_users = users;	
	
    System.out.println("Enter number of servers");                   // Insert num_servers
    Scanner scan2 = new Scanner(System.in);
    int servers = scan2.nextInt();			
    num_servers = servers;	
	
    System.out.println("Enter total number of elements");             // Insert total_elements
    Scanner scan3 = new Scanner(System.in);
    int ele = scan3.nextInt();			
    total_elements = ele;	
	
    startServer start = new startServer(bufferSize,num_users,num_servers,total_elements);
	
  }
}
