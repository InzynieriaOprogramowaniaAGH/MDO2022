1. Zainstaluj klienta Git i obsługę kluczy SSH
 - Obsługę klienta Git, jak i kluczy miałem zainstalowaną wcześniej, więc w tym przpypadku nie podjąłem żadnych działań

  [Zdjęcie 1]

2. Sklonuj repozytorium https://github.com/InzynieriaOprogramowaniaAGH/MDO2022 za pomocą HTTPS
 - Skolonwanie rezpozytrium zostało wykonanne za pomocą polecenia 
 "git clone https://github.com/InzynieriaOprogramowaniaAGH/MDO2022.git"

  [Zdjęcie 2]

3. Upewnij się w kwestii dostępu do repozytorium jako uczestnik i sklonuj je za pomocą utworzonego klucza SSH
 - Utwórz dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem
 Zgodnie z dokumentacją zawartą w https://www.ssh.com/academy/ssh/keygen możemy użyć 4 różynch algorytmów: rsa, dsa, ecdsa, ed25519.
 Zgodnie z wytycznymi w zadaniu, utworzyłem klucze za pomocą algorytmu ecdsa oraz dsa:
 "ssh-keygen -t dsa" -> klucz nie zabezpieczony hasłem [Zdjecie 3]
 "ssh-keygen -t ecdsa" -> klicz zabezpieczny hasłem [Zdjęcie 4]
 - Skonfiguruj klucz SSH jako metodę dostępu
 W tym przypadku należało zalogować się na swoje konto git, przejść do https://github.com/settings/keys, a następnie skopiować zawartość któregoś z kluczy
 "cat ~/.ssh/id_ecdsa.pub | clip" [Zdjecie 5]
 "ssh -T git@github.com" - Sprawdzam, czy dziala [Zdjecie 6]
 - Sklonuj repozytorium z wykorzystaniem protokołu SSH
 "git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022" [Zdjecie 7]

 Źródło: https://gist.github.com/xirixiz/b6b0c6f4917ce17a90e00f9b60566278

 4. Przełącz się na gałąź swojej grupy
 - "git checkout GCL01" [Zdjecie 8]

 5. Utwórz gałąź o nazwie "inicjały & nr indeksu"
 - "git checkout -b MK284011" [Zdjecie 9]

 6. Rozpocznij pracę na nowej gałęzi 
 - W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu"
 "mkdir MK248011" [Zdjecie 10]
 - W nowym katalogu dodaj plik ze sprawozdaniem
 "New-Item -Path 'C:\Repos\MDO2022\GCL\01\MK284011\sprawozdanie.md' -ItemType File"
 - Dodaj zrzuty ekranu
 - Wyślij zmiany do zdalnego źródła
 "git add ."
 "git status"
 "git commit -m 'sprawzodanie v3'" [Zdjecie 11]
 "git push" [Zdjecie 12] - W tym przypadku najpierw nalezalo uzyc "git push --set-upstream origin MK284011", aby stworzyc brancha, jednakze zrobilem to wczesniej
 - Spróbuj wciągnąć swoją gałąź do gałęzi grupowej
 "git checkout GCL01"
 "git branch -l"
 "git merge MK284011" [Zdjecie 13]
 - Zaktualizuj sprawozdanie i zrzuty o ten krok i wyślij aktualizację do zdalnego źródła (na swojej gałęzi)
 - Oznacz tagiem ostatni commit i wypchnij go na zdalną gałąź
 - Ustal hook, który będzie sprawdzał, czy wiadomość z commitem zawiera nazwę przedmiotu
 - W jaki sposób stworzyć hook, który będzie ustawiał prefiks wiadomości commitu tak, by miał nazwę przedmiotu?

 7. Wystaw Pull Request do gałęzi grupowej

 8. Zgłoś zadanie (Teams assignment)