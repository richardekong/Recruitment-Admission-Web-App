variable "flavor" { default = "m1.medium" }
variable "image" { default = "Rocky Linux 9 20220830" }
variable "name1" { default = "Recruitment Admissions Web Application Test version" }

variable "keypair" { default = "Recruitment_Web_App_keypair" } # you may need to change this
variable "network" { default = "temp_network" }   # you need to change this

variable "pool" { default = "cscloud_private_floating" }
variable "server1_script" { default = "./recruitment-web-app.sh"}
variable "security_description" { default = "Recruitment Admissions Web Application security group" }
variable "security_name" { default = "tf_rec_web_app_sec_group_test" }
