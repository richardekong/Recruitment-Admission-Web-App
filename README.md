
## Team 11 - Cardiff Midwifery Recruitment and Admission Web application prototype
Web Application prototype serving as a bases for a future system that will assist the Cardiff University's Midwifery department in granting admission offers to qualified candidates and prospective students for a given academic year.

We aimed to create a simple application which minimises clicks and allows you to upload Excel Files containing applicant data and historical data. And in turn, it provides you a table of applicants, candidate profiles and a summary bar with essential information.

## Team 11
- Faisal Alshathri
- Richard Willie Ekong
- Nishchay Bhatt
- Yinzuo Tao
- Yibo Wang

## Database Configuration
This project is configured to operate with a volatile H2 database. But you can always replace
this configuration by editing the application.properties file with following entries below:

spring.datasource.url=jdbc:mysql://csmysql.cs.cf.ac.uk:3306/[your database name here]
spring.datasource.username=[your username]
spring.datasource.password=[your password]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

All other relevant dependencies have been mentioned in the **build.gradle** and the **application.properties** files available in the main/resources.


## How to build this web application with Intellij IDE
Unzip the **Team11Bundle.zip**, open in intellij IDE and run the **Team11RecruitmentAndAdmissionsApplication.java** file located at
src\main\java\uk\ac\cf\cs\nsa\msc\web\team11recruitmentandadmissions directory.

## How to build this web application with with gradle
- So can install Java Development toolkit (JDK)
- Set the paths to Java_HOME and JDK bin on your device.
Consult this [guide](https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html)
to set JDK path.
- Install gradle and set the paths to gradle's binary directory on your device.
Check this [guide](https://gradle.org/install/) for more detail on gradle installation
to install gradle
- Build the project by typing **gradle build** command
on your terminal from the project root directory

- run the project by typing  **gradle bootrun**
on your terminal from the project root directory

##How to set up this application for use
- After building and running this application
- Go to your chrome browser and type 'http://localhost:8080' on your browser's
address input text
- You should see a login page. Click on **create one!** to access the sign up page
- Provide your desired username and password and click on 'sign up button'
- Then you would be redirected back to the login page
- Provide you valid username and password, and click to 'login' button to access
the application.
- The first page will have no record. You have to click on the 'settings tab'
to access the settings page.
- Upload the sample records(sample-historical-data.xlxs and sample-current-admission-data.xlxs)
 from the projects 'src' directory by clicking the browse buttons. Use the first browser
 button to upload sample-historical-data.xlxs, and use the second browse button to
 upload the sample-current-admission-data.
- Then provide you desired value for candidates you wish to admit for the given academic year.

- Submit your changes by clicking the submit button.

- Head back to the candidates page by clicking the candidates tab above to see the list of
candidates for admission.
