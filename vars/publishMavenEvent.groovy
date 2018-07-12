def call() {
    echo "Publishing event for Maven build"
    try {
        def pom = readMavenPom file: 'pom.xml'
        POM_VERSION = pom.version
        POM_ARTIFACT = pom.artifactId
        POM_GROUP = pom.groupId
        POM_PACKAGE = pom.packaging
        echo "Building $JOB_NAME"
        echo "Building Version=$POM_VERSION"
        echo "Building Artifact=$POM_ARTIFACT"
        echo "Building Package=$POM_PACKAGE"
        echo "Building Group=$POM_GROUP"
    } catch (e) {
        echo "cannot access pom.xml file"
    }
    
    try {
        publishEvent event: simpleEvent("$POM_GROUP:$POM_ARTIFACT:$POM_VERSION:$POM_PACKAGE"), verbose: true
    } catch (e) {
        echo "Publishing event failed"
    }
}
