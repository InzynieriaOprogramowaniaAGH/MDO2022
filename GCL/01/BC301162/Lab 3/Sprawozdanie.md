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

Do wolumenu kopiujemy klucze publiczne

`cp <nazwa_pliku> <miejsce_do_którego_kopiujemy>`

![11 1](https://user-images.githubusercontent.com/61689132/145464123-50fd34d8-4bb8-4359-aca3-519b9626a42e.png)
![11 2](https://user-images.githubusercontent.com/61689132/145464126-277dc44d-fcc0-48c6-88df-707ed89c7dca.png)

A następnie w kontenerze tworzymy folder i w nim wklejamy klucz

`mkdir <ścieżka_do_folderu>`

Klucz można wkleić na dwa sposoby

Pierwszy -> `cp <plik_z_kluczem> <miejsce_do_którego_go_wklejamy>`
Drugi -> `cat <plik_z_kluczem> >> <miejsce_do_którego_go_wklejamy>`

![12 1](https://user-images.githubusercontent.com/61689132/145464424-89de9832-43f5-47a3-b7a9-6ab6db7f3459.png)
![12 2](https://user-images.githubusercontent.com/61689132/145464437-81e5d962-64ac-4d5d-8207-f54e1fe4d725.png)

Po przeklejeniu kluczy, uruchamiamy usługę ssh w kontenerze

`service ssh start`

![13](https://user-images.githubusercontent.com/61689132/145464508-f01aa40f-7805-4ff4-9496-468bdc1e6475.png)

Możemy sprawdzić status usługi korzystając z komendy

`service ssh status`

Nie mamy zainstalowanej paczki która umożliwia użycie komendy `netstat`? Nic prostszego! Wystarczy wcześniej ją zainstalować

`apt-get install net-tools`

Po odpaleniu, sprawdzamy sobie adres IP naszego kontenera komendą

`netstat -tunpa4`

![14](https://user-images.githubusercontent.com/61689132/145464820-7ac35e4b-d588-4b3c-8dd1-66cf07a16154.png)

Wychodzimy sobie z kontenera i łączymy sie komendą 

`ssh root@<adres_ip_kontenera> -p <wcześniej_ustawiony_port>`

![15](https://user-images.githubusercontent.com/61689132/145464977-3e2090ef-0e94-405c-88ac-23dbc1eb4199.png)



