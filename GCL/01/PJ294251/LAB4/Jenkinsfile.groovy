pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                        sh '''
                        rm -rf MDO2022
                        git clone https://github.com/InzynieriaOprogramowaniaAGH/MDO2022.git
                        cd MDO2022
                        git checkout PJ294251
                        cd GCL/01/PJ294251/lab2
                        docker build -t app . -f ./Dockerfile-build
                        '''
                }
        }
        
        stage('Test') {
            steps {
                        sh '''
                        cd MDO2022/GCL/01/PJ294251/lab2
                        docker build -t test . -f ./Dockerfile-test
                        '''
                }
        }
        
        stage('Publish'){
            steps{
                script{
                    
                    try{
                        sh '''
                        cd MDO2022/GCL/01/PJ294251/lab2
                        docker build -t piter9669/mdo2022 -f ./Dockerfile-deploy.dockerfile .
                        docker login -u $USER_CREDENTIALS_USR -p $USER_CREDENTIALS_PSW
                        docker push piter9669/mdo2022 
                        '''
                    }
                    catch(error){}
                }
            }
        }
        
        stage('Deploy'){
            steps{
                script{
                    
                    try{

                        sh '''
                        cat ~/docker_pass.txt | docker login --username piter9669 --password-stdin
                        docker tag app:latest piter9669/mdo2022_deploy
                        docker push piter9669/mdo2022_deploy
                        
                        '''
                    }
                    catch(error){
                        
                    }
                }
            }
            post {
                    success{
                        mail to: 'piotr.jal9@gmail.com', subject: "Deploy OK", body: "Deploy OK: ${BUILD_URL}"
                    }
                    failure{
                        mail to: 'piotr.jal9@gmail.com', subject: "Deploy ERROR", body: "Deploy ERROR: ${BUILD_URL}"
                    }
                }
        }
                
        
                
    }
}

