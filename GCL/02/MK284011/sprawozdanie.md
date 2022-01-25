ZESTAWIENIE ŚRODOWISKA

1. Zainstauj Docker w systemie linkus

*Zdjecie1
    "sudo apt install docker-ce"

2. Zarejestruj się w Docker Hub i zapoznaj z sugerowanymi obrazami
    Strona: https://hub.docker.com/

*Zdjęcie2


3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql
    "docker run hello-world" -> pobierze nam obraz hello-world

*zdjecie3

4. Uruchom busybox
- Pokaż efekt uruchomienia kontenera
- Podłącz się do kontenera interaktywnie i wywołaj numer wersji

    "docker run busybox" -> uruchomi nam busybox, ale jako, że nie wcześniej go nie mieliśmy to pobierze nam pierw obraz
*zdjecie4
*zdjecie5


5. Uruchom "system w kontenerze"
    "docker run -it ubuntu" -> uruchomienie ubuntu w kontenerze
*zdjecie6

- Zaprezentuj PID1 w kontenerze i procesy dockera na hoście

PID1 można zaprezentować za pomocą polecenia ps

*zdjecie7
Natomiast procesy dockera na hoście za pomocą:
    "docker ps" -> aby sprwadzić id kontenera
    "docker top <id kontenera> -> aby wylistowac procesy dockera na hoscie

*zdjecie8

- Zaktualizuj pakiety
    "apt update && apt upgrade" -> wpisujemy polecenie wewnatrz kontenera

*zdjecie9

- Wyjdź
    Wystarczy wpisać polecenie "exit"

*zdjecie10

6. Pokaż uruchomione ( != "działające" ) kontenery, wyczyść je.
    "docker ps" -> pokazuje wszystkie uruchomione kontenery
    "docker ps -a" -> pokazuje nam wszystkie kontenery

*zdjecie11
    "docker rm <id kontenera> -> czyścimy kontener

*zdjecie12

7. Wyczyść obrazy
Podobnie jak powyżej:
    "docker images" -> sprawdzamy obrazy
    "docker image rm <id obrazu> -> usuwamy obraz

*zdjecie13

BUDOWANIE PROGRAMU

1. Znajdź projekt umożliwiający łatwe wywołanie testów jednostkowych
 - https://github.com/johnpapa/node-hello
2. Przeprowadź budowę/konfigurację środowiska
3. Uruchom testy
4. Ponów ten proces w kontenerze
 - Wybierz i uruchom platformę
    "sudo docker run -it ubuntu" -> wybrana platforma to unbuntu

*zdjecie14

 - Zaopatrz ją w odpowiednie oprogramowanie wstępne
    Jako, ze wybrana przeze mnie aplikacja bazuje potrzebowalem zainstalować nodejs, npm, git

    "apt-get update"
    "apt-get -y install curl gnupg"
    "curl -sL https://deb.nodesource.com/setup_11.x  | bash -"
    "apt-get -y install nodejs"
    "apt install npm"
    "apt install git"

 - Sklonuj aplikację
    git clone https://github.com/johnpapa/node-hello.git

 - Skonfiguruj środowisko i uruchom build
 - Uruchom testy
*zdjecie15

5. Stwórz Dockerfile, który ma to osiągnąć
 - Na bazie platformowego obrazu...
 - ...doinstaluj wymagania wstępne...
 - ...sklonuj repozytorium...
 - ...zbuduj kod

*zdjecie16
*zdjecie17
 
6. Zaprezentuj Dockerfile i jego zbudowanie
*zdjecie18
