# Metodyki DevOps

# Wybranie odpowiedniego projektu

Do wykonania ćwiczenia wykorzystano następujące repozytorium zawierające mechanizm budowania oraz testy jednostkowe:

- https://github.com/jterkalski/react-app

# Uruchomienie kontenera

1. Pobieram obraz ubuntu za pomocą komendy: `docker pull ubuntu`<br/>
   ![alt text](./Screenshots/1.png)
2. Uruchamiam ubuntu używając: `docker run ubuntu` oraz sprawdzam status przy pomocy `docker ps -a`<br/>
   ![alt text](./Screenshots/2.png)
3. Ponieważ kontener posiada status "Exited", używam komendy `docker run -dt ubuntu` z flagą detached aby uruchomić kontenener ponownie, tym razem w tle.<br/>
   ![alt text](./Screenshots/4.png)
4. Następnie używam `docker container prune` aby wyczyścić wszystkie niedziałające w danym momencie kontenery (ze statusem "Exited").<br/>
   ![alt text](./Screenshots/3.png)
5. Sprawdzam status<br/>
   ![alt text](./Screenshots/5.png)

# Budowanie programu

1. Teraz, gdy kontener działa już poprawnie, wchodzę na niego używając komendy `docker exec -it <id-kontenera> bash`<br/>
   ![alt text](./Screenshots/6.png)

2. Aktualizuję paczki za pomocę komend update oraz upgrade: `apt-get update && apt-get upgrade`<br/>
   ![alt text](./Screenshots/7.png)

3. Następnie instaluję Node.js komendą `apt-get install nodejs`<br/>
   ![alt text](./Screenshots/8.png)<br/>
   ![alt text](./Screenshots/9.png)<br/>

4. Installuję npm: `apt-get install npm`<br/>
   ![alt text](./Screenshots/10.png)<br/>

5. Sprawdzam czy zostały poprawnie zainstalowane poprzez sprawdzenie wersji<br/>
   ![alt text](./Screenshots/11.png)<br/>

6. Installuję git'a: `apt-get install git`<br/>
   ![alt text](./Screenshots/12.png)<br/>

7. Klonuje repozytorium używając `git clone`<br/>
   ![alt text](./Screenshots/13.png)<br/>

8. Przechodzę do katalogu z projektem (`cd react-app`), a następnie za pomocą `npm install` instaluje niezbędne do działania programu moduły (node_modules)<br/>
   ![alt text](./Screenshots/14.png)<br/>

9. Buduję program za pomocą `npm run build`, lecz nie udaje się go odpalić<br/>
   ![alt text](./Screenshots/15.png)<br/>

10. Doinstalowuje curl: `sudo apt install curl`, a następnie aktualizuję node'a do wersji v14.x<br/>
    ![alt text](./Screenshots/16.png)<br/>

11. Przy pomocy curl aktualizuje Node do wersji v14.x<br/>

- `curl -sL https://deb.nodesource.com/setup_14.x | bash -`
- `apt install -y nodejs`<br/>
  ![alt text](./Screenshots/17.png)<br/>
  ![alt text](./Screenshots/18.png)<br/>

12. Po zaktualizowaniu Node'a udaje się zbudować program<br/>
    ![alt text](./Screenshots/19.png)<br/>

13. Uruchamiam testy za pomocą `npm run test`, podczas uruchamiania pojawia się okno dialogowę, w tym momencie należy kliknąć "a" aby uruchomić wszystkie możliwe testy.<br/>
    ![alt text](./Screenshots/19.1.png)<br/>
    ![alt text](./Screenshots/19.2.png)<br/>
    ![alt text](./Screenshots/20.png)<br/>

# Tworzenie Dockerfile i budowanie obrazu

1. Stworzyłem nowy plik Dockerfile przy użyciu komendy `touch Dockerfile`<br/>
   ![alt text](./Screenshots/21.png)<br/>

2. Następnie, aby edytować plik zainstalowałem edytory tekstu (vim oraz nano)<br/>
   ![alt text](./Screenshots/22.png)<br/>

3. Przy użyciu nano edytowałem Dockerfile<br/>
   ![alt text](./Screenshots/23.png)<br/>

4. Po edycji i zapisaniu treść Dockerfile przedstawiała się w następujący sposób:<br/>
   ![alt text](./Screenshots/24.png)<br/>

5. Po wykonaniu wcześniejszych 2 kroków, zorientowałem się że wciąż znajduję się w kontenerze, więc wyszedłem z niego za pomocą komendy `exit`, a następnie powtórzyłem ostatnie dwa kroki.<br/>
   ![alt text](./Screenshots/25.png)<br/>

6. Następnie zbudowałem obraz używając `docker build -t=ubuntu-react-app -f Dockerfile .` gdzie **ubuntu-react-app** jest nazwą obrazu po zbudowaniu, a **Dockerfile** to wcześniej stworzony dockerfile.<br/>
   Otrzymałem następujący błąd:<br/>
   ![alt text](./Screenshots/26.png)<br/>

7. Błąd ten był spowodowany błędami w Dockerfile, które zostały naprawione w następujący sposób:<br/>
   ![alt text](./Screenshots/28.png)<br/>

8. Po naprawieniu błędów, udało się pomyślnie zbudować obraz.<br/>
   ![alt text](./Screenshots/29.png)<br/>
   ![alt text](./Screenshots/30.png)<br/>

9. Tworzę Dockerfile dla testów na bazie poprzedniego obrazu.<br/>Analogicznie jak wczesniej używam komend:<br/>

- `touch Dockerfile-test`<br/>
- `nano Dockerfile-test`<br/>
  ![alt text](./Screenshots/31.png)<br/>
  ![alt text](./Screenshots/31.1.png)<br/>

10. Po utworzeniu **Dockerfile-test** buduję za jego pomocą obraz <br/>**ubuntu-react-app-test** używając `docker build -t=ubuntu-react-app-test -f Dockerfile-test .` i przedstawiam wynik<br/>

    ![alt text](./Screenshots/32.png)<br/>

# Uruchormienie obrazów

1. Uruchomienie obrazu **ubuntu-react-app** za pomocą `docker run ubuntu-react-app`<br/>

   ![alt text](./Screenshots/33.png)<br/>

2. Uruchomienie obrazu **ubuntu-react-app-test** za pomocą `docker run ubuntu-react-app-test`<br/>

   ![alt text](./Screenshots/34.png)<br/>

# Uruchamianie i wykorzystywanie programu na zewnątrz kontenera

1. Tworzę nowy dockerfile **Dockerfile2** do uruchomienia aplikacji, na bazie istniejącego już głównego **Dockerfile**<br/>

   ![alt text](./Screenshots/35.png)<br/>

2. Tworzę obraz **ubuntu-react-app2** wykorzystując nowo utworzony **Dockerfile2** za pomocą komendy: `docker build -t ubuntu-react-app2 -f Dockerfile2 .`<br/>

   ![alt text](./Screenshots/36.png)<br/>

3. Ponieważ projekt defaultowo uruchamia się na porcie 3000, uruchamiam obraz z przekierowaniem portu "na zewnątrz" na port 5000 używając komendy:
   `docker run -it -p 5000:3000 ubuntu-react-app2`<br/>

   ![alt text](./Screenshots/37.png)<br/>

   Na załączonym obrazie widzimy, że program zostaje uruchomiony wewnątrz kontenera, na localhost:3000, jednak po przekierowaniu jest widoczny na zewnątrz na porcie 5000.<br/>

# Tworzenie kompozycji

1. Tworzę plik **docker-compose.yml**<br/>
   ![alt text](./Screenshots/38.png)<br/>

2. Pobieram docker-compose za pomocą `apt install docker-compose`<br/>
   ![alt text](./Screenshots/40.png)<br/>

3. Uruchamiam komendą `docker-compose up`<br/>

   ![alt text](./Screenshots/39.png)<br/>

4. Widzimy, że program działa, testy przechodzą, więc możemy wyłączyć używając **ctrl+C**<br/>

   ![alt text](./Screenshots/41.png)<br/>

## Czy dystrybuowanie wybranego oprogramowania w postaci kontenera ma sens?

Konteneryzacja tego typu oprogramowania ułatwia łączenie małych, niezależnych usług oraz pomaga wyeliminować błędy specyficzne dla środowiska poprzez możliwość lokalnego zreplikowania środowiska produkcyjnego.
Kolejnym plusem są dockerfile, dzięki którym inny programista może szybko uruchomić dany projekt nie martwiąc się o przygotowanie go i konfigurację.
Dystrybuowanie tego typu oprogramowania ma sens na pewno w przypadku, gdy chcemy użyć specyficznej wersji node'a. Dodatkowo biblioteka react-router używa backendu.

## Umotywować wybór (wewnątrz / na zewnątrz)

Ponieważ jest to projekt front-endowy uruchomienie go jedynie wewnątrz kontenera nie miałoby większego sensu. Na zewnątrz kontenera mamy możliwość wglądu w projekt w przeglądarce (tak jak wyżej zaprezentowano na przykładzie Mozilli Firefox).

<br/>

# Instalacja Jenkinsa w środowisku linuksowym z wykorzystaniem Dockera

1. Wykorzystuję do tego instrukcję: https://www.jenkins.io/doc/book/installing/docker/<br/>

2. Tworzę bridge network używając komendy `docker network create jenkins`<br/>
   ![alt text](./Screenshots/42.png)<br/>

3. Następnie używam komend z instrukcji:<br/>
   ![alt text](./Screenshots/43.png)<br/>
   ![alt text](./Screenshots/44.png)<br/>
   ![alt text](./Screenshots/45.png)<br/>

   Sprawdzam obrazy i kontenery:<br/>
   ![alt text](./Screenshots/46.png)<br/>

4. Kolejnym punktem z instrukcji było stworzenie Dockerfile<br/>
   ![alt text](./Screenshots/47.png)<br/>

5. Z Utworzonego wcześniej **Dockerfile-Jenkins** buduję obraz Blueocean jako kontener komendą: <br/>
   `docker build -t myjenkins-blueocean:2.319.3-1 -f Dockerfile-Jenkins .`<br/>
   ![alt text](./Screenshots/49.png)<br/>

6. Uruchamiam obraz poleceniami przekopiowanymi z instrukcji na stronie:<br/>
   ![alt text](./Screenshots/50.png)<br/>

   Sprawdzam obrazy i kontenery:<br/>
   ![alt text](./Screenshots/48.png)<br/>

7. Wchodzę do kontenera za pomocą: `docker exec -it jenkins-blueocean bash`<br/>
   ![alt text](./Screenshots/51.png)<br/>
   ![alt text](./Screenshots/52.png)<br/>

8. Uzyskuję hasło administratora i loguję się nim<br/>
   ![alt text](./Screenshots/53.png)<br/>

   Po zalogowaniu<br/>
   ![alt text](./Screenshots/54.png)<br/>

9. Po utworzeniu konta admina:<br/>
   ![alt text](./Screenshots/55.png)<br/>
