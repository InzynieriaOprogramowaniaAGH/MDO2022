# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk

* Pobierz obraz Ubuntu
```shell
$ dockerd
$ docker pull ubuntu
```

`[img] screen_1 - screen_3`
* Podłącz wolumin do kontenera
```shell
$ sudo docker volume
$ sudo docker volume ls
```
`[img] screen_4`
```shell
$ sudo docker volume create pendrajw
$ sudo docker volume ls
```
`[img] screen_5`
```shell
$ sudo docker volume inspect pendrajw
```
`[img] screen_6`
```shell
$ sudo mc /var/lib/docker/volumes/pendrajw/_data
```
`[img] screen_7`
```shell
$ sudo docker run -it --mount source=pendrajw,destination=/store ubuntu
```

* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście

```shell
$ cd
$ touch plik_testowy
$ touch /store/plit_testowy_store
```

`[img] screen_8 - screen_9`

* Skopiuj plik do katalogu woluminu, pokaż w kontenerze

`[img] screen_10 - screen_11`

#### "Kiepski pomysł": SSH
```shell
$ sudo netstat -tunpa
```
`[img] screen_12`

* Uruchom i wyeksponuj wybrany port w kontenerze
* zmień port na wybrany port >1024
```shell
$ sudo docker run -it --mount source=pendrajw,destination=/store --publish 2222:22 ubuntu
$ sudo docker ps -a
```

`[img] screen_13`

* Zainstaluj w kontenerze serwer ssh
```shell
$ apt-get update
$ apt-get -y install openssh-server
```

`[img] screen_14`

```shell
$ /usr/sbin/sshd -D &
$ mkdir /run/sshd
$ /usr/sbin/sshd -D &
```

`[img] screen_15 - screen_17`

* odnajdź adres IP kontenera w wewnętrznej sieci

`[img] screen_18 - screen_20`

* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
* zezwól na logowanie root
  
`[img] screen_21 - screen_23`

* uruchom usługę, połącz się z kontenerem
  
`[img] screen_24`

#### Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
  
https://github.com/michalmuchakr/docker-react-test-app

`[img] screen_25`

* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
   * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone

`[img] screen_26`

```shell
docker run \
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
```
`[img] screen_267`
   * Przygotuj obraz blueocean na podstawie obrazu jenkinsa

Dockerfile
```dockerfile
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

`[img] screen_28`

   * Uruchom blueocean
```shell
docker build -t myjenkins-blueocean:1.1 .
```
```dockerfile
docker run --name jenkins-blueocean --rm --detach \
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  myjenkins-blueocean:1.1
```
`[img] screen_29 - 30`

   * Zaloguj się i skonfiguruj Jenkins

`[img] screen_31 - 37`

#### Mikro-projekt Jenkins

* Utwórz projekt, który wyświetla uname
  `[img] screen_37 - 40`
* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta
* Utwórz "prawdziwy" projekt, który:
   * klonuje nasze repozytorium
   * przechodzi na osobistą gałąź
   * buduje obrazy z dockerfiles i/lub komponuje via docker-compose
     `[img] screen_40 - 46`
     `jenkins-build.log`

#### Sprawozdanie
* Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
   * Wymagania wstępne środowiska
   * Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
   * Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
