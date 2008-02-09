package esb.chapter8.transaction.mule;

import org.mule.DefaultExceptionStrategy;
import org.mule.api.MuleMessage;
import org.mule.api.endpoint.ImmutableEndpoint;

public class RollbackRoutingExceptionStrategy extends DefaultExceptionStrategy {

    public void handleRoutingException(MuleMessage message, ImmutableEndpoint endpoint, Throwable t)
    {
        defaultHandler(t);
        markTransactionForRollback();
        routeException(message, endpoint, t);
    }
}
