#!/bin/bash

echo "Geben Sie einen Projektnamen ein: "
read nm

serviceName="$(tr '[:lower:]' '[:upper:]' <<< ${nm:0:1})${nm:1}Service"

# Rename Proto-fiel
protoSearchString="MyService"
protoDir="src/main/proto/NameTemplateService.proto"
sed -i '' "s|$protoSearchString|$serviceName|g" "$protoDir"

mv "$protoDir" "src/main/proto/${serviceName}.proto"
gitProtoDir="src/main/proto/${serviceName}.proto"

# Rename Java-GRPC-Service-class
javaServiceClassDir="src/main/java/com/name_template/api/grpc/NameTemplateService.java"
sed -i '' "s|$protoSearchString|$serviceName|g" "$javaServiceClassDir"

#newpackage
packageName="$(tr '[:upper:]' '[:lower:]' <<< ${nm:0:1})${nm:1}"
javaClassName="$(tr '[:lower:]' '[:upper:]' <<< ${nm:0:1})${nm:1}"

javaPackageString="com.name_template"
newJavaPackageString="com.${packageName}"
javaSearchString="NameTemplate"



find src/ -type f -name "*.java" -exec sed -i '' "s|$javaPackageString|$newJavaPackageString|g" {} +
find src/ -type f -name "*.java" -exec sed -i '' "s|$javaSearchString|$serviceName|g" {} +

find src/ -type f -name "*NameTemplate*" -exec bash -c 'mv "$1" "$(dirname "$1")/$(basename "$1" | sed 's/NameTemplate/${serviceName}/')"' _ {} \;

mv "src/main/java/com/name_template" "src/main/java/com/${packageName}"




#git Process

git add *