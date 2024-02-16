#### Desription
 Simple Spring boot application to demonstrate the centralized logging for the Microservices.

##### Objective
 To understand the concepts and necessity of the centralized logging for the micorservice architechture

#### Features
- Spring Boot
- Spring Rest
- Lombok
- ELK stack [Check Out Documentation](https://www.elastic.co/elastic-stack/)

### How to run
- The Application it self:<p> Clone the repository.
run: mvn clean compile spring-boot:run
</p>
<p>
check the end points:
<blockQuote>
<p>curl localhost:8093/ElkDemo/api/v1/allAgents</p>
<p>curl localhost:8093/ElkDemo/api/v1/agentById?id=123</p>
</blockQuote>
</p>
<p>
<ul>
<li> The ELK stack configuration -- elasticsearch </li>
<ol>
<li>uncomment the port: E:\Program Files\Spring\Observability\Centralized Logging\elasticsearch-8.12.1\config\elasticsearch.yml </li>
<li> change the settings to xpack.security.enabled: false </li>
<li>go to:elasticsearch-8.12.1\bin and open command line / terminal and execute the elasticsearch.bat file </li>
<li>check: http://localhost:9200/
</ol>
<li>The ELK stack configuration -- logstash</li>
<ol type="a">
<li> create the logstash.cong file</li>
<li> add the required configurations
<li> place the .conf file outside the logstash folder
<li> <blockQuote> execute: .\bin\logstash -f .\logstash.conf
</ol>
</ul>
</p>

### After config steps
<ul>
<li> get the menu: http://localhost:9200/_cat
<li> get the indecis: /_cat/indices
<li> copy the indices name: ds-logs-generic-default-2024.02.16-000001
<li> serach it in elasticsearch: 
http://localhost:9200/.ds-logs-generic-default-2024.02.16-000001/_search
<li> Go to Management -> Data Views -> add index -> set timestamp to "i dont want to use" -> give a name of the Data view
<li> get to "Discover" -> look for the set data view
<li> check the logs</li>
</ul>

### Build the docker image
<ol>
<li>Open win+r-> type: services.msc -> start the docker service</li>
<li>execute the docker.exe to initiate the Daemon</li>
<li>Create the dockerfile inside the project folder: "Dockerfile"</li>
<li> Open powershell under the project folder and execute:
<blockQuote>docker build -t agent-example . </blockQuote>
<li> bind the port as: 8093 : 8093
<li> check in the browser: localhost:8093/ElkDemo/api/v1/allAgent
</ol>