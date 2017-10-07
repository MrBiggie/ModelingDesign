package exceptions;
/**
 * @author Project Group 16
 *         Shuyuan Dang 840992
 *         Feng Zhao 838219
 *         Hongyi Chen 822666
 */
/**
 * An exception thrown when a mail that is already delivered attempts to be delivered again.
 */
public class MailAlreadyDeliveredException extends Throwable    {

    public MailAlreadyDeliveredException(){
        super("This mail has already been delivered!");
    }
}
