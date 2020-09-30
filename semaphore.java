import java.util.*;
public class semaphore
  {
    
   //This is an example class for using a primitive synchronization (semaphore, lock). Please note that you
   //can ONLY put the synchronization keyword within these type of classes, and nowhere else within the program.
   
   private int count = 0;
   
   public semaphore(int init_val)  // semaphore is created and the parameter is passed 
   {                               // to decide the number of threads in critical section
	  count = init_val;
   }
   
   public synchronized void P()      // other Threads wait until number of allowed threads in critical sectle completes the task 
   {
	  count = count - 1;       //decreaments the count 
	  if(count < 0)            // checks count is negetive or not
	  {	 
        try{
     	wait();}               // make threads wait 
		catch(InterruptedException ie){
		System.out.println(ie);}
	  }
   }
   
   public synchronized void V()
   {
	   count = count + 1;         // Increaments the count
	   if(count <= 0)             // checks condition count 
 		   notify();              // notify the threads
   }
  
  }

