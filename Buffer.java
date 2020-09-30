import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
  {     									
	private LinkedList<Object> buf_list;	             // Instance of Linked list			
    private int elements;
	private int buf_size;                                // buffer size stored in this variable
	private semaphore sem = new semaphore(1) ;           // instance of semaphore created and set to only 1 thread permitted   
	
     public Buffer(int n)						//Buffer creation, with n indicating the maximum capacity
	{
	   buf_list = new LinkedList<Object>();                
	   elements = 0;
	   buf_size = n;                  // buffer size initialised through parameter 
        }
	
    public void AddEle(int id, int pos)              // Adding Element to the list 
	{
		sem.P();                                     // wait()
		if(buf_list.size()<= buf_size){              // Checks whether current list size exceeds buffer size or not
                 buf_list.add(elements);                      // adds elements to the list
 		elements++;                                  // increament element by 1
		System.out.print("User "+id +" add element " + getSize() +"/"+ buf_size +"\n");
		}
		sem.V();                                     // notify() other threads    
	}

    public void removeEle(int id, int pos)           // remove Element from the list    
	{
	  sem.P();	                                     // wait()
	  if(buf_list.size()>0){                         // checks whether current list size is empty or not 
          buf_list.remove();                             //  remove element from the list 
	  System.out.print("Server "+id +" removed element " + getSize() +"/"+ buf_size +"\n");
	  }
	  sem.V();                                        // notify() other threads
	}	
	
 	public int bufSize()                             // returns the size of buffer  
	{
	  return buf_size;	
	}
 
    public int getSize()                             // returns the current size of the buffer_list 
	{
	  return buf_list.size();	
	}
}	  
