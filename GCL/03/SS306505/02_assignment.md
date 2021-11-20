# Lab02

## Kilka rad zanim zaczniesz
- Niniejsza instrukcja zaklada prace pod systemem Windows z zainstalowanym WSL2
- Aby korzystac z docker'a pod WSL2 nalezy zainstalowac aplikacje [Docker Desktop for Windows ](https://hub.docker.com/editions/community/docker-ce-desktop-windows)

## Kroki

1. Zainstaluj Docker w systemie linuksowym
    - Sprawdzamy czy docker jest juz zainstalowany (screen 00)
        - docker --version
    - [Przykladowa instalacja dockera na WSL|https://docs.docker.com/desktop/windows/wsl/]
2. Zarejestruj się w [Docker Hub](https://hub.docker.com/) i zapoznaj z sugerowanymi obrazami (screen 01)
3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql (screen 02)
    - Uzyjemy do tego polecenia pull nazwa_obrazu
        - docker pull hello-world
    - Sprawdzamy pobrane obrazy poleceniem images
        - docker images
3. Uruchom busybox (screen 03)
    - Pokaż efekt uruchomienia kontenera
        - Uzyjemy polecenia do pokazania wszystkich dzialajacych lub uruchomionych kontenerow
            - docker ps -a
    - Podłącz się do kontenera interaktywnie i wywołaj numer wersji
        - Uzyjemy polecenia run z flagami -i (interactive) oraz -t (tty), dzieki ktorym bedziemy w stanie uruchomic kontener i komunikowac sie z nim
            - docker run -it busybox 
        - Numer wersji mozemy sprawdzic wykonujac na kontenerze polecenie uname z flaga a
            - uname -a
        - Z konsoli kontenera mozna wyjsc poleceniem exit
4. 