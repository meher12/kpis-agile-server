pipeline {
  agent  any

  //triggers {
    //    pollSCM ('* * * * *')
   // }

    /* node {
         label 'linux'
         customWorkspace '/home/meher/Documents/StockHere/dd'
     } */

  environment {
        //PATH = "$PATH:/usr/local/bin/"
        COMPOSE_PROJECT_NAME = "${env.JOB_NAME}-${env.BUILD_ID}"
        COMPOSE_FILE = 'docker-compose.yml'
  }

  options {
    buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '5', numToKeepStr: '5')
  }

  stages {
    stage('Checkout Backend') {
        steps {
        //define scm connection for polling
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'kpiswiseserver', url: 'https://github.com/meher12/kpis-agile-server.git']]])
        }
    }

    stage('Build') {
        steps {
          echo '-=- compiling project -=-'
          sh 'mvn clean compile'
        }
    }

    stage('Unit Test') {
        steps {
          echo '-=- execute unit tests -=-'
          sh 'mvn test'
        }
        post {
        always {
          junit '**/target/surefire-reports/TEST-*.xml'
        }
        }
    }

    stage('Scan') {
          steps {
            withSonarQubeEnv(installationName: 'sonar') {
          echo '-=- execute Sonarqube jacoco Scan -=-'
          sh 'mvn clean package  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml sonar:sonar'
            }
          }
    }

    stage('Quality Gate') {
        steps {
          timeout(time: 2, unit: 'MINUTES') {
            script {
            echo '-=- Get Sonarqube Quality Gate -=-'
              def qg = waitForQualityGate()
            if (qg.status != 'OK' || qg.status == 'WARN') {
              error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
            }

        // waitForQualityGate abortPipeline: true
         }
        }
    }

    /* stage('Tooling Docker versions') {
       steps {
         sh '''
           docker --version
          docker-compose --version
       '''
       }
    } */

    stage('Docker Deploy') {
      steps {
            //sh "docker-compose -f ${env.COMPOSE_FILE} build"
            //sh 'docker-compose up'
            sh 'docker-compose up --force-recreate --build'
      //sh "docker-compose build"
      }
    }
  }
}
