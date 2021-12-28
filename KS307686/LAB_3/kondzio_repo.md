# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk


* Pobierz obraz Ubuntu
```shell
$sudo docker pull ubuntu
```
![image](ss1.png)
* Podłącz wolumin do kontenera
```shell
$sudo docker volume ls
$sudo docker volume create --name dysk
$sudo docker volume inspect dysk
```
![image](ss2.png)
![image](ss3.png)
![image](ss4.png)
* Skopiuj plik do katalogu woluminu, pokaż w kontenerze
```shell
$touch example.txt
$sudo cp example.txt /var/lib/docker/volumes/dysk/_data/
$cd /var/lib/docker/volumes/dysk/_data/
$ls
$sudo docker run -it --mount type=bind,source=/var/lib/docker/volumes/dysk,target=/_data  ubuntu
```
![image](ss5.png)
![image](ss6.png)
* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście
```shell
$sudo docker run -it --mount type=bind,source=/var/lib/docker/volumes/dysk,target=/_data  ubuntu
$touch example_from_contener.txt
$exit
$cd /var/lib/docker/volumes/dysk/_data/
$ls
```
![image](ss7.png)
![image](ss8.png)
#### "Kiepski pomysł": SSH
* Uruchom i wyeksponuj wybrany port w kontenerze
```shell
$sudo netstat -tunpa
$sudo docker run -it --mount type=bind,source=/var/lib/docker/volumes/dysk,target=/_data  --publish 2222:22 ubuntu
$sudo docker ps
```
![image](ss9.png)
![image](ss10.png)
![image](ss11.png)
* Zainstaluj w kontenerze serwer ssh
```shell
$apt-get install upgrade && -y install openssh-server
```
* zmień port na wybrany port >1024
Zrobione 2 kroki temu 
* zezwól na logowanie root
* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
```shell
$ssh-keygen
$sudo cp id_rsa.pub /var/lib/docker/volumes/dysk/_data
```
![image](ss14.png)
```shell
$cp id_rsa.pub /root/.ssh/authorized_keys/
```
![image](ss15.png)
* odnajdź adres IP kontenera w wewnętrznej sieci
```shell
$ifconfig
```
![image](ss12.png)
![image](ss13.png)
* uruchom usługę, połącz się z kontenerem
```shell
ssh root@172.17.0.1 -p 2222e
```
![image](ss20.png)
#### Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
![image](2_5.png)
* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
  * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
![image](2_0.png)
  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa
```shell
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
```
![image](2_1.png)
  * Uruchom blueocean
![image](2_3.png)  
  * Zaloguj się i skonfiguruj Jenkins
![image](2_4.png)
#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname
![image](2_6.png)
* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 
* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium
![image](2_7.png)
  * przechodzi na osobistą gałąź
![image](2_8.png)
  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose
![image](2_9.png)
![image](2_10.png)
  
#### Sprawozdanie
* Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
  * Wymagania wstępne środowiska
  * Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
  * Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami

