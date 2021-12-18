pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh '''
                echo 'Starting building'
                apt-get update
                apt-get install git
                apt-get install npm -y
                rm -rf nodeunit
                git clone https://github.com/caolan/nodeunit.git
                cd nodeunit && npm install
                npm test
                '''
            }
        }
        
    }
    
}
