def call() {
    echo "Publishing event for Maven build"
    def jsonStr = ""
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
        jsonStr = """
    {
        "event":"$POM_GROUP:$POM_ARTIFACT:$POM_VERSION:$POM_PACKAGE",
        "artifactId": "$POM_ARTIFACT",
        "groupId": "$POM_GROUP",
        "version": "$POM_VERSION",
        "package": "$POM_PACKAGE"
    }
    """
    } catch (e) {
        echo "Cannot access pom.xml file"
        throw e
    }
    
    try {
        publishEvent event: jsonEvent(jsonStr), verbose: true
    } catch (e) {
        echo "Publishing event failed.  Check that plugins are installed and configured correctly."
        throw e
    }
}
