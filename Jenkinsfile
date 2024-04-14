pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/stancadorin47/LabAssiAsseProjectV01.git'
                sh './mvnw clean compile'
            }
        }
    }
}
