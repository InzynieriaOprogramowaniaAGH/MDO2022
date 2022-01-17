

# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk

* Pobierz obraz Ubuntu

      sudo docker pull ubuntu
      
img1
      
* Podłącz wolumin do kontenera

Tworzenie woluminu:

      sudo docker volume create moj_volumin
      
Podłączanie do kontenera:
      
      sudo docker run --interactive --tty --mount source=volumin,destination=/store ubuntu

img2

* Skopiuj plik do katalogu woluminu, pokaż w kontenerze

      touch plik_aaa
      cp plik_aaa /var/lib/docker/volumes/volumin/_data

img3

img4

* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście

      touch /store/plik2

img5


#### "Kiepski pomysł": SSH
* Uruchom i wyeksponuj wybrany port w kontenerze

      docker run -it --mount source=volumin,destination=/store --publish 2222:22 ubuntu

img6

* Zainstaluj w kontenerze serwer ssh

      apt-get update
      apt-get install openssh-server
      
img7

* zezwól na logowanie root

Odkomentowanie portu i logowania roota w pliku sshd_config i zmiana PermitRootLogin na yes 

      nano /etc/ssh/sshd_config
      

img8

* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze

Umieszczenie klucza publicznego w woluminie:

img9

Tworzymy katalog dla klucza

	mkdir /root/.ssh
      
img9

Kopiowanie klucza

	cp /store/id_ed25519.pub /root/.ssh/authorized_keys
      
img11
img10

* odnajdź adres IP kontenera w wewnętrznej sieci

      docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' nazwa_kontenera

img12
* uruchom usługę, połącz się z kontenerem

      mkdir /run/sshd
      /usr/sbin/sshd -D &

img13

Łączenie z kontenerem na domyślnym porcie (22):

	ssh root@172.17.0.4
      
lub

      ssh root@172.17.0.4 -p 22

img14


#### Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
  * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone

img15

  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa

Dockerfile skopiowany z dokumentacji:

img16

Budowanie obrazu:


    docker build -t myjenkins-blueocean:2.319.2-1 .

img17


  * Uruchom blueocean

img18

  * Zaloguj się i skonfiguruj Jenkins
  
  -Uruchamiamy Jenkinsa na porcie 8080
  
  img19
  
  -Hasła szukam w logach kontenera
  
  	docker logs <id_kontenera>
	

img20

img21

img22

#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname

img23

img24

Uruchomione przez Blue Ocean

img25

img26

* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 

Przypisuję do zmiennej godzinę, obliczam modulo, przyrównuję do 0 i wyświetlam error gdy jest nieparzysta.

img27

img28

* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium

Podajemy linka do repo

img29

  * przechodzi na osobistą gałąź

img30

  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose
  
img31

img32

img33