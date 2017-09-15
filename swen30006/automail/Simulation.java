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


    public static ArrayList<MailItem> MAIL_DELIVERED;
    public static double total_score = 0;



    public static void main(String[] args) {
        /** Used to see whether a seed is initialized or not */
        HashMap<Boolean, Integer> seedMap = new HashMap<>();
        // Read properties file here
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
                int buildingParameters = Integer.parseInt(automailProperties.getProperty("Number_of_Floors"));
                if (buildingParameters > 0) {
                    Building.setFLOORS(buildingParameters);
                } else {
                    System.out.println("the floors should be more than 0");
                }
                buildingParameters = Integer.parseInt(automailProperties.getProperty("Lowest_Floor"));
                if (buildingParameters > 0) {
                    Building.setLowestFloor(buildingParameters);
                }
                buildingParameters = Integer.parseInt(automailProperties.getProperty("Location_of_MailRoom"));
                if (buildingParameters > 0) {
                    Building.setMailroomLocation(buildingParameters);
                }


                /**
                 * initialise the mail to generate
                 */
                int MailToGenerate = Integer.parseInt(automailProperties.getProperty("Mail_to_Create"));
                if (MailToGenerate > 0) {
                    MAIL_TO_CREATE = MailToGenerate;
                } else {
                    System.out.println("MAIL_TO_CREATE should be more than 0");
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
        MailGenerator generator = new MailGenerator(MAIL_TO_CREATE, automail.mailPool, seedMap);
        Robot robot = new Robot(automail.robotBehaviour, automail.mailPool);

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
