### Metodyki devOps - laboratorium 2 sprawozdanie

#### Część 1

1. Zainstaluj Docker w systemie linuksowym
   Pobierz [docker](https://docs.docker.com/get-docker)
   Zweryfikuj zainstalowane oprogramowanie `docker --version`
2. Zarejestruj się w Docker Hub i zapoznaj z sugerowanymi obrazami
   Załóz konto na [docker hub](`https://hub.docker.com`)
   Zaloguj się zdalnie z pomocą terminala `docker login`
3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql
   `docker pull busybox`
4. Uruchom busybox
   - Pokaż efekt uruchomienia kontenera
     Wywołajywołaj `docker run busybox`
   - Podłącz się do kontenera interaktywnie i wywołaj numer wersji
     Wywołaj `docker run -it busybox`
5. Uruchom "system w kontenerze"
   - Zaprezentuj PID1 w kontenerze i procesy dockera na hoście
     Wywołaj `ps -a` z poziomnu kontenera i hosta
   - Zaktualizuj pakiety
     Wywołaj `apt-get update`
   - Wyjdź
     Wywołaj `exit` z poziomu kontenera
6. Pokaż uruchomione ( != "działające" ) kontenery, wyczyść je.
   Wywołaj `docker container prune -a`
7. Wyczyść obrazy
   Wywołaj `docker image prune -a`

#### Część 2

1.  Znajdź projekt umożliwiający łatwe wywołanie testów jednostkowych
    Przykładowy [projekt nodejs](`https://github.com/jaspenlind/node-ts-starter-template`)
2.  Przeprowadź budowę/konfigurację środowiska
    Wywołaj:
    `apt-get install -y git nodejs npm`
    `git clone https://github.com/jaspenlind/node-ts-starter-template`
    cd `node-ts-starter-template`
    `npm install`
    `npm run build`
3.  Uruchom testy
    `npm run test`
4.  Ponów ten proces w kontenerze
    - Wybierz i uruchom platformę
      Pobierz obraz ubuntu `docker pull ubuntu`
    - Zaopatrz ją w odpowiednie oprogramowanie wstępne
      `apt-get install -y git nodejs npm`
    - Sklonuj aplikację
      `git clone https://github.com/jaspenlind/node-ts-starter-template`
      cd `node-ts-starter-template`
    - Skonfiguruj środowisko i uruchom build
      `npm install`
      `npm run build`
    - Uruchom testy
      `npm run test`
5.  Stwórz Dockerfile, który ma to osiągnąć

    - Na bazie platformowego obrazu...
    - ...doinstaluj wymagania wstępne...
    - ...sklonuj repozytorium...
    - ...zbuduj kod
      Dockerfile_build:

                FROM ubuntu

                RUN apt-get update
                RUN apt-get install -y curl git
                RUN curl -sL https://deb.nodesource.com/setup_10.x | bash
                RUN apt-get install nodejs

                RUN git clone https://github.com/jaspenlind/node-ts-starter-template.git

                WORKDIR ./node-ts-starter-template

                RUN npm install
                RUN npm build

6.  Zaprezentuj Dockerfile i jego zbudowanie
    Zbuduj obraz `docker build -t node_build -f Dockerfile_build .`
7.  Na bazie obrazu utworzonego poprzednim dockerfilem stwórz kolejny, który będzie uruchamiał testy
    Dockerfile_tests:

                FROM node_build

                RUN npm test

    Zbuduj obraz `docker build -t node_tests -f Dockerfile_tests .`
