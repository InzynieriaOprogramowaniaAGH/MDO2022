# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk

* Pobierz obraz Ubuntu
* Podłącz wolumin do kontenera

Tworzenie woluminu:

      sudo docker volume create moj_volumin
      
Podłączanie do kontenera:
      
      sudo docker run --interactive --tty --mount source=moj_wolumin,destination=/store ubuntu

![image](https://user-images.githubusercontent.com/80592460/144719773-7cf12192-9ffa-405a-9575-1885f1da131e.png)

* Skopiuj plik do katalogu woluminu, pokaż w kontenerze

      touch plik_aaa
      cp plik_aaa /var/lib/docker/volumes/nazwa_twojego_voluminu/_data

![image](https://user-images.githubusercontent.com/80592460/144721276-9ee2904e-6bd2-4b74-b9e7-21ffd9129b6e.png)

![image](https://user-images.githubusercontent.com/80592460/144721094-5ea07af2-073b-4ad6-8613-d140abb4d05d.png)

* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście

      touch /stroe/plik_bbb

![image](https://user-images.githubusercontent.com/80592460/144721003-6c9cfc41-da68-4eb1-bb1d-06f4b278bd9b.png)


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
