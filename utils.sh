#!/bin/bash

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
NC='\033[0m'

echo "╔═══════════════════════════╗"
echo "║ Name your Service:        ║"
echo "╚═══════════════════════════╝"

read nm

serviceName="$(tr '[:lower:]' '[:upper:]' <<< ${nm:0:1})${nm:1}Service"


#Choise service-name

echo "${serviceName} is correct?"

select confirm in "Yes" "No"; do
    case $confirm in
        Yes)
            echo "╔═══════════════════════════╗"
            echo "║ Service name confirmed    ║"
            echo "╚═══════════════════════════╝"
            break
            ;;
        No)
            echo "╔═══════════════════════════╗"
            echo "║ Please enter new Name:    ║"
            echo "╚═══════════════════════════╝"
            read -r nm
            serviceName="$(tr '[:lower:]' '[:upper:]' <<< ${nm:0:1})${nm:1}Service"
            echo "${serviceName} is correct?"
            ;;
        *)
            echo -e "${RED}Info: NoInvalid option. Please select Yes(1) or No(2).${NC}"
            ;;
    esac
done


echo -e "${YELLOW}Info: clean Project.${NC}"


# clean
./gradlew clean


# package
packageName="$(tr '[:upper:]' '[:lower:]' <<< ${nm:0:1})${nm:1}"
old_packageName="name_template"
new_packageName="${packageName}"


old_javaClassName="NameTemplate"
new_javaClassName="${serviceName}"



echo -e "${YELLOW}Info: Rename files${NC}"


find src/ -type f -exec sed -i "s/$old_javaClassName/$new_javaClassName/g" {} +
find src/ -type f -exec sed -i "s/$old_packageName/$new_packageName/g" {} +

find src/ -type f -name "*${old_javaClassName}*" -exec bash -c 'mv "$1" "$(dirname "$1")/$(basename "$1" | sed 's/${old_javaClassName}/${new_javaClassName}/')"' _ {} \;

find src/ -depth -type d -name "*${old_packageName}*" -exec bash -c 'mv "$1" "$(dirname "$1")/$(basename "$1" | sed 's/${old_packageName}/${new_packageName}/')"' _ {} \;


#Rename gradle Project
old_gradleName="SessionService"
new_gradleName="${serviceName}"

find . -type f -exec sed -i "s/$old_gradleName/$new_gradleName/g" {} +

cd ..
cp -r MicroServiceTEMPLATE/ ${serviceName}

echo -e "${YELLOW}Info: Remove old Stuff${NC}"
#rm MicroServiceTEMPLATE/ -rf
cd ${serviceName}
#rm utils.sh


echo -e "${YELLOW}Info: Refresh new project${NC}"

./gradlew --refresh-dependencies



echo -e "${YELLOW}Info: Start git-process${NC}"

rm .git/ -rf
git init
git add * -f
git commit -m "${serviceName} init"
git branch -M main

reproURL="git@github.com:HobbyConnect/${serviceName}.git"

echo "${reproURL} pushed to this Repro?"

select confirm in "Yes" "No" "NoPush"; do
    case $confirm in
        Yes)
            echo "╔═══════════════════════════╗"
            echo "║ Push to repro             ║"
            echo "╚═══════════════════════════╝"
            git remote add origin ${reproURL}
            git push -u origin main
            break
            ;;
        No)
            echo "╔═══════════════════════════╗"
            echo "║ Please enter the repo URL ║"
            echo "╚═══════════════════════════╝"
            read -r nm
            serviceName="$(tr '[:lower:]' '[:upper:]' <<< ${nm:0:1})${nm:1}Service"
            echo "${serviceName} is correct?"
            ;;
        NoPush)
            echo -e "${YELLOW}Info: Skipping pushing${NC}"
            break
            ;;
        *)
            echo -e "${RED}Info: NoInvalid option. Please select Yes(1), No(2) or NoPush(3).${NC}"
            ;;
    esac
done





