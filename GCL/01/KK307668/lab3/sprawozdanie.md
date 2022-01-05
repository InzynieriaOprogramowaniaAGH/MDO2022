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
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium ![12.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/12.png)
* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
  * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone ![13.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/13.png)
  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa ![14.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/14.png)
  * Uruchom blueocean ![15.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/15.png) ![16.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/16.png)
  * Zaloguj się i skonfiguruj Jenkins ![17.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/17.png) ![18.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/18.png)  ![19.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/19.png)  ![20.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/20.png) ![21.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/21.png)

#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname ![22.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/22.png) ![23.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/23.png)
* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta ![24.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/24.png) ![25.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/25.png)
* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium ![26.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/26.png)
  * przechodzi na osobistą gałąź ![27.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/27.png)
  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose ![28.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab3/ss/28.png)

#### Sprawozdanie
* Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
  * Wymagania wstępne środowiska
  * Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
  * Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
