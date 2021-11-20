# Metodyki DevOps - Labolatoria 2

## Zestawienie środowiska

### Instalacja Dockera w systemie linux

**Komendy:**
- sudo apt-get install docker-cd docker-cd-cli containerd.io

![Ściąganie dockera](screenshots/1.PNG)

### Pobieramy obrazy hello-world, busybox, ubuntu

**Komendy:**
- docker pull [nazwa obrazu]

![Ściąganie obrazów](screenshots/2.PNG)

### Uruchamiamy busybox
**Efekt uruchominia kontenera: **
**Komendy:**
- docker run [nazwa obrazu]
- docker ps -a

![Efekt uruchomienia konenera](screenshots/3.PNG)

**Podłączenie się do kontenera interaktywnie i wywołanie numeru wersji**
**Komendy:**
- docker run -it [nazwa obrazu]
- busybox | head -1

![Połączenie interaktywne z kontenerem](screenshots/4.PNG)

### Uruchomić "system w kontenerze"

**Procesy wykonane przy połączeniu interaktywnym z systemem ubuntu**:

**Komendy:**
- docker run -it [nazwa obrazu systemu] bash
- ps ax

![Połączenie interaktywne z kontenerem2](screenshots/5.PNG)

**Procesy dockera na hoście (wywołane na w osobnym oknie)**:

**Komendy:**
- ps -aux | grep "docker"

![Procesy dockera na hoście](screenshots/6.PNG)

### Pokazać uruchomione kontenery (nie działające) i je wyczyścić:

**Komendy:**
- docker ps -a
- docker rm $(docker ps -a -q)

![Usunięcie niedziałających kontenerów](screenshots/7.PNG)

![Pokazanie niedziałających obrazów i czyszczenie ich](screenshots/7.PNG)

### Czyszczenie obrazów

**Komendy:**
- docker image
- docker rmi $(docker images -q)

Przed usunięciem wszystkich obrazów, trzeba było wcześniej zastopować kontener z ubuntu komendą **docker stop [id kontenera]**.

![Procesy dockera na hoście](screenshots/8.PNG)

## Budowanie programu

### Znalezienie projektu który łatwo umożliwia wywołanie testów jednostkowych

Naszy repozytorium będzie **node-js-getting-started**
https://github.com/lyhd/reactjs

### Przeprowadzić budowę/konfigurację środowiska

W tym przypadku mamy bardzo mocno uproszczoną sprawę. Należy ściągnąć projekt za pomocą komendy **git clone**, wejść do ściągniętego repozytorium w terminalu i wpisać komendę **npm install** (po upczednim zainstalowaniu samego npm). Po ściągnięciu wszystkiego, wystarczy wpisać **npm start**, wejść w przeglądarkę i wyszukać **localhost:5000**.


![npm install](screenshots/9.PNG)

![npm start](screenshots/10.PNG)

![strona po odpaleniu npm start](screenshots/11.PNG)

### Uruchomić testy

![npm test](screenshots/12.PNG)

### Stworzenie dockerfile (build + test)+ tworzenie obrazu + run

**Komendy:**
- docker build -t=[nazwa_obrazu_po_stworzeniu] -f [nazwa dockerfile] .

**Build:**
Zbudowano dwa następujące pliki dockerowe + stworzone obraz:

![docker files + docker build](screenshots/13.PNG)

Jak widzimy, działa bez zarzutu:

![docker files + docker build2](screenshots/14.PNG)

**Test:**
W CMD trzeba było na końcu dodać "a", ponieważ testy zaimplementowane w pobranym projekcie mają osobny interface do testów w którym należy wybrać tryb testu jaki chcemy.

![docker files + docker build3](screenshots/15.PNG)

Również możemy zauważyć że działa bez zarzutu:

![docker files + docker build4](screenshots/16.PNG)

## Kompozycja

**Komendy:**
- docker docker-compose.yml up
- docker docker-compose.yml down

Stworzono następujący plik docker-compose:

![cat docker-compose.yml](screenshots/17.PNG)

Uruchamiamy nasz plik docker-compose.yml:

![docker-compose.yml up](screenshots/18.PNG)

Na końcu po teście możemy wyłączyć nasz plik

![cat docker-compose.yml down](screenshots/19.PNG)
