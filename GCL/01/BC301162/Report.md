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
  ### - Utwórz dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem
  ### - Skonfiguruj klucz SSH jako metodę dostępu
  ### - Sklonuj repozytorium z wykorzystaniem protokołu SSH

