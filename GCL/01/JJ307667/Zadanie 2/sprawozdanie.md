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
- ps ax

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
