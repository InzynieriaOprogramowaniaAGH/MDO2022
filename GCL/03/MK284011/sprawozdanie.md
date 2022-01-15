1. Łączność i woluminy na podstawie "złych" praktyk
 - Pobierz obraz Ubuntu
    "sudo docker pull ubuntu"
*Zdjecie1

 - Podłącz wolumin do kontenera
    "docker volume create JenkisTest" -> stworzenie voluminum
*Zdjecie2
    "sudo docker run --interactive --tty --mount source=JenkisTest,destination=/store ubuntu" -> podpięcie woluminu do kontenera
*Zdjecie3 

 - Skopiuj plik do katalogu woluminu, pokaż w kontenerze
    "touch file1" -> utworzenie pliku
    "sudo cp plik /var/lib/docker/volume/JenkisTest/_ date" -> skopiowanie pliku do woluminium 
*Zdjecie4

 - Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście
    "touch file2" -> należało utworzyć plik w ścieżce /store
*Zdjecie5

2. "Kiepski pomysł": SSH
 - Uruchom i wyeksponuj wybrany port w kontenerze

    "sudo docker run --interactive --tty --mount source=JenkisTest,destination=/store --publish 8080 ubuntu"
*Zdjecie6

 - Zainstaluj w kontenerze serwer ssh
    "apt-get update" -> update
    "apt-get install openssh-server" -> instalacja

 - zmień port na wybrany port >1024
    Należało z edytować plik sshd_config
*Zdjecie7
 - zezwól na logowanie root
 *Zdjecie8
 - umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
    "ssh-keygen -t rsa" -> tworzenie klucza
*Zdjecie9
    Za pomocą narzędzia MC przeniosłem plik do kontenera
*Zdjecie10
*Zdjecie11
*Zdjecie12
 - odnajdź adres IP kontenera w wewnętrznej sieci
    Instalacja net-tools
    Następnie ifconfig
*Zdjecie13
 - uruchom usługę, połącz się z kontenerem
*Zdjecie14

3. Skonteneryzowany Jenkins stosujący Dockera
a) Pzygotowanie
 - Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
 - Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
 - Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
 - Przygotuj obraz blueocean na podstawie obrazu jenkinsa
 - Uruchom blueocean
 Wszystkie powyższe kroki wykonałem zgodnie zdokumentacją
 - Zaloguj się i skonfiguruj Jenkins'
 *Zdjecie15
 *Zdjecie16
 
b) Mikro-projekt Jenkins
 - Utwórz projekt, który wyświetla uname
 *Zdjecie17
 *Zdjecie18
 Utworzyłem nowy free-styleowy projekt i w segmencie build (execute shell) dodałem "uname -a"
 *Zdjecie19
 - Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta
 Podobnie jak wyżej
 *Zdjecie20
 - Utwórz "prawdziwy" projekt, który:
 - klonuje nasze repozytorium
 - przechodzi na osobistą gałąź
 - buduje obrazy z dockerfiles i/lub komponuje via docker-compose