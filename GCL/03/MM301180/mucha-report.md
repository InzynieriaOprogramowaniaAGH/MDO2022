# Metodyki devOps - laboratorium 1

## Zadania do wykonania

1. Zainstaluj klienta Git i obsługę kluczy SSH
   - Git for Windows został pobrany ze strony: https://git-scm.com/downloads
   ![App Screenshot](.\img\screen-1.PNG)
   - Zainstalowany git jest obsługiwane z terminala cmder, pobranego ze strony
2. Sklonuj repozytorium https://github.com/InzynieriaOprogramowaniaAGH/MDO2022 za pomocą HTTPS
   - ![App Screenshot](.\img\screen-2.PNG)
3. Upewnij się w kwestii dostępu do repozytorium jako uczestnik i sklonuj je za pomocą utworzonego klucza SSH
    - Utwórz dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem
      - Klucze ssh zostały wygenerowane, wykorzystując tutorial:
      https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent
    - Wykorzystane zostały klucze, które zostały wygenerowane przed ćwiczeniami:
    - ![App Screenshot](.\img\screen-3.PNG)
    - Skonfiguruj klucz SSH jako metodę dostępu
      - Klucze na komputerze:
        - ![App Screenshot](.\img\screen-4.PNG)
        - ![App Screenshot](.\img\screen-5.PNG)
      - Klucze wgrane na git’a
         - ![App Screenshot](.\img\screen-6.PNG)
    - Sklonuj repozytorium z wykorzystaniem protokołu SSH
       - ![App Screenshot](.\img\screen-7.PNG)
4. Przełącz się na gałąź swojej grupy
   - ![App Screenshot](.\img\screen-8.PNG)
5. Utwórz gałąź o nazwie "inicjały & nr indeksu" np. ```KD232144```
   - ![App Screenshot](.\img\screen-9.PNG)
6. Rozpocznij pracę na nowej gałęzi
    - W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu" np. ```KD232144```
    - ![App Screenshot](.\img\screen-10.PNG)
    - W nowym katalogu dodaj plik ze sprawozdaniem
    - Dodaj zrzuty ekranu
    - ![App Screenshot](.\img\screen-11.PNG)
    - Wyślij zmiany do zdalnego źródła
      - dalej wykorzystywany jest WebStorm i wbudowane narzędzie do git'a
      - zrzut ekranu z drzewka branch'ów:
        - ![App Screenshot](.\img\screen-12.PNG)
      
    - Spróbuj wciągnąć swoją gałąź do gałęzi grupowej
      - ![App Screenshot](.\img\screen-13.PNG)
    - Branch jest chroniony zatem wystąpił błąd
       - ![App Screenshot](.\img\screen-14.PNG)
    - Zaktualizuj sprawozdanie i zrzuty o ten krok i wyślij aktualizację do zdalnego źródła (na swojej gałęzi)
       - ![App Screenshot](.\img\screen-15.PNG)
    - Oznacz tagiem ostatni commit i wypchnij go na zdalną gałąź
       - ![App Screenshot](.\img\screen-16.PNG)
       - ![App Screenshot](.\img\screen-17.PNG)
      - ![App Screenshot](.\img\screen-18.PNG)
      - ![App Screenshot](.\img\screen-19.PNG)
      - ![App Screenshot](.\img\screen-20.PNG)
    - Ustal hook, który będzie sprawdzał, czy wiadomość z commitem zawiera nazwę przedmiotu
    - W jaki sposób stworzyć hook, który będzie *ustawiał* prefiks wiadomości commitu tak, by miał nazwę przedmiotu?
7. Wystaw Pull Request do gałęzi grupowej
8. Zgłoś zadanie (Teams assignment)

hook
commint.msg
