package strategies;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
import automail.StorageTube;

public interface IRobotBehaviour {
	/** 
	 * @param tube refers to the pack the robot uses to deliver mail.
	 * @return When this is true, the robot is returned to the mail room.
	 */
     boolean returnToMailRoom(StorageTube tube);
    
    /**
     * @param priority is that of the mail item which just arrived.
     */
     void priorityArrival(int priority);
    
    /**
     * @param mailPool used to put back or get mail.
     * @param tube refers to the pack the robot uses to deliver mail.
     * @return Return true to indicate that the robot is ready to start delivering.
     */
	 boolean fillStorageTube(IMailPool mailPool, StorageTube tube);
}
