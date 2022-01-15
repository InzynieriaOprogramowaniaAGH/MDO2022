# Metodyki DevOps - Laboratoria 2

## Zestawienie środowiska

### Instalacja Dockera w systemie Linux

    sudo apt-get install docker-cd docker-cd-cli containerd.io

Na zrzucie ekranu nie zainstalowało Dockera, ponieważ miałem już najnowszą wersję.

img1

Sprawdzam czy Docker działa poprawnie.

    docker run docker/whalesay cowsay hello


img2

###  Pobieramy obrazy hello-world, busybox, ubuntu

    docker pull [nazwa obrazu]

img3

###  Uruchamiamy busybox

    docker run [nazwa obrazu]

    docker ps -a
Kontener po zakończeniu działania obrazu Busybox zatrzymał się

img4

**Podłączamy się do kontenera w trybie interaktywnym z terminalem `-it` i wywołujemy numer wersji**

    docker run -it [nazwa obrazu]

    busybox | head -1

img5

###  Uruchomić "system w kontenerze"

Uruchamiamy terminal w kontenerze z Ubuntu

    docker run -it [nazwa obrazu systemu] bash

    ps ax

**Wyszukujemy procesy dockera na hoście . Aktualizujemy pakiety i zamykamy kontener poprzez zamknięcie terminala w Ubuntu**:

    ls /proc
    apt update
    exit

img6

###  Pokazać niedziałające kontenery i je wyczyścić:

    docker ps -a

    docker rm [np. po 2 pierwszych znakach id jeśli niewiele kontenerów]

img7

###  Czyszczenie obrazów

Sprawdzam obrazy

    docker images
Usuwam

    docker rmi [np. po 2 pierwszych znakach id jeśli niewiele obrazów]

Przed usunięciem wszystkich obrazów należy wyłączyć podlegające im kontenery

img8

##  Budowanie programu

###  Znalezienie projektu który łatwo umożliwia wywołanie testów jednostkowych

Projekt: https://github.com/lyhd/reactjs

###  Przeprowadzić budowę/konfigurację środowiska

Ściągam projekt za pomocą komendy `git clone https://github.com/lyhd/reactjs`, wchodzę do ściągniętego repozytorium `cd reactjs` i wpisuję komendę `npm install`. Uruchamiam aplikację `npm start`.

img9


###  Uruchomić testy

Robimy to za pomocą komendy `npm test`

img10

###  Stworzenie dockerfile (build + test)+ tworzenie obrazu + run

`docker build -t=[nazwa_obrazu_po_stworzeniu] -f [nazwa dockerfile] .` .

**Build:**
Dockerfile-build i uruchomienie docker build

    docker build -t=ubuntu_react -f Dockerfile-build .

img11

img12


**Test:**

Dockerfile-test i uruchomienie docker build

    docker build -t=ubuntu_react_test -f Dockerfile-test .


img13


##  Kompozycja

    docker docker-compose.yml up

    docker docker-compose.yml down

Plik docker-compose:

img15

Uruchamiamy nasz plik docker-compose.yml:

img16

Wyłączamy nasz plik

img17