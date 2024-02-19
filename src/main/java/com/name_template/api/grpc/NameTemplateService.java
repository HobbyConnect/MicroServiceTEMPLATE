package com.name_template.api.grpc;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.examples.lib.HelloReply;
import net.devh.boot.grpc.examples.lib.HelloRequest;
import net.devh.boot.grpc.examples.lib.MyServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class NameTemplateService extends MyServiceGrpc.MyServiceImplBase {

    /*
    Doc:
        https://yidongnan.github.io/grpc-spring-boot-starter/en/server/getting-started.html
     */


    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }







}
