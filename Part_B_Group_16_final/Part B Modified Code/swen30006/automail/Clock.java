package automail;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
public class Clock {
	
	/** Represents the current time **/
    private static int Time = 0;
    
    /** The threshold for the latest time for mail to arrive **/
    public static int LAST_DELIVERY_TIME = 100;

    public static int Time() {
    	return Time;
    }
    
    public static void Tick() {
    	Time++;
    }
}
