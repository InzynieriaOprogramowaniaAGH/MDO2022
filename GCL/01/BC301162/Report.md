# Metodyki DevOps, Zajecia 01, 6.11.2021, Bartlomiej Ciernia
 
## Wersja oprogramowania
Wszystkie czynności były wykonane na systemie Debian 11.
![0)Debian_Version](https://user-images.githubusercontent.com/61689132/140639887-a66acce0-de5b-4284-bbc1-660728881a90.png)
 
## Zadania do wykonania:
### 1. Zainstaluj klienta Git i obsługę kluczy SSH

Pierwszą czynnością jest zainstalowanie gita oraz obsługę kluczy SSH. Aby to wykonać, nalezy wprowadzić kilka komend w konsoli. 
Najpierw aktualizujemy wszystkie nowości komendą

sudo apt-get update

Następnie w celu zainstalowania gita wprowadzamy komendę

sudo apt-get install git-all

![1)Git install](https://user-images.githubusercontent.com/61689132/140640102-88b81918-f65c-432a-8ff2-9436586ab22f.png)

Po poprawnym zainstalowaniu i wywołaniu komendy

git --version

pojawi nam się aktualna wersja zainstalowanego oprogramowania

![1)Git version](https://user-images.githubusercontent.com/61689132/140640116-a6ddac91-d4d3-4feb-a76c-38e6a21cd606.png)

Kolejną czynnością jest zainstalowanie klienta SSH

sudo apt install openssh-client

Ja już miałem wcześniej zainstalowane to oprogramowanie dlatego w mojej konsoli pojawił się poniższy komunikat

![1)SSH install](https://user-images.githubusercontent.com/61689132/140640134-6f0cdcd5-b650-4b54-a485-258362fa13ed.png)

### 2. Sklonuj repozytorium https://github.com/InzynieriaOprogramowaniaAGH/MDO2022 za pomocą HTTPS

Aby wykonać kolejny krok, musimy zalogować się do naszego konta github, a następnie wejść w powyższy link. Pojawi nam się repozytorium.

![HTTPS](https://user-images.githubusercontent.com/61689132/140640335-bae6de54-b510-47ea-a367-de7867a8c94f.PNG)

W oknie repozytorium rozwijamy listę przyciskiem "Code", następnie wybieramy zakładkę "HTTPS", i kopiujemy link który pojawi nam się w oknie.

Następnie aby sklonować repozytorium, wracamy do naszej konsoli, przechodzimy do folderu w którym chcemy aby repozytorium nam się pojawiło komendą 

cd <nazwa_folderu>

a gdy już się w nim znajdziemy, do klonowania używamy poniższej komendy

git clone <link_HTTPS>

![2)Cloner repo to folder](https://user-images.githubusercontent.com/61689132/140640412-39ca37b1-2d3f-4bb0-bc72-b72254282133.png)

### 3. Upewnij się w kwestii dostępu do repozytorium jako uczestnik i sklonuj je za pomocą utworzonego klucza SSH
  #### - Utwórz dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem
  #### - Skonfiguruj klucz SSH jako metodę dostępu
  #### - Sklonuj repozytorium z wykorzystaniem protokołu SSH

Dzięki zainstalowanemu oprogramowaniu SSH, możemy utworzyć klucze dostępu. Aby to zrobić, pierwszy z nich tworzymy komendą

ssh-keygen -t ecdsa -C "nasz_email_połączony_z_gitem"

![3)SSH_key with password](https://user-images.githubusercontent.com/61689132/140640561-74d9e996-c31c-474a-9787-c7fbcb99a16c.png)

Przy tworzeniu, jeśli chcemy aby był zabezpieczony hasłem, podajemy je, w innym przypadku zostawiamy pole "passphrase" puste

Drugi klucz tworzymy komendą

ssh-keygen -t ed25519 -C "nasz_email_połączony_z_gitem"

![3)SSH_KEY_ED25519](https://user-images.githubusercontent.com/61689132/140640596-109863aa-4fb3-43dc-9a84-15b95d8c030c.png)

Po stworzeniu kluczy, musimy je ustawić jako metody dostępu w naszym gicie. Aby to zrobić, musimy przejść do:

Klikamy w nasz avatar w górnym prawym rogu -> Settings -> SSH and GPG keys -> New SSH key

W oknie które nam się pojawi, musimy ustawić nazwę naszego klucza, a w oknie pod nim wprowadzić sam klucz. Po poprawnym wykonaniu tej czynności, w zakładce "SSH and GPG keys" powinniśmy mieć poniższy rezultat:

![3)Add ssh keys on github profile](https://user-images.githubusercontent.com/61689132/140640692-77d538b1-e85b-4b3b-8d5c-63893cecc05a.png)

Po wykonaniu tych kroków, możemy przejść do klonowania repozytorium za pomocą SSH
Aby to zrobić, przechodzimy ponownie w link który mieliśmy przy drugim zadaniu, a następnie znowu rozwijamy listę przyciskiem "Code", w której wybieramy metodę SSH

![ssh](https://user-images.githubusercontent.com/61689132/140640744-39445407-d885-466f-9c92-b68e5757dec7.PNG)

Powtarzamy klonowanie, wybierając folder do którego chcemy sklonować nasze repozytorium, a następnie kopiujemy. 

cd <nazwa_folderu>

git clone <link_SSH>

Po poprawnym wykonaniu powyższych kroków, otrzymamy poniższy rezultat

![3)Clone repo by ssh](https://user-images.githubusercontent.com/61689132/140640812-5d672ff5-3d91-4bc6-a3f9-49d20f848c73.png)

### 4. Przełącz się na gałąź swojej grupy

Aby przejść na gałąź grupy do której zostaliśmy przydzieleni przez administratora, przechodzimy do sklonowanego repozytorium, a następnie korzystamy z poniższej komendy

git checkout <nazwa_branch'a>

![4)Change branch](https://user-images.githubusercontent.com/61689132/140640882-8f013d9f-3773-4aeb-b669-86c5d1e63d94.png)

### 5. Utwórz gałąź o nazwie "inicjały & nr indeksu" np. KD232144

Aby stworzyć swoją gałąź, korzystamy z poniższej komendy

git checkout -b <nazwa_stworzonego_przez_nas_branch'a>

![5)Create branch BC301162](https://user-images.githubusercontent.com/61689132/140640912-f9afe636-1cbd-4d52-aa3d-08c176ec0501.png)

### 6. Rozpocznij pracę na nowej gałęzi
  #### - W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu" np. ```KD232144```
  #### - W nowym katalogu dodaj plik ze sprawozdaniem
  #### - Dodaj zrzuty ekranu
  #### - Wyślij zmiany do zdalnego źródła
  #### - Spróbuj wciągnąć swoją gałąź do gałęzi grupowej
  #### - Zaktualizuj sprawozdanie i zrzuty o ten krok i wyślij aktualizację do zdalnego źródła (na swojej gałęzi)
  #### - Oznacz tagiem ostatni commit i wypchnij go na zdalną gałąź
  #### - Ustal hook, który będzie sprawdzał, czy wiadomość z commitem zawiera nazwę przedmiotu
  #### - W jaki sposób stworzyć hook, który będzie *ustawiał* prefiks wiadomości commitu tak, by miał nazwę przedmiotu?




