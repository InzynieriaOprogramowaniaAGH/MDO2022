pipeline {
    agent any

    environment {
        USER_CREDENTIALS = credentials('dockerhub-auth')
        DEBIAN_CREDENTIALS = credentials('fzegle23-debian')
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
                        git checkout FZ307698
                        cd GCL/03/FZ307698/lab3
                        docker build -t ubuntu-node -f ./Dockerfile-build .
                        '''
                
                        def to = "filipmikolajgabriel@gmail.com"
                        def subject = "Jenkins - Step ${currentBuild.currentResult}"
                        def content = '${JELLY_SCRIPT,template="html"}'
                        emailext(body: content, mimeType: 'text/html', replyTo: '$DEFAULT_REPLYTO', subject: subject, to: to)
            }
            catch (err)
            {
            def to = "filipmikolajgabriel@gmail.com"
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
                cd MDO2022/GCL/03/FZ307698/lab3
                docker build -t ubuntu-test -f ./Dockerfile-test .
                docker run -e CI=true ubuntu-test
                '''
            def to = "filipmikolajgabriel@gmail.com"
            def subject = "Jenkins - Step ${currentBuild.currentResult}"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            }
            catch (err)
            {
            def to = "filipmikolajgabriel@gmail.com"
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
                cd MDO2022/GCL/03/FZ307698/lab3
                docker build -t fzegle23/MDO2022 -f ./Dockerfile-deploy .
                docker login -u $USER_CREDENTIALS_USR -p $USER_CREDENTIALS_PSW
                docker push fzegle23/MDO2022
                '''
                        def to = "filipmikolajgabriel@gmail.com"
            def subject = "Jenkins - Step ${currentBuild.currentResult}"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            }
            catch (err)
            {
            def to = "filipmikolajgabriel@gmail.com"
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
                    ssh -o StrictHostKeyChecking=no -T filipzeglen@192.168.1.51 "pwd && sudo docker kill deploy && sudo docker system prune -f -a && sudo docker login -u $USER_CREDENTIALS_USR -p $USER_CREDENTIALS_PSW && sudo docker run -p 80:80 --name deploy -d dedronek/mdo2022-dk"
                    '''
                        }
            
            def to = "filipmikolajgabriel@gmail.com"
            def subject = "Jenkins - Step ${currentBuild.currentResult}"
            def content = '${JELLY_SCRIPT,template="html"}'
            emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to)
            }
            catch (err)
            {
            def to = "filipmikolajgabriel@gmail.com"
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