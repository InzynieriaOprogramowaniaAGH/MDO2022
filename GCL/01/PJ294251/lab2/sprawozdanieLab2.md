# Metodyki DevOps - Laboratoria 2

## Zestawienie środowiska

### Instalacja Dockera w systemie Linux

    sudo apt-get install docker-cd docker-cd-cli containerd.io

Na zrzucie ekranu nie zainstalowało Dockera, ponieważ miałem już najnowszą wersję.

![instalacja-dockera](imgs/1.png)

Sprawdzam czy Docker działa poprawnie.

    docker run docker/whalesay cowsay hello


![2](imgs/2.png)

###  Pobieramy obrazy hello-world, busybox, ubuntu

    docker pull [nazwa obrazu]

![3](imgs/3.png)

###  Uruchamiamy busybox

    docker run [nazwa obrazu]

    docker ps -a
Kontener po zakończeniu działania obrazu Busybox zatrzymał się

![4](imgs/4.png)

**Podłączamy się do kontenera w trybie interaktywnym z terminalem `-it` i wywołujemy numer wersji**

    docker run -it [nazwa obrazu]

    busybox | head -1

![5](imgs/5.png)

###  Uruchomić "system w kontenerze"

Uruchamiamy terminal w kontenerze z Ubuntu

    docker run -it [nazwa obrazu systemu] bash

    ps ax

**Wyszukujemy procesy dockera na hoście . Aktualizujemy pakiety i zamykamy kontener poprzez zamknięcie terminala w Ubuntu**:

    ls /proc
    apt update
    exit
![6](imgs/6.png)

###  Pokazać niedziałające kontenery i je wyczyścić:

    docker ps -a

    docker rm [np. po 2 pierwszych znakach id jeśli niewiele kontenerów]

![7](imgs/7.png)

###  Czyszczenie obrazów

Sprawdzam obrazy

    docker images
Usuwam

    docker rmi [np. po 2 pierwszych znakach id jeśli niewiele obrazów]

Przed usunięciem wszystkich obrazów należy wyłączyć podlegające im kontenery

![8](imgs/8.png)

##  Budowanie programu

###  Znalezienie projektu który łatwo umożliwia wywołanie testów jednostkowych

Projekt: https://github.com/lyhd/reactjs

###  Przeprowadzić budowę/konfigurację środowiska

Ściągam projekt za pomocą komendy `git clone https://github.com/lyhd/reactjs`, wchodzę do ściągniętego repozytorium `cd reactjs` i wpisuję komendę `npm install`. Uruchamiam aplikację `npm start`.

![9](imgs/9.png)


###  Uruchomić testy

Robimy to za pomocą komendy `npm test`

![10](imgs/10.png)
###  Stworzenie dockerfile (build + test)+ tworzenie obrazu + run

`docker build -t=[nazwa_obrazu_po_stworzeniu] -f [nazwa dockerfile] .` .

**Build:**
Dockerfile-build i uruchomienie docker build

    docker build -t=ubuntu_react -f Dockerfile-build .

![11](imgs/11.png)

![12](imgs/12.png)


**Test:**

Dockerfile-test i uruchomienie docker build

    docker build -t=ubuntu_react_test -f Dockerfile-test .


![13](imgs/13.png)

![14](imgs/14.png)

##  Kompozycja

    docker docker-compose.yml up

    docker docker-compose.yml down

Plik docker-compose:

![15](imgs/15.png)

Uruchamiamy nasz plik docker-compose.yml:

![16](imgs/16.png)

Wyłączamy nasz plik

![17](imgs/17.png)
