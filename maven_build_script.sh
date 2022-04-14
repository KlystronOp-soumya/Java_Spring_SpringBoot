echo "Starting Maven...."
mvn -T 4 clean compile -DskipTests=true exec:java
