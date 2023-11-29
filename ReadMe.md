#### Description
This project demonstartes the Spring MVC and Quartz scheduler integration.

#### Objective
- Spring MVC
- Quartz Scheduler
- J2EE
- understanding listeners in J2EE

#### Future Scope
- Job Execution could be controlled using an endpoint

## How to execute:
- #### Using Eclipse
    - Add the tomcat server
    - Allocate requiered heap space: -Xms256m -Xmx512m
    - Start the server
    - Wait for 10 seconds and monitor the console
- #### Using Maven [+Eclipse & Tomcat]
    - execute the goals: mvn clean compile package
    - get the generated WAR file
    - open Apache Tomcat manager-gui
    - deploy the artifact
    - monitor eclipse console