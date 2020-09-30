import java.util.*;

public class webserver extends Thread
  {										//Web server removes elements from the buffer
  private int id;
  private int num_elements;
  private int num_servers;
  public static Buffer buf;
  private int count = 0;
  private int limit;
  
  private Random rand = new Random();                    // create random values for int x which will be used to get random sleep values for webserver thread 
  private int x = rand.nextInt(200);
  
    public webserver(int i, int ele, int n_server, Buffer b)                   // webserver creation with parameters indicating the id, num_elements,num_servers and buffer			
    {
		id = i;
		num_elements = ele;
		num_servers = n_server;
		buf = b;
		limit = (num_elements/num_servers);
	}
    
	public int serverEleCount()           // Returns the total number of times server removed the elements from buffer
	{
         return count;
	}
  
    public void run()
	{
		for(int i=0; i< limit ;i++){                	
		if(buf.getSize()<= 0)                       // checks if current list size is empty or not
		{
			System.out.println("Buffer Empty - Web Server wait ");             // server wait and stop removing the elements from list when empty 
			try{
			Thread.sleep(200);                                    // server sleep() when buffer list empty
		    }
	      		catch(InterruptedException ie)
			{
				System.out.println(ie);
			}
		}
		
		buf.removeEle(id,i);                           // remove element method called from buffer
		
		try{
		Thread.sleep(x);                               // server sleep for random milliseconds between 0 to 200 inorder to observe context switching 
		}
		catch(InterruptedException ie){
		System.out.println(ie);}
		
		count++;                              // increament the count each time server is called
	   }
	}

  }   
