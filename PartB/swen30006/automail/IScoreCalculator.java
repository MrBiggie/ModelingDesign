package automail;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
public interface IScoreCalculator{
    /**
     * calculate the total score
     * @param deliveryItem
     * @return the score of this MailItem
     */
    //we change calculator into interface to be further extendable with different ways of calculation
    double calculateDeliveryScore(MailItem deliveryItem);
}
