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
* Uruchom i wyeksponuj wybrany port w kontenerze
* Zainstaluj w kontenerze serwer ssh
* zmień port na wybrany port >1024
* zezwól na logowanie root
* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
* odnajdź adres IP kontenera w wewnętrznej sieci
* uruchom usługę, połącz się z kontenerem

#### Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
   * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
   * Przygotuj obraz blueocean na podstawie obrazu jenkinsa
   * Uruchom blueocean
   * Zaloguj się i skonfiguruj Jenkins

#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname
* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta
* Utwórz "prawdziwy" projekt, który:
   * klonuje nasze repozytorium
   * przechodzi na osobistą gałąź
   * buduje obrazy z dockerfiles i/lub komponuje via docker-compose

#### Sprawozdanie
* Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
   * Wymagania wstępne środowiska
   * Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
   * Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
