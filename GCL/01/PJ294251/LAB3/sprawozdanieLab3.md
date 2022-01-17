

# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk

* Pobierz obraz Ubuntu

      sudo docker pull ubuntu
      
![1](imgs/1.png)
      
* Podłącz wolumin do kontenera

Tworzenie woluminu:

      sudo docker volume create moj_volumin
      
Podłączanie do kontenera:
      
      sudo docker run --interactive --tty --mount source=volumin,destination=/store ubuntu

![2](imgs/2.png)

* Skopiuj plik do katalogu woluminu, pokaż w kontenerze

      touch plik_aaa
      cp plik_aaa /var/lib/docker/volumes/volumin/_data

![3](imgs/3.png)

![4](imgs/4.png)

* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście

      touch /store/plik2

![5](imgs/5.png)


#### "Kiepski pomysł": SSH
* Uruchom i wyeksponuj wybrany port w kontenerze

      docker run -it --mount source=volumin,destination=/store --publish 2222:22 ubuntu

![6](imgs/6.png)

* Zainstaluj w kontenerze serwer ssh

      apt-get update
      apt-get install openssh-server
      
![7](imgs/7.png)

* zezwól na logowanie root

Odkomentowanie portu i logowania roota w pliku sshd_config i zmiana PermitRootLogin na yes 

      nano /etc/ssh/sshd_config
      

![8](imgs/8.png)

* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze

Umieszczenie klucza publicznego w woluminie:

![9](imgs/9.png)

Tworzymy katalog dla klucza

	mkdir /root/.ssh
      
![9](imgs/9.png)

Kopiowanie klucza

	cp /store/id_ed25519.pub /root/.ssh/authorized_keys
      
![11](imgs/11.png)
![10](imgs/10.png)

* odnajdź adres IP kontenera w wewnętrznej sieci

      docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' nazwa_kontenera

![12](imgs/12.png)
* uruchom usługę, połącz się z kontenerem

      mkdir /run/sshd
      /usr/sbin/sshd -D &

![13](imgs/13.png)

Łączenie z kontenerem na domyślnym porcie (22):

	ssh root@172.17.0.4
      
lub

      ssh root@172.17.0.4 -p 22

![14](imgs/14.png)


#### Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
  * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone

![15](imgs/15.png)

  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa

Dockerfile skopiowany z dokumentacji:

![16](imgs/16.png)

Budowanie obrazu:


    docker build -t myjenkins-blueocean:2.319.2-1 .

![17](imgs/17.png)


  * Uruchom blueocean

![18](imgs/18.png)

  * Zaloguj się i skonfiguruj Jenkins
  
  -Uruchamiamy Jenkinsa na porcie 8080
  
  ![19](imgs/19.png)
  
  -Hasła szukam w logach kontenera
  
  	docker logs <id_kontenera>
	

![20](imgs/20.png)

![21](imgs/21.png)

![22](imgs/22.png)


#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname

![23](imgs/23.png)

![24](imgs/24.png)

Uruchomione przez Blue Ocean

![25](imgs/25.png)

![26](imgs/26.png)

* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 

Przypisuję do zmiennej godzinę, obliczam modulo, przyrównuję do 0 i wyświetlam error gdy jest nieparzysta.

![27](imgs/27.png)

![28](imgs/28.png)

* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium

Podajemy linka do repo

![29](imgs/29.png)

  * przechodzi na osobistą gałąź

![30](imgs/30.png)

  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose
  
![31](imgs/31.png)

![32](imgs/32.png)

![33](imgs/33.png)
