package strategies;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
import automail.MailItem;
import automail.StorageTube;
import exceptions.TubeFullException;

import java.util.ArrayList;

public class BigSmartRobotBehaviour extends SmartRobotBehaviour{

    public static final int MAX_TAKE = 6;
    @Override
    public boolean returnToMailRoom(StorageTube tube) {
        /**
         * only return when the tube is empty
         */
        return tube.isEmpty();
    }

    @Override
    public void priorityArrival(int priority) {
    }

    @Override
    public boolean fillStorageTube(IMailPool mailPool, StorageTube tube) {

        ArrayList<MailItem> tempTube = new ArrayList<MailItem>();

        // Empty my tube
        while(!tube.tube.isEmpty()){
            mailPool.addToPool(tube.pop());
        }

        // Grab priority mail
        /**
         * Use Integer.min here to make program stronger in case someone arbitarily change default capacity
         */
        while(tempTube.size() < Integer.min(MAX_TAKE, tube.MAXIMUM_CAPACITY)){
            if(containMail(mailPool,MailPool.PRIORITY_POOL)){
                tempTube.add(mailPool.getHighestPriorityMail());
            }
            else{
                // Fill it up with non priority
                if(containMail(mailPool,MailPool.NON_PRIORITY_POOL)){
                    tempTube.add(mailPool.getNonPriorityMail());
                }
                else{
                    break;
                }

            }
        }

        // Sort tempTube based on floor
        tempTube.sort(new ArrivalComparer());

        // Iterate through the tempTube
        while(tempTube.iterator().hasNext()){
            try {
                tube.addItem(tempTube.remove(0));
            } catch (TubeFullException e) {
                e.printStackTrace();
            }
        }

        // Check if there is anything in the tube
        if(!tube.tube.isEmpty()){
            return true;
        }
        return false;
    }
}
