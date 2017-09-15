package exceptions;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
/**
 * This exception is thrown when a MailItem is added to a StorageTube which does not have the
 * capacity to hold said MailItem
 */
public class TubeFullException extends Exception {

    public TubeFullException(){
        super("Not enough space in the tube! ");
    }
}
