#!/bin/bash

#variables
DEPLOYMENT=recruitmentwebapp
IMAGE=richydave/recruitmentwebapp2022:v0.0.2
TYPE=LoadBalancer
PORT=8080
#color variables
RED=$(tput setaf 1)
GREEN=$(tput setaf 2)
DEFAULT_COLOR=$(tput sgr0)

echo -e '\n'
if [ -d gke_destroy.sh ];then 
    bash gke_destroy.sh
else    
    #check if the deployment exists
    if kubectl get deployment "$DEPLOYMENT" >/dev/null 2>&1; then
        #delete any existing deployment and service
        echo "${GREEN}Deleting previous deployment and service for $DEPLOYMENT${DEFAULT_COLOR}"
        kubectl delete deployment $DEPLOYMENT
        echo -e '\n'
        kubectl delete service $DEPLOYMENT
    fi
fi
echo -e '\n'

#Re-create the deployment and service
echo "${GREEN}Creating a new deployment and service for $DEPLOYMENT${DEFAULT_COLOR}"
echo -e '\n'
kubectl create deployment recruitmentwebapp --image=$IMAGE
echo -e '\n'
echo "${GREEN}Exposing $DEPLOYMENT${DEFAULT_COLOR}"
echo -e '\n'
kubectl expose deployment $DEPLOYMENT --type=$TYPE --port=$PORT
echo -e '\n'
#show created pods

for((i=0;i<=5;i++))
do
	if [ "$i" -eq 0 ];then echo -ne "${GREEN}Exposing $DEPLOYMENT _ ${DEFAULT_COLOR} \r"
	elif [ "$i" -eq 1 ];then echo -ne "${GREEN}Exposing $DEPLOYMENT \ ${DEFAULT_COLOR} \r"
	elif [ "$i" -eq 2 ];then echo -ne "${GREEN}Exposing $DEPLOYMENT | ${DEFAULT_COLOR} \r"
	elif [ "$i" -eq 3 ];then echo -ne "${GREEN}Exposing $DEPLOYMENT / ${DEFAULT_COLOR} \r"
	elif [ "$i" -eq 4 ];then echo -ne "${GREEN}Exposing $DEPLOYMENT _  ${DEFAULT_COLOR} \r"
	elif [ "$i" -eq 5 ];then echo -ne "${GREEN}$DEPLOYMENT exposed successfully! ${DEFAULT_COLOR}"
	fi
	sleep 1
done
echo -e '\n'
kubectl get pods --sort-by=.metadata.creationTimestamp --namespace default --selector=app=$DEPLOYMENT
#show created service after 5 seconds
echo -e '\n'
echo -e "${GREEN}Preparing to initiate $DEPLOYMENT service${DEFAULT_COLOR}"

for((i=1;i<=15;i++))
do
    TIME_ELAPSED=$((i * 2))
    echo -ne "${GREEN}$TIME_ELAPSED seconds gone ${DEFAULT_COLOR} \r"
    if [ "$i" -eq 15 ];then echo -ne "" 
    fi
    sleep 2
done    

echo -e '\n'

for((i=0;i<=5;i++))
do
        if [ "$i" -eq 0 ];then echo -ne "${GREEN}Initiating $DEPLOYMENT service _ ${DEFAULT_COLOR}\r"
        elif [ "$i" -eq 1 ];then echo -ne "${GREEN}Initiating $DEPLOYMENT service \ ${DEFAULT_COLOR}\r"
        elif [ "$i" -eq 2 ];then echo -ne "${GREEN}Initiating $DEPLOYMENT service | ${DEFAULT_COLOR}\r"
        elif [ "$i" -eq 3 ];then echo -ne "${GREEN}Initiating $DEPLOYMENT service / ${DEFAULT_COLOR}\r"
        elif [ "$i" -eq 4 ];then echo -ne "${GREEN}Initiating $DEPLOYMENT service _ ${DEFAULT_COLOR}\r"
        elif [ "$i" -eq 5 ];then echo -ne "${GREEN}$DEPLOYMENT service Initialized successfully!${DEFAULT_COLOR}"
        fi
        sleep 1
done
echo -e '\n'
kubectl get service --sort-by=.metadata.creationTimestamp --namespace default --selector=app=$DEPLOYMENT
