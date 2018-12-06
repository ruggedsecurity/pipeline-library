def call(domain) {
    echo "Publishing event for Security Scan of ${domain}"
    try {
        JSONSTR = """
    {
        "event":"REQUEST_SECURITY_SCAN:$domain",
        "appName": "$domain",
        "type": "SECURITY_SCAN",
    }
    """
    } catch (e) {
        echo "Cannot format JSON message"
        throw e
    }
    
    try {
        publishEvent event: jsonEvent(JSONSTR), verbose: true
    } catch (e) {
        echo "Publishing event failed.  Check that plugins are installed and configured correctly."
        throw e
    }
}
