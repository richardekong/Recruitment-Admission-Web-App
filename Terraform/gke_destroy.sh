#!/bin/bash
#variables
DEPLOYMENT=recruitmentwebapp
#color variables
RED=$(tput setaf 1)
GREEN=$(tput setaf 2)
DEFAULT_COLOR=$(tput sgr0)

#delete any existing deployment and service
#check if the deployment exists
if ! kubectl get deployment "$DEPLOYMENT" >/dev/null 2>&1; then
    steps=4
    echo "${RED}Error:${DEFAULT_COLOR} no deployment of $DEPLOYMENT found"
    for ((step=1;step<=$steps;step++))
    do
        if [ "$step" -eq 1 ]; then echo -ne "${GREEN}Exiting .${DEFAULT_COLOR}\r"
        elif [ "$step" -eq 2 ]; then echo -ne "${GREEN}Exiting ..${DEFAULT_COLOR}\r"
        elif [ "$step" -eq 3 ]; then echo -ne "${GREEN}Exiting ...${DEFAULT_COLOR}\r"
        elif [ "$step" -eq 4 ]; then echo -ne "\r" 
        fi
        sleep $steps 
    done       
    exit 0
else
    echo 'Deleting application previous deployment and service for '$DEPLOYMENT
    echo -e '\n'
    kubectl delete  deployment $DEPLOYMENT
    echo -e '\n'
    kubectl delete service $DEPLOYMENT
    echo -e '\n'
fi

