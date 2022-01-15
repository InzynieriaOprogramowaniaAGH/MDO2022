pipeline {
    agent any
    environment {
        registry = "generalcube/mm301180_dev_ops"
        registryCredential = 'generalcube'
    }
    stages {
        stage('build') {
            steps {
                script{
                    try {
                        sh 'docker-compose build --no-cache builder'
                    } catch(e) {
                        throw e
                    }
                }
            }
        }
        stage('test') {
            steps {
                script{
                    try {
                        sh 'docker-compose build --no-cache tester'
                    } catch(e) {
                        throw e
                    }
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        sh 'docker push generalcube/mm301180_dev_ops:rc01'
                    }
                }
            }
        }
    }
    post {
        success {
            mailSender('Build finish successfully!')
        }
        unstable {
            mailSender('Build finish unstable!')
        }
        failure {
            echo 'Build failed sad-frog.jpg!'
            mailSender('Build failed sad-frog.jpg!')
        }
        changed {
            echo 'Things were different before...'
            echo 'Interesting...'
        }
    }
}

def mailSender(String buildStatus) {
    emailext (
        mimeType: 'text/html',
        subject: "${buildStatus}: of build [${env.BUILD_NUMBER}]'",
        body: "Link to build: (${env.BUILD_URL})",
        to: 'michal.mucha.kr@gmail.com'
    )
}
