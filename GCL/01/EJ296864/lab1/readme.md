# Metodyki DevOps - lab01

## Środowisko

Ćwiczenia labolatoryjne zostały wykonane na laptopie z macOS Big Sur 11.6

![Zdjecie srodowiska](screenshots/macos-big-siur.png)

## Instalacja klienta Git

Git został uprzednio zainstalowany, można go znaleźć w katalogu:

![Zdjecie gdzie jest git](screenshots/which-git.png)

Być może został on zainstalowany przeze mnie przy użyciu **brew install git**, ale nie pamiętam tego.

## Obsługa kluczy SSH

Na urządzeniu, z którego korzystam klient SSH był również zainstalowany, można go znaleźć w katalogu:

![Zdjecie gdzie jest ssh](screenshots/which-ssh.png)

## Klonowanie repozytorium za pomocą HTTPS

Klonowanie repozytorium odbywa się za pomocą komendy **git clone**. Po komendzie należy umieścić adres repozytorium, który znajdziemy wchodząc w to repozytorium i klikając poniższe:

![Zdjecie git clone https](screenshots/git-clone-https.png)

Po sklonowaniu, pliki zapiszą się w folderze, który aktualnie był wybrany:

![Zdjecie z terminala git clone](screenshots/git-clone-https-terminal.png)

## Klonowanie repozytorium za pomocą klucza SSH

### Generowanie kluczy SSH

W celu zaoszczędzenia kilku godzin, na wstępie warto wspomnieć, że w przypadku wybrania innej nazwy kluczy niż defaultowa, należy umieścić poniższe linijki w pliku **~/.ssh/config**:

Host github.com
    HostName github.com
    IdentityFile ~/.ssh/id_rsa_github


 W przypadku braku powyższego autentykacja się nie powiedzie.

Generowanie kluczy SSH odbywa się przy użyciu komendy **ssh-keygen**.

Pierwszy klucz jest generowany z pomocą algorytmu ECDSA przy użyciu komendy **ssh-keygen -t ecdsa-sha2-nistp256**. 
Po wpisaniu komendy jesteśmy proszeni o podanie, gdzie ma znajdować się wygenerowany klucz, jeśli nie podamy ścieżki, zostanie wybrana domyślna, podana w nawiasie. Opcjonalnie możemy podać hasło

![Zdjecie generowania klucza ecdsa-sha2-nistp256](screenshots/ssh-keygen-ecdsa-sha2-nistp256.png)

Drugi klucz generujemy z pomocą algorytmu ED25519 przy użyciu komendy **ssh-keygen -t ed25519**

![Zdjecie generowania klucza ed25519](screenshots/ssh-keygen-ed25519.png)

U mnie klucze znajdują się pod ścieżką **/Users/eryk.jalowiec/.ssh/**:

![Zdjecie kluczy ssh](screenshots/ssh-keys.png)

### Konfiguracja kluczy SSH na koncie Github

W celu konfiguracji kluczy SSH dla danego konta należy udać się pod adres **https://github.com/settings/keys**.

Po kliknięciu **New SSH Key**:

![Zdjecie gdzie dodac klucz](screenshots/ssh-key-add.png)

 Zostaniemy przeniesieni do strony, gdzie będziemy mogli dodać nasze wygenerowane klucze publiczne:

![Zdjecie dodawania klucza ecdsa](screenshots/ssh-ecdsa-key-add.png)

W taki sam sposób dodajemy drugi klucz:

![Zdjecie dodawania klucza ed25519](screenshots/ssh-ed25519-key-add.png)

### Klonowanie repozytorium za pomocą SSH

Tak samo jak poprzednio, udajemy się na stronę z repozytorium, a następnie wybieramy metodę SSH, zamiast HTTPS:

![Zdjecie git clone ssh](screenshots/git-clone-ssh.png)

Jeżeli do wygenerowanego klucza SSH było podane hasło, trzeba będzie je podać przy klonowaniu.

Po sklonowaniu, pliki zapiszą się w folderze, który aktualnie był wybrany, tak samo jak w przypadku klonowania przez HTTPS:

![Zdjecie z terminala git clone](screenshots/git-clone-ssh-terminal.png)

## Przełączanie się na gałąź mojej grupy

Zmieniać gałęzie możemy dzięki poleceniu **git checkout**

Przełączam się na gałąź mojej grupy:

![Zdjecie git checkout](screenshots/git-checkout-gcl01.png)

## Tworzenie nowej gałęzi

Nową gałąź tworzymy przy pomocy polecenia **git branch <nazwa_galezi>**, ale wtedy nie zostaniemy przeniesieni na nią, co jest bez sensu, gdyż tworząc nową gałąź, w zwyczaju mamy od razu na niej pracować. W związku z tym stosujemy polecenie **git checkout -b <nazwa_galezi>**

![Zdjecie git checkout EJ296864](screenshots/git-checkout-b-EJ296864.png)

W ten sposób z gałęzi **GCL01** powstała kopia mojej gałęzi o nazwie **EJ296864**

## Wysyłanie zmian do zdalnego źródła

W celu wysłania zmian na **origin**, najpierw musimy dodać pliki do stage'a, przy użyciu komendy **git add .**:

![Zdjecie git add](git-add.png)

Następnie robimy commit z plikami, które właśnie dodaliśmy do stage'a, przy użyciu komendy **git commit -m "komentarz"**:

![Zdjecie git commit](git-commit.png)

Na samym końcu pozostaje nam wypchnąć zmiany lokalne do origina przy użyciu **git push**:

![Zdjecie git push -u origin EJ296864](git-push-u-origin-EJ296864.png)

## Próba wciągnięcia mojej gałęzi do gałęzi grupowej

W tym celu przechodzę na gałąź, która ma mieć moje zmiany po merge'u, czyli **GCL01**, przy użyciu **git checkout GCL01**.
Następnie używam polecenia **git merge <nazwa_galezi>**:

![Zdjecie git merge EJ296864](git-merge-EJ296864.png)

O ile lokalnie to się udaje bez problemu, o tyle zdalnie nie ma takiej, opcji, gdyż w ustawieniach repozytorium jest właśnie tak ustawione:

![Zdjecie git push failed](git-push-failed.png)

## Oznaczenie tagiem i wypchnięcie taga na zdalną gałąź

W Gicie są 2 rodzaje tagów, zwykłe (lightweight) oraz annotated. W zadaniu użyję annotated. Tworzy się je przy pomocy komendy:

**git tag nazwa_taga <IDcommita> -a -m 'dodatkowa_uwaga'**

Jeśli nie dodamy IDcommita, to domyślnie zostanie użyty ten, na którym jesteśmy.

Tak więc też robię:
![Zdjecie git tag push --tags](git-tag-push-tags.png)

**git push --tags** - wysyłamy zmiany włączając w to również tagi. Wysyła wszystkie tagi, więc jeśli mamy jakieś tymczasowe, to średnio.
