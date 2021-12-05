Imię i nazwisko: Julia Żychowska, kierunek: Informatyka Stosowana (NS), nr. indeksu: 307699, przedmiot: Metodyki DevOps, sprawozdanie: 03, grupa: GĆL03

Sprawozdanie

Łączność i woluminy na podstawie "złych" praktyk

  - Pobierz obraz Ubuntu
  
    Na początku pobrałam Ubuntu za pomocą polecenia: "docker pull ubuntu", chociaż miałam już wcześniej pobrany. 
    
    ![image](https://user-images.githubusercontent.com/28841971/144750679-57bf5fef-5c65-4ce6-9a0d-2219c6e903c3.png)
    
  - Podłącz wolumin do kontenera
  
    Stworzyłam wolumin poleceniem "sudo docker volume create wolumin" o nazwie "wolumin". Poleceniem "sudo docker volume ls" sprawdziłam czy rzeczywiście się utworzył.
    
    ![image](https://user-images.githubusercontent.com/28841971/144750741-0769efdb-c6a6-4ee7-98dd-e10e72dd1f3f.png)
    
    Następnie podłączyłam go do ubuntu poleceniem: "sudo docker run --interactive --tty --mount source=wolumin,destination=/store ubuntu".
    
    ![image](https://user-images.githubusercontent.com/28841971/144750820-be633b36-8392-4cff-9fcc-d2bad9b2ff10.png)

  - Skopiuj plik do katalogu woluminu, pokaż w kontenerze
  
    Na początku źle zrobiłam bo utworzony plik skopiowałam do samego wolumin, a powinnam do _ date, wyszło to przy robieniu kolejnego podpunktu. Dlatego mam pomieszaną kolejność. Poleceniem: "touch plik" utworzyłam plik, a poleceniem "sudo cp plik /var/lib/docker/volume/wolumin/_ date". 
    
    ![image](https://user-images.githubusercontent.com/28841971/144751084-df25e832-5dfc-4c6a-a5f4-51911cadabf8.png)
    
    Następnie wyświetliłam to w kontenerze, najpierw uruchomiłam kontener, umieściłam się w odpowiednim miejcu "cd /store" i wyświetliłam zawartość "ls -a". : 
    
    ![image](https://user-images.githubusercontent.com/28841971/144751107-00559958-a77f-43c0-b2b5-9a78f95d99a8.png)
    
  - Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście
  
    W uruchomionym kontenerze utworzyłam plik2 "touch /store/plik2
    
    ![image](https://user-images.githubusercontent.com/28841971/144751232-b9631411-0227-4265-9108-650307489d0d.png)
    
    Następnie sprawdziłam czy jest rzeczywiście utworzony. Wykorzystałam polecenie sudo mc -a i przeszłam do odpowiedniego katalogu.
    
    ![image](https://user-images.githubusercontent.com/28841971/144751257-3ea5f4f7-710c-41ad-9ee3-7cf880bf4192.png)
    
"Kiepski pomysł": SSH

  - Uruchom i wyeksponuj wybrany port w kontenerze

    Uruchomiłam kontener z portem 2048:20, poleceniem: "sudo docker run --interactive --tty --mount source=wolumin,destination=/store --publish 2048:20 ubuntu".

    ![image](https://user-images.githubusercontent.com/28841971/144752663-06142ce5-7404-4dfc-a6ea-6699467541fb.png)

  - Zainstaluj w kontenerze serwer ssh
  
    Najpierw zaaktualizowałam poleceniem "apt-get update"
    
    ![image](https://user-images.githubusercontent.com/28841971/144752671-d84b5a11-aa75-4481-b5e5-54328b6fcc75.png)
    
    a następnie zainstalowałam openssh-server, poleceniem: "apt-get install openssh-server"
    
    ![image](https://user-images.githubusercontent.com/28841971/144752802-8d9dd9a4-8103-46cf-b889-c96e4af9e4b7.png)
    ![image](https://user-images.githubusercontent.com/28841971/144752820-75146305-a6fb-40e9-83a8-e1bce5d8cc1a.png)
    
  - zmień port na wybrany port >1024
    
    Najpierw musiałam zainstalować poleceniem "apt-get install vim" program vim, który pozwali mi odtworzyć pliki. Następnie weszłam w plik sshd_config
    
    ![image](https://user-images.githubusercontent.com/28841971/144752975-01c66a35-a25c-4d46-b119-cae605314a54.png)
    ![image](https://user-images.githubusercontent.com/28841971/144752999-61cf5d05-b0e5-4590-8eeb-db4d3d19bc88.png)

    i edytowałam port z 22, na 2048:20
    
    ![image](https://user-images.githubusercontent.com/28841971/144753012-07d74c12-a62e-4b80-8523-833a611be7a8.png)

  - zezwól na logowanie root
  
    Używając tego samego polecenia co przy zmianie portu zezwalam na logowanie root

    ![image](https://user-images.githubusercontent.com/28841971/144753449-046a6c7e-443a-4fa8-b7ef-22b65eb2836f.png)

    przy PermitRootLogin usuwam prohibit-password na yes
    
    ![image](https://user-images.githubusercontent.com/28841971/144753481-75409d2e-84d5-4fd8-9719-4b2e54d40ec5.png)

  - umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
  
    
  - odnajdź adres IP kontenera w wewnętrznej sieci
  - uruchom usługę, połącz się z kontenerem

Skonteneryzowany Jenkins stosujący Dockera

Przygotowanie

  - Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
  - Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
    - Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
    - Przygotuj obraz blueocean na podstawie obrazu jenkinsa
    - Uruchom blueocean
    - Zaloguj się i skonfiguruj Jenkins

Mikro-projekt Jenkins

  - Utwórz projekt, który wyświetla uname
  - Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta
  - Utwórz "prawdziwy" projekt, który:
    - klonuje nasze repozytorium
    - przechodzi na osobistą gałąź
    - buduje obrazy z dockerfiles i/lub komponuje via docker-compose

Sprawozdanie
  - Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
    - Wymagania wstępne środowiska
    - Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
    - Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
