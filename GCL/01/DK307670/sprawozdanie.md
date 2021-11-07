## Zadania do wykonania
1. Zainstaluj klienta Git i obsługę kluczy SSH
    ![screen1](img/screen1.png)
    git i ssh znajdowaly sie juz na sprzecie z ktorego korzystam
2. Sklonuj repozytorium https://github.com/InzynieriaOprogramowaniaAGH/MDO2022 za pomocą HTTPS
     ![screen2](img/screen2.png)
3. Upewnij się w kwestii dostępu do repozytorium jako uczestnik i sklonuj je za pomocą utworzonego klucza SSH
   - Utwórz dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem
    ![screen3](img/screen3.png)
    ![screen4](img/screen4.png)
   - Skonfiguruj klucz SSH jako metodę dostępu
    ![screen5](img/screen5.png)
    ![screen6](img/screen6.png)
   - Sklonuj repozytorium z wykorzystaniem protokołu SSH
     ![screen7](img/screen7.png)
4. Przełącz się na gałąź swojej grupy
5. Utwórz gałąź o nazwie "inicjały & nr indeksu" np. ```KD232144```
    ![screen8](img/screen8.png)
6. Rozpocznij pracę na nowej gałęzi
   - W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu" np. ```KD232144```
   - W nowym katalogu dodaj plik ze sprawozdaniem
   - Dodaj zrzuty ekranu
    ![screen9](img/screen9.png)
   - Wyślij zmiany do zdalnego źródła
    ![screen10](img/screen10.png)
   - Spróbuj wciągnąć swoją gałąź do gałęzi grupowej
    ![screen11](img/screen11.png)
   - Zaktualizuj sprawozdanie i zrzuty o ten krok i wyślij aktualizację do zdalnego źródła (na swojej gałęzi)
     ![screen12](img/screen12.png)
   - Oznacz tagiem ostatni commit i wypchnij go na zdalną gałąź
   - Ustal hook, który będzie sprawdzał, czy wiadomość z commitem zawiera nazwę przedmiotu
    ![screen13](img/screen13.png)
    ![screen14](img/screen14.png)
   - W jaki sposób stworzyć hook, który będzie *ustawiał* prefiks wiadomości commitu tak, by miał nazwę przedmiotu?
    ![screen16](img/screen15.png)
1. Wystaw Pull Request do gałęzi grupowej
2. Zgłoś zadanie (Teams assignment)