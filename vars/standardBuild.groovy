// defined in /vars/standardBuild.groovy
def call(Map config=[:], body) {
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()
  def toolHome = tool config.tool
  withEnv([
    "PATH=${toolHome}/bin:${env.PATH}"
  ]) {
    stage('checkout') {
      checkout scm
    }
    stage('main') {
      sh config.mainScript
    }
    stage('post') {
      sh config.postScript
    }
  }
}
