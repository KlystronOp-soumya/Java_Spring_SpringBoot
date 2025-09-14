# Description 
Simple Http and Azure Storage queue implementation in Java
[Go through https://learn.microsoft.com/en-us/azure/azure-functions/functions-reference?tabs=blob&pivots=programming-language-java for more]

## How To
<p>
    <ul>
        <li> Create the project using Maven:
        <p>
            mvn archetype:generate \
            -DarchetypeGroupId="com.microsoft.azure" \ -DarchetypeArtifactId="azure-functions-archetyp"\
             -DarchetypeVersion="LATEST" -DjavaVersion="17" \ -DgroupId="com.demo.azure" \  -DartifactId="HttpQueueTriFuncApp" \    -Dversion="1.0-SNAPSHOT" -Dpackage="com.demo.azure" \ -DappName="HttpQueueTriFuncApp-1757589469951" -B
        </p>
        <li> set up the storage emulator : download the offical storage emulator
        <li> use docker azurite

</p>