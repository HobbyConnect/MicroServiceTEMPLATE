package com.name_template.name_template;

import com.name_template.name_template.util.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.examples.lib.HealthCheckResponse;
import net.devh.boot.grpc.examples.lib.HelloRequest;
import net.devh.boot.grpc.examples.lib.HelloResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GrpcRequestHandler {

    private ObjectMapper objectMapper;

    public GrpcRequestHandler(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public HelloResponse hello(HelloRequest request){
         /*
            Implement logic here or in other classes which called here

         */
        String message = "Hello " + request.getName();
        return objectMapper.convert(message);
    }


    public HealthCheckResponse health(){
        HealthCheckResponse.Status status = performHealthCheck();
        return objectMapper.convert(status);
    }

    private HealthCheckResponse.Status performHealthCheck() {
        /*
            Implement your health check status here

         */
        return HealthCheckResponse.Status.HEALTHY;
    }


}
