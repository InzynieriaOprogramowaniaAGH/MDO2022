## Projekt Zaliczeniowy Wojciech Pajor

1. Instalujemy dokera a na nim obraz ubuntu:
![1.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/1.png)
2. Następnie instalujemy busybox:
![2.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/2.png)
3. Uruchamiamy kontener za pomocą: ``` sudo docker run busybox ```
4. sprawdzamy co jest w srodku: ```sudo docker ps -a ``` :
![3.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/3.png)
5. Wywołujemy numer wersji: ``` sudo docker run -it busybox ```:
![4.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/4.png)
6. Czyścimy działające dockery przy użyciu ``` sudo docker rm $(sudo docker ps --filter status=exited -q) ```

##### Część 1: Program w dokerze z testem i dockerfile + docker compose
1. Naszym programem będzie zwykły angular z testem dostępny pod linkiem: ``` https://github.com/fyodorio/angular-unit-testing-examples ```
2. Instalujemy niezbędne oprogramowanie wstępne: ```sudo apt install nodejs``` ```sudo apt install npm``` ```sudo npm i -g @angular/cli@11.1.1```
3. Odpalamy kontener : ```sudo docker run -it ubuntu bash``` oraz instalujemy niezbędne oprogramowanie:
4. ```apt-get install -yq curl git nano``` ```apt-getnode install npm``` ```apt install nodejs```
5. klonujemy repo z naszym programem z testami ```git clone https://github.com/fyodorio/angular-unit-testing-examples```
6. wchodzimy do sklonowanego folderu i instalujemy moduły: `npm i```
7. uruchamiamy nasz build: ```npm run build```
8. Wynik testu:
9. ![5.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/5.png)
10. Odpalamy testy: ```npm run e2e```:
11. ![6.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/6.png)
12. Tworzymy dockerfile ```nano dockerfile``` :
13. ![7.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/7.png)
14. odpalamy build dockera używając pliku dockerfile: ```sudo docker build -t angular:latest . -f dockerfile```
15. ![8.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/8.png)
16. Tworzymy dockerfile-with-test które będzie oparte o poprzedni dockerfile i będzie dodawać odpalanie testów:
17. ![9.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/9.png)
18. Utworzenie docker-compose.yml:
19. ![10.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/10.png)


##### Część 2: Automatyzator Jenkins
1. Tworzymy "bridge-network" ```docker network create jenkins```
2. ![11.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/11.png)
3. Postępujemy zgodnie z instrukcją:
4. ![12.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/12.png)
5. ![13.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/WP286214/ProjektZaliczeniowy/13.png)
6. Wstawiamy wygenerowane hasło i tworzymy konto jenkins (localhost:8080) 
7. Tworzymy swój build



   
