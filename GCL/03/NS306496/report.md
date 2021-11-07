### Metodyki devOps - laboratorium 1 sprawozdanie

1. Upewnij się, że masz zainstalowane `git` oraz narzędzie do obsługi kluczy SSH, przykładowo `ssh-keygen`
2. Sklonuj repozytorium za pomocą HTTPS, używając polecenia `git clone`
3. Utwórz dwa klucze SSH, w tym co najmniej jeden zabezpieczony hasłem. Przykładowo `ssh-keygen -t ed25519 -C "your_email@gmail.com"`
4. Dodaj utworzone klucze SSH jako metodę dostępu w github: `Settings > SSH and GPG keys`
5. Sklonuj repozytorium za pomocą SSH, używając polecenia `git clone` oraz wprowadź hasło którym zabezpieczony jest klucz SSH
6. Przełącz się na gałąź swojej grupy za pomocą `git checkout GCL03`, gdzie `03` jest numerem grupy
7. Stwórz nowy branch o nazwie `{inicjały}{numer_indeksu}` za pomocą `git checkout -b NS306496`
8. Wprowadź zmiany i wyślij zmiany do zdalnego źródła za pomocą `git push --set-upstream origin NS306496`
9. Wciągnij swoje zmiany lokalnie w gałąź grupową
   `git checkout GLC03`
   `git pull`
   `git merge NS306496`
10. Wypchnij połączone gałęzie na zdalne repozytorium
    `git push`
    Akcja nie jest możliwa, ponieważ gałąź `GLC03` jest chroniona, oznacza to tyle, że nie można bezpośrednio kontrybuować do takiej gałęzi
11. Ustal hook, który będzie sprawdzał, czy wiadomość z commitem zawiera nazwę przedmiotu
    - Otwórz folder `.git/hooks`
    - Żeby "zainstalować" hook, usuń rozszerzenie `.sample` wybranego hooka `commit-msg.sample`
    - Napisz swój własny skrypt, który w przypadku nie spełnienia walidacji zwraca wartość różną niż zero
    - Zapisz plik
12. W jaki sposób stworzyć hook, który będzie ustawiał prefiks wiadomości commitu tak, by miał nazwę przedmiotu?
    - Należy użyć `prepare-commit-msg` hook'a
