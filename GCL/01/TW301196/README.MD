# Sprawozdanie Metodyki DevOps

## Środowisko wykonywania zadań

Środowiskiem na którym wykonywane są zadania jest system Fedora z rodziny Linux. 
**Fedora 35 Workstation**

## Instalacja git

Instalacja odbywa się wykonaniem następującej komendy w terminalu fedory:

`sudo yum install git`

aby upewnić się, że git został zainstalowany poprawnie oraz sprawdzić jego wersję można wykorzystać komendę:

`git --version`

![Zrzut ekranu - 1](1.jpg)

![Zrzut ekranu - 1_1](1_1.jpg)

Do generowania kluczy ssh wykorzystam narzędzie **ssh-keygen** które jest juz dostępne w fedorze.

## Klonowanie repozytorium

Do sklonowania repozytorium użyłem komendy:

`git clone https://github.com/InzynieriaOprogramowaniaAGH/MDO2022.git`

![Zrzut ekranu - 2](2.jpg)

## Klucze SSH

Do wygenerowania kluczy SSH użyłem następujących poleceń:

`ssh-keygen -t ecdsa` 

![Zrzut ekranu - 3_1](3_1.jpg)

`ssh-keygen -t ed25519`

![Zrzut ekranu - 3_2](3_2.jpg)

Podczas tworzenia klucza istnieje możliwość wskazania miejsca gdzie chcemy zapisać klucz oraz możliwość ustawienia hasła.

Aby odczytać klucz należy przejść do wskazanego katalogu i za pomocą edytora tekstu odczytać klucz.
Na stronie github w ustawieniach konta można dodać utworzone klucze.
W ustawieniach należy przejść do zakładki **SSH and GPG keys** a następnie buttonem **New SSH key** dodajemy klucz.

![Zrzut ekranu - 3_7](3_7.jpg)

W polu title podaje się tytuł klucza, następnie w polu Key wprowadza się klucz SSH.

![Zrzut ekranu - 3_8](3_8.jpg)

## Klonowanie repozytorum za pomocą SSH

Teraz mogę sklonować repozytorium za pomocą SSH.
Adres można uzyskać ze strony repozytorium

![Zrzut ekranu - 3_4](3_4.jpg)

Klonowanie wykonałem za pomocą komendy:

`git clone git@github.com:InzynieriaOprogramowaniaAGH/MDO2022.git`

![Zrzut ekranu - 3_6](3_6.jpg)

Podczas tworzenia na jednym z kluczy ustawiłem hasło, dlatego podczas klonowania zostałem poproszony o wpisanie hasła

![Zrzut ekranu - 3_5](3_5.jpg)

## Branche

Po wejściu w repozytorium przełączyłem się na branch swojej grupy komendą:

`git checkout GCL01`

![Zrzut ekranu - 4](4.jpg)

Tworząc nowy branch użyłem komendy: 

`git checkout -b TW301196`

![Zrzut ekranu - 5](5.jpg)

Polecenem `cd` przeszedłem do folderu GCL/01 i komendą `mkdir` utworzyłem tam nowy katalog

![Zrzut ekranu - 6_1](6_1.jpg)

Poleceniem `touch` utworzyłem plik README.MD

![Zrzut ekranu - 6_2](6_2.jpg)

Przy pomocy eksploratora plików w fedorze przeniosłem screeny do folderu w repozytorium lokalnym

![Zrzut ekranu - 6_3](6_3.jpg)

Następnie użyłem komend `git add *.*` aby git zaczął śledzić te wszystkie pliki oraz `git status` aby sprawdzić wszystkie zmiany

![Zrzut ekranu - 6_4](6_4.jpg)

Aby dodać commit wykorzystałem komendę 
`git commit -m "Added readme.md file and screenshots"`

![Zrzut ekranu - 6_5](6_5.jpg)

Do wypchnięcia zmian na zdalne repozytorium użyłem polecenia
`git push --set-upstream origin TW301196`

![Zrzut ekranu - 6_6](6_6.jpg)

Po zakończeniu operacji wszystkie pliki są już na zdalnym repozytorium na GitHub

![Zrzut ekranu - 6_6](6_7.jpg)

Podczas próby wciągania utworzonego przeze mnie brancha na branch grupowy komendą `git merge TW301196` otrzymałem następujący błąd:
 
 ![Zrzut ekranu - 6_8](6_8.jpg)
Jest to spowodowane tym, że wszystkie próby mergowania się z gałęzią grupową muszą być zatwierdzone, a więc w tym przypadku jedyną drogą mergowania jest wystawienie Pull Request'a.

Po dokonaniu zmian sprawdzam wszystko komendą `git status`

 ![Zrzut ekranu - 6_9](6_9.jpg)

Następnie tworze nowego commita taką samą procedurą jak w przypadku pierwszego.

Do otagowania commita wykorzystałem komendę `git tag v2.1`

 ![Zrzut ekranu - 6_10](6_10.jpg)

 Wysyłanie commita z tagiem na repozytorium zdalne odbywa się komendą `git push origin --tags`

![Zrzut ekranu - 6_11](6_11.jpg)

## Hooks

Aby utworzyć hook, który będzie sprawdzał czy wiadomość z commitem zawiera nazwę przedmiotu należy przejść w głownym folderze repozytorium do ukrytego katalogu **.git** a następnie do katalogu **hooks**.
Należy utworzyć plik o nazwie **commit-msg** i nadać mu uprawnienia do wykonywania.

![Zrzut ekranu - 6_12](6_12.jpg)

Następnie z użyciem edytora tekstu (w moim przypadku nano) należy napisać odpowiedni skrypt sprawdzający czy wiadomość zawiera odpowiedni ciąg znaków

![Zrzut ekranu - 6_13](6_13.jpg)

Po próbie stworzenia commitu dostaję wiadomość **Commit message is missing prefix**

![Zrzut ekranu - 6_14](6_14.jpg)

Aby stworzyć hook ustawiający prefiks wiadomości commitu można odpowiednio zmodyfikować napisany wcześniej skrypt:

![Zrzut ekranu - 6_15](6_15.jpg)

## Pull request

Aby stworzyć nowego pull request'a na stronie GitHub przechodzi się do zakładki **Pull request** a następnie klika się w button **Create pull request**.

![Zrzut ekranu - 6_17](6_17.jpg)

Kolejnym krokiem jest wybranie odpowiednich branchy i zatwierdzenie.

![Zrzut ekranu - 6_18](6_18.jpg)