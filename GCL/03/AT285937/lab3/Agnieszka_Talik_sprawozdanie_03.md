# Zajęcia 03

#### Łączność i woluminy na podstawie "złych" praktyk

* Pobierz obraz Ubuntu

      sudo docker pull ubuntu
      
![image](https://user-images.githubusercontent.com/80592460/144741165-6811ff90-9738-4929-bb7d-10f3db964bae.png)
      
* Podłącz wolumin do kontenera

Tworzenie woluminu:

      sudo docker volume create moj_volumin
      
Podłączanie do kontenera:
      
      sudo docker run --interactive --tty --mount source=moj_wolumin,destination=/store ubuntu

![image](https://user-images.githubusercontent.com/80592460/144719773-7cf12192-9ffa-405a-9575-1885f1da131e.png)

* Skopiuj plik do katalogu woluminu, pokaż w kontenerze

      touch plik_aaa
      cp plik_aaa /var/lib/docker/volumes/nazwa_twojego_voluminu/_data

![image](https://user-images.githubusercontent.com/80592460/144721276-9ee2904e-6bd2-4b74-b9e7-21ffd9129b6e.png)

![image](https://user-images.githubusercontent.com/80592460/144721094-5ea07af2-073b-4ad6-8613-d140abb4d05d.png)

* Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście

      touch /stroe/plik_bbb

![image](https://user-images.githubusercontent.com/80592460/144721003-6c9cfc41-da68-4eb1-bb1d-06f4b278bd9b.png)


#### "Kiepski pomysł": SSH
* Uruchom i wyeksponuj wybrany port w kontenerze

      docker run -it --mount source=moj_wolumin,destination=/store --publish 2222:22 ubuntu

![image](https://user-images.githubusercontent.com/80592460/144742850-7a87b887-2cfa-4158-ab44-b119d5491d38.png)

* Zainstaluj w kontenerze serwer ssh

      apt-get update
      apt-get install openssh-server
      
![image](https://user-images.githubusercontent.com/80592460/144742341-91c7c5dd-fde5-4c5b-968d-adcad5ccb23e.png)

* zezwól na logowanie root

Odkomentowanie portu i logowania roota w pliku sshd_config i zmiana PermitRootLogin na yes 

      nano /etc/ssh/sshd_config
      
![image](https://user-images.githubusercontent.com/80592460/144744454-4bed90f6-7407-44a8-8718-7b4194a9995e.png)

![image](https://user-images.githubusercontent.com/80592460/144747403-6cfda672-9644-4fd0-b63c-0e2966fb4cb3.png)

* umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze

Umieszczenie klucza publicznego w woluminie:

![image](https://user-images.githubusercontent.com/80592460/144746657-1d5cda22-b59a-4029-98eb-430b0ea6d7dd.png)

Tworzymy katalog dla klucza

	mkdir /root/.ssh
      
![image](https://user-images.githubusercontent.com/80592460/144746694-058ded2a-23f2-45fd-a488-2cfa8d7e7101.png)

Kopiowanie klucza

	cp /store/id_rsa.pub /root/.ssh/authorized_keys
      
![image](https://user-images.githubusercontent.com/80592460/144747223-106422e5-0ebc-4092-95dc-4bd093a7cad2.png)

![image](https://user-images.githubusercontent.com/80592460/144747233-53290e2c-2243-4a01-98f1-054bc9407b3d.png)


* odnajdź adres IP kontenera w wewnętrznej sieci

      docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' nazwa_kontenera

![image](https://user-images.githubusercontent.com/80592460/144743895-08eefb7e-d689-47e4-aa9c-03e52ddae96f.png)


* uruchom usługę, połącz się z kontenerem

      mkdir /run/sshd
      /usr/sbin/sshd -D &

![image](https://user-images.githubusercontent.com/80592460/144745803-91ba8479-83d1-45b0-be64-84b1e6489443.png)

Łączenie z kontenerem na domyślnym porcie (22):

	ssh root@172.17.0.4
      
lub

      ssh root@172.17.0.4 -p 22
      
![image](https://user-images.githubusercontent.com/80592460/144747279-f53ee313-633b-4f85-98dd-c0cf824151a8.png)









#### Skonteneryzowany Jenkins stosujący Dockera

#### Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
  * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa
  * Uruchom blueocean
  * Zaloguj się i skonfiguruj Jenkins
  
#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname
* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 
* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium
  * przechodzi na osobistą gałąź
  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose
  
#### Sprawozdanie
* Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
  * Wymagania wstępne środowiska
  * Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
  * Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
