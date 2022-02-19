# Projekt zaliczeniowy Metodyki DevOps
# Uruchomienie kontenera z ubuntu
### 1. Należy pobrać ubuntu za pomocą komendy `docker pull ubuntu`

![pull ubuntu](https://user-images.githubusercontent.com/48067540/154765887-a59324d9-45a1-47c0-953b-01aec161aa34.png)

### 2. Aby kontener po uruchomieniu nie został natychmiast zamknięty uruchamiamy go w trybie detached korzystająć z flagi -dt 

  `docker run -dt ubuntu`
  
  oraz sprawdzamy status za pomocą `docker ps -a`
  
  ![run ubuntu detached](https://user-images.githubusercontent.com/48067540/154766028-0549a7b1-559e-433c-b3c8-b3cbffccaafa.png)

# Wybór programu

W projekcie wykorzystam następujące repozytorium umożliwiające budowę oraz uruchumoenie testów jednostkowych.

`https://github.com/caolan/nodeunit`

# Budowanie programu w kontenerze
### 1. Otwieram konsolę bash uruchumionego w kontenerze ubuntu za pomocą 

`docker exec -it <id> bash`

![open container bash](https://user-images.githubusercontent.com/48067540/154766705-a06d86f8-7344-40c1-aa2d-4e85b0a73b92.png)

### 2. Aktualizuję pakiety `apt-get update && apt-get upgrade`

![packages update](https://user-images.githubusercontent.com/48067540/154766858-13657639-f842-428d-9eff-c886e7d1e17c.png)

### 3. Instaluję Node.js `apt-get install nodejs`

![intall node](https://user-images.githubusercontent.com/48067540/154767020-a8fdf7d4-c864-48ed-9f11-5cd9191c4280.png)

![node version](https://user-images.githubusercontent.com/48067540/154767432-d137737f-7650-4a33-b484-8798eed94964.png)


### 4. Instaluję npm `apt-get install npm`

![install npm](https://user-images.githubusercontent.com/48067540/154767213-320bd914-8054-4595-9dc0-8410c3dc7bdc.png)

![npm version](https://user-images.githubusercontent.com/48067540/154767435-986fb9f3-5805-4896-99b3-ab40db45fc87.png)

### 5. Instaluję git `apt-get install git`

![install git](https://user-images.githubusercontent.com/48067540/154767594-18cc5e56-a4fc-4478-8279-499ce1e22220.png)

### 6. Klonuję repozytorium `git clone <repo_url>`

![clone repo](https://user-images.githubusercontent.com/48067540/154767822-5729a691-4d82-4a63-aa5e-dff9937b244b.png)

### 7. Instaluję w katalogu projektu wszystkie potrzebne biblioteki `npm install`

![install node packages](https://user-images.githubusercontent.com/48067540/154768040-261492dc-0ad5-4a69-b3f8-eaad265ce6b0.png)

### 8. Budowa aplikacji i uruchomienie testów 
```
npm build
npm test
```

![build and run test](https://user-images.githubusercontent.com/48067540/154768851-d93bfe9d-62c7-4be4-b7e7-dc8ab3d6670e.png)

![test results](https://user-images.githubusercontent.com/48067540/154768857-431b532e-b0a4-42fa-b2c4-4b84f460c9e5.png)

# Dockerfile oraz budowanie obrazu
### 1. Tworzę plik Dockerfile
Tworzę plik Dockerfile za pomocą `touch Dockerfile` oraz edytuję go używając `nano Dockerfile`

![create and edit dockerfile](https://user-images.githubusercontent.com/48067540/154769865-5de9aae1-8ad9-4226-aa12-8d01e3f887cb.png)

![Dockerfile build](https://user-images.githubusercontent.com/48067540/154770209-1cc315e9-5977-4d66-8631-139ef1569f92.png)

Używam `ARG DEBIAN_FRONTEND=noninteractive` aby podczas wykonywania `apt-get ...` nie pokazywało się okno dialogowe. 

### 2. Buduję obraz używająć `docker build -t=ubuntu-nodeunit -f Dockerfile .`

![Build dockerfile](https://user-images.githubusercontent.com/48067540/154770456-0ee5555f-18ef-4ea4-bc07-f551e223adcb.png)

Sprawdzam czy obraz został zbudowany

![nodeunit build image](https://user-images.githubusercontent.com/48067540/154770495-88939f60-a80b-442d-a110-75464d3f92d3.png)

### 3. Tworzę dockerfile uruchamiający testy analogicznie wykorzystując komendy `touch Dockerfile-test` i do edycji `nano Dockerfile-test`

![create and edit test dockerfile](https://user-images.githubusercontent.com/48067540/154770882-36a0e011-4ff1-470e-967c-776f45372e05.png)

![Dockerfile test](https://user-images.githubusercontent.com/48067540/154770890-fdedccf2-56f5-4223-b139-4fcba2109283.png)

### 4. Buduję obraz przy użyciu Dockerfile-test

`docker build -t=ubuntu-nodeunit-test -f Dockerfile-test .`

![buikd dockerfile test](https://user-images.githubusercontent.com/48067540/154771152-60edc8e5-e548-4e1a-b4da-a07cd20e0809.png)

![nodeunit test image](https://user-images.githubusercontent.com/48067540/154771175-4f6df206-6210-4a5f-a24e-897a88be758e.png)

# Uruchamiam obrazy
### 1. Uruchamiam obraz nodeunit za pomocą `docker run ubuntu-nodeunit`

![run ubuntu nodeunit](https://user-images.githubusercontent.com/48067540/154771480-04256e44-42f4-47c1-a584-4c43a85f8666.png)

### 2. Uruchamiam obraz z testami za pomocą `docker run ubuntu-nodeunit-test`

![run ubuntu nodeunit test](https://user-images.githubusercontent.com/48067540/154771517-6c696c16-cc1f-4202-955e-f46646fd6b79.png)

## Jako że poprzednio wybrany projekt okazał się w tym momencie być niewystarczjący (niemożliwy do odpalenia ponieważ jedyne co wykonywał to testy), zmieniłem projekt na wykonaną przeze mnie kiedyś aplikację i wykonałem dla niego wszystkie kroki od początku wykorzystując poprzedni kontener z zainstalowanymi już node, npm, git

`git clone https://github.com/NorStas/zagadnienie_posrednika_frontend.git`

![git clone](https://user-images.githubusercontent.com/48067540/154775437-6f1ab2fb-0f8a-4fcf-917a-aa01bc90fb90.png)

`npm install`

![npm install](https://user-images.githubusercontent.com/48067540/154775446-18dc1a82-6de0-429c-8498-8015b1b2f505.png)

`npm test`

![npm test](https://user-images.githubusercontent.com/48067540/154775462-1527baad-de95-4651-b24c-7bee21783886.png)

`npm run-script build`

![npm build](https://user-images.githubusercontent.com/48067540/154775472-5cb5b3b3-8bfe-4285-835f-59654c1d9816.png)

## Stworzyłem dla niego nowe dockerfile'y wykonujące build i testy

### Dockerfile

<img width="538" alt="Dockerfile-build" src="https://user-images.githubusercontent.com/48067540/154776063-cc17954d-4d47-4b97-920a-7330c043f93a.png">

![Dockerfile webapp build](https://user-images.githubusercontent.com/48067540/154775887-5f3ae681-1085-4e19-872b-cff3df217919.png)

### Dockerfile-test

![dockerfile-test](https://user-images.githubusercontent.com/48067540/154776679-433c50c4-a549-44e5-8e0a-faf4375520b7.png)

![dockerfile-test-build](https://user-images.githubusercontent.com/48067540/154775901-2bde5abf-8c1e-47f7-a331-f9d1ddf3f33a.png)

## Uruchomiłem obrazy dla nowego projektu

![docker images](https://user-images.githubusercontent.com/48067540/154775978-edfb1dfa-ea6c-478a-9d4d-0a71562fe139.png)

![run build image](https://user-images.githubusercontent.com/48067540/154777052-643f75b3-f026-4e8f-8549-7ab112a4754c.png)

![run test image](https://user-images.githubusercontent.com/48067540/154777055-f58d6906-a17a-4b64-a407-d43527a93ed0.png)

# Uruchomienie aplikacji i wykorzystanie jej poza kontenerem

### 1. Na podstawie poprzedniego Dockerfile służącego do budowania aplikacji stworzyłem nowy umożliwiający jej uruchomienie

<img width="403" alt="Dockerfile-run" src="https://user-images.githubusercontent.com/48067540/154778553-c467fbe1-6a07-4d78-9d5c-e0b713d10c83.png">

Ponieważ kod używał docelowo adresu 127.0.0.1:8080 użyłem argumentu `--host:0.0.0.0` aby aplikacja była dostępna spoza kontenera pod `localhost:port`

Zbudowałem obraz za pomocą `docker build -t ubuntu-webapp-run -f Dockerfile-run .`

![build Dockerfile-run](https://user-images.githubusercontent.com/48067540/154777327-e0006db8-7943-4742-9ba9-6f110192df0c.png)

### 2. Uruchamiam projekt wykorzystując zbudowany obraz, projekt otwiera się na porcie 8080, należy więc przekierować go "na zewnątrz"  na port 8000

`docker run -it -p 3030:8080 ubuntu-webapp-run`

![run webapp](https://user-images.githubusercontent.com/48067540/154778594-90730b20-1ae4-40c1-b5d8-2b7c96cb72ec.png)

Aplikacja jest pod "zbindowanym" portem 3030

![webapp](https://user-images.githubusercontent.com/48067540/154778602-143c8b1d-511e-4983-a03f-9e97e25e1d4b.png)

# Kompozycja
### 1. Tworzę plik docker-compose.yml

![docker-compose](https://user-images.githubusercontent.com/48067540/154779582-56bafb41-a147-495b-a5a6-4d464c2361b1.png)

Uruchamiam za pomocą `dokcer-compose up`

![docker-compose up](https://user-images.githubusercontent.com/48067540/154779578-4986e528-dca8-4423-bedf-3ba5578b9a91.png)

Aplikacja uruchamia się a testy przechodzą. 

# Czy dystrybuowanie oprogramowania w postaci kontenera ma sens, umotywować wybór?
Konteneryzacja tego oprogramowania pozwala zbudować je w szybki sposób w przypadku deployment'u. Będzie również przydatna w procesie development'u dla programistów backend, dzięki ułatwieniu uruchamiania aplikacji będą oni mogli w łatwy sposób przetestować lokalnie aplikację bez konieczności znajomości technologi frontendowych., zaoszczędzi to również czas potrzebny na klonowanie repozytorium. W całym procesie tworzenia oprogramowania webowego pozwoli to na zaoszczędzenie czasu oraz wyelminowanie błędów ludzkich przy wielokrotnych uruchomieniach. Dzięki uruchomieniu aplikacji w kontenerze w połączeniu z kubernetesem będziemy mogli wykorzystać je w ten sposób, aby przy procesie wdrożenia uniknąć downtime'u środowiska produkcyjnego. 

# Instalacja jenkinsa z wykorzystaniem Docker'a
### 1. Tworzę bridge network `docker network create jenkins`

![jenkins create bridge](https://user-images.githubusercontent.com/48067540/154779905-c9f9241e-8e96-4b4f-9a3f-c0ef1c3a056d.png)


### 2. Wykorzystuję komendę z instrukcji
```
docker run \
  --name jenkins-docker \
  --rm \
  --detach \
  --privileged \
  --network jenkins \
  --network-alias docker \
  --env DOCKER_TLS_CERTDIR=/certs \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-data:/var/jenkins_home \
  --publish 2376:2376 \
  docker:dind \
  --storage-driver overlay2
  ```
  
  ![jenkins run](https://user-images.githubusercontent.com/48067540/154779912-15e64cda-5f21-4587-98e2-519a200d8436.png)

![jenkins up](https://user-images.githubusercontent.com/48067540/154779980-06905ca6-14b8-471c-a461-781316dd9f42.png)

### 3. Tworzę Dockerfile

<img width="524" alt="Dockerfile-jenkins" src="https://user-images.githubusercontent.com/48067540/154780056-7c3a0605-cbb3-4e16-b6d8-f7379f49a5d0.png">

### 4. Wykorzystując Dockerfile buduję obraz Blueocean
`docker build -t myjenkins-blueocean:2.319.3-1 -f Dockerfile-jenkins .`

![Build blueocean image](https://user-images.githubusercontent.com/48067540/154780129-e341cf06-6753-4b26-a0d9-3dd1a31fda60.png)

### 5. Uruchamiam obraz
```
docker run \
  --name jenkins-blueocean \
  --rm \
  --detach \
  --network jenkins \
  --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client \
  --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 \
  --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  myjenkins-blueocean:2.319.3-1 
  ```
  
  ![run jenkins](https://user-images.githubusercontent.com/48067540/154780180-199927bc-c884-435b-8e94-06691262c695.png)

  ### 6. Wchodzę do kontenera jenkinsa `docker exec -it jenkins-blueocean bash`
  
  ![jenkins bash](https://user-images.githubusercontent.com/48067540/154780255-fc356a8b-7631-446d-81f4-0781c793945e.png)
  
  "Wyciągam" hasło admina
  
  `cat /var/jenkins_home/secrets/initialAdminPassword`

### 7. Loguję się do jenkinsa

  ![jenkins web](https://user-images.githubusercontent.com/48067540/154780286-3902a3ce-0022-4c8c-85a1-3ba06a950bc2.png)

![jenkins logged in](https://user-images.githubusercontent.com/48067540/154780396-a9b3e79b-da76-46d7-82ea-df9bf360afce.png)

### 8. Tworzę nowy projekt 'multi-configurational-project'

![new project](https://user-images.githubusercontent.com/48067540/154780434-54f829ae-b382-432c-9e7f-6fbb2e81c0a3.png)

### 9.Podaję URL do repozytorium projektowego

Oraz wybieram moją gałąź projektu

![GITHUB BRANCH](https://user-images.githubusercontent.com/48067540/154780547-8262c4d2-878f-4383-9953-f3e0627530cc.png)

### 10. Wybieram użycie docker-compose.yml jako build step

![build step](https://user-images.githubusercontent.com/48067540/154781014-2f2a6b07-862b-41f5-92ad-f713b00b7b8b.png)

### 11. Instalujemy docker-compose na kontenerze jenkinsa

![Jenkins docker compose install](https://user-images.githubusercontent.com/48067540/154781884-c53f1293-b3d3-4419-97a4-c1ace1dc8400.png)

![jenkins docker compose chmod](https://user-images.githubusercontent.com/48067540/154781918-6d0d5b95-7613-40fc-967c-72f41d9258c1.png)








