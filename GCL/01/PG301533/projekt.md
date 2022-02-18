#### Projekt zaliczeniowy

* Znaleziono projekt _create-react-app_ wśród repozytoriów GitHub zawierający mechanizm budowania oraz testy jednostkowe

* Sklonowano projekt z GitHub za pomocą `git clone git@github.com:facebook/create-react-app.git`

![image01](img/01.png)

* Pobrano obraz _ubuntu_ za pomocą `docker pull ubuntu`. Będzie to środowisko, w którym uruchomimy powyższy projekt.

![image02](img/02.png)

* Utworzono _Dockerfile_ umożliwiający build bez konieczności przygotowania/konfiguracji środowiska

![image03](img/03.png)

* Uruchomiono budowanie kontenera za pomocą powyższego _Dockerfile_, stosując `docker build build/.`

![image04](img/04.png)

![image05](img/05.png)

* Utworzono _Dockerfile_ uruchamiający testy (oparty na poprzednim _Dockerfile_), stosując `docker build test/.`

![image06](img/06.png)

* Wynik testów

![image07](img/07.png)

* Utworzono _docker-compose.yml_ uruchamiający powyższe pliki za pomocą `docker-compose up`

![image08](img/08.png)

* Uruchomienie programu w kontenerze za pomocą `docker-compose run --rm my-ubuntu-build`, a następnie `cd my-app` oraz `npm start`

![image09](img/09.png)

![image10](img/10.png)

* Po otwarciu strony:

![image11](img/11.png)

* Wprowadzenie zmian w kodzie pliku _src/App.js_ w celu wykazania, że funkcjonalność nie jest ograniczona konteneryzacją i uruchomienie ponowne

![image12](img/12.png)

![image13](img/13.png)

* Dystrybuowanie wybranego oprogramowania w postaci kontenera ma sens ponieważ można go aktualizować oraz zmieniać na różne potrzeby

* Wyciągnięcie artefaktu z kontenera przez skopiowanie projektu do volumenu za pomocą `cp -r my-app/ my-vol/`

![image14](img/14.png)

* Uruchomienie projektu na zewnątrz kontenera za pomocą `npm start` jako _root_ (uprawnienia do uruchomienia ma _root_ ponieważ w kontenerze działaliśmy jako _root_)

![image15](img/15.png)

![image16](img/16.png)

* Najlepszym wyborem jest uruchomienie/aktualizacja projektu w kontenerze. Umotywowane jest to tym, że w łatwy sposób można zmieniać projekt, ew. wprowadzać nowe funkcjonalności.
W przypadku niepowodzeń można cofnąć się do działającego obrazu.

* Zainstalowano automatyzator Jenkins

![image17](img/17.png)

* Przygotowano _Dockerfile_ tworzący _Blueocean_

![image18](img/18.png)

* Użyto 

```
docker run --name jenkins-blueocean --rm \
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  myjenkins-blueocean:2.319.3-1
```

* W wyniku otrzymano

![image19](img/19.png)

* W przeglądarce otwarto stronę o adresie _http://localhost:8080_, podano hasło otrzymane wcześniej i ukończono konfigurację

![image20](img/20.png)

![image21](img/21.png)

![image22](img/22.png)

* Projekt umieszczono w prywatnym repozytorium

![image23](img/23.png)

* Utworzono nowy projek w _Jenkins_

![images24](img/24.png)

* Ustawiono repozytorium kodu

![images25](img/25.png)

* Dodano _Credentials_ (wcześniejszy error znikł)

![images26](img/26.png)

* Zmieniono nazwę branchy z _master_ na _main_

![images27](img/27.png)

* Spróbowano uruchomić kompozycję _Docker Compose_ ale napotkano błąd

* Potrzebna była modyfikacja pliku _Dockerfile_ dla _Jenkinsa_ aby uruchomić kompozycję

![images28](img/28.png)

* Uruchomiono kompozycję _Docker Compose_ przy użyciu projektu w _Jenkinsie_

![images29](img/29.png)

* Załączono logi _jenkins.logs_

* Na tym kroku zakończono realizację dalszej części projektu

* Wykonano commit na dedykowanej gałęzi w repozytorium MDO2022


