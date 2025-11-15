mvn -T 2 -U "io.quarkus:quarkus-maven-plugin:create" `
        "-DprojectGroupId=com.demo.quarkus.orm" `
        "-DprojectArtifactId=customer" `
        "-DpackageName=com.demo.quarkus.jpa" `
        "-Dextensions=jdbc-mariadb, hibernate-orm"
