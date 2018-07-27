def call(hostName) {
    echo "Publishing security event for Scan Failure"
    try {
        env.HOST_NAME = hostName
       
        echo "Failed Test for $HOST_NAME"
        JSONSTR = """
        {
            "event":"Security-Event",
            "hostName": "$HOST_NAME"
        }
        """
        publishEvent event: jsonEvent(JSONSTR), verbose: true
        
    } catch (e) {
        echo "Publishing security event failed.  Check that plugins are installed and configured correctly."
        throw e
    }
}
