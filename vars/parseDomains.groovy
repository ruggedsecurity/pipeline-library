def parseDomains(data) {
    // readJSON returns a non-serializable object, which we need to
    // iterate over and store into a new list because pipelines require
    // serializable data.
    configs = []
    for (el in data) {
        configs << el
    }
    return configs
}
