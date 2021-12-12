# Metodyki DevOps - lab03

## Środowisko

Ćwiczenia labolatoryjne zostały wykonane na laptopie z macOS Big Sur 11.6

![Zdjęcie środowiska](../lab1/screenshots/macos-big-siur.png)

## Łączność i woluminy na podstawie "złych" praktyk

### Pobranie obrazu Ubuntu

Obraz Ubuntu był już pobrany na poprzednich labolatoriach. Pobrana została konkretna wersja 20.04.

![Pobranie obrazu Ubuntu](screenshots/1.pobranie-obrazu-ubuntu.png)

W przeciwnym razie pobrana zostałaby wersja najnowsza, co nie jest zalecane, ponieważ jeśli mielibyśmy system oparty na obrazie *latest*, w razie wypuszczenia nowej wersji, nie wiedzielibyśmy, jak ona się zachowa.

### Podłączenie woluminu do kontenera

Na początku stworzyłem wolumin. 

![Utworzenie woluminu](screenshots/2.tworzenie-woluminu.png)

Jest to po prostu pewien katalog na dysku w katalogu roboczym dockera, który zacznie być widoczny w kontenerze, jeśli my go do niego podepniemy, u mnie na znajduje się on pod ścieżką:

**"/var/lib/docker/volumes/my-volume/_data"**

Następnie listuję wolumen:

![Listowanie woluminu](screenshots/2.1.wylistowanie-woluminow.png)

Sprawdzam, gdzie znajduje się mój nowoutworzony wolumin:

![Inspekcja woluminu](screenshots/2.0.1.inspekcja-woluminu.png)

Podłączam wolumin do kontenera:

![Podłączenie woluminu do kontenera](screenshots/2.2.podlaczenie-woluminu-do-kontenera.png)

W tym momencie niestety okazuje się, że ścieżka */var/lib/docker* nie istnieje na mojej lokalnej maszynie, ponieważ w Docker for Mac kontenery są przechowywane na wirtualnej maszynie.
Więcej można przeczytać tutaj: https://stackoverflow.com/questions/38532483/where-is-var-lib-docker-on-mac-os-x/65645462#65645462

![Nieprawidłowa ścieżka](screenshots/2.4.nieprawdilowy-path.png)

Również, żadne z przedstawionych rozwiązań nie pomogło, oprócz *docker run* z przełącznikiem *-v*:

![Podłączenie woluminu do kontenera cd](screenshots/2.3.podlaczenie-woluminu-2.png)

### Kopiowanie pliku do katalogu woluminu i pokazanie go w kontenerze

Tworzę plik, który kopiuję do folderu **~/volume-host**:

![Kopiowanie do katalogu woluminu](screenshots/3.kopiowanie-do-katalogu-woluminu.png)

Sprawdzam przekopiowany plik na kontenerze w folderze **/store**:

![Sprawdzenie przekopiowanego pliku](screenshots/3.1.sprawdzenie-przekopiowanego-pliku.png)

Jak widać, plik istnieje.

### Utworzenie pliku w kontenerze i pokazanie go na hoście

Tworzę plik na kontenerze:

![Utworzenie pliku na kontenerze](screenshots/4.utworzenie-pliku-na-kontenerze.png)

Plik pokazuje się w folderze z woluminem na hoście:

![Sprawdzenie pliku na hoście](screenshots/4.1.sprawdzenie-pliku-na-hoscie.png)

## "Kiepski pomysł": SSH

### Uruchomienie i wyeksportowanie wybranego portu w kontenerze

Uruchamiam kontener z wybranem portem 7312:

![Uruchomienie kontenera z wybranym portem](screenshots/5.uruchomienie-kontenera-z-wybranym-portem.png)

Przekierowuję port 7312 hosta na port 22 wewnątrz kontenera. 

### Instalacja serwera SSH w kontenerze

Instalacja serwera SSH obywa się przy użyciu komendy: **apt-get install openssh-server**.

Po wykonaniu tej komendy sprawdzam wersję serwera SSH:

![Sprawdzenie wersji SSH](screenshots/6.sprawdzenie-wersji-ssh.png)

### Zmiana portu na wybrany port

