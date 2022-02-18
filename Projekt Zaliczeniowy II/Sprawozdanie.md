# Metodyki DevOps

# Wybranie odpowiedniego projektu

Do wykonania ćwiczenia wykorzystano następujące repozytorium zawierające mechanizm budowania oraz testy jednostkowe:

- https://github.com/jterkalski/react-app

# Uruchomienie kontenera

1. Pobieram obraz ubuntu za pomocą komendy: `docker pull ubuntu`
   ![alt text](./Screenshots/1.png)
2. Uruchamiam ubuntu używając: `docker run ubuntu` oraz sprawdzam status przy pomocy `docker ps -a`
   ![alt text](./Screenshots/2.png)
3. Ponieważ kontener posiada status "Exited", używam komendy `docker run -dt ubuntu` z flagą detached aby uruchomić kontenener ponownie, tym razem w tle.
   ![alt text](./Screenshots/4.png)
4. Następnie używam `docker container prune` aby wyczyścić wszystkie niedziałające w danym momencie kontenery (ze statusem "Exited").
   ![alt text](./Screenshots/3.png)
5. Sprawdzam status
   ![alt text](./Screenshots/5.png)

# Budowanie programu

1. Teraz, gdy kontener działa już poprawnie, wchodzę na niego używając komendy `docker exec -it <id-kontenera> bash`
   ![alt text](./Screenshots/6.png)

2. Aktualizuję paczki za pomocę komend update oraz upgrade: `apt-get update && apt-get upgrade`
   ![alt text](./Screenshots/7.png)

3. Następnie instaluję Node.js komendą `apt-get install nodejs`
   ![alt text](./Screenshots/8.png)
   ![alt text](./Screenshots/9.png)

4. Installuję npm: `apt-get install npm`
   ![alt text](./Screenshots/10.png)

5. Sprawdzam czy zostały poprawnie zainstalowane poprzez sprawdzenie wersji
   ![alt text](./Screenshots/11.png)

6. Installuję git'a: `apt-get install git`
   ![alt text](./Screenshots/12.png)

7. Klonuje repozytorium używając `git clone`
   ![alt text](./Screenshots/13.png)

8. Przechodzę do katalogu z projektem (`cd react-app`), a następnie za pomocą `npm install` instaluje niezbędne do działania programu moduły (node_modules)
   ![alt text](./Screenshots/14.png)

9. Buduję program za pomocą `npm run build`, lecz nie udaje się go odpalić
   ![alt text](./Screenshots/15.png)

10. Doinstalowuje curl: `sudo apt install curl`, a następnie aktualizuję node'a do wersji v14.x
    ![alt text](./Screenshots/16.png)

11. Przy pomocy curl aktualizuje Node do wersji v14.x

- `curl -sL https://deb.nodesource.com/setup_14.x | bash -`
- `apt install -y nodejs`
  ![alt text](./Screenshots/17.png)
  ![alt text](./Screenshots/18.png)

12. Po zaktualizowaniu Node'a udaje się zbudować program
    ![alt text](./Screenshots/19.png)

13. Uruchamiam testy za pomocą `npm run test`, podczas uruchamiania pojawia się okno dialogowę, w tym momencie należy kliknąć "a" aby uruchomić wszystkie możliwe testy.
    ![alt text](./Screenshots/19.1.png)
    ![alt text](./Screenshots/19.2.png)
    ![alt text](./Screenshots/20.png)

# Tworzenie Dockerfile i budowanie obrazu

1. Stworzyłem nowy plik Dockerfile przy użyciu komendy `touch Dockerfile`
   ![alt text](./Screenshots/21.png)

2. Następnie, aby edytować plik zainstalowałem edytory tekstu (vim oraz nano)
   ![alt text](./Screenshots/22.png)

3. Przy użyciu nano edytowałem Dockerfile
   ![alt text](./Screenshots/23.png)

4. Po edycji i zapisaniu treść Dockerfile przedstawiała się w następujący sposób:
   ![alt text](./Screenshots/24.png)

5. Po wykonaniu wcześniejszych 2 kroków, zorientowałem się że wciąż znajduję się w kontenerze, więc wyszedłem z niego za pomocą komendy `exit`, a następnie powtórzyłem ostatnie dwa kroki.
   ![alt text](./Screenshots/25.png)

6. Następnie zbudowałem obraz używając `docker build -t=ubuntu-react-app -f Dockerfile .` gdzie **ubuntu-react-app** jest nazwą obrazu po zbudowaniu, a **Dockerfile** to wcześniej stworzony dockerfile.<br/>
   Otrzymałem następujący błąd:
   ![alt text](./Screenshots/26.png)

7. Błąd ten był spowodowany błędami w Dockerfile, które zostały naprawione w następujący sposób:
   ![alt text](./Screenshots/28.png)

8. Po naprawieniu błędów, udało się pomyślnie zbudować obraz.
   ![alt text](./Screenshots/29.png)
   ![alt text](./Screenshots/30.png)

9. Tworzę Dockerfile dla testów na bazie poprzedniego obrazu.<br/>Analogicznie jak wczesniej używam komend:

- `touch Dockerfile-test`
- `nano Dockerfile-test`<br/>
  ![alt text](./Screenshots/31.png)
  ![alt text](./Screenshots/31.1.png)

10. Po utworzeniu **Dockerfile-test** buduję za jego pomocą obraz **ubuntu-react-app-test** używając `docker build -t=ubuntu-react-app-test -f Dockerfile-test .` i przedstawiam wynik

    ![alt text](./Screenshots/32.png)

# Uruchormienie obrazów

1. Uruchomienie obrazu **ubuntu-react-app** za pomocą `docker run ubuntu-react-app`

   ![alt text](./Screenshots/33.png)

2. Uruchomienie obrazu **ubuntu-react-app-test** za pomocą `docker run ubuntu-react-app-test`

   ![alt text](./Screenshots/34.png)

# Uruchamianie i wykorzystywanie programu na zewnątrz kontenera

1. Tworzę nowy dockerfile **Dockerfile2** do uruchomienia aplikacji, na bazie istniejącego już głównego **Dockerfile**

   ![alt text](./Screenshots/35.png)

2. Tworzę obraz **ubuntu-react-app2** wykorzystując nowo utworzony **Dockerfile2** za pomocą komendy: `docker build -t ubuntu-react-app2 -f Dockerfile2 .`

   ![alt text](./Screenshots/36.png)

3. Ponieważ projekt defaultowo uruchamia się na porcie 3000, uruchamiam obraz z przekierowaniem portu "na zewnątrz" na port 5000 używając komendy:
   `docker run -it -p 5000:3000 ubuntu-react-app2`

   ![alt text](./Screenshots/37.png)

   Na załączonym obrazie widzimy, że program zostaje uruchomiony wewnątrz kontenera, na localhost:3000, jednak po przekierowaniu jest widoczny na zewnątrz na porcie 5000.

# Tworzenie kompozycji

1. Tworzę plik **docker-compose.yml**
   ![alt text](./Screenshots/38.png)

2. Pobieram docker-compose za pomocą `apt install docker-compose`
   ![alt text](./Screenshots/40.png)

3. Uruchamiam komendą `docker-compose up`

   ![alt text](./Screenshots/39.png)

4. Widzimy, że program działa, testy przechodzą, więc możemy wyłączyć używając **ctrl+C**

   ![alt text](./Screenshots/41.png)

## Czy dystrybuowanie wybranego oprogramowania w postaci kontenera ma sens?

Konteneryzacja tego typu oprogramowania ułatwia łączenie małych, niezależnych usług oraz pomaga wyeliminować błędy specyficzne dla środowiska poprzez możliwość lokalnego zreplikowania środowiska produkcyjnego.
Kolejnym plusem są dockerfile, dzięki którym inny programista może szybko uruchomić dany projekt nie martwiąc się o przygotowanie go i konfigurację.

## Umotywować wybór (wewnątrz / na zewnątrz)

Ponieważ jest to projekt front-endowy uruchomienie go jedynie wewnątrz kontenera nie miałoby większego sensu. Na zewnątrz kontenera mamy możliwość wglądu w projekt w przeglądarce (tak jak wyżej zaprezentowano na przykładzie Mozilli Firefox).
