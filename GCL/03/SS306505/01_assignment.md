# Lab01

## Kilka rad zanim zaczniesz
- Postaraj się unikać polskich znaków przy nadawaniu nazw plikom oraz katalogom

## Kroki


1. Zainstaluj klienta Git i obsługę kluczy:
    - Sprawdzenie czy klient git-a jest już zainstalowany oraz gdzie znajduje się w systemie (screenshot nr 0)
        - git --version 
        - which git
        - whereis git
    - [Przykładowa instalacja Git-a](https://docs.gitlab.com/ee/gitlab-basics/start-using-git.html#install-git)
    - Obsługa kluczy jest możliwa jeśli posiadamy w naszym systemie klienta ssh a co za tym idzie generator kluczy, którego istnienie możemy sprawdzić poniższym poleceniem
        - ssh-keygen
    - [Przykładowa instalacja klienta OpenSSH](https://phoenixnap.com/kb/ssh-to-connect-to-remote-server-linux-or-windows)

2. Sklonój repozytorium https://github.com/InzynieriaOprogramowaniaAGH/MDO2022 za pomocą HTTPS
    - Użyjemy w tym celu polecenia klonującego repozytorium
        - git clone https://github.com/InzynieriaOprogramowaniaAGH/MDO2022
3. Utwórz dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem
    - Polecenie ssh-keygen użyte z flagą -t pozwoli nam wybrać sposób szyfrowania (domyślny to rsa), a flaga -C dodać komentarz którym będzie np. adres email naszego konta GitHub (screenshot numer 1 i 2)
        - ssh-keygen -t ed25519 -C "github_account_email"
4. Skonfiguruj klucz SSH jako metodę dostępu
    - Pierwotnie sposób w jaki komunikujemy się z remote repo jest ustawiony po protokole HTTPS
    - Aby to zmienić musimy wyedytować plik konfiguracyjny który znajduje się w MDO2022/.git/config
    - W efekcie sekcja [remote "origin"] powinna zawierać login i nazwe hosta oraz repo z jakim chcemy rozmawiać (screenshot numer 3)
5. Sklonuj repozytorium z wykorzystaniem protokołu SSH
    - Usuń katalog MDO2020 wraz z zawartością poleceniem
        - rm -rf MDO2020/
    - Sklonuj repo używając polecenia git clone z użyciem SSH
        - git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git
6. Przełącz się na gałąź swojej grupy
    - Użyj polecenia checkout nazwa_gałęzi
        - git checkout GCL03
    - Jeśli ta gałąź nie jest widoczna możesz użyć polecenia fetch, aby zaktualizować remote gałęzie w lokalnym repo
        - git fetch
7. Utwórz lokalną gałąź o nazwie "inicjały & nr indeksu" np. KD232144
    - Do tworzenia gałęzi użyj jednego z dwóch poleceń
        - git branch nazwa_gałęzi
        - git checkout -b nazwa_gałęzi
      
      Pierwsze polecenie tylko stworzy gałąź, drugie stworzy, a następnie przełączy się na nią
8. W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu" np. SS306505 (screenshot 04)
    - Poleceniem cd przejdź do katalogu swojej grupy 
        - cd MDO2022/GCL/03
    - Utwórz swój katalog
        - mkdir SS306505
    - Przejdź do tego katalogu poleceniem cd
        - cd SS306505
9. W nowym katalogu dodaj plik ze sprawozdaniem (screenshot 04)
    - Poleceniem touch możesz stworzyć plik z konkretnym rozszerzeniem np. md lub txt
        - touch 01_assignment.md
10. Dodaj zrzuty ekrany z wykonywanych przez siebie działań na jeden z dwóch podanych niżej sposobów
    - Możesz użyć polecenia cp aby je przekopiować
        - cp ścieżka_do_zrzutu ścieżka_do_katalogu_zrzutów_w_lokalnym_repo
    - Możesz użyć dowolnego edytora np. VS Code, którego uruchomisz poleceniem
        - code .
    
        Następnie przeciągnij i upuść na edytorze swoje zrzuty
11. Dodaj pliki do repo lokalnego używając polecenia add i nazwy pliku 
    - git add 01_assignment.md
    - git add nazwa_zrzutu.png
    
    Jeśli chcesz dodać wszystko jednym poleceniem użyj:
    - git add .
12. Zakomituj zmiany poleceniem commit z flagą -m, która pozwoli dodać opis zmian jakich dokonaliśmy
    - git commit -m "Dodanie pliku sprawozdania i zrzutow"

13. Z poziomy GitHub'a utwórz nową remote gałąź.
Powinna ona wychodzić z gałęzi GCL03 i posiadać nazwe inicjały & nr indeksu (w naszym wypadku SS306505).

14. Wyślij zmiany do zdalnego źródła
    - Aby wysłać nasze zmiany z lokalnego repo użyj polecenia push, nazwy źródła oraz gałęzi
        - git push origin SS306505
    - Jeśli jesteś ciekaw czym dokładnie jest źródło możesz sprawdzić to poleceniem
        - git remote -v

15. Spróbuj wciągnąć swoją gałąź do gałęzi grupowej (screenshot 07)
    - W tym celu przełączymy się na gałąź grupową
        - git checkout GCL03
    - Następnie wciągniemy zmiany z naszej gałęzi do gałęzi grupowej
        - git merge 
    - Próba wypchnięcia tego do zdalnego repo 
        - git push origin GCL03
    
        Powinna zakończyć się błędem, ponieważ gałąź GCL03 jest chroniona, co oznacza że musi zostać stworzony i zatwierdzony pull request zanim zmergujemy się z naszym kodem do zdalnego repo (screenshot 08)

16. Oznacz tagiem ostatni commit i wypchij na zdalną gałąź
    - Użyj polecenia tag, nazwy oraz gałęzi do której ma się odnosić
        - git tag v0.1 SS306505
    - Aby wypchnać go na zdalną gałąź użyj polecenia push
        - git push origin v0.1

17. Ustal hook, który będzie sprawdzał, czy wiadomość z commitem zawiera nazwę przedmiotu (screenshot 10-12)
    - Należy przejść do katalogu MDO2020/.git/hooks
        - cd MDO2020/.git/hooks
    - Następnie stworzyć plik commit-msg i nadać odpowiednie para
        - touch commit-msg
        - chmod 755 commit-msg
    - Uzupełnij zawartość pliku (screenshot 11)
        - nano commit-msg
    - Używając polecenia commit sprawdź czy hook działa
        - git commit -m "bledna wiadomosc"
        - git commit -m "DevOps poprawna wiadomosc"
18. W jaki sposób stworzyć hook, który będzie ustawiał prefiks wiadomości commitu tak, by miał nazwę przedmiotu?
    - Należy przejść do katalogu MDO2020/.git/hooks
        - cd MDO2020/.git/hooks
    - Następnie stworzyć plik prepare-commit-msg i nadać odpowiednie prawa (screenshot 13)
        - touch prepare-commit-msg
        - chmod 755 prepare-commit-msg
    - Uzupełnij zawartość pliku (screenshot 14)
        - nano prepare-commit-msg
    - Używając polecenia commit sprawdź czy hook działa
        - git commit -m "Testowy commit"
        
        Po wykonaniu polecenia na wyjściu z konsoli zobaczysz "DevOps Testowy commit"

    

    




    
    
    