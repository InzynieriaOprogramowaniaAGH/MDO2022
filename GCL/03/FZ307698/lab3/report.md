# Zajęcia 03

# Przebieg ćwiczenia
### Łączność i woluminy na podstawie "złych" praktyk
##### Ćwiczenie zostało przeprowadzone na systemie Windows z wykorzytsaniem WSL2
1. Należy pobrać obraz Ubuntu   `docker pull ubuntu`
2. Podłącz wolumin do kontenera 
    Do podłączenia woluminu do kontenera należy użyć flagi -v w czasie uruchamiania. (Należy sprawdzić nazwę katalogu użytkownika. W moim przypadku jest to filip)
    Dodatkowo podłączymy katalog `/home/filip/volume` z hosta do katalogu `/home/root` 
    Żeby to zrobić należy wykonać polecenie: `docker run -it -v /home/filip/volume:/home/root ubuntu`
3. Skopiuj plik do katalogu woluminu, pokaż w kontenerze 
    Należy przejść do katalogu wolumenu na hoscie i utworzyć plik txt `echo 'bleblelbe HOSTA' > /home/filip/volume/nazwa-pliku-z-hosta.txt`
4. Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście
   Należy przejść do katalogu kontenera i utworzyć plik txt `echo 'blalbalba CONTENERA' > /home/root/nazwa-pliku-z-kontenera.txt`

[screenshots/01-pull-ubuntu.png]
[screenshots/02-attach-volume.png]

### "Kiepski pomysł": SSH
1. Uruchom i wyeksponuj wybrany port w kontenerze
    Do wyeksportowania portu należy użyć polecenia z flagą: `docker run -it -v /home/seb/volume:/home/root --publish 8080:8080 ubuntu`

2. Zainstaluj w kontenerze serwer ssh
    Aktualizujemy system `apt update` dalej wykorzystując menadżera pakietów należy doinstalować serwer ssh `apt install openssh-server`.
    Ponieważ na prezentowanym systemie nie istnieje systemctl, będzie trzeba uruchomić daemona sshd ręcznie [zrzut 03] `/usr/sbin/sshd -D &`
3. Zmień port na wybrany port > 1024
    Zmienic linie z Portem w pliku sshd_config [zrzut 04]. Możemy do tego wykorzystać np. edytor nano. `nano /etc/ssh/sshd_config`
4. Zezwól na logowanie root
    W tym celu edytujemy ponownie plik /etc/ssh/sshd_config `echo 'PermitRootLogin yes' >> /etc/ssh/sshd_config`
    Teraz musimy uruchomić daemona od nowa `/usr/sbin/sshd -D &`
5. Umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
    Żeby to zrobić, trzeba skopiować na hoście klucz publiczny do katalogu wolumenu `cp /home/root/id_ed25519.pub /root/.ssh/authorized_keys`
    W kontenerze kopiujemy klucz ten publiczny do zaufanych kluczy `cat /home/root/id_ed25519.pub > /root/.ssh/authorized_keys`
6. Odnajdź adres IP kontenera w wewnętrznej sieci [zrzut 05]
    W tym celu wykorzystamy polecenie: `ip addr`
        
7. Uruchom usługę, połącz się z kontenerem
    W tym celu wykorzystamy polecenie: `/usr/sbin/sshd -D &`
    Z poziomu hosta podlaczymy sie po ssh do kontenera [zrzut 05] `ssh root@172.17.6.154 -p 8080`

[screenshots/03-expose-ports.png]
[screenshots/04-manualy-run-deamon.png]
[screenshots/05-ssh-port-from-22-to-8080.png]
[screenshots/06-add-sshkey-to-trusted-keys.png]
[screenshots/07-connect-with-ssh.png]

### Skonteneryzowany Jenkins stosujący Dockera
#### Przygotowanie
1. Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
    W folderze z naszym projektem MDO2022 wykonujemy polecenie `git pull origin FZ307698` aby mieć pewność, że pracujemy z aktualnym repozytorium.

2. Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
    Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
    
        ```shell
        docker run \
            --name jenkins-docker \
            --rm \
            --detach \
            --privileged \
            --network jenkins \
            --network-alias docker \
            --env DOCKER_TLS_CERTDIR=/certs \
            --volume jenkins-docker-certs:/certs/client \
            --volume jenkins-data:/var/jenkins_home \
            --publish 2376:2376 \
            docker:dind \
            --storage-driver overlay2
        ```
    Przygotuj obraz blueocean na podstawie obrazu jenkinsa `Dockerfile-lab3`
        
            ```
            FROM jenkins/jenkins:2.319.1-jdk11
            USER root
            RUN apt-get update && apt-get install -y lsb-release
            RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
            https://download.docker.com/linux/debian/gpg
            RUN echo "deb [arch=$(dpkg --print-architecture) \
            signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
            https://download.docker.com/linux/debian \
            $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
            RUN apt-get update && apt-get install -y docker-ce-cli
            USER jenkins
            RUN jenkins-plugin-cli --plugins "blueocean:1.25.1 docker-workflow:1.26"
            ```
            
    Do zbudowania wykorzystamy polecenie `build` : `docker build -f 03_Dockerfile -t myjenkins-blueocean:1.1 .`
        
    Uruchom blueocean - W tym celu wykorzystamy gotowe polecenia
        
            ```shell
            docker run \
                --name jenkins-blueocean \
                --rm \
                --detach \
                --network jenkins \
                --env DOCKER_HOST=tcp://docker:2376 \
                --env DOCKER_CERT_PATH=/certs/client \
                --env DOCKER_TLS_VERIFY=1 \
                --publish 8080:8080 \
                --publish 50000:50000 \
                --volume jenkins-data:/var/jenkins_home \
                --volume jenkins-docker-certs:/certs/client:ro \
                myjenkins-blueocean:1.1
            ```
    Sprawdzamy wyniki `docker ps`
    Zaloguj się i skonfiguruj Jenkins 
    W adresie przeglądarki przejdź do http://localhost:8080 
        Hasło do Jenkinsa można znaleść w logach kontenera `docker logs <container_id>`
        Jenkins poprosi o stworzenie konta admina i wybor wtyczek [Można przeklikać]
    
    [screenshots/08-docker-network-docker-run.png]
    [screenshots/09-create-new-dockerfile-blueocean-jenkins.png]
    [screenshots/10-build-jenkins-image.png]
    [screenshots/10-run-blueocean.png]
    [screenshots/11-find-jenkins-password.png]
    [screenshots/12-run-and-configure-jenkins.png]
    [screenshots/13-running-jenkins.png]
    
    ### Mikro-projekt Jenkins

    1. Utwórz projekt, który wyświetla uname
        Należy wybrać Nowy Projekt i nadać mu jakąś nazwę. W zakłądce "Budowanie" dodaj krok "Uruchom powłokę" i wpisz polecenie [zrzut 12] `uname -a`
        Wybierz "Uruchom", żeby uruchomić build. Nastepnie sprawdź w logach konsoli wynik działania builda [zrzut 13]
        
    2. Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 
        Tworzymy projekt analogicznie jak uprzednio. W polu "Uruchom powłokę" należy wpisać skrypt [zrzut 14]

        ```bash
        hour=$(date +"%H")

        if [ $((hour)) -eq 0 ]; 
        then
            echo "I would like to say it's EVEN HOUR"
            exit 0
        else
            echo "I would like to say it's ODD HOUR"
            exit 1
        fi
        ```
        Sprawdzamy wynik
        
        [screenshots/14-create-project.png]
        [screenshots/15-unamea.png]
        [screenshots/16-success.png]
        [screenshots/17-success-logs.png]
        [screenshots/26-script-hour.png]
        [screenshots/18-odd-hour.png]
        
    3. Buduje obrazy z dockerfiles i/lub komponuje via docker-compose
        W pierwszej kolejności trzeba podłączyć się do kontenera jenkinsa i zainstalować w nim docker-compose
        
                ```
                docker exec -it -u root <container_id> /bin/bash
                curl -L "https://github.com/docker/compose/releases/download/1.26.0/docker-compose-$(uname -s)-$(uname -m)"  -o /usr/local/bin/docker-compose
                mv /usr/local/bin/docker-compose /usr/bin/docker-compose
                chmod +x /usr/bin/docker-compose
                ```
        Następnie instalujemy wtyczkę do Docker Compose w Jenkinsie:
        Ekran Główny >> Zarządzaj Jenkinsem >> Zarządzaj wtyczkami >> Dostępne >> Docker Compose Build Step
        W kolejnym kroku trzeba utworzyć nowy projekt i nadać mu nazwę np. nazwą brancha. W tym wypadku jest to `FZ307698`
            - Sekcja 'Repozytorium kodu' dodaj adres URL do repo [zrzut 18]
            - Sekcja 'Budowanie' wybieramy naszą wtyczkę i wpisujemy ścieżke do pliku z docker-compose.yaml [zrzut 19]
        Uruchom projekt
        Sprawdź wynik w logach
        
        [screenshots/19-new-project.png]
        [screenshots/20-project-url.png]
        [screenshots/21-set-branch.png]
        [screenshots/22-add-docker-compose-plugin.png]
        [screenshots/23-add-build-step-docker-compose.png]
        [screenshots/25-result.png]