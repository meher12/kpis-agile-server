pipeline {
  agent  any
  tools {
     jdk "JDK_JENKINS"
     maven "MAVEN_JENKINS"
  }

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
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitserver', url: 'https://github.com/meher12/kpis-agile-server.git']]])
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
          sh 'mvn test' //'mvn -DskipTests'
          echo '-=- create the jar file for Akk -=-'
          sh 'mvn clean install'
        }
        post {
        always {
          junit '**/target/surefire-reports/TEST-*.xml'
        }
        }
    }

    stage('Sonar Scan') {
          steps {
            withSonarQubeEnv(installationName: 'sonarQu') {
          echo '-=- execute Sonarqube jacoco Scan -=-'
          sh 'mvn clean package  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml sonar:sonar'
            }
          }
    }

    stage('Sonar Quality Gate') {
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
            

            // script for development and jenkins
             sh '''
             echo ' Check for a running container, stop it, then remove it '
             '''
//             sh 'chmod +x crebuild.sh'
//             sh './crebuild.sh'
            sh 'docker-compose up'
//             sh 'docker compose up'

            // script for production mode
//             sh ' echo " Check if db container running" '
//             sh " echo ' Build apps in container ' "
//             sh 'docker network prune -f'
//             sh ' chmod +x dockercompose.sh'
//             sh ' ./dockercompose.sh'

            
            
      }
    }
  }
}
