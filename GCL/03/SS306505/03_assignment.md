# Lab03

## Kilka rad zanim zaczniesz
- Niniejsza instrukcja zaklada prace pod systemem Windows z zainstalowanym WSL2
- Aby korzystac z docker'a pod WSL2 nalezy zainstalowac aplikacje [Docker Desktop for Windows ](https://hub.docker.com/editions/community/docker-ce-desktop-windows)

## Kroki

### Łączność i woluminy na podstawie "złych" praktyk
1. Pobierz obraz Ubuntu [zrzut 00]
    - Uzyjemy do tego polecenie pull, ktore pobierze obraz z DockerHub'a
        - docker pull ubuntu
2. Podłącz wolumin do kontenera [zrzut 01]
    - Aby podlaczyc wolumin do kontenera uzyjemy flagi -v podczas uruchamiania
    - Podlaczymy katalog /home/seb/volume z hosta do katalogu /home/root w kontenerze
        - docker run -it -v /home/seb/volume:/home/root ubuntu
3. Skopiuj plik do katalogu woluminu, pokaż w kontenerze [zrzut 01]
    - W katalogu wolumenu na hoscie tworzymy plik
        - echo 'Hejka z hosta!' > /home/seb/volume/hello-from-host.txt
    - W kontenerze listujemy podmonotwany katalog 
        - ls -alh /home/root
4. Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście
    - W katalogu podmontowanym kontenera tworzymy plik
        - echo 'Hejka z kontenera!' > /home/root/hello-from-container.txt
    - Na hoscie listujemy katalog wolumenu
        - ls -alh /home/seb/volume

### "Kiepski pomysł": SSH
1. Uruchom i wyeksponuj wybrany port w kontenerze
    - Aby wyeksportowac port nalezy uzyc flagi -p lub --publish
    - Mozemy eksportowac kilka portow jednoczesnie
        - docker run -it -v /home/seb/volume:/home/root --publish 2222:22 ubuntu
2. Zainstaluj w kontenerze serwer ssh
    - Nalezy najpierw zaktualizowac system
        - apt update
    - Nastepnie menadzerem pakietow doinstalowac serwer ssh
        - apt install openssh-server
3. Zmień port na wybrany port > 1024
    - ???????????????????
4. Zezwól na logowanie root
    - Nalezy w tym celu zaktualizowac plik /etc/ssh/sshd_config
        - echo 'PermitRootLogin yes' >> /etc/ssh/sshd_config
    - Nastepnie zrestartowac serwis sshd lub opdalić daemon'a od nowa
        - systemctl restart sshd.service
        - /usr/sbin/sshd -D &
5. Umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
    - Na hoście kopiujemy klucz publiczny do katalogu wolumenu
        - cp ~/.ssh/id_rsa.pub /home/seb/volume
    - W kontenerze kopiujemy klucz ten publiczny do zaufanych kluczy
        - echo /home/root/id_rsa.pub > ~/.ssh/authorized_keys
6. Odnajdź adres IP kontenera w wewnętrznej sieci [zrzut 04]
    - Adres ip kontenera mozemy znalezc uzywajac polecenia inspect
        - docker inspect <container_id>
        - docker inspect d3f13 | grep IPAddress | cut -d '"' -f 4
7. Uruchom usługę, połącz się z kontenerem
    - Doinstalujemy systemctl aby sprawniej uruchomic usluge
        - apt install systemctl
    - Uruchomimy ja jak w normalnym ubuntu
        - systemctl start sshd.service
    - Z poziomu hosta podlaczymy sie po ssh do kontenera
        - ssh root@172.17.0.2

### Skonteneryzowany Jenkins stosujący Dockera
#### Przygotowanie
1. Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium [zrzut 06]
    - Mozemy sprawdzic to logujac sie na GitHubie
    - Mozemy rowniez zrobic to lokalnie prze git'a
        - git pull origin SS306505
        - git status

2. Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
    - Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
        - docker run \
            --name jenkins-docker \
            --rm \
            --detach \
            --privileged \
            --network jenkins \
            --network-alias docker \
            --env DOCKER_TLS_CERTDIR=/certs \
            --volume jenkins-docker-certs:/certs/client \
            --volume jenkins-data:/var/jenkins_home \
            --publish 2376:2376 \
            docker:dind \
            --storage-driver overlay2
    - Przygotuj obraz blueocean na podstawie obrazu jenkinsa
        - 03_Dockerfile
            ```
            FROM jenkins/jenkins:2.319.1-jdk11
            USER root
            RUN apt-get update && apt-get install -y lsb-release
            RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
            https://download.docker.com/linux/debian/gpg
            RUN echo "deb [arch=$(dpkg --print-architecture) \
            signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
            https://download.docker.com/linux/debian \
            $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
            RUN apt-get update && apt-get install -y docker-ce-cli
            USER jenkins
            RUN jenkins-plugin-cli --plugins "blueocean:1.25.1 docker-workflow:1.26"
            ```
        - Zbudujemy to uzywajac komendy build [zrzut 07]
            - docker build -f 03_Dockerfile -t myjenkins-blueocean:1.1 .
        
    - Uruchom blueocean
        - Uzyjemy gotowej komendy
            - docker run \
                --name jenkins-blueocean \
                --rm \
                --detach \
                --network jenkins \
                --env DOCKER_HOST=tcp://docker:2376 \
                --env DOCKER_CERT_PATH=/certs/client \
                --env DOCKER_TLS_VERIFY=1 \
                --publish 8080:8080 \
                --publish 50000:50000 \
                --volume jenkins-data:/var/jenkins_home \
                --volume jenkins-docker-certs:/certs/client:ro \
                myjenkins-blueocean:1.1 
    - Wyniki uruchomienia obu kontenerow mozemy sprawdzic poleceniem ps [zrzut 8]
        - docker ps
    - Zaloguj się i skonfiguruj Jenkins 
        - W adresue przegladarki przejdz do http://localhost:8080 [zrzut 09]
        - Haslo do Jenkinsa mozemy znalezc w logach kontenera
            - docker logs <container_id>
        - Haslo mozemy rowniez zobaczyc wykonujac komende w kontenerze
            - sudo cat /var/lib/jenkins/secrets/initialAdminPassword
        - Jenkins poprosi o stworzenie konta admina i wybor wtyczek
        - Po przeklikaniu powinnismy zobaczyc ekran glowny [zrzut 10]
    
    ### Mikro-projekt Jenkins

    1. Utwórz projekt, który wyświetla uname
        - Klikamy Nowy Projekt i nadajemy mu nazwe [zrzut 11]
        - W zakladce Budowanie dodajemy krok Uruchom powloke i wpisujemy komende [zrzut 12]
            - uname -a
        - Klikamy Uruchom, aby uruchomic naszego builda
        - Sprawdzamy w logach konsoli wynik dzialania builda [zrzut 13]
    2. Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 
        - Tworzymy projekt jak w kroku poprzednim 
        - W polu Uruchom powloke wpisujemy sciezke bezwgledna do skryptu shell-a [zrzut 14]
            -/tmp/skrypt.sh
        - Tworzymy skrypt
            -touch skrypt.sh
            -chmod ugo+x skrypt.sh
        - skrypt.sh 
        ```
        #!/bin/bash
        currenthour=$(date +"%H")
        if ! (($currenthour%2)) ; then 
            echo "Even hour!"
            exit 0
        else
            echo "Odd hour!"
            exit 1
        fi
        ```
        - Sprawdzamy dla godziny parzystej [zrzut 15] oraz nieparzystej [zrzut 16]
    3. Buduje obrazy z dockerfiles i/lub komponuje via docker-compose
        - Najpierw musimy podlaczyc sie do contenera jenkinsa i doinstalowac docker-compose'a
            - uzyjemy w tym celu nastepujacych polecen [zrzut 17]
                ```
                docker exec -it -u root <container_id> /bin/bash
                curl -L "https://github.com/docker/compose/releases/download/1.26.0/docker-compose-$(uname -s)-$(uname -m)"  -o /usr/local/bin/docker-compose
                mv /usr/local/bin/docker-compose /usr/bin/docker-compose
                chmod +x /usr/bin/docker-compose
                ```
        - Doinstalowujemy wtyczke do Jenkins-a
            - Wchodzimy z głównego ekranu jenkinsa -> Zarządzaj Jenkinsem -> Zarządzaj wtyczkami -> Dostępne -> Docker Compose Build Step Plugin
        - Następnie tworzymy nowy projekt jak poprzednio i nadajemy mu nazwe
            - W sekcji 'Repozytorium kodu' wpisujemy adres URL [zrzut 18]
            - W sekcji 'Budowanie' wybieramy naszą wtyczkę i wpisujemy ścieżke do pliku z docker-compose.yaml [zrzut 19]
        - Klikamy Uruchom projekt
        - Oglądamy wyniki logów w konsoli [zrzut 20-21]