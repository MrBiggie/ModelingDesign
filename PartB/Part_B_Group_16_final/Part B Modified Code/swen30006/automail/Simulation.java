package automail;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
import exceptions.ExcessiveDeliveryException;
import strategies.Automail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * This class simulates the behaviour of AutoMail
 */
public class Simulation {

    /**
     * Constant for the mail generator
     */
    private static int MAIL_TO_CREATE = 60;
    private  static int Mail_Count_Percentage_Variation = 20;
    private  static int Priority_Mail_is_One_in = 6;

    public static ArrayList<MailItem> MAIL_DELIVERED;

    public static double total_score = 0;



    public static void main(String[] args) {
        /** Used to see whether a seed is initialized or not */
        HashMap<Boolean, Integer> seedMap = new HashMap<>();
        Properties automailProperties = new Properties();
        FileReader inStream = null;

        try {
            inStream = new FileReader("automail.properties");
            /**
             * load automailporperty from property file
             */
            automailProperties.load(inStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * set the seed
             */

                /** Read the seed from property file */
            if (automailProperties.containsKey("Seed")) {
                int seed = Integer.parseInt(automailProperties.getProperty("Seed"));
                seedMap.put(true, seed);
            } else {
                seedMap.put(false, 0);
            }

            if (inStream != null) {
                /**
                 * initialise building parameters
                 */
                int Number_of_Floors = Integer.parseInt(automailProperties.getProperty("Number_of_Floors"));
                if (Number_of_Floors > 0) {
                    Building.FLOORS = Number_of_Floors;
                } else {
                    System.out.println("Number_of_Floors should be more than 0, this simulation will default it as 9");
                }

                int Lowest_Floor = Integer.parseInt(automailProperties.getProperty("Lowest_Floor"));
                if (Lowest_Floor > 0) {
                    Building.LOWEST_FLOOR = Lowest_Floor;
                }
                else{
                    System.out.println("Lowest_Floor should be more than 0, this simulation will default it as 1");
                }

                int Location_of_MailRoom = Integer.parseInt(automailProperties.getProperty("Location_of_MailRoom"));
                if (Location_of_MailRoom > 0) {
                    Building.MAILROOM_LOCATION = Location_of_MailRoom;
                }
                else{
                    System.out.println("Location_of_MailRoom should be more than 0, this simulation will default it as 1");
                }


                /**
                 * initialise the Mail Generation Parameters
                 Priority_Mail_is_One_in = 6
                 */
                int MailToGenerate = Integer.parseInt(automailProperties.getProperty("Mail_to_Create"));
                if (MailToGenerate > 0) {
                    MAIL_TO_CREATE = MailToGenerate;
                } else {
                    System.out.println("MAIL_TO_CREATE should be more than 0, this simulation will default it as 60");
                }

                Mail_Count_Percentage_Variation = Integer.parseInt(automailProperties.getProperty("Mail_Count_Percentage_Variation"));
                Priority_Mail_is_One_in = Integer.parseInt(automailProperties.getProperty("Priority_Mail_is_One_in"));


                
                /**
                 * initialise Clock Parameter Last_Delivery_Time
                 */
                int Last_Delivery_Time = Integer.parseInt(automailProperties.getProperty("Last_Delivery_Time"));
                if(Last_Delivery_Time > 0) {
                    Clock.LAST_DELIVERY_TIME = Last_Delivery_Time;
                } else {
                    System.out.println("Last_Delivery_Time should be more than 0, this simulation will default it as 100");
                }


                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        MAIL_DELIVERED = new ArrayList<>();


        /**
         * Initialize the Automail and MailGenerator and the Robot
         */
        Automail automail = new Automail(automailProperties.getProperty("Robot_Type"));
        MailGenerator generator = new MailGenerator(MAIL_TO_CREATE, automail.mailPool, seedMap,Mail_Count_Percentage_Variation,Priority_Mail_is_One_in);
        Robot robot = new Robot(automail.robotBehaviour, automail.mailPool, Double.parseDouble(automailProperties.getProperty("Delivery_Penalty")));

        /** Initiate all the mail */
        generator.generateAllMail();

        /**
         * run until all of the mails were delivered
         */
        int priority;
        while (MAIL_DELIVERED.size() != generator.MAIL_TO_CREATE) {
            priority = generator.step();
            if (priority > 0) robot.behaviour.priorityArrival(priority);
            try {
                robot.step();
            } catch (ExcessiveDeliveryException e) {
                e.printStackTrace();
                System.out.println("Simulation unable to complete..");
                System.exit(0);
            }
            Clock.Tick();
        }
        printResults();
    }

    public static void printResults() {
        System.out.println("T: " + Clock.Time() + " | Simulation complete!");
        System.out.println("Final Delivery time: " + Clock.Time());
        System.out.printf("Final Score: %.2f%n", total_score);
    }
}
