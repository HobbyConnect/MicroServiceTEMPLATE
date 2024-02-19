echo "Give Project a Name: "

read nm

# Rename GRPC-Service

protoSearchString="SayHello"
protoReplaceString="test"
protoDir="src/main/proto/NameTemplateService.proto"

sed -i '' "s|$protoSearchString|$protoReplaceString|g" "$protoDir"

#mv $protoDir "src/main/proto/${nm}Service.proto"



