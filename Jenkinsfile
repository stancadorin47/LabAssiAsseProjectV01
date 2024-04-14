pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/stancadorin47/LabAssiAsseProjectV01.git'
                sh './mvnw clean compile'
            }
        }

        stage('Test'){
            steps{
                sh './mvnw test'
            }

            post{
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

    }
}
