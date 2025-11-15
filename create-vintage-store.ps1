mvn -U "io.quarkus:quarkus-maven-plugin:create" `
        "-DprojectGroupId=com.demo.quarkus.orm" `
        "-DprojectArtifactId=vintage-store" `
        "-DpackageName=com.demo.quarkus.panache" `
        "-Dextensions=jdbc-postgresql, hibernate-orm-panache"
