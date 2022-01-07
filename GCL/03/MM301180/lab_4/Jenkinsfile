pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                sh('docker-compose build --no-cache builder')
            }
        }
        stage('test') {
            steps {
                sh('docker-compose build --no-cache tester')
            }
        }
    }
}
