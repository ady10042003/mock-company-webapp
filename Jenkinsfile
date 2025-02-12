pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
   agent any
   stages {
    stage("Test") {
      steps {
        sh 'gradlew test'
      }
    }
    stage("Build") {
      steps {
        sh 'gradlew build'
      }
    }
   }
}