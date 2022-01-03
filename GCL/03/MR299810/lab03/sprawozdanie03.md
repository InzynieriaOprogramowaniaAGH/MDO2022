

## Sprawozdanie Metodyki DevOps
Mateusz Rudziński
Laboratoria 3

 - Pobierz obraz ubuntu
 Na moim urządzeniu posiadałem już wcześniej ściągnięty obraz Ubuntu
 
![enter image description here](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/obraz_ubuntu.png)
	
 - Tworzenie woluminu oraz podłączanie do niego kontenera 
Wolumin tworzymy przy pomocy polecenia 
`docker volume create "nazwa woluminu"`

![enter image description here](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/docker_volume_ls.png)

- Podłączenie woluminu do kontenera wraz z tworzeniem nowego pliku wewnątrz woluminu z poziomu kontenera

Widoczność pliku na hoście

![screen file_in_volume_host_container](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/file_in_volume_host_container.png)

Tworzenie pliku z poziomu hosta i widoczność w kontenerze

![screen host_volume_file.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/host_volume_file.png)

- SSH
Uruchomienie i wyeksponowanie wybranego portu w kontenerze

![screen docker_publish_ssh_port](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/docker_publish_ssh_port.png)

![screen active_container_ssh](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/active_container_ssh.png)

- W kontenerze instalujemy SSH

![screen ssh_version](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/ssh_version.png)

![screen net_tools_version](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/net_tools_version.png)

- Wydruk z netstat po stronie kontenera 

![screen container_sshd](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/container_sshd.png)

- Adres IP kontenera

![screen container_ip](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/container_ip.png)

- Kopiowanie klucza publicznego do wolumenu

![screen ssh_key](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/ssh_key.png)

![screen ssh_key_copy_to_volume](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/ssh_key_copy_to_volume.png)

![screen copy_sshkey_container](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/copy_sshkey_container.png)

- Łączenie się z kontenerem 

![screen connecting ssh](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/connecting_ssh.png)

- Skonteneryzowany Jenkins stosujący Dockera

Z powodu błędów zwracanych przez Jenkinsa pozwoliłem sobie na edycję Dockerfile'i.

screen dockerfile_jenkins
screen dockerfile_jenkins2

- Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone

Uruchamianie obrazu Jenkinsa:
````
docker run --name jenkins-docker --rm --detach \
  --privileged --network jenkins --network-alias docker \
  --env DOCKER_TLS_CERTDIR=/certs \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-data:/var/jenkins_home \
  --publish 2376:2376 docker:dind --storage-driver overlay2
````

- Przygotuj obraz blueocean na podstawie obrazu jenkinsa

Dockerfile dla tworzenia obrazu blueocean

````
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
````
- Uruchom blueocean
- 
![screen blueocean_running.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/blueocean_running.png)

- Zaloguj się i skonfiguruj Jenkins
- 
![screen Jenkins4.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/Jenkins4.png)
#### Mikro-projekt Jenkins
W celu wywołania komendy uname tworzymy w Jenkins projekt typu ogólnego. 

![screen jenkins_ogolny.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/jenkins_ogolny.png)

W zakładce budowanie wybieramy z dropboxa opcje "Uruchom Powłokę"  wpisując tam komendę którą chcemy wywołać.

![enter image description here](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/uname.png)

Wynik uruchomienia komendy

![screen successjenkins.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/successjenkins.png)

W celu utworzenia "prawdziwego" projektu 

Tworzymy nowy projekt wybierając opcje Multi-configuration project

#### W WYNIKACH NAZWA PROJEKTU JEST INNA ZE WZGLĘDU NA PODEJMOWANIE RÓŻNYCH PRÓB NAPRAWIENIA NAPOTKANYCH BŁĘDÓW 

![screen realJenkins1.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/realJenkins1.png)

Następnie wewnątrz konfiguracji projektu ustawiamy GitHub project wewnątrz wstawiając link naszego git

![screen realJenkins2.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/realJenkins2.png)

Następnie niżej w zakladce "Repozytorium kodu" wybieramy Git i wklejamy link do repo. 
W oknie branches to build wpisujemy wybraną przez nas gałąź.

![screen realJenkins3.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/realJenkins3.png)

W zakładce Budowanie przechodzimy do folderu w którym znajdują się Dockerfile które będziemy chcieli zbudować następnie z tych Dockerfile'i budujemy obrazy.

![screen realJenkins4.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/realJenkins4.png)

Uruchamiamy projekt i otrzymujemy wynik

![screen realJenkins5.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab03/realJenkins5.png)







