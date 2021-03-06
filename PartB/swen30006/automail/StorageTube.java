package automail;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
import exceptions.TubeFullException;

import java.util.Stack;

/**
 * The storage tube carried by the robot.
 */
public class StorageTube {
    public static int MAXIMUM_CAPACITY;
    public Stack<MailItem> tube;

    /**
     * Constructor for the storage tube
     */
    //default StorageTube constructor
    public StorageTube(){
        this.tube = new Stack<>();
        this.MAXIMUM_CAPACITY = 4;
    }
    //StorageTube constructor with parameters setting different capacity
    public  StorageTube(int capacity){
        this.tube = new Stack<>();
        this.MAXIMUM_CAPACITY = capacity;
    }

    /**
     * @return if the storage tube is full
     */
    public boolean isFull(){
        return tube.capacity() == MAXIMUM_CAPACITY;
    }

    /**
     * @return if the storage tube is empty
     */
    public boolean isEmpty(){
        return tube.isEmpty();
    }
    
    /**
     * @return the first item in the storage tube (without removing it)
     */
    public MailItem peek() {
    	return tube.peek();
    }

    /**
     * Add an item to the tube
     * @param item The item being added
     * @throws TubeFullException thrown if an item is added which exceeds the capacity
     */
    public void addItem(MailItem item) throws TubeFullException {
        int current = getSize();
        if(current + 1 <= MAXIMUM_CAPACITY){
        	tube.add(item);
        } else {
            throw new TubeFullException();
        }
    }

    /** @return the size of the tube **/
    public int getSize(){
    	return tube.size();
    }
    
    /** 
     * @return the first item in the storage tube (after removing it)
     */
    public MailItem pop(){
        return tube.pop();
    }

}
