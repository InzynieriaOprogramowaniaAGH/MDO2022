# Lab03

## Kilka rad zanim zaczniesz
- Niniejsza instrukcja zaklada prace pod systemem Windows z zainstalowanym WSL2
- Aby korzystac z docker'a pod WSL2 nalezy zainstalowac aplikacje [Docker Desktop for Windows ](https://hub.docker.com/editions/community/docker-ce-desktop-windows)

## Kroki

### Łączność i woluminy na podstawie "złych" praktyk
1. Pobierz obraz Ubuntu [zrzut 00]
    - Uzyjemy do tego polecenie pull, ktore pobierze obraz z DockerHub'a
        - docker pull ubuntu
2. Podłącz wolumin do kontenera [zrzut 01]
    - Aby podlaczyc wolumin do kontenera uzyjemy flagi -v podczas uruchamiania
    - Podlaczymy katalog /home/seb/volume z hosta do katalogu /home/root w kontenerze
        - docker run -it -v /home/seb/volume:/home/root ubuntu
3. Skopiuj plik do katalogu woluminu, pokaż w kontenerze [zrzut 01]
    - W katalogu wolumenu na hoscie tworzymy plik
        - echo 'Hejka z hosta!' > /home/seb/volume/hello-from-host.txt
    - W kontenerze listujemy podmonotwany katalog 
        - ls -alh /home/root
4. Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście
    - W katalogu podmontowanym kontenera tworzymy plik
        - echo 'Hejka z kontenera!' > /home/root/hello-from-container.txt
    - Na hoscie listujemy katalog wolumenu
        - ls -alh /home/seb/volume

### "Kiepski pomysł": SSH
1. Uruchom i wyeksponuj wybrany port w kontenerze
    - Aby wyeksportowac port nalezy uzyc flagi -p lub --publish
    - Mozemy eksportowac kilka portow jednoczesnie
        - docker run -it -v /home/seb/volume:/home/root --publish 2222:22 ubuntu
2. Zainstaluj w kontenerze serwer ssh
    - Nalezy najpierw zaktualizowac system
        - apt update
    - Nastepnie menadzerem pakietow doinstalowac serwer ssh
        - apt install openssh-server
3. Zmień port na wybrany port > 1024
    - ???????????????????
4. Zezwól na logowanie root
    - Nalezy w tym celu zaktualizowac plik /etc/ssh/sshd_config
        - echo 'PermitRootLogin yes' >> /etc/ssh/sshd_config
    - Nastepnie zrestartowac serwis sshd lub opdalić daemon'a od nowa
        - systemctl restart sshd.service
        - /usr/sbin/sshd -D &
5. Umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
    - Na hoście kopiujemy klucz publiczny do katalogu wolumenu
        - cp ~/.ssh/id_rsa.pub /home/seb/volume
    - W kontenerze kopiujemy klucz ten publiczny do zaufanych kluczy
        - echo /home/root/id_rsa.pub > ~/.ssh/authorized_keys
6. Odnajdź adres IP kontenera w wewnętrznej sieci [zrzut 04]
    - Adres ip kontenera mozemy znalezc uzywajac polecenia inspect
        - docker inspect <container_id>
        - docker inspect d3f13 | grep IPAddress | cut -d '"' -f 4
7. Uruchom usługę, połącz się z kontenerem
    - Doinstalujemy systemctl aby sprawniej uruchomic usluge
        - apt install systemctl
    - Uruchomimy ja jak w normalnym ubuntu
        - systemctl start sshd.service
    - Z poziomu hosta podlaczymy sie po ssh do kontenera
        - ssh root@172.17.0.2

### Skonteneryzowany Jenkins stosujący Dockera
#### Przygotowanie
1. Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium [zrzut 06]
    - 

2. Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
Przygotuj obraz blueocean na podstawie obrazu jenkinsa
Uruchom blueocean
Zaloguj się i skonfiguruj Jenkins
    