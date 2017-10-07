package strategies;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
public class Automail {
	      
    //public Robot robot;
    public IMailPool mailPool; 
    public IRobotBehaviour robotBehaviour;

    public Automail(String behaviourtype) {
    	    	
    	/** Initialize the MailPool */
    	mailPool = new MailPool();


        /**
         * Initialize the RobotBehaviour, select by behaviourtype
         */

        switch (behaviourtype){
            case "Small_Comms_Smart" : robotBehaviour = new SmartRobotBehaviour();break;
            case "Small_Comms_Simple" : robotBehaviour = new SimpleRobotBehaviour();break;
            case "Big_Smart" : robotBehaviour =  new BigSmartRobotBehaviour();break;
            default: robotBehaviour = new SimpleRobotBehaviour();
        }
    	
    }
    
}
