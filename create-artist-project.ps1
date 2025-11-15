mvn -U "io.quarkus:quarkus-maven-plugin:create" `
        "-DprojectGroupId=com.demo.quarkus.jpa" `
        "-DprojectArtifactId=artist" `
        "-DpackageName=com.demo.quarkus.jpa" `
        "-Dextensions=jdbc-mysql, quarkus-agroal"
