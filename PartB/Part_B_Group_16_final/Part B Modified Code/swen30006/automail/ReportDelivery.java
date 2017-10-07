package automail;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
import exceptions.MailAlreadyDeliveredException;


public class ReportDelivery implements IMailDelivery {
    private double penalty;
    public  ReportDelivery(double penalty){
        this.penalty = penalty;
    }
    @Override
    public void deliver(MailItem deliveryItem) {
        /**
         * Confirm the delivery and calculate the total score
         */
        IScoreCalculator myScoreCalculator = new ScoreCalculator(penalty);
        
        if (!Simulation.MAIL_DELIVERED.contains(deliveryItem)) {
            System.out.println("T: " + Clock.Time() + " | Delivered " + deliveryItem.toString());
            Simulation.MAIL_DELIVERED.add(deliveryItem);
            // Calculate delivery score
            Simulation.total_score += myScoreCalculator.calculateDeliveryScore(deliveryItem);
        } else {
            try {
                throw new MailAlreadyDeliveredException();
            } catch (MailAlreadyDeliveredException e) {
                e.printStackTrace();
            }
        }
    }
    
}
