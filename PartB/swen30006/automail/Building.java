package automail;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
public class Building {

    public static int getFLOORS() {
        return FLOORS;
    }

    public static int getLowestFloor() {
        return LOWEST_FLOOR;
    }

    public static int getMailroomLocation() {
        return MAILROOM_LOCATION;
    }

    public static void setFLOORS(int FLOORS) {
        Building.FLOORS = FLOORS;
    }

    public static void setLowestFloor(int lowestFloor) {
        LOWEST_FLOOR = lowestFloor;
    }

    public static void setMailroomLocation(int mailroomLocation) {
        MAILROOM_LOCATION = mailroomLocation;
    }

    /** The number of floors in the building **/
    private static int FLOORS = 9;
    
    /** Represents the ground floor location */
    private static int LOWEST_FLOOR = 1;

    /** Represents the mailroom location */
    private static int MAILROOM_LOCATION = 1;

}
