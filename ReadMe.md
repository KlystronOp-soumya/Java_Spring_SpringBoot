## Defintion of porject
This project demostrates the basic concept of Spring Cloud Task framework. This project also structures the fundamental implementation for another quite broad project.

## Objective
<li>To demonstarte the Spring Cloud Task </li>
<li>To get familiarize with Cloud Tasks </li>
<li>Basic understanding of the Spring Cloud DataFlow Server</li>
<li> Basic understanding of Spring Cloud Task with Spring Batch</li>

## **How to run this project**
<li>Spring cloud and cloud dataflow server initializes a quite good number of Metadata Tables. For this project I have executed the below script directly copied from the github repository of Spring Cloud .</li>

> Link for schemas: [Task Schemas](https://github.com/spring-cloud/spring-cloud-dataflow/tree/main/spring-cloud-dataflow-server-core/src/main/resources/schemas)

<ol>
<li> To run the project clone this project and import this in eclipse.
<li>Establish connection with MySQL server.
<li> Execute all the scripts the skippers are not required though but considering any future scope execute the scripts for the Skipper Server.
<li> This project can be executed in two ways:
<ol>
<li>Using the eclipse/Maven command.
Maven command to execute: clean compile -DskipTests=true package install spring-boot:run
<li>Using the spring cloud dataflow server see below
</ol>
<li> Check the log files and the database a table named "Bill_STATEMENTS" will be created with a single entry.
</ol>

## **Execution in Spring Cloud DataFlow server**
### _Deployer configurations_:
deployer.batch-demo-task.spring.cloud.dataflow.features.streams-enabled=false
deployer.batch-demo-task.spring.cloud.dataflow.features.schedules-enabled=false
deployer.batch-demo-task.spring.cloud.dataflow.features.tasks-enabled=true
deployer.batch-demo-task.cpu=1
deployer.batch-demo-task.disk=100
deployer.batch-demo-task.local.working-directories-root=D:\Program Files\Spring\Spring Data Flow
deployer.batch-demo-task.memory=100
spring.cloud.dataflow.task.platformName=default

### _Running the Server_:
<li>Execute the below script in CMD. The Bash script will be similar something like this. Please follow the 'export' to set the environment variable.</li>
 @echo off
timeout 3 > nul

set PATH=.;C:\Program Files\Java\Jdk1.8\jdk1.8.0_281\bin
echo "Setting necessary environment varibales===================>"

set SPRING_CLOUD_DATAFLOW_FEATURES_STREAMS_ENABLED=false
set SPRING_CLOUD_DATAFLOW_FEATURES_SCHEDULES_ENABLED=false
set SPRING_CLOUD_DATAFLOW_FEATURES_TASKS_ENABLED=true
set spring_datasource_url=jdbc:mariadb://localhost:3306/CYOLASBCOM
set spring_datasource_username=Soumya
set spring_datasource_password=system
set spring_datasource_driverClassName=org.mariadb.jdbc.Driver
set spring_datasource_initialization_mode=always
set spring_flyway_enabled=false

timeout 3 > nul

start java -jar spring-cloud-dataflow-server-2.10.0.jar

> To download the jar Maven Central can be used

<li> 
launch the [DataFlow Server](http://localhost:9393/dashboard/)</li>

>**Follow further steps from here**: [Reference Document](https://dataflow.spring.io/docs/batch-developer-guides/)
and [Spring Cloud Task](https://docs.spring.io/spring-cloud-task/docs/2.4.5/reference/html/)

#### Step 1
![batch-task-demo_ss1](https://user-images.githubusercontent.com/69466192/212487783-3d4fca15-2ee6-4e4a-8499-6e92b28c0ec0.JPG)

#### Step 2
![batch-task-demo_ss2](https://user-images.githubusercontent.com/69466192/212487789-1b6233f0-102b-41ac-8802-baa71369115d.JPG)

#### Step 3
![batch-task-demo_ss3](https://user-images.githubusercontent.com/69466192/212487792-8af4b52f-63b9-4c82-8f7f-d434a55f3897.JPG)

## **Integration for composite task**
Combine this project  with [SpringCloudTask Demo](https://github.com/KlystronOp-soumya/Java_Spring_SpringBoot.git) in order to create a _composite task_ in spring cloud data flow server.

<li> <b>Steps to create composite task</b>
<ol>
<li>Start the Spring cloud data flow server</li>
<li>Deploy the projects SpringCloudTask_Demo and SpringCloudBatchTask_Demo</li>
<li>Create the pipeline as depicted in the picture below</li>
<li>Launch the task</li>
<li>Look for the logs defined in the deployer configuration above
</ol>

> Also for composite tasks refer to this link:
[Spring Cloud Task](https://docs.spring.io/spring-cloud-task/docs/2.4.5/reference/html/)

![ct-bill_process_ss1](https://user-images.githubusercontent.com/69466192/212487802-37b77691-c0e8-4f6a-9810-f0616c0560bb.JPG)

