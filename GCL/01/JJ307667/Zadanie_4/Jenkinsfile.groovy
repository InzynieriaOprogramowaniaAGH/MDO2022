pipeline {
    agent any
    environment {
        DOCKER_HUB_LOGIN = credentials('docker-hub-login')
        DOCKER_HUB_PASSWORD = credentials('docker-hub-password')
    }
    stages {
        stage('Build') {
            steps {
                sh '''
		        rm -rf MDO2022/
                git clone https://github.com/InzynieriaOprogramowaniaAGH/MDO2022.git
                cd MDO2022
                git checkout JJ307667
                cd GCL/01/JJ307667/Zadanie_2
                docker build -t ubuntu_react . -f ./Dockerfile-build
                '''
            }

            post {
                success {
                    mail to: 'jagiellojedrzej@gmail.com', subject: "Jenkins pipeline (MDO2022) - build stage - passed", body: "Build has been finished successfully - ${BUILD_URL}"
                }
                failure {
                    mail to: 'jagiellojedrzej@gmail.com', subject: "Jenkins pipeline (MDO2022) - build stage - failed", body: "Build has been finished with fail - ${BUILD_URL}"
                }
            }
        }
        stage('Test'){
            steps{
                sh '''
                cd MDO2022/GCL/01/JJ307667/Zadanie_2
                docker build -t ubuntu_react_test . -f ./Dockerfile-test
                '''
            }

            post {
                success {
                    mail to: 'jagiellojedrzej@gmail.com', subject: "Jenkins pipeline (MDO2022) - test stage - passed", body: "Test of MDO2022 pipeline has been finished successfully - ${BUILD_URL}"
                }
                failure {
                    mail to: 'jagiellojedrzej@gmail.com', subject: "Jenkins pipeline (MDO2022) - test stage - failed", body: "Test of MDO2022 pipeline has been finished with fail - ${BUILD_URL}"
                }
            }
        }
        stage('Publish'){
            steps{
                sh '''
                cd MDO2022/GCL/01/JJ307667/Zadanie_2
                docker login -u $DOCKER_HUB_LOGIN -p $DOCKER_HUB_PASSWORD
                docker build -t luckymylove/mdo2022_jedrzej_jagiello -f ./Dockerfile-deploy .
                docker push luckymylove/mdo2022_jedrzej_jagiello
                '''
            }

            post {
                success {
                    mail to: 'jagiellojedrzej@gmail.com', subject: "Jenkins pipeline (MDO2022) - publish stage - passed", body: "Publish of MDO2022 pipeline has been finished successfully - ${BUILD_URL}"
                }
                failure {
                    mail to: 'jagiellojedrzej@gmail.com', subject: "Jenkins pipeline (MDO2022) - publish stage - failed", body: "Publish of MDO2022 pipeline has been finished with fail - ${BUILD_URL}"
                }
            }
        }
        stage('Deploy'){
            steps {
                sshagent(credentials: ['deploy_machine']) {
                    sh '''
                    ssh -o StrictHostKeyChecking=no -T root@192.168.0.158 "pwd && sudo docker kill deploy && sudo docker system prune -f -a && sudo docker login -u $DOCKER_HUB_LOGIN -p $DOCKER_HUB_PASSWORD && sudo docker run -p 80:80 --name deploy -d luckymylove/mdo2022_jedrzej_jagiello"
                    '''
                }
            }

            post {
                success {
                    mail to: 'jagiellojedrzej@gmail.com', subject: "Jenkins pipeline (MDO2022) - deploy stage - passed", body: "Deploy of MDO2022 pipeline has been finished successfully - ${BUILD_URL}"
                }
                failure {
                    mail to: 'jagiellojedrzej@gmail.com', subject: "Jenkins pipeline (MDO2022) - deploy stage - failed", body: "Deploy of MDO2022 pipeline has been finished with fail - ${BUILD_URL}"
                }
            }
        }
    }
}


