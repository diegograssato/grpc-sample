package org.dtux.agenda.interceptor.strategy;
import io.grpc.*;

public interface GrpcOnMessageInterceptor {


    Object onMessage(Object message);
}
