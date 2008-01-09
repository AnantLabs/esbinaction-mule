package esb.chapter8.transaction.mule;

import org.mule.impl.DefaultExceptionStrategy;
import org.mule.umo.UMOMessage;
import org.mule.umo.endpoint.UMOImmutableEndpoint;

public class RollbackRoutingExceptionStrategy extends DefaultExceptionStrategy {

    public void handleRoutingException(UMOMessage message, UMOImmutableEndpoint endpoint, Throwable t)
    {
        defaultHandler(t);
        markTransactionForRollback();
        routeException(message, endpoint, t);
    }
}
