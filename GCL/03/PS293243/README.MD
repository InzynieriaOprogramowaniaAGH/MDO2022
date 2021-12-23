# Metodyki DevOps - sprawozdanie - zajęcia 03

## Łączność i woluminy na podstawie "złych" praktyk

### Pobranie obrazu Ubuntu

Aby pobrać obraz Ubuntu dla Dockera, należy wykonać następującą komendę:

`docker pull ubuntu`

![Zrzut ekranu - docker pull ubuntu](docker-pull-ubuntu.jpg)

### Podłączenie woluminu do kontenera

Aby podpiąć wolumin do kontenera, najpierw musimy utworzyć katalog, który będzie naszym woluminem - na przykład:

`mkdir /media/exthdd1/DockerVolume`

Następnie podczas uruchamiania kontenera, musimy zastosować flagę `-v <ścieżka lokalna>:<ścieżka w kontenerze>` - na przykład:

`docker run -it -v /media/exthdd1/DockerVolume:/media/DockerVolume ubuntu`

![Zrzut ekranu - docker volume](docker-volume.jpg)

### Kopiowanie/przenoszenie plików z hosta do woluminu

Aby skopiować plik, który chcemy, aby był dostępny z poziomu naszego kontenera, musimy go skopiować/przenieść do woluminu, który jest podpięty pod kontener.

![Zrzut ekranu - przenoszenie pliku](move-file-to-docker-volume.jpg)

Teraz plik, który skopiowaliśmy/przenieśliśmy do woluminu, będzie dostępny z poziomu naszego kontenera.

![Zrzut ekranu - cat w dockerze](docker-cat.jpg)

### Kopiowanie/przenoszenie plików z kontenera do woluminu

Sytuacja wygląda analogicznie w drugą stronę - jeżeli skopiujemy/przeniesiemy plik, który jest dostępny w naszym kontenerze do woluminu, będzie on dostępny z poziomu hosta.

![Zrzut ekranu - przenoszenie pliku](move-file-to-docker-volume-from-container.jpg)

![Zrzut ekranu - cat na hoście](host-cat.jpg)

## "Kiepski pomysł": SSH

### Eksponowanie portów 

Aby wyeksponować port z naszego kontenera, musimy dodać flagę `-p <port na hoście>:<port z dockera>` - na przykład:

`docker run -it -v /media/exthdd1/DockerVolume:/media/DockerVolume -p 9999:9999 ubuntu`

Jeżeli port, którego chcieliśmy użyć jest już wykorzystany przez inną aplikację, otrzymamy błąd.

![Zrzut ekranu - docker port error](docker-port-error.jpg)

Wtedy musimy albo zmienić port, który chcieliśmy wyeksponować, albo zamknąć aplikację, która aktualnie z niego korzysta i blokuje nam dostęp.

![Zrzut ekranu - docker port ok](docker-port-ok.jpg)

### Instalacja serwera SSH

Aby zainstalować na kontenerze stworzonym na podstawie czystego obrazu Ubuntu, musimy wykonać następującą komendę:

`apt update && apt install openssh-server -y`

![Zrzut ekranu - apt install openssh-server](apt-install-openssh.jpg)

### Zmiana portu serwera SSH

Domyślnie SSH jest uruchomione na porcie 22 - jeżeli chcemy wyeksponować serwis na zewnątrz, musimy skierować go pod port, który sprecyzowaliśmy przy tworzeniu kontenera - w naszym przypadku jest to `8888`.

Ponieważ na podstawowym obrazie Ubuntu nie mamy zainstalowanego edytora plików tekstowych, instalujemy `nano`.

`apt install nano`

![Zrzut ekranu - apt install nano](apt-install-nano.jpg)

Ścieżka, pod którą dostępny jest plik konfiguracyjny serwera OpenSSH to `/etc/ssh/sshd_config`. Uruchamiamy edytor `nano` na tym pliku:

`nano /etc/ssh/sshd_config`

![Zrzut ekranu - nano openssh](nano-openssh.jpg)

Za pomocą edytora, zmieniamy port z `22` na `8888`, usuwamy znak odpowiedzialny za odkomentowanie linijki `#` i zapisujemy plik.

![Zrzut ekranu - nano openssh port](nano-openssh-port.jpg)

### Zezwalanie na logowanie root

Proces wygląda tak samo jak w poprzednim opisanym kroku - natomiast zamiast zmieniać linijkę dotyczącą portu, szukamy linijki z tekstem `PermitRootLogin` i zmieniamy jej wartość na `yes`.

![Zrzut ekranu - nano openssh root](nano-openssh-root.jpg)

### Dodawanie kluczy publicznych do kontenera za pomocą woluminu

Musimy skopiować klucz publiczny, za pomocą, którego chcemy się uwierzytelnić do naszego woluminu.

`cp ~/.ssh/id_ed25519.pub /media/exthdd1/DockerVolume/`

![Zrzut ekranu - ssh copy to volume](ssh-copy-to-volume.jpg)

Następnie z poziomu naszego kontenera, dodajemy skopiowany klucz publiczny do zaufanych.

Najpierw tworzymy katalog `/root/.ssh`, a następnie kopiujemy do niego nasz klucz jako `authorized_keys`.

![Zrzut ekranu - ssh key to docker](ssh-key-to-docker.jpg)

### Adres IP kontenera

Aby odnaleźć nasz adres IP kontenera, możemy na hoście wywołać komendę:

`docker inspect <ID kontenera> || grep IPAddress`.

![Zrzut ekranu - docker ip](docker-ip.jpg)

### Uruchamianie usługi SSH na kontenerze

Aby uruchomić daemon'a usługi SSH, musimy wykonać następującą komendę:

`/usr/sbin/sshd -D &`

![Zrzut ekranu - ssh daemon](ssh-daemon.jpg)

Jeżeli otrzymamy błąd o brakującym folderze `/run/sshd`, musimy go utworzyć przez `mkdir`.

![Zrzut ekranu - ssh daemon fail](ssh-daemon-fail.jpg)

### Połączenie z kontenerem przez SSH

Gdy wszystko jest już skonfigurowane poprawnie, możemy połączyć się z naszym kontenerem przez hosta komendą:

`ssh root@<IP kontenera> -p <port wystawiony>`

![Zrzut ekranu - ssh](ssh-inside.jpg)

## Skonteneryzowany Jenkins stosujący Dockera

### Uruchamianie obrazu Dockera, który eksponuje środowisko zagnieżdżone
### Na podstawie instrukcji - https://www.jenkins.io/doc/book/installing/docker/

Tworzymy nowy mostek sieciowy o nazwie `jenkins`:

`docker network create jenkins`

![Zrzut ekranu - docker network](docker-network.jpg)

Uruchamiamy nowy kontener - jeżeli brakuje nam obrazu `docker:dind`, zostanie on automatycznie pobrany:

    docker run --name jenkins-docker --rm --detach \
    --privileged --network jenkins --network-alias docker \
    --env DOCKER_TLS_CERTDIR=/certs \
    --volume jenkins-docker-certs:/certs/client \
    --volume jenkins-data:/var/jenkins_home \
    --publish 2376:2376 docker:dind --storage-driver overlay2

![Zrzut ekranu - docker run](docker-run.jpg)

### Tworzenie obrazu **blueocean** na podstawie obrazu **jenkins**

Tworzymy nowy Dockerfile dla **blueocean**:

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
    RUN jenkins-plugin-cli --plugins "blueocean:1.25.2 docker-workflow:1.26"

I buildujemy nowy obraz:

`docker build -t myjenkins-blueocean:1.1 .`

![Zrzut ekranu - docker build start](docker-build-start.jpg)
![Zrzut ekranu - docker build end](docker-build-end.jpg)

### Uruchomienie **blueocean**

Gdy mamy już zbudowany obraz **blueocean**, możemy go uruchomić:

    docker run --name jenkins-blueocean --rm --detach \
    --network jenkins --env DOCKER_HOST=tcp://docker:2376 \
    --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 \
    --publish 8080:8080 --publish 50000:50000 \
    --volume jenkins-data:/var/jenkins_home \
    --volume jenkins-docker-certs:/certs/client:ro \
    myjenkins-blueocean:1.1

![Zrzut ekranu - docker run blueocean](docker-run-blueocean.jpg)

Przechodzimy do przeglądarki i udajemy się pod adres http://localhost:8080/

![Zrzut ekranu - jenkins unlock](jenkins_unlock.jpg)

Jeżeli pracujemy na innym komputerze, niż ten, gdzie uruchomiony jest Jenkins, musimy dodatkowo ustawić przekierowanie portów.

W moim przypadku, ustawiłem to w kliencie SSH `KiTTY`, z którego korzystam.

![Zrzut ekranu - kitty](kitty.jpg)

### Logowanie i konfiguracja Jenkinsa

Ścieżka do pliku, w którym znajduje się hasło do Jenkinsa, jest nam pokazana po wejściu na stronę w przeglądarce. Zawartość tego pliku możemy zwrócić przez wykonanie komendy w kontenerze:

`cat /var/jenkins_home/secrets/initialAdminPassword`

Na przykład:

`sudo docker exec e13c3e83aefa cat /var/jenkins_home/secrets/initialAdminPassword`

![Zrzut ekranu - jenkins pass](jenkins-pass.jpg)

Po zalogowaniu hasłem, które właśnie otrzymaliśmy, przechodzimy do konfiguracji Jenkinsa.

![Zrzut ekranu - jenkins config](jenkins-config.jpg)

Po wstępnej konfiguracji przeniesieni zostaniemy do ekranu głównego.

![Zrzut ekranu - jenkins index](jenkins-index.jpg)

## Mikro-projekt Jenkins

### Projekt wyświetlajacy **uname**

Z menu dostępnego po lewej stronie wybieramy opcję **New Item**.

![Zrzut ekranu - uname #1](uname-1.jpg)

Wpisujemy nazwę naszego projektu - w tym przypadku będzie to `uname` i wybieramy **Freestyle project**. Klikamy **OK**.

![Zrzut ekranu - uname #2](uname-2.jpg)

Przechodzimy do **Build**. Klikamy **Add build step** i wybieramy opcję **Execute shell**. W polę komendy wpisujemy `uname -a`. Wciskamy przycisk **Save**.

![Zrzut ekranu - uname #3](uname-3.jpg)
![Zrzut ekranu - uname #4](uname-4.jpg)

W menu po lewej stronie ekranu klikamy **Build Now**.

![Zrzut ekranu - uname #5](uname-5.jpg)

Przechodzimy do **Build History** i wybieramy naszego builda.

![Zrzut ekranu - uname #6](uname-6.jpg)

Klikamy na **Console Output** i wyświetli nam się wynik działania naszego builda - czyli to, co zwróciła nam komenda `uname -a`.

![Zrzut ekranu - uname #7](uname-7.jpg)

### Projekt zwracający błąd, gdy aktualna godzina jest nieparzysta

Tworzymy nowy projekt **Freestyle project**.

Przechodzimy do **Build**. Klikamy **Add build step** i wybieramy opcję **Execute shell**. W polę komendy podajemy poniższy skrypt:

    #!/bin/bash

    hour=$(date +%H)
    echo $hour

    if [ $((hour%2)) -eq 0 ];
    then

    exit 0

    else

    exit 1

    fi

Wciskamy przycisk **Save**.

![Zrzut ekranu - jenkins hour script](jenkins-hour-script.jpg)

Uruchamiamy builda i sprawdzamy wyjście.

![Zrzut ekranu - jenkins hour odd](jenkins-hour-odd.jpg)
![Zrzut ekranu - jenkins hour even](jenkins-hour-even.jpg)

### Projekt "prawdziwy"

Zadaniem naszego prawdziwego projektu będzie sklonowanie naszego repozytorium, przejście na osobistą gałąź i zbudowanie obrazów na podstawie docker-compose.

Najpierw instalujemy wtyczkę, która pozwoli nam na dodanie kroku do builda na podstawie naszego docker-compose.

Przechodzimy do **Dashboard**, **Manage Jenkins** i następnie **Manage Plugins**. Tam klikamy na zakładkę **Available** i wyszukujemy wtyczki **Docker Compose Build Step**, którą instalujemy.

![Zrzut ekranu - jenkins plugin manager](jenkins-plugin-manager.jpg)

Żebyśmy mieli obsługę docker-compose na naszym Jenkinsie, musimy zainstalować go w skontenerowanym Dockerze.

Wykonujemy kolejno komendy:

`sudo docker exec -u 0 <ID kontenera> curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`

`sudo docker exec -u 0 <ID kontenera> chmod +x /usr/local/bin/docker-compose`

![Zrzut ekranu - docker apk](docker-apk.jpg)

W ustawieniach projektu pod **Source Code Management** podajemy adres do naszego repozytorium oraz nazwę gałęzi.

![Zrzut ekranu - jenkins git branch](jenkins-git-branch.jpg)

Pod **Build** dodajemy nowy krok - **Docker Compose Build Step** i podajemy ścieżkę do naszego `docker-compose.yml`.

![Zrzut ekranu - jenkins docker compose](jenkins-docker-compose.jpg)

Zapisujemy projekt i uruchamiamy go.

![Zrzut ekranu - jenkins docker compose success](jenkins-docker-compose-success.jpg)

![Zrzut ekranu - jenkins docker compose log](jenkins-docker-compose-log.jpg)