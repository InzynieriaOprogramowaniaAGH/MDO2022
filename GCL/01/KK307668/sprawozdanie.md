# Sprawozdanie Krzysztof Kachniarz IS

1. Zainstaluj klienta Git i obsługę kluczy SSH
    > git-installed.png
2. Sklonuj repozytorium za pomocą HTTPS
    > git-clone-https.png
3. Upewnij się w kwestii dostępu do repozytorium jako uczestnik i sklonuj je za pomocą utworzonego klucza SSH
    > sprawdzenie-dostępu.png
    * Utwórz dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem
    > klucz-bez-hasła.png<br>
    > klucz-z-haslem.png
    * Skonfiguruj klucz SSH jako metodę dostępu
    > ssh-add.png <br>
    > ssh-add-git.png
    * Sklonuj repozytorium z wykorzystaniem protokołu SSH
    > git-clone-ssh.png
4. Przełącz się na gałąź swojej grupy
    > checkout.png
5. Utwórz gałąź o nazwie "inicjały & nr indeksu" np. KD232144
    > git-nowy-branch.png
6. Rozpocznij pracę na nowej gałęzi
    * W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu" np. KD232144
        > nowy-katalog.png
    *  W nowym katalogu dodaj plik ze sprawozdaniem
        > touch-sprawozdanie.png
    * Dodaj zrzuty ekranu
    * Wyślij zmiany do zdalnego źródła
        > git-add-&-git-commit.png <br>
        > wyslanie-zmian-do-zdalnego-zrodla.png
    * Spróbuj wciągnąć swoją gałąź do gałęzi grupowej
        > wciagniecie-zmian-do-galezi-grupowej.png
    * Zaktualizuj sprawozdanie i zrzuty o ten krok i wyślij aktualizację do zdalnego źródła (na swojej gałęzi)
        > aktualizacja-repo-o-krok-6-6.png
    * Oznacz tagiem ostatni commit i wypchnij go na zdalną gałąź
        > git-tag.png
    * Ustal hook, który będzie sprawdzał, czy wiadomość z commitem zawiera nazwę przedmiotu
        > hook1.png<br>
        > utworzony-hook.png
    * W jaki sposób stworzyć hook, który będzie ustawiał prefiks wiadomości commitu tak, by miał nazwę przedmiotu?
7. Wystaw Pull Request do gałęzi grupowej