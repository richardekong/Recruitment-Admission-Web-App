#! /usr/bin/bash


#INSTALLING A PROTOTYPE OF CARDIFF UNIVERSITY'S MIDWIFERY RECRUITMENT AND ADMISSION WEB APPLICATION

echo "installing mariaDB ..."

sudo yum install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb

echo "creating mysql_secure_installation.tx ..."
touch mysql_secure_installation.txt
cat << `EOF` >> mysql_secure_installation.txt

n
Y
c22106964
P@55w07d4cmt655
Y
Y
Y
Y
Y
`EOF`


echo "running mysql_secure_installation..."
sudo mysql_secure_installation < mysql_secure_installation.txt

cat << `EOF` >> gitlab_recruitment_web_app_keypair.key
-----BEGIN OPENSSH PRIVATE KEY-----
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
-----END OPENSSH PRIVATE KEY-----
`EOF`

chmod 400 gitlab_recruitment_web_app_keypair.key

touch ~/.ssh/known_hosts
ssh-keyscan git.cardiff.ac.uk >> ~/.ssh/known_hosts
chmod 644 ~/.ssh/known_hosts

sudo yum install git -y

ssh-agent bash -c 'ssh-add gitlab_recruitment_web_app_keypair.key; git clone git@git.cardiff.ac.uk:c22106964/team-11-recruitment-and-admissions-copy-c22106964.git'

cd 'team-11-recruitment-and-admissions-copy-c22106964'

mysql -uroot -pP@55w07d4cmt655 < src/BuildDB.sql

sudo yum install java-17-openjdk -y

#install wget
#use it to download the gradle 7.6 from the official gradle repository located at https://services.gradle.org/distributions/gradle-7.6-bin.zip
#install the unzip utility
#create a gradle directory in the opt/ directory, where it is recommended to store your tools
#use the unzip utility to unpack the gradle-7.6-bin.zip file to /opt/gradle directory

sudo yum install wget -y
wget https://services.gradle.org/distributions/gradle-7.6-bin.zip
sudo yum install unzip -y
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-7.6-bin.zip

#make rockylinux aware of the gradle directory by exporting the path to the environment variable
export PATH=$PATH:/opt/gradle/gradle-7.6/bin
gradle -v
#build the (recruitment and admissions) web application project
gradle build

#run the project
gradle bootrun
