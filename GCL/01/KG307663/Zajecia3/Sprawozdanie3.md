# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk

* Pobierz obraz Ubuntu

`PobranieObrazuUbuntu.png`
* Podłącz wolumin do kontenera

`StworzenieWoluminu.png`

`SprawdzenieWoluminu.png`

`podlaczeniewoluminum.png`

* Skopiuj plik do katalogu woluminu, pokaż w kontenerze

`StworzeniePlikuNaHoscieDoKontenera.png`

* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście

`PliczekZKontenera.png`

#### "Kiepski pomysł": SSH
* Uruchom i wyeksponuj wybrany port w kontenerze

`OdpalenieZPrzekierowaniemportu.png`
* Zainstaluj w kontenerze serwer ssh

`InstalacjaSSHWkontenerze.png`
* zmień port na wybrany port >1024
* zezwól na logowanie root

`EdycjaPlikuKonfiguracyjnegoSSH.png`
* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze

`Skopiowaniekluczydowoluminium.png`

`SkopiowanieKluczyZwoluminium.png`
* odnajdź adres IP kontenera w wewnętrznej sieci

OdczytanieAdresuKontenera.png
* uruchom usługę, połącz się z kontenerem

`uruchomienie usługi `

`service ssh start`

`PodlaczenieDoKonteneraSSH.png`

#### Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
  * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
  `Jenkins1`
  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa
  `DockerFileJenkins`
  * Uruchom blueocean
  `ZbudowanieJenkinsaBlueOcean`
  * Zaloguj się i skonfiguruj Jenkins
  `Jenkins`
  `Haslojenkins`
  `InstalacjaJenkins`
  `JenkinsDziala`
  
#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname
`uname`
* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 
`wrongdaterun`
* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium
  `projektzgita1`
  * przechodzi na osobistą gałąź
  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose
  `projekszgita2`
  
#### Sprawozdanie
* Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
  * Wymagania wstępne środowiska
  * Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
  * Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
