# Metodyki DevOps - lab03

## Środowisko

Ćwiczenia labolatoryjne zostały wykonane na laptopie z macOS Big Sur 11.6

![Zdjęcie środowiska](../lab1/screenshots/macos-big-siur.png)

Potem postawiłem Ubuntu na Paralellsach, gdyż Docker for Mac na macOS nie działa tak jak powinien, więcej poniżej:

![Ubutnu środowisko](screenshots/0.ubuntu-srodowisko.png)

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


### Zmiana portu na wybrany port > 1024 oraz zezwolenie na logowanie root'a

W celu zmiany portu edytuję plik **sshd_config**, znajdujący się w */etc/ssh/* oraz dodaję linijkę **PermitRootLogin yes**, gdyż nie było jest standardowo:

![Zmiana portu i pozwolenie na logowanie root'a](screenshots/7.zmiana-portu-i-umozliwienie-logowania-roota.png)

### Umieszczenie klucza publicznego w woluminie

Kopiuję klucz publiczny hosta do woluminu:

![Kopiowanie kluczu publicznego hosta](screenshots/8.1.kopiowanie-kluczu-pub-hosta-do-wolimuinu.png)

### Kopiuję klucz publiczzny do zaufanych w kontenerze

Na początku tworzę folder **.ssh** w kontenerze:

![Utworzenie folderu .ssh](screenshots/8.utworzenie-folderu-.ssh.png)

Kopiuję klucz publiczny z woluminu do pliku zaufanych znajdującego się w folderze *~/.ssh/* w kontenerze pod nazwą **authrozed_keys**:

![Umieczenie klucza publicznego hosta w ~/.ssh/authorized_keys](screenshots/8.2.umieszczenie-klucza-pub-w-authorized_keys.png)

### Odnalezienie adresu IP kontenera w wewnętrznej sieci

Adres kontenera możemy uzyskać poleceniem **docker container inspect <container_id>**:

![Odnalezienie adresu IP kontenera](screenshots/9.odnalezienie-adresu-ip-kontenera.png)

### Uruchomienie usługi

Aby SSH działało, potrzebne jest uruchomienie serwisu **sshd -D &**, znajdującego się w */usr/sbin/*, oraz utworzenie folderów **/run/sshd**:

![Dokonfigurowanie SSH](screenshots/6.2.dokonfigurowanie-ssh-2.png)

Sprawdzam status połączeń sieciowych przy użyciu *netstat*:

![netstat -tunpa](screenshots/6.3.netstat-tunpa.png)

Widać, że usługa SSH działa na kontenerze na porcie 22.

### Połączenie się z kontenerem

Niestety z tego samego powodu, dla którego nie mogłem znaleźć ścieżki */var/lib/docker* (https://docs.docker.com/desktop/mac/networking/):

![Brak docker0 bridge](screenshots/10.docker0.png)

Nie ma też wirtualnego interfejsu *docker0* :(

![ifconfig grep docker](screenshots/10.1.ifconfig-docker.png)

To jest ten moment, żeby zmienić sprzęt.

#### Few moments later - Postawienie Ubuntu na Paralellsach

Powtórzyłem wszystkie powyższe kroki na nowo postawionym hoście Ubuntu 20.04 LTS.

Finalnie, udało się połączyć, w pliku */etc/ssh/sshd_config* użyłem portu 4200, zamiast 2222, który był na powyżej, gdy zmieniałem go będąc na kontenerze postawionym na natywnym macOS.

![netstat -tunpa na kontenerze](screenshots/10.3.netstat-tunpa-na-kontenerze.png)

![Wejście na kontener z Ubuntu](screenshots/10.2.wejscie-na-kontener-po-ssh.png)

Wniosek z tego taki, że mimo iż mapujemy dane porty host:kontener, ostateczny port kontenera zostanie wzięty z pliku *sshd_config* i zrestartowaniu serwisu sshd.

## Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie

### Upewnienie się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium

![Upewnienie się](screenshots/11.upewnienie-sie.png)

### Zapoznanie się z instrukcją

Zapoznałem się z instrukcją postawienia kontenera jenkinsowego:
*https://www.jenkins.io/doc/book/installing/docker/*

### Uruchomienie obrazu Dockera, który eksponuje środowisko zagnieżdżone

Uruchamia, obraz dockera ze środowiskiem zagnieżdżonym:

![Uruchomienie obrazu dockera](screenshots/12.uruchomienie-obrazu-dockera.png)

### Przygotowanie obrazu Blue Ocean na podstawie obrazu Jenkinsa

Przygotowałem obraz Blue Ocean na podstawie obrazu Jenkinsa

![Przygotowanie obrazu Blue Ocean](screenshots/13.przygotowanie-obrazu-blueocean.png)

### Uruchomienie Blue Ocean

Na początku buduję obraz Blue Ocean:

![Budowanie obrazu Blue Ocean](screenshots/13.1.budowanie-obrazu-blueocean.png)
![Koniec budowania obrazu Blue Ocean](screenshots/13.1.1.koniec-budowania-obrazu-blueocean.png)

Następnie uruchamiam kontener z Blue Ocean:

![Uruchomienie kontenera z Blue Ocean](screenshots/13.2.uruchomienie-kontenera-blueocean.png)

### Loguję się i konfiguruję Jenkins

Odblokowuję Jenkinsa sprawdzając hasło admina używając **docker logs <container_id>**:

![Docker logs](screenshots/14.docker-logs.png)

Następnie odblokowuje Jenkinsa podanym hasłem:

![Odblokowanie Jenkinsa](screenshots/14.1.odblokowanie-jenkinsa.png)

Po zalogowaniu się mogę korzystać z serwisu:

![Można korzystać z Jenkins](screenshots/14.2.jenkins-alive.png)

#### Mikro-projekt Jenkins

### Utworzenie projektu, który wyświetla uname

Tworzę nowy projekt - na dashboardzie klikam **New item**, następnie podaję nazwę projektu i wybieram **Freestyle projekt**. Następnie w sekcji **Build** podaję skrypt bashowy do wykonania:

![Tworzenie projektu ze skryptem Bash](screenshots/15.projekt-uname.png)

Sprawdzam output konsolowy:

![Output](screenshots/15.1.logi-z-konsoli.png)

### Utwórz projekt, który zwraca błąd, gdy godzina jest nieparzysta

Projekt jest tworzony w taki sam sposób, zmienia się jedynie skrypt:

```shell
hour=$(date '+%H')
if [ $((hour  % 2 )) -eq 0 ];
then
    echo "is even";
    exit 0;
else
    echo "is odd";
    exit 1;
fi
```

![Sprawdzenie czy parzyste](screenshots/15.4.check-if-even.png)

Logi konsolowe:

![Logi konsolowe - sprawdzenie parzystości godziny](screenshots/15.5.logi-z-konsoli.png)

Godzina była w formacie UTC, a czas na mojej maszynie jest CET (UTC + 1), aktualnie 22:51, więc wg czasu UTC, jest to godzina nieparzysta, więc projekt nie zostanie zbudowany.

### Utworzenie "prawdziwego" projektu, który klonuje nasze repozytorium, przechodzi na osobistą gałąź, buduje obrazy z dockerfiles i/lub komponuje via docker-compose

Na początek instaluję wtyczkę **Docker Compose Build Step** wchodząc w **Manage Jenkins** -> **Plugin Manager**:

![Instalacja wtyczki Docker COmpose Build Step](screenshots/16.instalacja-wtyczki-docker-compose.png)

Tworzę nowy projekt **Multi-configuration project**

Następnie wybieram poniższe ustawienia:

![Wybranie repozytorium](screenshots/16.2.wybranie-repozytorium.png)

![Klonowanie repozytorium](screenshots/16.1.klonowanie-repo.png)

![Przejście na osobistą gałąź](screenshots/16.3.przejscie-na-osobista-galaz.png)

Niestety budowanie nie jest zakończone sukcesem i nie jestem w stanie się dowiedzieć dlaczego, gdyż z logów nic nie wynika:

![Build failed](screenshots/16.5.console-faile.png)