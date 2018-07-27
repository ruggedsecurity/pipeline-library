def call(hostName) {
    echo "Publishing security event for Scan Failure"
    try {
        env.HOST_NAME = ${hostName}
       
        echo "Failed Test for $APP_NAME"
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
    try {
        mail to: 'bmcconnell@cloudbees.com', subject: 'The Security Test failed :(', body: 'Security failed'
    } catch (e) {
        echo "Sending security event failed.  Check SMTP Settings."
        throw e
    }
}
