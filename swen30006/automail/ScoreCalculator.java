package automail;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
public class ScoreCalculator implements IScoreCalculator {
    //implement the calculation method
    @Override
    public double calculateDeliveryScore(MailItem deliveryItem) {
        // Penalty for longer delivery times
        final double penalty = 1.1;
        double priority_weight = 0;
        // Take (delivery time - arrivalTime)**penalty * (1+sqrt(priority_weight))
        if (deliveryItem instanceof PriorityMailItem) {
            priority_weight = ((PriorityMailItem) deliveryItem).getPriorityLevel();
        }
        return Math.pow(Clock.Time() - deliveryItem.getArrivalTime(), penalty) * (1 + Math.sqrt(priority_weight));
    }
}
