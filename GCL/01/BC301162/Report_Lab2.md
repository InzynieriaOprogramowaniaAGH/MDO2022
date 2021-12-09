# Metodyki DevOps, Zajęcia 02, 21.11.2021, Bartłomiej Ciernia

# Zestawienie środowiska:
## 1. Zainstaluj Docker w systemie linuksowym

Instalacje dockera zaczynamy od aktualizacji pakietów apt oraz instalacji pakietów które umożliwiają apt korzystanie z repozytorium

 sudo apt-get update
 
 sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
    
Dodajemy oficjalny klucz GPG dockera

 curl -fsSL https://download.docker.com/linux/debian/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

I na koniec instalujemy go poleceniem
 
 sudo apt-get install docker.ce

Po poprawnym wykonaniu kroków, możemy sprawdzić jaką wersje dockera posiadamy poleceniem

 docker -v

![1](https://user-images.githubusercontent.com/61689132/142767355-c0d14633-b9c2-4c58-97b2-625619589212.png)

## 2. Zarejestruj się w Docker Hub i zapoznaj z sugerowanymi obrazami

Wchodzimy na stronę https://hub.docker.com/ a następnie zakładamy tam konto. Po poprawnym założeniu, możemy sprawdzić nasz profil klikając na nasz pseudonim w górnym prawym rogu strony.
![2](https://user-images.githubusercontent.com/61689132/142767632-08aebb5d-4318-4e7a-8b9f-7d4d45be4b5c.png)

## 3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql

Aby pobrać interesujące nas obrazy, korzystamy z komendy

 sudo docker pull <nazwa_obrazu>
 
 My korzystamy z poniższych poleceń
  
  sudo docker pull hello-world
  sudo docker pull busybox
  sudo docker pull ubuntu
  sudo docker pull mysql
![3 1](https://user-images.githubusercontent.com/61689132/142767701-6dddcfe4-72ee-4cce-bbf9-123e8178c2a2.png)
![3 2](https://user-images.githubusercontent.com/61689132/142767704-7337c503-5bd2-439e-ae49-34fcf4d1da80.png)

Możemy sprawdzić zainstalowane obrazy komendą

 sudo docker images
 
![3 3](https://user-images.githubusercontent.com/61689132/142767722-34343ec1-013a-4c4a-825a-adc3e0cc3498.png)

## 4. Uruchom busybox

Wybrany obraz uruchamiamy poleceniem

 sudo docker run <nazwa_obrazu>
 
![4 1](https://user-images.githubusercontent.com/61689132/142767771-17082645-1eaf-4d37-8b00-3155d021ad07.png)
![4 2](https://user-images.githubusercontent.com/61689132/142767774-5f6607fa-fa7b-41b2-a252-9b8d802438d1.png)

Aby sprawdzić uruchomione procesy korzystamy z komendy

sudo docker ps -a

![4 3](https://user-images.githubusercontent.com/61689132/142767848-6bdbb8a3-444b-4f4f-b65e-1ea37d62117d.png)

## 5. Uruchom "system w kontenerze"

Aby uruchomić ubuntu w kontenerze, korzystamy z poniższej komendy

sudo docker run -it ubuntu bash

Następnie aby sprawdzić listę procesów, w konsoli wpisujemy

 ps -aux
 
![5 1](https://user-images.githubusercontent.com/61689132/142767905-fdda71c3-512f-4df6-9966-c6a0af5566c4.png)
![5 2](https://user-images.githubusercontent.com/61689132/142767907-f5be1e4e-ff59-4fdf-8137-e11b30a92969.png)

Aby wyjść z konsoli wpisujemy polecenie

exit

## 6. Pokaż uruchomione kontenery, wyczyść je

Do pokazania uruchomionych kontenerów używamy komendy

sudo docker ps-a

![6 1](https://user-images.githubusercontent.com/61689132/142767969-d7c6412e-1a0b-411b-9167-4a5018d80afe.png)

Aby wyczyścić kontener korzystamy z polecenia

sudo docker rm <id_kontenera>

![6 2](https://user-images.githubusercontent.com/61689132/142767994-93c70411-cdfa-44b4-a6b1-966c1b8d95f5.png)

## 7. Wyczyść obrazy

Ponownie możemy podejrzeć nasze obrazy poleceniem

sudo docker images

![7 1](https://user-images.githubusercontent.com/61689132/142768020-a886e904-2d56-425b-a382-0af89aab9e9f.png)

A następnie w celu ich wyczyszczenia, korzystamy z polecenia

sudo docker rmi <nazwa_obrazu>

![7 2](https://user-images.githubusercontent.com/61689132/142768045-0c3e37b5-06eb-420b-81f2-71d361d83d91.png)
![7 3](https://user-images.githubusercontent.com/61689132/142768050-45ac6aa7-a221-4a2a-ba8d-5bf10c108aff.png)

Po wykonaniu czyszczenia, ponownie użyłem komendy wyświetlającej obrazy i jak widzimy, nie ma aktualnie żadnego.

# Budowanie programu
## 1. Znajdź projekt umożliwiający wywołanie testów jednostkowych

Projektem który wybrałem jest szkielet aplikacji w reakcie ze strony https://create-react-app.dev/

Do jego instalacji konieczne są pakiety nodejs oraz npm, które instalujemy za pomocą komendy

sudo apt install nodejs npm

Następnie tworzymy aplikację komendą 

npx create-react-app my-app

![8](https://user-images.githubusercontent.com/61689132/142768285-2a925c12-b751-4455-a4a4-d82d7bb3c6f5.png)

Po poprawnym wykonaniu tej operacji, możemy przejść do folderu z aplikacją i sprawdzić czy stworzyły nam się pliki

cd my-app
ls

![8 2](https://user-images.githubusercontent.com/61689132/142768268-1d28da08-b419-44bc-a460-08a74aaeaa1a.png)

## 2. Przeprowadź budowę/konfigurację środowiska

Następnie korzystamy poniższej komendy, aby uzyskać zoptymalizowaną wersje aplikacji

npm run build

![8 3](https://user-images.githubusercontent.com/61689132/142768370-035c4097-c970-4dae-bc41-a8688345d418.png)

## 3. Uruchom testy

A następnie aby odpalić testy, korzystamy z komendy 

npm run test

Poprawne testowanie, ukaże nam poniższy rezultat

![9](https://user-images.githubusercontent.com/61689132/142768394-d79527ad-d34b-48d5-95cb-2a4319a72cf3.png)

## 4. Ponów ten proces w kontenerze

Nowy kontener uruchamiamy korzystając z ubuntu. 

sudo docker run -dt --name ubuntu ubuntu

Skorzystałem z flagi -dt, aby kontener działał w tle. Bez tej flagi kontener po uruchomieniu dostanie status EXIT, gdyż nie będzie miał żadnych innych poleceń do wykonania

![10](https://user-images.githubusercontent.com/61689132/142768545-7df91596-6a13-4a95-bc1d-e55229380adc.png)

Wchodzimy do uruchomionego kontenera komendą

sudo docker exec -it <id_kontenera> bash

![11](https://user-images.githubusercontent.com/61689132/142768643-7f9ea6ae-52ae-4180-9679-9584a24d44c8.png)

Aktualizujemy i instalujemy odpowiednie oprogramowanie, wpisując w konsolę

apt-get update && apt-get upgrade && apt-get install nodejs npm

![12](https://user-images.githubusercontent.com/61689132/142768670-1cc0fa71-341d-4df3-85dc-1c05c226f39c.png)

Aby sklonować repozytorium do naszego kontenera, musimy z niego wyjść wpisując w konsolę polecenie

exit

A następnie kopiujemy repozytorium do miejsca docelowego

sudo docker cp <ścieżka_repo> <ścieżka_docelowa>

Po poprawnym wykonaniu operacji, wracamy z powrotem do kontenera komendą

sudo docker exec -it <id_kontenera> bash

Po wejściu, sprawdzamy czy w kontenerze pojawiło sie nasze repozytorium poniższymi komendami

ls     - Komenda do sprawdzenia plików w miejscu w którym jesteśmy
cd <ścieżka>     - Komenda pozwalające przejść do wybranego folderu

Poprawne wykonanie powyższych kroków, jest załączone na poniższym zrzucie ekranu

![13](https://user-images.githubusercontent.com/61689132/142768793-8234b9e4-858e-4e10-8fcb-9b8304b8fa8d.png)

Będąc w folderze z projektem, ponownie budujemy go tym razem w kontenerze

npm run build

![14](https://user-images.githubusercontent.com/61689132/142768811-49f6fa37-159d-4170-9781-bff381f36c24.png)

Oraz ponownie testujemy, tym razem w kontenerze, korzystając z polecenia

npm run test

![15](https://user-images.githubusercontent.com/61689132/142768834-732ce112-aa2a-41a4-808b-9b10a828fdc4.png)
