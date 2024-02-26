package com.name_template.name_template;

import com.name_template.name_template.healt.HealthManager;
import com.name_template.name_template.healt.HealthStatus;
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
    private HealthManager healthManager;

    public GrpcRequestHandler(
            ObjectMapper  objectMapper,
            HealthManager healthManager
    ){
        this.objectMapper  = objectMapper;
        this.healthManager = healthManager;
    }

    public HelloResponse hello(HelloRequest request){
         /*
            Implement logic here or in other classes which called here

         */
        String message = "Hello " + request.getName();
        return objectMapper.convert(message);
    }


    public HealthCheckResponse health(){
        HealthStatus status = healthManager.getStatus();
        return objectMapper.convert(status);
    }

}
