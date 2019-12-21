pipeline {
  agent {
    docker {
      image 'markhobson/maven-chrome'
    }
  }
  stages {
    stage('Automation Test'){
      steps {
        sh 'mvn clean install -Dheadless=true -Ddocker=true'
      }
    }
  }
  post {
    always {
      archiveArtifacts(artifacts: 'target/', fingerprint: true)
      junit 'target/cucumber.xml'
    }
  } 
}

