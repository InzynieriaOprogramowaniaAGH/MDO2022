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
Naszym programem będzie zwykły angular z testem dostępny pod linkiem: ``` https://github.com/fyodorio/angular-unit-testing-examples ```
1.Instalujemy niezbędne oprogramowanie wstępne:
```sudo apt install nodejs```
```sudo apt install npm```
```sudo npm i -g @angular/cli@11.1.1```
2.Odpalamy kontener : ```sudo docker run -it ubuntu bash``` oraz instalujemy niezbędne oprogramowanie:
```apt-get install -yq curl git nano```
```apt-getnode install npm```
```apt install nodejs```
-klonujemy repo z naszym programem z testami ```git clone https://github.com/fyodorio/angular-unit-testing-examples```
   
