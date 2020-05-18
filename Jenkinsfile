pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        cleanWs(cleanWhenSuccess: true)
        sh 'clean compile'
      }
    }

    stage('Test') {
      steps {
        sh 'test'
      }
    }

  }
}