package com.name_template.name_template.util;

import net.devh.boot.grpc.examples.lib.HealthCheckResponse;
import net.devh.boot.grpc.examples.lib.HelloResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ObjectMapper {

    /* ignore grpc-internals */ @Mapping(target = "mergeFrom", ignore = true)@Mapping(target = "clearField", ignore = true) @Mapping(target = "clearOneof", ignore = true)@Mapping(target = "messageBytes", ignore = true)@Mapping(target = "unknownFields", ignore = true)@Mapping(target = "mergeUnknownFields", ignore = true)@Mapping(target = "allFields", ignore = true)
    HelloResponse   convert(String message);

    /* ignore grpc-internals */ @Mapping(target = "mergeFrom", ignore = true)@Mapping(target = "clearField", ignore = true) @Mapping(target = "clearOneof", ignore = true)@Mapping(target = "unknownFields", ignore = true)@Mapping(target = "mergeUnknownFields", ignore = true)@Mapping(target = "allFields", ignore = true)
    @Mapping(target = "statusValue", ignore = true)
    HealthCheckResponse convert(HealthCheckResponse.Status status);


}
