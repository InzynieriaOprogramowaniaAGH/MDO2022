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

![image](https://user-images.githubusercontent.com/80592460/145644154-994e21de-1933-4925-9b88-b86834d81936.png)

  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa

Dockerfile skopiowany z dokumentacji:

![image](https://user-images.githubusercontent.com/80592460/145644493-16d56b35-ff0b-4db2-b9fd-6f1040186ee1.png)

Budowanie obrazu:

	docker build -t myjenkins-blueocean:1.1 .

![image](https://user-images.githubusercontent.com/80592460/145645907-2fa78b2e-3b80-4361-a6e8-e42ae1266d02.png)

![image](https://user-images.githubusercontent.com/80592460/145645981-49fe5d5d-af1b-4a9f-a3d8-0fa4141f29db.png)


  * Uruchom blueocean

![image](https://user-images.githubusercontent.com/80592460/145646234-e20fbcee-253b-4895-a7a5-60b2b3bc348c.png)

  * Zaloguj się i skonfiguruj Jenkins
  
  -Uruchamiamy Jenkinsa na porcie 8080
  
  ![image](https://user-images.githubusercontent.com/80592460/145647194-f7d2756e-dce3-4978-b724-c1abf6c64425.png)
  
  -Szukamy hasła w logach kontenera
  
  	docker logs 78e6f1810214d255e74ff9e01bd50d99a9d24490d6d763f7cac4cda6132ad167
	
Gotowe

![image](https://user-images.githubusercontent.com/80592460/145647329-dc339a47-f21b-44d8-8d13-da5ec5ebfc7f.png)

![image](https://user-images.githubusercontent.com/80592460/145647374-ba53a3b1-d3e5-44b8-9dc3-fd51c2e0f5f3.png)

![image](https://user-images.githubusercontent.com/80592460/145647501-3403a707-96cd-48a0-928d-a7e894e32091.png)

#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname

![image](https://user-images.githubusercontent.com/80592460/145648434-eea4ddcd-3385-4f1b-b320-efb7b56b2374.png)

![image](https://user-images.githubusercontent.com/80592460/145648548-19d11c27-0951-4fe0-9ef8-26e22f04d2fb.png)

![image](https://user-images.githubusercontent.com/80592460/145649148-3714fbd3-a95b-4714-ac5a-8b881a01c0d9.png)

![image](https://user-images.githubusercontent.com/80592460/145649245-90cb44ed-9f62-4598-862a-bb16057c0257.png)

* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 

Zakładam, że nieparzysta ma być godzina, a nie minuty. Przypisuję więc do zmiennej godzinę, obliczam modulo, przyrównuję do 0 i wyświetlam error gdy jest nieparzysta.

![image](https://user-images.githubusercontent.com/80592460/145652583-27161fc6-8691-4e1c-a52b-0c2c7e72da36.png)

![image](https://user-images.githubusercontent.com/80592460/145652634-c69834b4-d29b-47ab-a096-abcce92d9dfa.png)

* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium

Podajemy linka do repo

![image](https://user-images.githubusercontent.com/80592460/145652757-f01c296c-a278-4c78-aba0-7095b04a5d31.png)

  * przechodzi na osobistą gałąź

![image](https://user-images.githubusercontent.com/80592460/145652792-5cf173c6-23d8-44bb-991b-e51a4d8a7906.png)

  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose
  
![image](https://user-images.githubusercontent.com/80592460/145654694-03718a49-ed6f-4f4c-9c1f-2b772c5fe1f3.png)

![image](https://user-images.githubusercontent.com/80592460/145654710-dc3ce6c1-49e6-494d-8259-b8aaf9e84c38.png)

![image](https://user-images.githubusercontent.com/80592460/145654728-224b508e-7879-4cdf-9acf-02390ad8e8d6.png)

![image](https://user-images.githubusercontent.com/80592460/145654743-06704857-dad4-47aa-8049-9d8fd93ab4ed.png)


  
#### Sprawozdanie
* Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
  * Wymagania wstępne środowiska
  * Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
  * Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
