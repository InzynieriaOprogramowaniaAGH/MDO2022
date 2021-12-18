

## Sprawozdanie Metodtki DevOps
Mateusz Rudziński
Laboratoria 3

 - Pobierz obraz ubuntu
 Na moim urządzeniu posiadałem już wcześniej ściągnięty obraz Ubuntu
 
	screen obraz_ubuntu
	
 - Tworzenie woluminu oraz podłączanie do niego kontenera 
Wolumin tworzymy przy pomocy polecenia 
`docker volume create "nazwa woluminu"`

screen docker_volume_ls

- Podłączenie woluminu do kontenera wraz z tworzeniem nowego pliku wewnątrz woluminu z poziomu kontenera

Widoczność pliku na hoście

screen file_in_volume_host_container

Tworzenie pliku z poziomu hosta i widoczność w kontenerze

screen host_volume_file.png

- SSH
Uruchomienie i wyeksponowanie wybranego portu w kontenerze

screen docker_publish_ssh_port

screen active_container_ssh

W kontenerze instalujemy SSH

screen ssh_version

screen net_tools_version

Wydruk z netstat po stronie kontenera 

screen container_sshd

Adres IP kontenera

screen container_ip

Kopiowanie klucza publicznego do wolumenu

screen ssh_key

screen ssh_key_copy_to_volume

screen copy_sshkey_container

Łączenie się z kontenerem 

screen connecting ssh
