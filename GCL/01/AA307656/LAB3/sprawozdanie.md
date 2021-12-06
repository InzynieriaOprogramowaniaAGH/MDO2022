# Część pierwsza - czas do końća weekendu


## 1 . Łączność i woluminy na podstawie "złych" praktyk

   - Pobierz obraz Ubuntu
   - Podłącz wolumin do kontenera
   
    $ docker volume create pendrive
    $ docker volume inspect pendrive

        "CreatedAt": "2021-12-04T15:51:48+01:00",
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/var/lib/docker/volumes/pendrive/_data",
        "Name": "pendrive",
        "Options": {},
        "Scope": "local"
    

    $ sudo docker run -iteractive --tty --mount source=pendrive,destination=/store ubuntu

![screen: utworzenie volumenu](screenshots/1.png)
  
   - Skopiuj plik do katalogu woluminu, pokaż w kontenerze
    $ sudo cp sprawozdanie /var/lib/docker/volumes/pendrive/_data

![screen: plik pokazany w kontenerze](screenshots/2.png)	
    
   - Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście
   
![screen: pokazanie pliku na hoscie](screenshots/3.png)	


## 2 . "Kiepski pomysł": SSH

   - Uruchom i wyeksponuj wybrany port w kontenerze
   
    $ sudo docker run --interactive --tty --mount source=pendrive,destination=/store --publish 2345:22 ubuntu
  
![screen: uruchomione procesy](screenshots/4.png)
![screen: docker-proxy](screenshots/5.png)

    $ sudo docker ps -a
    $ sudo docker container inspect "id"

![screen: poszukiwanie ip contenera](screenshots/7.png)

   - Zainstaluj w kontenerze serwer ssh
   
   	# apt update
   	# apt install ssh
   	# apt-get -y install openssh-server
   	# apt-get install net-tools
   	# mkdir /run/sshd
	# /usr/sbin/sshd -D &

   - zmień port na wybrany port >1024
   
   $ sudo docker inspect 23d9fb41d069
   
![screen: poszukiwanie ip contenera](screenshots/6.png)
   

   - zezwól na logowanie root
   - umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
    # ls -la /store
    # cp /store/id_ed25519.pub /root/.ssh/authorized_keys
      
![screen: skopiowanie klucza publicznego](screenshots/8.png)
 
   - odnajdź adres IP kontenera w wewnętrznej sieci
   - uruchom usługę, połącz się z kontenerem
   
![screen: netstat w kontenerze](screenshots/9.png)
![screen: błędy z kluczem](screenshots/10.png)
![screen: uruchomiona usługa z kontenerem](screenshots/11.png)


Skonteneryzowany Jenkins stosujący Dockera
Przygotowanie

    Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
    Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
        Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
        Przygotuj obraz blueocean na podstawie obrazu jenkinsa
        Uruchom blueocean
        Zaloguj się i skonfiguruj Jenkins

Mikro-projekt Jenkins

    Utwórz projekt, który wyświetla uname
    Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta
    Utwórz "prawdziwy" projekt, który:
        klonuje nasze repozytorium
        przechodzi na osobistą gałąź
        buduje obrazy z dockerfiles i/lub komponuje via docker-compose

Sprawozdanie

    Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
        Wymagania wstępne środowiska
        Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
        Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami

