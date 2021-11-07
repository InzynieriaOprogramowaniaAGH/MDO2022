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

