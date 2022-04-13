mvn  -T 4 -DskipTests=true -o clean compile package 
cd target
java -jar UltimateSpringBootBatchDemo-0.0.1-SNAPSHOT.jar BatchRunnerApp
