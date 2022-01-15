# Zajęcia 04

## Wprowadzenie

### Zapewnij dostępność plików w gałęzi
* pliki pochodzą z repo: 
  * https://github.com/michalmuchakr/docker-react-test-app
  

* Skrypty, Docker files, kompozycje (jeżeli są), Jenkinsfile
  * docker-compose.yml
  * Dockerfile-build
  * Dockerfile-test
  
`[img] screen_1`

* dostępne w odpowiedniej gałęzi i katalogu w MDO2022
  * Wrzucone 

### Stwórz Jenkinsfile: opis
* Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile
`[img] screen_2`

* Docelowo, ma zawierać etapy "Build" i "Test"
* Przejściowo, pipeline napisany bezpośrednio w jenkins
  `[img] screen_3 - 4`

### Jenkinsfile: przebieg
* Przykładowe zbiory czynności w Jenkinsfile:
    * build
        * git pull
        * npm install
        * npm build
    * test
        * npm test
`[img] screen_5 - 6`

### Jenkinsfile: powiadomienia
* Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
    * krok 1 - logowanie do jenkinsa
      * `[img] screen_7`
    * krok 2 - Instalacja rozszerzenia do wysyłania emaili - Mailer
      * `[img] screen_8 - 12`
    * krok 3 - konfiguracja mailera
      * stworzone zostało konto do wysyłania maili:
        * mm301180.mail.sender@gmail.com
        * `[img] screen_13`
      * konfiguracja mailera w Jenkins
        * `[img] screen_14 - 15`
      * test konfiguracji
        * `[img] screen_16 - 17`
      * test mail z jenkinsFile
        * `[img] screen_16 - 17`
      * mail z pipeline
        * `[img] screen_18`

### Jenkinsfile: deploy
* W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
* Dodano nazwę użytkownika oraz token bezpieczeństwa z docekrhub
  * Manage Jenkins -> Manage Credentials -> Domains(global) -> Add Credentials.
    * `[img] screen_19 - 21`
* Stworzono repozytorium w dockerhub
  * `[img] screen_22`
* Pipeline z sukcesem wypycha obraz dokerowy na dockerhub'a
  * `[img] screen_23 - 26`
