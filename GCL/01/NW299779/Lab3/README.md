# Sprawozdanie LAB 3
## Pobranie obrazu Ubuntu

Pobieramy obraz ubuntu za pomocą komendy 
```
docker pull ubuntu
```
![Docker_pull](https://user-images.githubusercontent.com/48067540/146619809-836d2fc9-54cc-43dd-8345-ab4be72d42f3.png)

## Podłączamy wolumen do kontenera

### Najpierw należy stworzyć wolumen

![Zrzut ekranu 2021-12-18 o 00 51 17](https://user-images.githubusercontent.com/48067540/146620382-35f85800-e64c-42cf-85fb-28508eac5482.png)

```
docker volume create ubuntu-volume
```

### Następnie odpalamy obraz ubuntu z podłączonym wolumenem

```
docker run -it --name=ubuntuvol --mount source=ubuntu-volume,destination=/volumens ubuntu
```
### Możemy teraz sprawdzić że volumen został podłączony

<img width="958" alt="Zrzut ekranu 2021-12-18 o 00 55 36" src="https://user-images.githubusercontent.com/48067540/146620597-7b893520-bda9-4a82-8962-3085ddd30bf7.png">

### Przenosimy lokalnie utworzony plik do folderu wolumenu

```
docker run --rm -v $PWD:/source -v ubuntu-volume:/dest -w /source alpine cp test_file /dest
```

![Zrzut ekranu 2021-12-18 o 01 06 31](https://user-images.githubusercontent.com/48067540/146621209-b1744c00-42f0-4bbe-bc9a-ae8db2c3842d.png)

### W odpalonym kontenrze możemy sprawdzić że plik dodał się do folderu wolumenu

<img width="909" alt="Zrzut ekranu 2021-12-18 o 01 07 13" src="https://user-images.githubusercontent.com/48067540/146621239-9647c620-346d-43e7-afe1-2e18b42f4498.png">

Sytuacja wygląda podobnie w drugą stronę, gdy przeniesiemy plik na kontenerze będzie widoczny on w folderze wolumenu hosta.

## SSH

### Eksponowanie portów

Aby wyksponować port kontenera, musimy dodać flagę 
```
-p <port na hoście>:<port z dockera>
```

<img width="1034" alt="Zrzut ekranu 2021-12-18 o 01 11 34" src="https://user-images.githubusercontent.com/48067540/146621508-1e1aecc9-7219-4015-a894-22d5c8211068.png">

### Instalujemy serwer SSH

Musimy zaaktualizować pakiety oraz zainstalować openssh

```
apt update && apt install openssh-server -y
```

<img width="602" alt="Zrzut ekranu 2021-12-18 o 01 14 30" src="https://user-images.githubusercontent.com/48067540/146621637-2760c274-da6d-4f74-9d9e-a0b7ef1743a4.png">

### Zmiana portu serwera SSH

Domyślnie port SSH jest ustawiony na 22 więc musimy go zmienić na port wyeksponowany przy tworzeniu kontenera
Ścieżka pliku konfiguracyjnego OpenSSH to:
```
/etc/ssh/sshd_config
```
Odpalamy go za pomocą nano zmieniamy port i odkomentowywujemy linijkę.

<img width="539" alt="Zrzut ekranu 2021-12-18 o 01 17 48" src="https://user-images.githubusercontent.com/48067540/146621771-0dcfdf82-f0b9-4566-ae82-2b1facffa737.png">

### Zezwolenie na logowanie roota

Wartość 
```
PermitRootLogin
```
Zamieniamy na yes

<img width="193" alt="Zrzut ekranu 2021-12-18 o 01 19 24" src="https://user-images.githubusercontent.com/48067540/146621844-044076b4-7477-4203-9f23-96d918fcb0c8.png">

### Dodawanie kluczy publicznych do kontenera

Do naszego woluminu musimy skopiować klucz publiczny którym chcemy się logować

![Zrzut ekranu 2021-12-18 o 01 23 10](https://user-images.githubusercontent.com/48067540/146622025-49dcdece-7ba6-4a1e-8b6b-077aa9ad0116.png)

Następnie z poziomu kontenera dodajemy ten klucz do zaufanych kopiując go do stworzonego przez nas w kontenerze folderu
```
/root/.ssh
```
<img width="536" alt="Zrzut ekranu 2021-12-18 o 01 24 57" src="https://user-images.githubusercontent.com/48067540/146622535-41ff81f5-4a62-4a78-a0aa-57f38abfd287.png">

### Adres IP kontenera
Aby odnaleźć adres IP kontenera wywolujemy 
```
docker inspect 95bc3f33110c || grep IPAddress
```

![Zrzut ekranu 2021-12-18 o 01 36 37](https://user-images.githubusercontent.com/48067540/146622625-dfa99dcc-eda4-46f1-a0e1-d673414ce662.png)

![Zrzut ekranu 2021-12-18 o 01 37 46](https://user-images.githubusercontent.com/48067540/146622666-f859d800-14bf-4c9f-ad22-0de8f9213e1f.png)

### Uruchamianie usługi SSH na kontenerze 

Uruchamiamy daemona SSH na kontenerze
```
/usr/sbin/sshd -D &
```
<img width="510" alt="Zrzut ekranu 2021-12-18 o 01 38 54" src="https://user-images.githubusercontent.com/48067540/146622729-bc4d8ab1-c49c-41aa-8400-9de6bc324bca.png">

Otrzymamy błąd o brakującym folderze więc go tworzymy

<img width="295" alt="Zrzut ekranu 2021-12-18 o 01 40 21" src="https://user-images.githubusercontent.com/48067540/146622761-38cf1f16-97e4-4283-a0c9-15fe939fab5e.png">

### Połączenie z kontenerem przez SSH

Za pomocą komendy
```
ssh root@<IP kontenera> -p <port wystawiony>
```
łączymy się z kontenerem po SSH

## Skonteneryzowany Jenkins
### Uruchamianie obrazu Dockera który eksponuje środowisko zagnieżdżone

Tworzymy mostek sieciowy o nazwie Jenkins

<img width="525" alt="Zrzut ekranu 2021-12-18 o 01 46 08" src="https://user-images.githubusercontent.com/48067540/146623014-217db464-ae5f-4c08-ac45-5cdd09fe7d5c.png">

Uruchamiamy nowy kontener

```
docker run --name jenkins-docker --rm --detach \
--privileged --network jenkins --network-alias docker \
--env DOCKER_TLS_CERTDIR=/certs \
--volume jenkins-docker-certs:/certs/client \
--volume jenkins-data:/var/jenkins_home \
--publish 2376:2376 docker:dind --storage-driver overlay2
```

<img width="650" alt="Zrzut ekranu 2021-12-18 o 01 47 19" src="https://user-images.githubusercontent.com/48067540/146623060-e9eedb61-b7d9-42ad-9eff-1b243292e7c9.png">

### Tworzenie obrazu blueocean na podstawie obrazu Jenkins

Tworzymy nowy dockerfile dla blueocean
```
FROM jenkins/jenkins:2.319.1-jdk11
USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
https://download.docker.com/linux/debian \
$(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean:1.25.2 docker-workflow:1.26"
```

A następnie budujemy nowy obraz
```
docker build -t myjenkins-blueocean:1.1 .
```

<img width="641" alt="Zrzut ekranu 2021-12-18 o 02 09 37" src="https://user-images.githubusercontent.com/48067540/146623890-db76568f-8efe-4e12-b78e-f19c1ce14a1b.png">

### Uruchamianie blueocean
Gdy mamy utworzony Dockerfile wywołujemy komendę 

```
docker run --name jenkins-blueocean --rm --detach \
--network jenkins --env DOCKER_HOST=tcp://docker:2376 \
--env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 \
--publish 8080:8080 --publish 50000:50000 \
--volume jenkins-data:/var/jenkins_home \
--volume jenkins-docker-certs:/certs/client:ro \
myjenkins-blueocean:1.1
```
<img width="681" alt="Zrzut ekranu 2021-12-18 o 02 10 53" src="https://user-images.githubusercontent.com/48067540/146623930-a176df67-3779-4310-8552-dd65e8a001e4.png">

Jenkins jest dostępny pod adresem

```
localhost:8080
```
![Zrzut ekranu 2021-12-18 o 02 11 23](https://user-images.githubusercontent.com/48067540/146624008-64af12c7-417d-44cf-934d-459b9bc886d9.png)

### Logowanie i konfiguracja Jenkinsa
Hasło admina dostępne jest po wywołaniu komendy 

```
cat /var/jenkins_home/secrets/initialAdminPassword
```
<img width="434" alt="Zrzut ekranu 2021-12-18 o 02 12 56" src="https://user-images.githubusercontent.com/48067540/146624006-fdf1db33-f47a-472b-abdd-16bdce698383.png">

Po zalogowaniu możemy przejść do konfiguracji Jenkinsa

![Zrzut ekranu 2021-12-18 o 02 14 48](https://user-images.githubusercontent.com/48067540/146624038-02e31800-2420-4157-bb06-f9d5d481e814.png)

![Zrzut ekranu 2021-12-18 o 02 16 07](https://user-images.githubusercontent.com/48067540/146624078-994b38d6-1d27-455b-9d08-c7a93b74e446.png)

## Miniprojekt Jenkins

### Tworzymy nowy projekt

![Zrzut ekranu 2021-12-18 o 02 17 04](https://user-images.githubusercontent.com/48067540/146624103-34107f49-1730-4849-b6d1-4ff8220ddd72.png)

Następnie dodajemy krok budowania

![Zrzut ekranu 2021-12-18 o 02 18 25](https://user-images.githubusercontent.com/48067540/146624136-68771154-ce1c-4194-832d-cae38cb5c025.png)

Uruchamiamy projekt 

![Zrzut ekranu 2021-12-18 o 02 20 00](https://user-images.githubusercontent.com/48067540/146624194-8c358e28-f45f-4026-abf9-6d9d78300ba0.png)

![Zrzut ekranu 2021-12-18 o 02 20 39](https://user-images.githubusercontent.com/48067540/146624203-fe1c9536-3576-431d-8477-de617eb8dc5d.png)

## Projekt zwracający błąd gdy godzina jest nieparzysta

Tworzymy nowy projekt w ten sam sposób a jako krok budowania podajemy kod:
```
#!/bin/bash

hour=$(date +%H)
echo $hour

if [ $((hour%2)) -eq 0 ];
then

exit 0

else

exit 1

fi
```

Uruchamiamy projekt

![Zrzut ekranu 2021-12-18 o 02 23 31](https://user-images.githubusercontent.com/48067540/146624283-5ad1ce9b-ba67-43a2-8d4d-9288b94e1356.png)







