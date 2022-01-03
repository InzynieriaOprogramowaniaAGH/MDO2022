# Lab04

## Kilka rad zanim zaczniesz
- Niniejsza instrukcja zaklada prace pod systemem Windows z zainstalowanym WSL2
- Aby korzystac z docker'a pod WSL2 nalezy zainstalowac aplikacje [Docker Desktop for Windows ](https://hub.docker.com/editions/community/docker-ce-desktop-windows)
- W trakcie uzywania poleceń docker'a w kontenerach w pipeline'ach moze sie pojawic problem z samopodpisanymi certyfikatami
- Istnieje kilka rozwiązań tego problemu:
    - Podzielenie się samopodpisanym certyfikatem ze światem 
    - Wyłączenie weryfikacji z użyciem TLS-a **(wybrane przeze mnie)**
    - Dodanie insecure-registries do daemon.json

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
> Oczekiwanie na kontakt z prowadzącym w celu ustalenia strategii wdrożenia