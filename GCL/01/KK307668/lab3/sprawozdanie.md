# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk

* Pobierz obraz Ubuntu <br> ![1.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/1.png)
* Podłącz wolumin do kontenera <br> ![2.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/2.png)
* Skopiuj plik do katalogu woluminu, pokaż w kontenerze <br> ![3.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/3.png)
* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście <br> ![4.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/4.png)

#### "Kiepski pomysł": SSH
* Uruchom i wyeksponuj wybrany port w kontenerze <br> ![5.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/5.png)
* Zainstaluj w kontenerze serwer ssh <br> ![6.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/6.png)
* zmień port na wybrany port >1024 <br> ![7.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/7.png)
* zezwól na logowanie root <br> ![8.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/8.png)
* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze <br> ![8.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/9.png)
* odnajdź adres IP kontenera w wewnętrznej sieci <br> ![10.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/10.png)
* uruchom usługę, połącz się z kontenerem <br> ![11.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/11.png)

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
