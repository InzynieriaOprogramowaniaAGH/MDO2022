# Metodyki DevOps - Projekt zaliczeniowy II

## Zadania do wykonania

1.  ### Znaleźć wśród repozytoriów GitHuba projekt zawierający mechanizm budowania (Automake, Meson, npm...) oraz testy jednostkowe:

       Wybrane zostało repozytorium https://github.com/jterkalski/react-app, które zawiera postawowy projekt utworzyny przez *create-react-app* - domyślny mechanizm buildowania i testy jednostkowe.  
       Projekt posiada licencję **GNU GPL wersji 3** pozwalającą na wykorzystanie w szerokim zakresie, w tym możliwość do forkowania i modyfikowania na własne potrzeby.
       
2.  ### Wykazać w środowisku Docker, że program jest możliwy do zbudowania, a testy przechodzą:
      
      Wymaganiami wstępnymi będzie pobranie obrazu jakiegoś systemu (w moim przypadku będzie to Ubuntu), następnie pobranie narzędzi, które pozwolą na pobranie i zbudowanie projektu, czyli gita oraz nodejs
      
      
      ## Build programu i wykonanie testów w kontenerze: 
      
      1. Pobieram obraz Ubuntu:  
       `docker pull ubuntu`  
       ![Pobranie obrazu ubuntu](screenshots/1.pull_ubuntu.png)  
      
      2. Tworzę kontener z tego obrazu (z flagą **-it**, żeby móc działać na jego konsoli):  
       `docker run -it ubuntu`  
       ![Utworzenie kontenera z obrazu ubuntu](screenshots/2.run_ubuntu_container.png)  

       3. W kontenerze aktualizuję listę paczek apt-get:  
        `apt-get update`  
       ![Aktualizacja paczek apt-get](screenshots/3.apt-get_update.png)  
       
       4. Do wybranego projektu potrzebujemy node.js w wersji 14+ (domyślnie pobiera się wersja 10), więc pobieram narzędzie **curl** i używam go do zaciągnięcia stosownej paczki:  
       `apt-get install curl -y`  
       ![Instalacja curl](screenshots/4.1.install_curl.png)  
       
       `curl -fsSl https://deb.nodesource.com/setup_current.x | bash -`  
       ![Pobranie paczki z nodejs](screenshots/4.2.curl_latest_nodejs.png)  
       
       5. Następnie zaciągam do naszego kontenera **gita** i **nodejs** (przy okazji pobierze się npm, bez kroku 4 trzeba pobrać go oddzielnie):  
       `apt-get install git nodejs -y`  
       ![Instalowanie paczek potrzebnych do uruchomienia projektu](screenshots/5.1.git_nodejs_install.png)
         
       Sprawdzam czy prawidłowo się pobrało:  
       ![Instalowanie paczek potrzebnych do uruchomienia projetku](screenshots/5.1.git_nodejs_install.png)  
       
       6. Sklonowanie wybranego repozytorium z GitHuba:  
       `git clone https://github.com/jterkalski/react-app.git`  
       ![Klonowanie projektu](screenshots/6.clone_repo.png)  
       
       7. Przechodzę do katalogu głównego projektu i instaluję zależności:  
       `cd /react-app/`  
       `npm install`  
       ![Instalowanie zależności](screenshots/7.npm_install.png)  
       
       8. Buduję projekt przy użyciu komendy:  
       `npm run build`  
       ![Instalowanie zależności](screenshots/8.npm_build.png)  
       
       9. Wykonuję testy stworzone w projekcie (**a** powoduje wykonanie wszystkich testów. Po wywołaniu komendy wyniki wyświetlają się w odświeżonym ekranie):  
       `npm test a`  
       ![Komenda do testów](screenshots/9.1.npm_test.png)  
       ![Wyniki testów](screenshots/9.2.npm_test_result.png)  
       
       
       ## Dockerfile
       
       1. Dockerfile umożliwiający ponowienie buildu bez konieczności przygotowania środowiska:  
       ![Dockerfile-build](screenshots/10.1.Dockerfile-build.png)  
       
       Dodanie **ENV DEBIAN_FRONTEND="noninteractive"** powoduje mniejszą ilość komunikatów.  
       
       Kolejny punkt, w którym trzeba było dostarczyć Dockerfile uruchamiający testy, oparty o poprzedni Dockerfile (dziedziczący po nim) można było rozumieć na kilka sposobów - czyli można stworzyć obraz z Dockerfile-build i użyć go w Dockerfile-tests, ale wykonałem to dosłownie - przepisałem poprzedniego dockerfile i rozszerzyłem stage'a o wykonanie testów.  
       
       2. Dockerfile rozszerzony o testy:  
       ![Dockerfile-tests](screenshots/10.2.Dockerfile-tests.png)  


       ## Docker-compose
       
       1. Aby móc używać docker-compose, na początku pobieram odpowiednie naczędzia (kierując się poradnikiem na stronie https://docs.docker.com/compose/install/):  
       `sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`  
       `sudo chmod +x /usr/local/bin/docker-compose`  
       ![Pobranie docker-compose](screenshots/11.1docker-compose.png)  
       
       2. Kompozycja uruchamiająca dockerfile z poprzednich kroków:  
       ![Dockerfile-tests](screenshots/11.2.docker-compose_content.png)  
       
       3. Uruchamiam kompozycję:  
       `docker-compose up --build`  
       ![Wynik docker-compose 1](screenshots/12.docker-compose_result_1.png)  
       ![Wynik docker-compose 2](screenshots/12.docker-compose_result_2.png)  
       ![Wynik docker-compose 3](screenshots/12.docker-compose_result_3.png)  
       
       Nie było to pierwsze uruchomienie, więc większość została pobrana z cashe:) Możemy też zauważyć, że duplikacja kodu pierwszego dockerfile nie powodowała ponownego budowania - je też pobrało z cache'a.  
       Budowanie projektu oraz wykonanie testów przebiegło pomyślnie. 


3.  ### Wykazać, że zbudowany program można uruchomić i wykorzystać  
       
       W celu uruchomienia i wykorzystania programu, dodałem do Dockerfile-build komendę `npm start`, która uruchamia aplikację i zapisałem jako nowy Dockerfile-start i zbudowałem za pomocą komendy `docker build react-start -f Dockerfile-start .` :  
       ![Dockerfile-start](screenshots/13.1.dockerfile-start.png)  
       
       
       Wybrana aplikacja jest aplikacją webową, więc w celu sprawdzenia działania musimy uruchomić ją na zewnątrz kontenera. W tym celu tworzymy kontener z przekierowaniem portu 3000, na której działa aplikacja na port 3001 hosta.  
       `docker run -dp 3001:3000 react-start`  
       
       Przedstawienie działania aplikacji na **localhost:3001**:  
        ![Działająca aplikacja](screenshots/13.2.npm_start.png)


3.  ### Zainstalować w środowisku linuksowym, z wykorzystaniem Dockera, automatyzator Jenkins

       W celu instalacji Jenkinsa wykorzystano instrukcję znajdującą się na stronie: https://www.jenkins.io/doc/book/installing/docker/  
       
       1. Tworzę sieć **jenkins** oraz kontener ze składnikiem **DIND**:  
       `docker network create jenkins`  
        ![jenkins i dind](screenshots/14.1.jenkins_dind.png)
       
       
       2. Sprawdzam czy został utworzony:  
        `docker ps`  
        ![dind](screenshots/14.2.dind.png)  
       
       
       3. Następny krok to stworznie **Dockerfile-jenkins**, pozwalający stworzyć Blueocean. Obraz z dockerfil'a tworzymy używając komendy: `docker build myjenkins-blueocean - f Dockerfile-jenkins .`  
        ![Blueocean dockerfile](screenshots/15.1.jenkins-blueocean.png)  
       
       
       Tworzę kontener z **Blueocean**:  
        ![Blueocean dockerfile](screenshots/15.2.jenkins-blueocean_run.png)  
       
       
       # Jenkins  
       
       W celu skonfigurawania **Jenkinsa** pobieram klucz wykorzystując komendę `sudo docker exec jenkins-blueocean cat /var/jenkins_home/secrets/initialAdminPassword`, następnie przechodzę na `localhost:8080` i wpisuję usyskane hasło:   
        
       ![Jenkins pass](screenshots/16.jenkins_pass.png)  
       
       
       Następnie tworzę użytkownika i instaluję domyślne wtyczki:  
       
       ![Nowy user](screenshots/17.1.create_jenkins_admin.png)   
       ![Domyślna konfiguracja](screenshots/17.2.jenkins_configuration.png)   
        
        
       ## Uruchomienie kompozycji w Jenkinsie 
       
       ### 1. W celu wykorzystania docker-compose w Jenkinsie instaluję przechodzę do konsoli stworzonego kontenera wykorzystując komendę `docker exec -u root -it jenkins-blueocean /bin/bash` (używam flagi **-u** i przekazuję użytkownika **root**, aby mieć prawa zapisu wewnątrz kontenera, dzieki temu nie muszę pobierać sudo i tam dodawać użytkowników).

       Instaluję docker-compose:  
       `curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`  
       I nadaję prawa:  
       `chmod +x /usr/local/bin/docker-compose`
       
       ![docker-compose instalacja](screenshots/18.docker-compose-jenkins.png)  
       
       
       
       ### 2. Kolejnym warunkiem działania docker-compose w projekcie jest zainstalowanie odpowiedniej wtyczki.  
       Wracam do strony Jenkinsa, przechodzę o **Manage Jenkins**  >  **Manage Plugins**  i instaluję wtyczkę **Docker Compose Build Step**:  
        ![Docker Compose Build Step](screenshots/19.docker_compose_plugin.png)   
        
       ### 3. Tworzenie joba
       
       Na stonie głównej wybieram **New Item**  >  **Multi-configuration project**  i nazywam go **devops-react**:  
       ![Create Job](screenshots/20.1.jenkins_create_job.png)   
        
       W **Source Code Management** wybieram Git, wpisuję adres grupowego repozytorium i nazwę swojej gałęzi:  
       ![Create Job - git](screenshots/20.2.jenkins_create_job_git.png)   

       W sekcji **Build** wpisuję adres pliku kompozycji, a jako komendę ustawiam **Start all services**:  
       ![Create Job - build](screenshots/20.3.jenkins_create_job_build.png)   
       
       
       
       Po zapisaniu, po lewej stronie pojawi się menu, z którego wybieram opcję zbudowania. Na dole pojawia się nowy build:  
       ![build jenkins](screenshots/21.build_jenkins.png)   
       
       Build projektu zakończył się sukcesem - szczegóły wykonania znajdują się w **Console Output**:  
       ![build jenkins sukces](screenshots/22.build_success.png)   
       
       Logi wykonania znajdują się w pliku **jenkins_build.log**. Nie wiem czy dobrze zrozumiałem ten punkt, ale w celu sformatowania logów użyłem pluginu **Log File Filter Plugin**, którego następnie skonfigurowałem w **Manage Jenkins**  >  **Configure System**. Wtyczka pozwala na zastosowanie regexów na logach - poniższa konfiguracja usuwa wyjścia konsoli:  
       
       ![build jenkins](screenshots/23.log_filter_plugin.png)   
       
