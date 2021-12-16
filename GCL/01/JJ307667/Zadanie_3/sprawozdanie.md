# Metodyki DevOps - Laboratoria

#### Łączność i woluminy na podstawie "złych" praktyk

* Pobierz obraz Ubuntu

![Pobieranie ubuntu](screenshots/1.PNG)

* Podłącz wolumin do kontenera
* Skopiuj plik do katalogu woluminu, pokaż w kontenerze
* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście

Tworzenie woluminu

```sh
docker volume create [nazwa nowego woluminu]
docker volume ls
docker volume inspect [nazwa stworzonego woluminu]
```

![Tworzenie nowego wolumenu](screenshots/2.PNG)


Podłączanie woluminu do kontenera + tworzenie nowego pliku do wolumenu z poziomu kontenera

```sh
docker run -it --mount source=[nazwa woluminu],destination=/store [nazwa obrazu]
```
![Podłączanie woluminu do kontenera](screenshots/3.PNG)

Stworzony plik widać na hoście:

![Plik z wolumenu na hoscie](screenshots/4.PNG)

Tworzenie pliku na hoście i wyświetlanie w kontenerze:

![Plik z wolumenu na hoscie](screenshots/5.PNG)

#### "Kiepski pomysł": SSH
* Uruchom i wyeksponuj wybrany port w kontenerze
* zmień port na wybrany port >1024

```sh
docker run -it --mount source=[nazwa woluminu],destination=/store --publish [port_na_hoście]:[port_kontenera] [nazwa obrazu]
```
![Wyeksponowano port w kontenerze](screenshots/6.PNG)

* Zainstaluj w kontenerze serwer ssh

```sh
apt-get update
apt-get -y install openssh-server
```
**Należy pamiętać o stworzeniu folderu /run/sshd (komenda mkdir /run/sshd)**

![Instalacja serwera ssh](screenshots/7.PNG)

* odnajdź adres IP kontenera w wewnętrznej sieci

**Należy najpierw zainstalować net-tools (komenda apt install net-tools)**

![Adres IP kontenera](screenshots/8.PNG)


* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
* zezwól na logowanie root

```sh
mc /var/lib/docker/volumes/[nazwa woluminu]/_data
```
Miałem u siebie dwa klucze publiczne więc dla pewności skopiowałem oba

![Kopiowanie kluczy publicznych](screenshots/9.PNG)

Skopiowanie zawartości obu kluczy publicznych z woluminu do root/.ssh/authorized_keys na kontenerze

![Kopiowanie zawartości kluczy publicznych na kontenerze](screenshots/10.PNG)

* uruchom usługę, połącz się z kontenerem

Ponieważ w moim przypadku dostępy w kluczach publicznych były dla użytkownika jedrzej a nie root, na hoście musiałem wylogować się z roota aby się połączyć.

![Łączenie przez SSH z kontenerem](screenshots/11.PNG)


#### Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium

![Dockerfile w repo](screenshots/12.PNG)

* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
  * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone


  ```sh
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

  ![docket network create jenkins](screenshots/13.PNG)

  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa
  ![Dockerfile w repo](screenshots/14.PNG)


  * Uruchom blueocean
  ![Uruchamianie blueocean](screenshots/15.PNG)
  ![Uruchamianie blueocean 2](screenshots/16.PNG)

  * Zaloguj się i skonfiguruj Jenkins
  ![Jenkins_1](screenshots/17.PNG)

  Hasło do admina otrzymujemy w ten sposób:
  ![Jenkins_2](screenshots/18.PNG)

  Kontynuujemy konfiguracje Jenkinsa:
  ![Jenkins_2](screenshots/19.PNG)
  ![Jenkins_2](screenshots/20.PNG)
  ![Jenkins_2](screenshots/21.PNG)


#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname

Tworzymy projekt typu **Ogólny projekt** i w budownie dodajemy krok **Uruchom powłokę** i wpisujemy odpowiednią komendę:
![Uname1](screenshots/22.PNG)

Działa bez zarzutu:
![Uname2](screenshots/23.PNG)

* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta

Projekt tworzymy w identyczny sposób jak poprzedni. Zmienia się tylko nazwa projektu, ewentualny opis i oczywiście komenda.
![is_hour_even1](screenshots/24.PNG)

Również działa bez zarzutu:
![is_hour_even2](screenshots/25.PNG)

* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium
  * przechodzi na osobistą gałąź
  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose

Przed wykonaniem projektu, należy doinstalować wtyczkę **Docker Compose Build Step**
![is_hour_even2](screenshots/26.PNG)

Przystępujemy do wykonania projektu:
![Prawdziwy_projekt](screenshots/27.PNG)

![Prawdziwy_projekt](screenshots/28.PNG)

![Prawdziwy_projekt](screenshots/29.PNG)

![Prawdziwy_projekt](screenshots/30.PNG)

Projekt buduje się bez zarzutu:
![Prawdziwy_projekt](screenshots/31.PNG)

Testy zbudowały się bez problemu, cały projekt również:
![Prawdziwy_projekt](screenshots/32.PNG)


#### Sprawozdanie
* Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
  * Wymagania wstępne środowiska
    * Docker (do uruchamiania obrazów)
    * Wtyczka Docker Compose Build Step (do zdefiniowania budowania projektu w Jenkinsie)
  * Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
  ![Diagram aktywności](screenshots/Diagram_aktywnosci.jpg)

  * Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
  ![Diagram wdrożeniowy](screenshots/Diagram_wdrożeniowy.jpg)
