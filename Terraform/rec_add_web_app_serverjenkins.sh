#!/usr/bin/bash
cd /home/rocky
echo in directory $PWD

echo "updating system package"
sudo dnf update -y

# echo "instaling langauge package"
# sudo dnf install langpacks-en glibc-all-langpacks -y


echo "installing google chrome"
sudo dnf install wget -y
sudo update-crypto-policies --set LEGACY
wget https://dl.google.com/linux/linux_signing_key.pub
sudo rpm --import linux_signing_key.pub
wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
sudo dnf install google-chrome-stable_current_x86_64.rpm -y

# #installing docker
# echo "Installing docker"
# sudo dnf config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# sudo dnf install docker-ce -y
# sudo dnf install docker-ce-cli -y
# sudo dnf install containerd.io -y
# sudo systemctl enable docker
# sudo systemctl start docker
# sudo systemctl status docker
# sudo usermod -aG docker $(whoami)

echo "installing MariaDB..."
# sudo yum install mysql -y
sudo dnf install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb


echo "creating mysql_secure_installation.txt..."
touch mysql_secure_installation.txt
cat << `EOF` >> mysql_secure_installation.txt

n
Y
root
root
Y
Y
Y
Y
Y
`EOF`

echo "running mysql_secure_installation..."
sudo mysql_secure_installation < mysql_secure_installation.txt

# sudo yum install wget -y
sudo yum install unzip -y
sudo yum install git -y

echo "Installing Java 17..."
sudo yum install java-17-openjdk-devel -y
echo java --version

echo "install Jenkins"
# https://pkg.jenkins.io/redhat-stable/
curl --silent --location http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo | sudo tee /etc/yum.repos.d/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo dnf install https://dl.fedoraproject.org/pub/epel/epel-release-latest-8.noarch.rpm -y
sudo yum install jenkins -y

echo "installing gitlab server key... has to be added to the jenkins user home (~) dir "
mkdir /var/lib/jenkins/.ssh
sudo touch /var/lib/jenkins/.ssh/known_hosts
sudo ssh-keyscan git.cardiff.ac.uk >> /var/lib/jenkins/.ssh/known_hosts
sudo chmod 644 /var/lib/jenkins/.ssh/known_hosts


# If you want jenkins on port 8081 so you can run your app on 8080 then change the default jenkins port.
#(look up linux sed - it is really cool)
# sudo sed -i 's/JENKINS_PORT="8080"/JENKINS_PORT="8081"/g' /etc/sysconfig/jenkins
sudo systemctl start jenkins
systemctl status jenkins
sudo systemctl enable jenkins

echo "Installing gradle..."
# wget https://services.gradle.org/distributions/gradle-6.7.1-bin.zip
wget https://services.gradle.org/distributions/gradle-7.6-bin.zip
sudo mkdir /opt/gradle
# sudo unzip -d /opt/gradle gradle-6.7.1-bin.zip
# export PATH=$PATH:/opt/gradle/gradle-6.7.1/bin
sudo unzip -d /opt/gradle gradle-7.6-bin.zip
export PATH=$PATH:/opt/gradle/gradle-7.6/bin
echo gradle -v

echo "Installing terraform..."
cd /home/rocky
wget https://releases.hashicorp.com/terraform/1.1.5/terraform_1.1.5_linux_amd64.zip
unzip terraform_1.1.5_linux_amd64.zip
sudo mv terraform /usr/local/bin/
