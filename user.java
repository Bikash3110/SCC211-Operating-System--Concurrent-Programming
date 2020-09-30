import java.util.*;

public class user extends Thread
   {											
  private int id;
  private int num_elements;
  private int num_users;
  public static Buffer buf;
  private int count = 0;
  private int limit;
  
  private Random rand = new Random();                             // create random values for int x which will be used to get random sleep values for user thread  
  private int x = rand.nextInt(100);
  
     public user(int i, int ele,int n_user, Buffer b)							//Created user will add a certain number of elements to the buffer.
     {
        id = i;
		num_elements = ele;
		num_users = n_user;
		buf = b;
	   limit = num_elements/num_users;	
     }
	 	
	public int userEleCount()                    //Returns the total number of times a user added elements to the buffer
	{
         return count;
	}
	
	 public void run()                                                              
	 { 
       for(int i=0; i< limit ;i++){              
		if(buf.getSize()>= buf.bufSize())                  // checks whether the current size of list exceeds buffer size or not
		{
			System.out.println("Buffer Full - User now Sleeping");        // user waits if current list size equals or try to exceed buffer size
			try{	
 			 Thread.sleep(150);                            // user sleep() when current list size => buffer size
		    }
			catch(InterruptedException ie)
			{
				System.out.println(ie);
			}
		}
		
		buf.AddEle(id,i);                               // Adds elements to Buffer
	
    	try{	
		Thread.sleep(x);}                         // creates random sleep values 
		catch(InterruptedException ie){
		System.out.println(ie);}
		count++;                                  // keeps count of number of times user adds the elements to buffer 
	   }	   	 	 
	 }

   }   
