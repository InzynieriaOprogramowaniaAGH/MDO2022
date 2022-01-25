# Metodyki DevOps - lab02

## Środowisko

Ćwiczenia labolatoryjne zostały wykonane na laptopie z macOS Big Sur 11.6

![Zdjęcie środowiska](../lab1/screenshots/macos-big-siur.png)

## Zestawienie środowiska

### Instalacja Dockera

Dockera zainstalowano przy użyciu missing package managera *brew* i komendy **brew install docker**, jednak wtedy
wyświetlał się komunikat, że docker daemon nie działa. Po znalezieniu rozwiązania w Google, zainstalowano Docker Desktop i wtedy można było przejść do pracy.
Warto zauważyć, że polecenia dockerowe możemy wykonywać z dowolnego miejsca w systemie.

Wersja Dockera poniżej:

![Docker version](screenshots/docker-version.png)

### Rejestracja na Docker Hub

Konto na Docker Hub zostało założone już jakiś czas temu podczas robienia kursów z Dockerem:

![Docker Hub](screenshots/docker-hub.png)

### Pobranie obrazów dockerowych

Z repozytorium pobrano obrazy przy użyciu komendy **docker pull:

- hello-world

![docker pull hello-world](screenshots/docker-pull-hello-world.png)

- busybox

![docker pull busybox](screenshots/docker-pull-busybox.png)

- ubuntu:20.04

![docker pull ubuntu:20.04](screenshots/docker-pull-ubuntu-20.04.png)

- mysql

![docker pull mysql](screenshots/docker-pull-mysql.png)

### Uruchomienie kontenera z busybox
#### Podejście zwykłe

Do uruchomienia kontenera użyto komendy **docker run <image_name>**.\
Należy pamiętać, że sama w sobie komenda uruchamia kontener na podstawie podanego obrazu, ale bez dodatkowych parametrów runtime nie wie co dalej począć, więc zatrzymuje kontener.\
Listę wszystkich naszych kontenerów otrzymano przy użyciu komendy **docker ps -a**. Można też użyć **docker container list -a** jak ktoś lubi:

![Uruchomienie busybox](screenshots/uruchomienie-busybox.png)

#### Podejście interaktywne

Aby uruchomić kontener w trybie interaktywnym, używamy przełączników **-ti**. Dzięki temu runtime, po wystartowaniu kontenera jako pierwszą komendę uruchamia shella:

![Uruchomienie bubsybox interaktywnie](screenshots/uruchomienie-busybox-interaktywnie.png)

Przy użyciu komendy **busybox** będąc na kontenerze, możemy sprawdzić numer wersji:

![Busybox name](screenshots/busybox-name.png)

### Uruchomienie systemu w kontenerze

Podobnie jak w przypadku *busybox*, tym razem uruchomiono Ubuntu 20.04, od razu w trybie interaktywnym:

![Uruchomienie ubuntu interaktywnie](screenshots/docker-run-ubuntu-ti.png)

#### Prezentacja PID1 w kontenerze

Będąc na kontenerze, przy użyciu komendy **ps aux** jesteśmy w stanie sprawdzić procesy tego kontenera:

![Prezentacja PID1 w kontenerze](screenshots/prezentacja-pid1.png)

#### Prezentacja procesów Dockera na hoście

Mając uruchomione kontenery busybox i ubuntu:20.04 odpalam w trzecim oknie terminala znaną już komendę **ps aux** i,
po pipe'ie grepuję z przełącznikiem **-i** (ignore case, insensitive matching) po słowie "docker". 

![Proocesy Dockera na hoście](screenshots/procesy-dockera-na-hoscie.png)

Myślę, że powinny nas interesować konretnie procesy 10000 < ID < 11500, które odpowiadają za runtime aktualnie odpalonych kontenerów.

#### Aktualizacja pakietów

Na Ubuntu znajduje się package manager o nazwie APT (Advanced Packaging Tool). Używając poleceń **apt update**
i **apt upgrade** aktualizujemy paczki (można było wykonać polecenia łącząc je dwoma ampersandami):

![Aktualizacja pakietów](screenshots/aktualizacja-pakietow.png)

#### Wyjście z kontenera

Z kontenera wychodzimy tak jak z każdego shella, czyli komendą **exit**:

![Wyjście z kontenera z Ubuntu](screenshots/exit-ubuntu.png)

### Pokazanie aktualnych kontenerów:

Przy użyciu znanej już komendy **docker container list -a** otrzymujemy aktualnie uruchomione kontenery, z czegoo tylko jeden o imieniu "**pedantic_haibt"** działa:

![Listowanie uruchomionych konteneórw](screenshots/listowanie-kontenerow.png)

### Wyczyszczenie obrazów

Przed wyczyszczeniem obrazów, najpierw je listuję przy użyciu **docker image ls**:

![Listowanie obrazów](screenshots/listowanie-obrazow.png)

Następnie je usuwam komendą **docker image rm <image_name>, <image_name2>**:

![Czyszczenie obrazów](screenshots/czyszczenie-obrazow.png)

## Budowanie programu

### Znalezienie odpowiedniego projektu

Jako projekt wybrano Create React App (https://create-react-app.dev/). Posługiwano się także guidem: https://create-react-app.dev/docs/getting-started/

### Przeprowadzenie budowy i konfiguracji środowiska
#### Konfiguracja środowiska

Zanim ściągniemy aplikację, musimy posiadać zainstalowany pakiet NodeJS.
Na systemie operacyjnym macOS, można to zrobić za pomocą komendy **brew install node**:

![Instalacja node](screenshots/brew-install-node-1.png)
![Koniec instalacji node](screenshots/brew-install-node-finish.png)

Po instalacji sprawdzam wersję pakietu Node:

![Wersja node](screenshots/node-version.png)

#### Instalacja aplikacji

Aplikację ściągamy przy użyciu komendy **npx create-react-app <nazwa_aplikacji>**:

![Instalacja aplikacji](screenshots/npx-create-react-app.png)

#### Budowanie aplikacji 

Po instalacji przechodzę do folderu z aplikacją używając **cd my-app** i budujemy aplikację używając **npm run build**:

![Budowanie aplikacji](screenshots/npm-run-build.png)

#### Uruchomienie testów

Zgodnie z instrukcją, testy uruchamiane są przy użyciu komendy **npm test**:

![Uruchomienie testów](screenshots/npm-test.png)

Poprowadzi nas to później do interaktywnego okna z testami:

![Uruchomione interaktywnie testy](screenshots/npm-test-interactive.png)

### Ponowienie procesu na kontenerze
#### Wybranie i uruchomienie platformy

Na początek odpalam nowy kontener z Ubuntu. Obraz nie jest znaleziony, został usunięty,
więc jest zaciągany nowy z repozytorium Dockera (dobrą praktyką jest wyspecyfikowanie konkretnej wersji, ponieważ w innym 
przypadku ściągana jest zawsze *latest*, co może prowadzić do różnych rezultatów):

![Uruchomienie kontenera z Ubuntu](screenshots/kontener-ubuntu.png)

Warto zwrócić uwage na flagę **-d** (**--detached**), która odpowiada za działanie kontenera w tle

#### Zaopatrzenie platformy w odpowiednie oprogramowanie

Gdy kontener już działa, wchodzę na niego, wykonując na nim komendę **bash**, przy użyciu **docker exec -ti <nazwa_kontenera>**

![Wejście na kontener](screenshots/wejscie-na-kontener.png)

Następnie zaopatruję go w odpowiednie oprogramowanie:

![Instalacja niezbędnego oprogramowania na kontenerze](screenshots/zaopatrzenie-kontenera.png)

Doinstalowuję npm, gdyż okazało się, że nie ma go pakiecie nodejs:

![Doinstlowanie npm na kontenerze](screenshots/doinstalowanie-npm.png)

Wersja npm na kontenerze z Ubuntu:

![Wersja npm na kontenerze](screenshots/npm-version-kontener.png)

To jest ciekawe, że na Ubuntu jest inna wersja npm i node niż na lokalnym macOS. Paczki były zauktualizowane do najnowszych w obu przypadkach.

#### Klonowanie aplikacji

Do sklonowania aplikacji użyto komendy **docker cp <source> <container>:<path>** 

![Klonowanie aplikacji](screenshots/klonowanie-aplikacji.png)

Następnie na kontenerze patrzę, czy faktycznie aplikacja została sklonowana:

![Sprawdzenie sklonowania](screenshots/sprawdzenie-sklonowania.png)

#### Konfiguracja środowiska i uruchomienie testów

Środowisko zostało już wyposażone w odpowiednie oprogramowanie, teraz czas uruchomić build, przy pomocy już nam znanej komendy **npm run build**:

![Odpalenie builda na kontenerze](screenshots/npm-run-build-container.png)

Następnie uruchamiono testy przy pomocy **npm test** 

![Odpalenie testów na kontenerze](screenshots/npm-test-kontener.png)

Tak jak w poprzednim przypadku, po uruchomieniu testów, pojawia się menu z testem interaktywnym: 

![Test interaktywnie](screenshots/npm-test-interactive-kontener.png)

### Prezentacja Dockerfile i jego zbudowanie

Dockerfile wygląda jak poniżej:

![Dockerfile](screenshots/Dockerfile-new.png)

Warto zaznaczyć, że ARG DEBIAN_FRONTEND został ustawiony na *noninteractive*, ponieważ podczas
instalacji pakietu *npm*, potrzebna była interakcja w sprawie wyboru regionu użytkownika, która blokowała instalację.

Zbudowanie obrazu następuje dzięki komendzie **docker build -t <nazwa_obrazu> \<path>** oraz dostępne obrazy zostały pokazane na poniższym zdjęciu:

![Zbudowanie obrazu i obrazy](screenshots/zbudowanie-obrazu-i-obrazy.png)

Jak widać, proces budowania przebiegł pomyślnie.

Po odpaleniu kontenera i sprawdzeniu logów, przy użyciu **docker logs <container_name>**, można zobaczyć, że aplikacja została zbudowana:

![Uruchomienie kontenera z aplikacją](screenshots/uruchomienie-kontenera-z-aplikacja.png)

Sprawdzenie jego logów:

![Sprawdzenie logów kontenera gdzie była budowana aplikacja](screenshots/sprawdzenie-logow-z-.png)

### Tworzenie obrazu, który uruchamia testy, na bazie poprzedniego obrazu

Dockerfile dla obrazu, który uruchamia testy:

![Dockerfile-test](screenshots/Dockerfile-test.png)

Budowanie obrazu odbywa się za pomocą tej samej komendy, co poprzednio, z małym wyjątkiem,
że z przełącznikiem **-f** dodajemy ścieżkę do Dockerfile, przy pomocy którego obraz ma zostać zbudowany.
Domyślnie nie trzeba jej podawać, gdyż ten plik nazywa się Dockerfile.

![Budowanie obrazu z testami](screenshots/budowanie-obrazu-z-testami.png)

Uruchomienie kontenera, który uruchamia testy:

![Uruchomienie kontenera z testami](screenshots/uruchomienie-kontenera-z-testami.png)

Sprawdzenie logów dla tego kontenera:

![Sprawdzenie logów z testami](screenshots/sprawdzenie-logow-z-testami.png)

Na koniec z własnej woli sprawdzam uruchomione kontenery. Interesują nas obrazy o nazwie zaczynającej się od *ubuntu-init*.
Widać wyraźnie, że po zakończonej pracy, kontener odpowiedzialny za buildowanie aplikacji *ubuntu-init-react-react-app*
zostaje wyłączony. Kontener odpowiedzialny za testowanie aplikacji *ubuntu-init-react-react-app-test* działa w tle i wydaje mi się,
że jest to spowodowane tym, że podczas uruchamiania testów osobiście, wchodziliśmy w interaktywne okno, z którego później trzeba było wyjść:

![Sprawdzenie aktualnych kontenerów](screenshots/sprawdzenie-aktualnych-kontenerow.png)

## Runda bonusowa: kompozycja

Tworzę plik o nazwie *docker-compose.yml* przy użyciu *touch*:

![Utworzenie docker-compose.yml](screenshots/tworzenie-docker-compose.yml.png)

Jeżeli plik się nie nazywa "docker-compose.yml", to musimy wskazać jego nazwę przez *-f*.

### Niepoprawne wykonanie zadania, po skończeniu tej części zorientowałem się, że nie o to chodziło 

Do stworzenia pliku docker-compose'a używam narzędzia *composerize* - https://github.com/magicmark/composerize
Dzięki temu narzędziu zostanie wygenerowany kod, który przekleimy do *docker-compose.yml*
Można je prosto zainstalować używając npm'a:

![Instalacja composerize](screenshots/instalacja-composerize.png)

Tworzę kompozycję najpierw dla obrazu do budowania aplikacji:

![Docker compose - budowanie aplikacji](screenshots/docker-compose-budowanie-aplikacji.png)

Następnie dla obrazu z uruchamianiem testowania aplikacji:

![Docker compose - testowanie aplikacji](screenshots/docker-compose-testowanie-aplikacji.png)

Na sam koniec sklejam wygenerowane kody w jedną całość do pliku *docker-compose.yml*:

![docker-compose.yml](screenshots/docker-compose.yml.png)

Jedyne co nam teraz pozostaje to wywołanie komendy **docker-compose up**.

![docker-compose up](screenshots/docker-compose-up.png)

Kontenery będą zachowywać się tak samo jak uruchamialiśmy je osobno, ten odpowiedzialny za build aplikacji, po zrobieniu tego zostanie wyłączony,
a drugi, odpowiedzialny za testowanie, prze to, że jest to interaktywne testowanie, cały czas trzyma sesję w terminalu.

### Poprawne wykonanie zadania

Niestety dopiero teraz zorientowałem się, że trzeba było użyć kompozycję na bazie Dockerfile.
Poprawna kompozycja została przedstawiona poniżej:
![docker-compose.yml poprawny](screenshots/docker-compose.yml-poprawny.png)

Po uruchomieniu **docker-compose up** otrzymujemy nasz porządany widok:

![docker-compose up poprawny](screenshots/docker-compose-up-poprawny.png)