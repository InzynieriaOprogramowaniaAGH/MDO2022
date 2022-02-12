## Projekt Zaliczeniowy Wojciech Pajor

1. Instalujemy dokera a na nim obraz ubuntu: \
![1.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/1.png)
2. Następnie instalujemy busybox:\
![2.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/2.png)
3. Uruchamiamy kontener za pomocą: ``` sudo docker run busybox ```
4. Sprawdzamy co jest w srodku: ```sudo docker ps -a ``` :\
![3.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/3.png)
5. Wywołujemy numer wersji: ``` sudo docker run -it busybox ```:\
![4.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/4.png)
6. Czyścimy działające dockery przy użyciu ``` sudo docker rm $(sudo docker ps --filter status=exited -q) ```

### Część 1: Program w dokerze z testem i dockerfile + docker compose
1. Naszym programem będzie zwykły Angular z testem dostępny pod linkiem: ``` https://github.com/fyodorio/angular-unit-testing-examples ```
2. Instalujemy niezbędne oprogramowanie wstępne: ```sudo apt install nodejs``` ```sudo apt install npm``` ```sudo npm i -g @angular/cli@11.1.1```
3. Odpalamy kontener: ```sudo docker run -it ubuntu bash```
4. Oraz instalujemy niezbędne oprogramowanie:```apt-get install -yq curl git nano``` ```apt-getnode install npm``` ```apt install nodejs```
5. Klonujemy repo z naszym programem z testami ```git clone https://github.com/fyodorio/angular-unit-testing-examples```
6. Wchodzimy do sklonowanego folderu i instalujemy moduły: ```npm i```
7. Uruchamiamy nasz build: ```npm run build```
8. Wynik testu: \
![5.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/5.png)
9. Odpalamy testy: ```npm run e2e```:\
![6.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/6.png)
10. Tworzymy dockerfile ```nano dockerfile```: \
![7.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/7.png)
11. Odpalamy build dockera używając pliku dockerfile: ```sudo docker build -t angular:latest . -f dockerfile``` \
![8.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/8.png)
12. Tworzymy dockerfile-with-test które będzie oparte o poprzedni dockerfile i będzie dodawać odpalanie testów: \
![9.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/9.png)
13. Utworzenie docker-compose.yml: \
![10.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/10.png)


### Część 2: Automatyzator Jenkins
1. Tworzymy "bridge-network" ```docker network create jenkins```\
![11.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/11.png)
2. Postępujemy zgodnie z instrukcją:\
![12.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/12.png)\
![13.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/13.png)
3. Wstawiamy wygenerowane hasło i tworzymy konto jenkins localhost:8080
4. Budujemy używając jenkinsfile
5. Konwertujemy na pipeline:\
![15.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/15.png)
6. -----Quickfix napisałem dockerfile-with-tests a plik nazywa sie docker-with-tests i godzina kombinowania czemu test nie działa....
7. Aktualny pipeline:\
![16.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/16.png)
8. Dodajemy stage "Deploy":\
![17.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/17.png)
9. Wygenerowane artefakty można pobrać pod zakładką "Artifacts":\
![18.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/18.png)

### Część 3: Kubernetes
1. Upewniamy się czy docker jest dostępny i tworzymy w razie potrzezby: ```sudo docker build -t bldr:latest . -f ./dockerfile```
2. Instalujemy wymagania do Minicube oraz kubectl
3. ``` sudo usermod -aG docker $USER && newgrp docker```
4. ```sudo chmod 666 /var/run/docker.sock```
![19.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/19.png)
![20.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/20.png)
5. Uruchamiamy: ```minicube start```
6. Z nieznanych powodów wywaliła mi się wirtualna maszyna przy odpalaniu minikube
![21.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/21.png)
7. Najprawdopodobniej za mało miejsca na dysku
8. Rezulatat odpalenia minikube:\
![22.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/22.png)
9. Wdrażanie przykładowego kontenera przez k8s:\
![23.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/23.png)
10. Wdrażamy własny kontener:\
![24.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/24.png)
11. Deployment:
12. Tworzymy plik deployment.yaml:\
![25.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/25.png)
13. Aplikujemy wdrożenie:\
![26.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/26.png)





   
