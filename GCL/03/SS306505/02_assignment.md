# Lab02

## Kilka rad zanim zaczniesz
- Niniejsza instrukcja zaklada prace pod systemem Windows z zainstalowanym WSL2
- Aby korzystac z docker'a pod WSL2 nalezy zainstalowac aplikacje [Docker Desktop for Windows ](https://hub.docker.com/editions/community/docker-ce-desktop-windows)

## Kroki

### Zestawienie środowiska
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
4. Uruchom busybox (screen 03)
    - Pokaż efekt uruchomienia kontenera
        - Uzyjemy polecenia do pokazania wszystkich dzialajacych lub uruchomionych kontenerow
            - docker ps -a
    - Podłącz się do kontenera interaktywnie i wywołaj numer wersji
        - Uzyjemy polecenia run z flagami -i (interactive) oraz -t (tty), dzieki ktorym bedziemy w stanie uruchomic kontener i komunikowac sie z nim
            - docker run -it busybox 
        - Numer wersji mozemy sprawdzic wykonujac na kontenerze polecenie uname z flaga a
            - uname -a
        - Z konsoli kontenera mozna wyjsc poleceniem exit
5. Uruchom "system w kontenerze"
    - Uzyj w tym celu polecenia z poprzedniego punktu np. dla obrazu ubuntu
        - docker run -it ubuntu
    - Zaprezentuj PID1 w kontenerze i procesy dockera na hoście (screen 04)
        - Uzyjemy komendy systemowej ps z flagami a, u, x
            - ps -aux
        - Aby wyswitlic konkretny PID mozesz uzyc flagi p
            - ps -p 1
    - Zaktualizuj pakiety (screen 05)
        - W ubuntu sluzy do tego polecenia apt-get
            - apt-get update
    - Wyjdź
        - Z konsoli kontenera mozesz wyjsc uzywajac polecenia exit
6. Pokaż uruchomione ( != "działające" ) kontenery, wyczyść je (screen 06)
    - Uruchomione kontenery mozna zobaczyc uzywajac polecenia ps -a
        - docker ps -a
    - Aby wyczyscic kontenery uzyjemy polecenia container prune
        - docker container prune
7. Wyczyść obrazy (screen 07)
    - Aby wyczyscic obrazy nie polaczone z ani jednym kontenerem uzyjemy polecenia image prune
        - docker image prune -a
    - Ciekawostka:
        - Jesli chcialbys wyczyscic wszystko co tworzy docker (obrazy, kontenery, siec, wolumeny) mozesz uzyc polcecenia system prune
            - docker system prune

### Budowanie programu

1. Znajdź projekt umożliwiający łatwe wywołanie testów jednostkowych
    - [Przykladowy projekt w node.js z testami jednostkowymi](https://github.com/Lissy93/quick-example-of-testing-in-nodejs)
2. Przeprowadź budowę/konfigurację środowiska
    - W tym celu musimy najpierw pobrac z github'a repozytorium (screen 08)
        - git clone https://github.com/Lissy93/quick-example-of-testing-in-nodejs.git to clone repo
    - Nastepnie wejsc do jego katalogu 
        - cd quick-example-of-testing-in-nodejs to navigate into project
    - Projekt ten wymaga zainstalowania node.js i managera pakietow npm 
    - W tym celu skorzystamy z [tego tutoriala](https://docs.microsoft.com/en-us/windows/dev-environment/javascript/nodejs-on-wsl) 
    - Wyniki instalacji mozemy sprawdzic poleceniami npm i node z flaga version (screen 09)
        - npm --version
        - node --version
    - Po zainstalowaniu node i npm mozemy zainstalowac zaleznosci projektu (screen 10)
        - npm install
    
3. Uruchom testy (screen 11)
    - Aby uruchomic testu na srodowisko node'a uzywamy polecenia:
        - npm test

4. Ponów ten proces w kontenerze
    - Wybierz i uruchom platformę np. ubuntu (analogicznie do poprzednio opisanych krorków)
        - docker pull ubuntu
        - docker run -it ubuntu 
    - Zaopatrz ją w odpowiednie oprogramowanie wstępne    
        - apt update
        - apt install apt
        - apt install git
        - apt install curl
        - apt install nodejs
        - apt install npm
    - Zainstalowanie mozemy potwierdzic wydajac komendy z flaga --version (jak opisano wczesniej)
    - Sklonuj aplikację
        - git clone https://github.com/Lissy93/quick-example-of-testing-in-nodejs.git
        - cd quick-example-of-testing-in-nodejs
    - Skonfiguruj środowisko i uruchom build (screen 12)
        - npm install 
    - Uruchom testy (screen 13)
        - npm test

5. Stwórz Dockerfile, który ma to osiągnąć
    - Na bazie platformowego obrazu
        - FROM node:latest
        - ENV HOME=/home/app
    - Doinstaluj wymagania wstępne
        - RUN apt-get update 
        - RUN apt-get install git
    - Sklonuj repozytorium
        - RUN git clone https://github.com/Lissy93/quick-example-of-testing-in-nodejs.git
    - Zbuduj kod
        - RUN npm install --silent --progress=false
6. Zaprezentuj Dockerfile i jego zbudowanie
    - Dockerfile (02_Dockerfile_build)

        ```
        FROM node:latest

        WORKDIR /usr/app

        RUN apt-get update -qq
        RUN apt-get install git -qq

        RUN git clone https://github.com/Lissy93/quick-example-of-testing-in-nodejs.git
        RUN cd /usr/app/quick-example-of-testing-in-nodejs && npm install --silent
        ```
    - Do zbudowania uzyjemy polecenia build (screen 14)
        - docker build -t node_app:0.1 -f 02_Dockerfile_build .

    - Sprawdzamy czy obraz pojawil sie na liscie
        - docker images
    
7. Na bazie obrazu utworzonego poprzednim dockerfilem stwórz kolejny, który będzie uruchamiał testy
    - Dockerfile (02_Dockerfile_tests)

        ```
        FROM node_app:0.1

        WORKDIR /usr/app/quick-example-of-testing-in-nodejs

        CMD [ "npm","test" ]
        ```
    - Nastepnie musimy zbudowac i uruchomic kontener z testami aplikacji (screen 15)
        - docker build -t node_app_run_tests:0.1 -f 02_Dockerfile_tests .
        - docker run -it node_app_run_tests:0.1

### Runda bonusowa: kompozycja

1. Zdefiniuj kompozycję, która stworzy dwie usługi
    - Pierwszą na bazie dockerfile'a budującego
    - Drugą na bazie pierwszej
        - docker-compose
    
            ```
            version: '2'
            services:
                builder:
                    image: node_app:0.1
                    build: 
                        context: .
                        dockerfile: ./02_Dockerfile_build
                test_runner:
                    image: node_app_with_tests:0.1
                    build: 
                        context: .
                        dockerfile: ./02_Dockerfile_tests
                    depends_on:
                    - builder
            ```
2. Wdróż (screen 16)
    - Aby uruchomić kompozycje uzyjemy polecenia:
        - docker-compose up
    - Aby ją zatrzymać mozemy uzyc polecenia:
        - docker-compose down