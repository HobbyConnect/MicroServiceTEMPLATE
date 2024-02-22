package com.name_template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class NameTemplateApplication {

    public static void main(String[] args) throws UnknownHostException {
        Environment env = SpringApplication.run(NameTemplateApplication.class, args).getEnvironment();



        String grpcPort =  env.getProperty("grpc.server.port");




        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> log.info(
                        "\n############################################################################"
                                +"\n>>> SHUTDOWN SERVER: "+env.getProperty("spring.application.name")
                                +"\n############################################################################"
                )));
        log.info(
                "\n----------------------------------------------------------"
                        + "\n\tApplication '{}' is running! Access URLs:"
                        + "\n\tLocal           : \tlocalhost:{}"
                        + "\n\tExternal        : \t{}:{}"
                        + "\n\tProfile(s)      : \t{}"
                        + "\n\tGrpc port       : \t{}"
                        +"\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                grpcPort,
                InetAddress.getLocalHost().getHostAddress(), grpcPort,
                env.getActiveProfiles(),
                grpcPort);
    }
}
