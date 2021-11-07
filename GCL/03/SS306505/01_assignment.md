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
8. W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu" np. SS306505
    - Poleceniem cd przejdź do katalogu swojej grupy 
        - cd MDO2022/GCL/03
    - Utwórz swój katalog
        - mkdir SS306505
    - Przejdź do tego katalogu poleceniem cd
        - cd SS306505
9. W nowym katalogu dodaj plik ze sprawozdaniem
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
Powinna ona wychodzić z gałąźy GCL03 i posiadać nazwe inicjały & nr indeksu (w naszym wypadku SS306505).

14. Wyślij zmiany do zdalnego źródła
    - Aby wysłać nasze zmiany z lokalnego repo musimy dodać remote repo poleceniem remote
        - git remote add remotes/MDO2020/SS306505
    - Następnie wysłać zakomitowane zmiany poleceniem push
        - git push origin SS306505
        
    



Z poziomy GitHub'a utwórz nową remote gałąź.
Powinna ona wychodzić z gałąźy GCL03 i posiadać nazwe inicjały & nr indeksu (w naszym wypadku SS306505).


    
    
    