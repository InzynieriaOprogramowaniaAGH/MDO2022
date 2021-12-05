# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk

```shell

```

* Pobierz obraz Ubuntu
```shell
$sudo docker pull ubuntu
```
* Podłącz wolumin do kontenera
```shell
$sudo docker volume ls
$sudo docker volume create --name dysk
$sudo docker volume inspect dysk
```
* Skopiuj plik do katalogu woluminu, pokaż w kontenerze
```shell
$touch example.txt
$sudo cp example.txt /var/lib/docker/volumes/dysk/_data/
$cd /var/lib/docker/volumes/dysk/_data/
$ls
$sudo docker run -it --mount type=bind,source=/var/lib/docker/volumes/dysk,target=/_data  ubuntu
```
* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście
```shell
$sudo docker run -it --mount type=bind,source=/var/lib/docker/volumes/dysk,target=/_data  ubuntu
$touch example_from_contener.txt
$exit
$cd /var/lib/docker/volumes/dysk/_data/
$ls
```

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
