# Lab04

## Kilka rad zanim zaczniesz
- Niniejsza instrukcja zaklada prace pod systemem Windows z zainstalowanym WSL2
- Aby korzystac z docker'a pod WSL2 nalezy zainstalowac aplikacje [Docker Desktop for Windows ](https://hub.docker.com/editions/community/docker-ce-desktop-windows)
- W trakcie uzywania poleceń docker'a w kontenerach w pipeline'ach moze sie pojawic problem z samopodpisanymi certyfikatami
- Istnieje kilka rozwiązań tego problemu:
    - Podzielenie się samopodpisanym certyfikatem ze światem 
    - Wyłączenie weryfikacji z użyciem TLS-a **(wybrane przeze mnie)**
    - Dodanie insecure-registries do daemon.json
- Musisz posiadać dostęp z uczelnianego konta do [platformy Azure'a](https://portal.azure.com)

## Kroki

### Konfiguracja DIND 
Podczas uruchamiania kontenera DIND należy dodać poniższą opcje. \
`--env DOCKER_TLS_VERIFY=1` \
Pozwoli to **wyłączyć** weryfikacje z użyciem TLS-a i pozbyć się problemu z samopodpisanymi certyfikatami. ![zrzut 00](screenshots/04/00-run-dind.png)


### Stwórz Jenkinsfile: opis
1. Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile
    - Z menu Jenkins'a wybieramy Nowy projekt i wybieramy typ Pipeline
    - W sekcji Pipeline wklejamy nasz skrypt [Jenkinsfile](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/SS306505/GCL/03/SS306505/Jenkinsfile)
   
   ![zrzut 01](screenshots/04/01-copy-paste-pipeline.png)
2. Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
    > **Budowanie następuje na kontenerze CI**
    > - Podejście to nie jest najlepsze, lecz najprostsze oraz najszybsze w implementacji
    > - Zastosowanie dedykowanego DIND pozwoliłoby na izolacje środowiska Jenkinsa i jego agenta
    > - Stosując dedykowany DIND uniknelibyśmy możliwych konfliktów wyeskponowanych portów

3. Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:
    - **albo pipeline zawiera treść Jenkinsfile'a** *(opisane w kroku 1)*
    - albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego

    ![zrzut 02](screenshots/04/02-working-pipeline.png)
### Jenkinsfile: przebieg
1. Przykładowe zbiory czynności w Jenkinsfile: Jednokrokowy pipeline (Build i test), pobierający narzędzie docker-compose i uruchamiajacy docker compose up na kompozycji z poprzednich zajęć
    - Aby uniknąć interakcji z menedżerem pakietów oraz problemów z brakiem uprawnień zrezygnowałem z instalacji docker-compose'a
    - Zamiast tego używam w Jenkinsfile-u komend dockerowych do budowania i uruchamianinia obrazów oraz powstałych z nich kontenerów

    ![zrzut 03](screenshots/04/03-docker-cmds.png)  
### Jenkinsfile: powiadomienia
1. Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
    > Rozwiązanie bazuje na znalezionym [tutorialu](https://www.youtube.com/watch?v=CGSwDpDfEMw) w którym użyto [Serwisu SMTP zapewnionego przez SendGrid](https://sendgrid.com/solutions/email-api/smtp-service/)
    
    |Krok|Opis|
    |-|-|
    |1|Należy najpierw skonfigurować serwer poczty|
    |2|Następnie ustawić potrzebne opcje (nazwa serwera smtp, dane do autentykacji, numer portu) w środowisku Jenkinsa (Manage Jenkins -> Configure System -> Extended E-mail Notification) |
    |3|W Jenkinsfile-u ustawić odbiorce, temat, zawartość maila oraz mime type|
    
    ![zrzut 04](screenshots/04/04-send-email-jenkinsfile.png)
    ![zrzut 05](screenshots/04/05-email-message.png)
    ![zrzut 06](screenshots/04/06-email-messages.png)
### Jenkinsfile: deploy
1. W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
    - Kandydatem do wydania będzie kontener dockerowy
    - Miejscem publikacji będzie Docker Hub
2. Dopisany został produkcyjny Dockerfile. \
   Jego zadania to:
    - Ściągnięcie aplikacji
    - Instalacja produkcyjnych zależności
    - Uruchomienie samej aplikacji

    ```Dockerfile
    FROM node:16-alpine as builder

    WORKDIR /usr/app

    ENV NODE_ENV=production

    RUN apk update --quiet
    RUN apk add git --quiet
    RUN git clone https://github.com/scoquix/quick-example-of-testing-in-nodejs.git
    RUN cd /usr/app/quick-example-of-testing-in-nodejs && npm install --only=prod

    FROM node:16-alpine

    WORKDIR /usr/app

    COPY --from=builder /usr/app/quick-example-of-testing-in-nodejs ./

    CMD [ "npm", "start" ]
    ```

3. Do Jenkinsfile dodano nowy stage o nazwie 'Publish on Docker Hub', który będzie budował produkcyjny obraz, logował się do Docker Hub'a i publikował zbudowany obraz.
    ```Groovy
    stage('Publish on DockerHub') {
        steps {
            script{
                try {
                    echo '\033[34;1mStarting publishing on DockerHub\033[0m'
                    sh '''
                    cd ${SRC_PATH}
                    docker build -t node-app-prod -f 04_Dockerfile_prod .
                    docker login -u $DOCKER_USR -p $DOCKER_PSW
                    docker tag node-app-prod $DOCKER_USR/node-app-prod
                    docker push $DOCKER_USR/node-app-prod
                    '''
                    echo '\033[34;1mPublished\033[0m'   
                } catch(e) {
                    currentBuild.result = "FAILED"
                    throw e
                }
            }
        }
        post {
            always {
                notifyBuild(currentBuild.result)
            }
        }
    }
    ```

4. Przed odpalenie pipeline'a należy jeszcze dodać credentiale dla Docker Huba do Jenkins'a
![zrzut 07](screenshots/04/07-added-credentials.png)
Dashboard -> Manage Jenkins -> Credentials -> Jenkins -> Global credentials (unrestricted) -> Add Credentials -> Username with Password

5. Uruchomienie pipeline'a z nowym krokiem
![zrzut 08](screenshots/04/08-added-publish-stage.png)

6. Wdrożenie obrazu odbędzie się na maszynie wirtualnej w chmurze Azure
    - Stwórz konto na platformie Azure
    - Utwórz maszynke wirtualną z Ubuntu
    - Skonfiguruj klucze SSH (analogicznie do procedury z laboratorium 1)

    ![zrzut 09](screenshots/04/09-azure-vm.png)
7. Dodaj credentiale dostępowe do maszyny wirtualnej w Jenkinsie
    - nazwe użytkownika
    - klucz prywatny SSH (możesz go wygenerować lokalnie lub przez Azure'a) 

    ![zrzut 10](screenshots/04/10-azure-creds.png)
8. Dodaj wtyczke SSH Agent Plugin do Jenkinsa
    ![zrzut 11](screenshots/04/11-ssh-agent-plugin.png)
9. W Jenkinsfile dodaj nowego stage'a, który będzie odpowiadał za wdrożenie
    ```Groovy
    stage("Deployment on Azure") {
        steps {
            script {
                try {
                    sshagent(credentials: ['AZURE_CREDENTIALS']) {
                        sh '''
                            ssh -o StrictHostKeyChecking=no seb@51.107.190.28 "
                                sudo docker system prune --force --all && 
                                sudo echo "$DOCKER_PSW" | docker login -u "$DOCKER_USR" --password-stdin &&
                                sudo docker run -p 80:80 -d $DOCKER_USR/node-app-prod
                            "
                        '''
                    }
                
                } catch (err) {
                    currentBuild.result = "FAILED"
                    throw e
                }
            }
        }
        post {
            always {
                notifyBuild(currentBuild.result)
            }
        }
    }
    ```
10. Uruchomienie pipeline'a z pełnym procesem
![zrzut 12](screenshots/04/12-completed-pipeline.png)

