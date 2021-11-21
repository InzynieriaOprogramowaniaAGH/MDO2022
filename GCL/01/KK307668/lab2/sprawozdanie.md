# Zajęcia 02
Kachniarz Krzysztof
---



# Zestawienie środowiska
1. Zainstaluj Docker w systemie linuksowym
 `docker-instalacja.png`
2. Zarejestruj się w Docker Hub i zapoznaj z sugerowanymi obrazami `docker-hub.png`
3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql
    - `docker-pull-hello-world.png`
    - `docker-pull-mysql.png`
    - `docker-pull-ubuntu.png`
4. Uruchom busybox
   - Pokaż efekt uruchomienia kontenera 
        - `docker-uruchomienie-busybox-lista-kontenerow.png` (brak efektów po uruchomieniu, ponieważ uruchomił się sam shell bez argumentów)
   - Podłącz się do kontenera interaktywnie i wywołaj numer wersji
        - `interaktywne-wywolanie-busybox.png`
5. Uruchom "system w kontenerze"
   - Zaprezentuj PID1 w kontenerze i procesy dockera na hoście
        - `PID1 w kontenerze.png`
        - `procesy-dockera-na-hoscie.png`
   - Zaktualizuj pakiety
        - `aktualizacja-pakietow.png`
   - Wyjdź
        - `wyjscie-z-ubuntu-w-kontenerze.png`
6. Pokaż uruchomione ( != "działające" ) kontenery, wyczyść je.
    - `lista uruchomionych kontenerow.png`
    - `wyczyszczenie-kontenerow.png`
7. Wyczyść obrazy
    - `usuniecie-obrazow.png`

# Budowanie programu
1. Znajdź projekt umożliwiający łatwe wywołanie testów jednostkowych
    - `https://github.com/fyodorio/angular-unit-testing-examples`
2. Przeprowadź budowę/konfigurację środowiska
    - `nodejs-install.png`
    - `apt-install-npm.png`
    - `npm-i-angular.png`
    - `npm-i-w-projekcie.png`
    - `npm-run-build.png`
3. Uruchom testy
    - `run-tests.png`
4. Ponów ten proces w kontenerze
   - Wybierz i uruchom platformę
        - `4/1.png`
   - Zaopatrz ją w odpowiednie oprogramowanie wstępne
        - `nodejs-install.png`
        - `apt-install-npm.png`
        - `npm-i-angular.png`
        - `4/4 (chrome install).png`
   - Sklonuj aplikację
        - `4/2.png`
        - `4/3 (git clone).png`
   - Skonfiguruj środowisko i uruchom build
        - `4 (npm i).png`
        - `4/4 (build).png`
   - Uruchom testy
        - `4/5.png (testy nie przeszły, ponieważ ze względu na charakterystykę testów w angularze wystąpił błąd z otwarciem przeglądarki w kontenerze. Lecz sama idea została osiągnięta)`
5. Stwórz Dockerfile, który ma to osiągnąć
6. Zaprezentuj Dockerfile i jego zbudowanie
    - `5/Dockerfile.png`
    - `5/docker-image.png`
    - `5/part1.png`
    - `5/part2.png`
7. Na bazie obrazu utworzonego poprzednim dockerfilem stwórz kolejny, który będzie uruchamiał testy
    - `7/Dockerfile.png`
    - `7/uruchomienie-testow-na-bazie-poprzedniego-obrazu.png`

   