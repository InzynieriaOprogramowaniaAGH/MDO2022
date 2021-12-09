# Metodyki DevOps, Zajęcia 03, 9.12.2021, Bartłomiej Ciernia

## Łączność i woluminy na podstawie "złych" praktyk

Pobieramy obraz ubuntu za pomocą komendy

`docker pull ubuntu`

![1](https://user-images.githubusercontent.com/61689132/145458744-390e02ac-3f88-4daf-b4b9-7fa0f6431c1a.png)

Następnie, aby podłączyć wolumin do kontenera, musimy stworzyć wolumin korzystając z komendy 

`docker volume create <nazwa_woluminu>`

![2](https://user-images.githubusercontent.com/61689132/145459207-9e26ea9c-0538-4e87-9cd4-b963650a8490.png)

Możemy podglądnąć nasz wolumin poniższą komendą

`docker volume inspect <nazwa_woluminu>`

![3](https://user-images.githubusercontent.com/61689132/145460029-20a3e3ee-f81e-490d-bfa7-9b65e70beefa.png)

Tworzymy w folderze "Mountpoint" testowy plik

`touch <nazwa_pliku>`

![4](https://user-images.githubusercontent.com/61689132/145460082-ddc59757-3938-49cd-bef3-abeef63be385.png)

I podłączamy kontener komendą 

`docker run -it -v <nazwa_woluminu>:<nazwa_miejsca_w_kontenerze> <obraz>`

A po tym tworzymy ponownie testowy plik, aby sprawdzić czy poprzedni pojawił nam się w kontenerze
  
![5](https://user-images.githubusercontent.com/61689132/145460110-c0a79883-4a25-4b5a-afd7-627699892899.png)

## "Kiepski pomysł": SSH
  
Uruchomienie i wyeksponowanie portu w kontenerze, robimy za pomocą komendy
  
`docker run --interactive --tty -p <numer portu> --mount source=<nazwa_woluminu>,target=<nazwa_miejsca_w_kontenerze> <obraz>`

![6](https://user-images.githubusercontent.com/61689132/145460743-024c2031-9543-4d19-9b94-7d2accdc2b78.png)

Aby wyjść z działającego kontenera bez wyłączania go, wciskamy kombinację klawiszy
  
`CTRL + P + Q`

A gdy chcemy wrócić do kontenera, korzystamy z komendy
  
`docker exec it <numer_kontenera> bash`
 
Jak widzimy, kontener jest odpalony

![7](https://user-images.githubusercontent.com/61689132/145460827-b5e082f3-08bf-4e77-9443-5fadf979cc19.png)

Instalujemy ssh w kontenerze
  
`apt-get install openssh-server`

![8](https://user-images.githubusercontent.com/61689132/145461358-926e5f3d-2b68-4de6-81ff-671d543d43b3.png)

Po poprawnym zainstalowaniu ssh, wchodzimy znajdujemy plik sshd_config, w moim przypadku była to poniższa ścieżka

`cd /etc/ssh/sshd_config`

I edytujemy go, w moim przypadku była to edycja z pomocą edytora nano który zainstalowałem komendą

`apt-get install nano`
  
W celu edycji, wpisujemy poniższą komendę  
  
`nano sshd_config` 

W pliku zmieniamy numer portu

![9](https://user-images.githubusercontent.com/61689132/145461627-e59dc020-7dbd-40cd-bc20-91bd8d2e405e.png)

Oraz zezwalamy na logowanie roota, które znajduje się w tym samym pliku

![10](https://user-images.githubusercontent.com/61689132/145461713-b18ed7b6-45ae-4607-8f36-d5cfeb8dc8fa.png)











