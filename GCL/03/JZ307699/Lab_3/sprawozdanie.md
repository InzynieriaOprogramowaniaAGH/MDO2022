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
    
    Najpierw musiałam zainstalować poleceniem "apt-get install vim" program vim, który pozwali mi odtworzyć pliki. Następnie weszłam w plik sshd_config.
    
    ![image](https://user-images.githubusercontent.com/28841971/144752975-01c66a35-a25c-4d46-b119-cae605314a54.png)
    ![image](https://user-images.githubusercontent.com/28841971/144752999-61cf5d05-b0e5-4590-8eeb-db4d3d19bc88.png)

    i edytowałam port z 22, na 20
    
    ![image](https://user-images.githubusercontent.com/28841971/144757288-853c9671-c031-4445-804f-43876ab798dd.png)

    Także powtórzyłam te czynności po prostu w terminalu. 

    ![image](https://user-images.githubusercontent.com/28841971/144757255-1bff6ee5-d0e6-45cf-ae96-ca096318827b.png)


  - zezwól na logowanie root
  
    Używając tego samego polecenia co przy zmianie portu zezwalam na logowanie root.

    ![image](https://user-images.githubusercontent.com/28841971/144753449-046a6c7e-443a-4fa8-b7ef-22b65eb2836f.png)

    przy PermitRootLogin usuwam prohibit-password na yes
    
    ![image](https://user-images.githubusercontent.com/28841971/144757285-8f306da6-9fa6-41d6-9f1e-cdaaeeb688ce.png)
    
    Tak samo tutaj powtórzyłam zmiane ogólnie w terminalu.
    
    ![image](https://user-images.githubusercontent.com/28841971/144757257-7834016c-bbfc-48c1-a8ca-1c12643c3510.png)

  - umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
  
   Stworzyłam klucz rsa w katalogu .ssh (wejście do katalogu "cd ~/.ssh"), poleceniem: "ssh-keygen -t rsa" 

   ![image](https://user-images.githubusercontent.com/28841971/144757351-62f69f86-d910-49d5-bd2f-1583be578a41.png)
   
   i klucz publiczny umieściłam w woluminie. (na screenie przeniesiony nie z .ssh ze względu, że przypadkiem przeniosłam w inne miejsce zapominając o screenie)
   
   ![image](https://user-images.githubusercontent.com/28841971/144757567-cff70d83-4123-4c48-a07c-7a9cf42fd96f.png)
   
   ![image](https://user-images.githubusercontent.com/28841971/144757572-5ef5ba91-c44c-4c64-a328-47df8208bc90.png)
   
   ![image](https://user-images.githubusercontent.com/28841971/144757546-cb2980f4-e8f4-4bc6-b198-682cdb9f5738.png)
   
   Następnie utworzyłam katalog .ssh skopiowałam klucz do pliku zaufanego poleceniem
   
   ![image](https://user-images.githubusercontent.com/28841971/144757931-1c1620ac-db4f-4d72-8dc2-975ceb01d190.png)
    
  - odnajdź adres IP kontenera w wewnętrznej sieci

    Najpierw zainstalowałam net-tools, poleceniem: "apt-get install net-tools". 

    ![image](https://user-images.githubusercontent.com/28841971/144757498-0b9a631e-40ca-4fe9-b3b9-9ff367da5432.png)

    Później używając "ifconfig" wyświetliłam adres IP kontenera.
    
    ![image](https://user-images.githubusercontent.com/28841971/144757622-20e08660-f936-46ab-97e8-ff0b22a85d9d.png)
    
  - uruchom usługę, połącz się z kontenerem
  
    Poleceniem "ssh root@127.17.0.3 -p 20" uruchomiłam usługe.
    
    ![image](https://user-images.githubusercontent.com/28841971/144758004-22c55bc0-00e9-45c9-a6c6-f3c69cfe7121.png)

Skonteneryzowany Jenkins stosujący Dockera

Przygotowanie

  - Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
  
    Upewniłam się, że Dockerfiles znajdują się w repozoytorium
    
    ![image](https://user-images.githubusercontent.com/28841971/145502824-c5eeb7b7-c766-4e0a-adb9-ba19824122cf.png)

  - Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker/
    - Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone
      
      Uruchomiłam według instrukcji zamieszczonej wyżej obraz Dockera i wszelkie zależności.
      
      ![image](https://user-images.githubusercontent.com/28841971/145502981-95c25d91-6e63-4558-bef4-afa9c4944be2.png)
      ![image](https://user-images.githubusercontent.com/28841971/145502993-cff1cbc3-8053-4929-a91d-967a5b5f1e01.png)
      
    - Przygotuj obraz blueocean na podstawie obrazu jenkinsa
    
      Poleceniem "vim Dockerfile" utworzyłam Dockerfile i zamieściłam tam jego zawartość.
      
      ![image](https://user-images.githubusercontent.com/28841971/145503213-b3608f4f-e0b1-4a8c-b3ca-2e457cc296b4.png)
      
      Następnie zbudowałam obraz na podstawie Dockerfile
      
      ![image](https://user-images.githubusercontent.com/28841971/145503267-a9572674-3501-47ab-a77f-805fad2c570d.png)
      ![image](https://user-images.githubusercontent.com/28841971/145503281-e04eb200-a883-4ac5-920c-cfd397f80183.png)
    
    - Uruchom blueocean
      
      Następnie uruchomiłam blueocean

      ![image](https://user-images.githubusercontent.com/28841971/145503367-7ebb9f98-0aa4-4a18-ae4f-c2869c32f9b8.png)
      
    - Zaloguj się i skonfiguruj Jenkins
    
      Następnie odtworzyłam w przeglądarce localhost:8080 i została uruchmiona strona Jenkinsa z prośbą do zalogowania.
      
      ![image](https://user-images.githubusercontent.com/28841971/145503541-0190ecfb-8321-40f3-8607-36ad114a0bea.png)

      Hasło, które było potrzebne do zalogowania się uzyskałam poleceniem "sudo docker logs 54f82c7e6788". Ostatni numer to id kontenera, który wcześniejszymi poleceniami utworzyłam. 
      
      ![image](https://user-images.githubusercontent.com/28841971/145503568-263bd6ed-6404-44cf-b7c0-1f5a72ce0a2c.png)

      Po wpisaniu hasła uzyskałam dostęp do zawartości. 
      
      ![image](https://user-images.githubusercontent.com/28841971/145503773-af737e1d-c82c-408d-a43f-b7e39ec13ce7.png)

Mikro-projekt Jenkins

  - Utwórz projekt, który wyświetla uname

    Stworzyłam nowy projekt, podając mu nazwe "Projekt" i wybierając opcje ogólny projekt. Nastepnie przeszłam do opcji budowanie i ustawiłam na "Uruchom powłoke". Tam wpisałam polecenie uname -a:
    
    ![image](https://user-images.githubusercontent.com/28841971/145504064-a52a3fa1-a0ea-443c-ae7e-35cff2a3004b.png)

    Zapisałam projekt i uruchomiłam.
    
    ![image](https://user-images.githubusercontent.com/28841971/145504155-83a5ed42-010d-41a2-9759-1f9384ab72ee.png)
    
  - Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta

    Tak jak poprzednio stworzyłam projekt. Użyłam następujących komend:
    Data=`date +"%-H"` // do zmiennej Data przypisałam aktualną godzine (samą), - między %, a H pozwala mi usunąć zero w przypadku godziny jednocyfrowej.
    Test=`expr $Data \% 2` // Zmienna Test oblicza mi wartość modulo z wcześniej uzyskanej godziny
    if [ $Data -eq 0]
    then
      echo "Godzina jest poprawna"
    else
      echo "ERROR - Godzina nie jest poprawna"
    fi // instrukcja warunkowa, która w momencie godziny parzystej wyswietla komunikat, ze jest poprawna, a w momencie nieparzystej wyświetla błąd. 
    
    ![image](https://user-images.githubusercontent.com/28841971/145504692-632debc2-992c-4fa4-946d-2a494f8859c5.png)
    
    Projekt wykonał się poprawnie
    
    ![image](https://user-images.githubusercontent.com/28841971/145504814-1dcb4de1-5bc0-41df-b82f-be9ddcd058c5.png)
    
  - Utwórz "prawdziwy" projekt, który:
    - klonuje nasze repozytorium
    
      W opcjach projektu zaznaczyłam git, żeby sklonować repozytorium. Podałam tam adres repozytorium.
    
      ![image](https://user-images.githubusercontent.com/28841971/145504854-575aa498-f6cb-40aa-b461-0b274f7888ce.png)
    
    - przechodzi na osobistą gałąź

      Pod adresem repozytorium wpisałam swoją gałąź na którą ma się przełączyć.
      
      ![image](https://user-images.githubusercontent.com/28841971/145504951-ad752621-980f-4b4d-be7f-13020f6668d4.png)

    - buduje obrazy z dockerfiles i/lub komponuje via docker-compose

      Na końcu w powłoce użyłam następnujących komend: 
      cd GCL/03/JZ307699/Lab_3 // przejscie do odpowiedniego katalogu w którym znajduje się Dockerfile
      docker build -t easy/sauce:latest . // zbudowanie obrazu z Dockerfile
      cd Dockerfile2 // przejscie do katalogu gdzie znajduję się drugi Dockerfile
      docker build -t easy/sauce2:latest . // zbudowanie z drugiego Dockerfile
      docker run easy/sauce2:latest // uruchomienie testu
      
      ![image](https://user-images.githubusercontent.com/28841971/145505256-8043f126-6c83-47e3-ad4d-c0e01c2ea026.png)

      Projekt po dłuższej chwili zakończył się sukcesem.
      
      ![image](https://user-images.githubusercontent.com/28841971/145505310-ffb3d0d1-45ff-4ee4-9cb5-b6a24b75e8c1.png)
      ![image](https://user-images.githubusercontent.com/28841971/145505322-f9127b39-999f-4282-a201-1d10337e13d8.png)


Sprawozdanie
  - Opracuj dokument z diagramami UML, opisującymi proces CI. Opisz:
    - Wymagania wstępne środowiska
    - Diagram aktywności, pokazujący kolejne etapy (collect, build, test, report)
    - Diagram wdrożeniowy, opisujący relacje między składnikami, zasobami i artefaktami
