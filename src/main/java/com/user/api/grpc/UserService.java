package com.name_template.api.grpc;


import com.name_template.name_template.GrpcRequestHandler;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.examples.lib.*;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class NameTemplate extends NameTemplateGrpc.NameTemplateImplBase {

    /*
        Implement here all endpoint which added in the NameTemplate.proto
     */

    private GrpcRequestHandler grpcRequestHandler;

    public NameTemplate(GrpcRequestHandler grpcRequestHandler){
        this.grpcRequestHandler = grpcRequestHandler;
    };



    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = grpcRequestHandler.hello(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void healthCheck(HealthCheckRequest request, StreamObserver<HealthCheckResponse> responseObserver) {
        HealthCheckResponse response = grpcRequestHandler.health();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }









}
