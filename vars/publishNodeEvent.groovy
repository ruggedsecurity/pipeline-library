def call() {
    echo "Publishing event for Node build"
    try {
        def json = readJSON file: 'package.json'
        VERSION = json['version']
        APP_NAME = json['name']
       
        echo "Building $APP_NAME"
        echo "Building Version=$VERSION"
        JSONSTR = """
    {
        "event":"$APP_NAME:$VERSION",
        "appName": "$APP_NAME",
        "type": "PUB",
        "version": "$VERSION"
    }
    """
    } catch (e) {
        echo "Cannot access package.json file"
        throw e
    }
    
    try {
        publishEvent event: jsonEvent(JSONSTR), verbose: true
    } catch (e) {
        echo "Publishing event failed.  Check that plugins are installed and configured correctly."
        throw e
    }
}
