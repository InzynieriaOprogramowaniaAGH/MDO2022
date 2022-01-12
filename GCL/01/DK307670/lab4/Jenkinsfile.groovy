pipeline {
    agent any

    environment {
        USER_CREDENTIALS = credentials('dockerhub-auth')
        DEBIAN_CREDENTIALS = credentials('dawidkedron-debian')
    }
    stages {
        stage('Build') {
            steps {
                script {
                    try {
                        sh '''
                        rm -r MDO2022
                        git clone https://github.com/InzynieriaOprogramowaniaAGH/MDO2022.git
                        cd MDO2022
                        git checkout DK307670
                        cd GCL/01/DK307670/lab2
                        docker build -t ubuntu-node -f ./Dockerfile-build .
                        '''
                
                        def to = "dk.dedron@gmail.com"
                        def subject = "Jenkins - Step ${currentBuild.currentResult}"
                        def content = '${JELLY_SCRIPT,template="html"}'
                        emailext(body: content, mimeType: 'text/html', replyTo: '$DEFAULT_REPLYTO', subject: subject, to: to)
            }
            catch (err)
            {
            def to = "dk.dedron@gmail.com"
            def subject = "Jenkins - Build FAILED"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            
            currentBuild.result = 'ABORTED'
            error('Stopping build…') 
            }
        }
        }
        }
        stage('Test') {
            steps {
            script {
            try {
                sh '''
                cd MDO2022/GCL/01/DK307670/lab2
                docker build -t ubuntu-test -f ./Dockerfile-test .
                docker run -e CI=true ubuntu-test
                '''
            def to = "dk.dedron@gmail.com"
            def subject = "Jenkins - Step ${currentBuild.currentResult}"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            }
            catch (err)
            {
            def to = "dk.dedron@gmail.com"
            def subject = "Jenkins - Build FAILED"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            
            currentBuild.result = 'ABORTED'
            error('Stopping build…') 
            }
        }
        }
        }
        stage('Publish') {
            steps {
            script {
            try {
                sh '''
                cd MDO2022/GCL/01/DK307670/lab2
                docker build -t dedronek/mdo2022-dk -f ./Dockerfile-deploy .
                docker login -u $USER_CREDENTIALS_USR -p $USER_CREDENTIALS_PSW
                docker push dedronek/mdo2022-dk
                '''
                        def to = "dk.dedron@gmail.com"
            def subject = "Jenkins - Step ${currentBuild.currentResult}"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            }
            catch (err)
            {
            def to = "dk.dedron@gmail.com"
            def subject = "Jenkins - Build FAILED"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            
            currentBuild.result = 'ABORTED'
            error('Stopping build…') 
            }
        }
        }
        }
        stage('Deploy') {
            steps {
            script {
            try {
                    sshagent(credentials: ['debian-sh']) {
                    sh '''
                    ssh -o StrictHostKeyChecking=no -T dawidkedron@192.168.1.40 "pwd && sudo docker kill deploy && sudo docker system prune -f -a && sudo docker login -u $USER_CREDENTIALS_USR -p $USER_CREDENTIALS_PSW && sudo docker run -p 80:80 --name deploy -d dedronek/mdo2022-dk"
                    '''
                        }
            
            def to = "dk.dedron@gmail.com"
            def subject = "Jenkins - Step ${currentBuild.currentResult}"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            }
            catch (err)
            {
            def to = "dk.dedron@gmail.com"
            def subject = "Jenkins - Build FAILED"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            
            currentBuild.result = 'ABORTED'
            error('Stopping build…') 
            }
        }
        }
        }
    }
}