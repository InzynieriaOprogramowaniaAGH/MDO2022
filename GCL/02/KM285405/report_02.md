# Metodyki DevOps 2021/2022 NS
Zajęcia 02 - 2021-11-20
---
# Zestawienie środowiska
## 1. Instalacja Docker
Wykorzystywane środowisko to Debian na WSL2 - wymagało to również zainstalowania Docker Dekstop na windows, zgodnie z instrukcją: 
https://docs.docker.com/desktop/windows/wsl/

Zainstalowany Docker Desktop z integracją na WSL
![](img_02/01_windows_docker.png)

![](img_02/01_dockerwindows.png)

Instalacja na środowisku linuksowym zgodnie z instrukcją:
https://docs.docker.com/engine/install/debian/
![](img_02/01_dockerversion.png)

## 2. Rejestracja w Docker Hub
![](img_02/01_dockerhub.png)

## 3. Pobierz hello-world, busybox, ubuntu

Do pobrania wykorzystuje się komendę: docker image pull, natomiast przy próbie uruchomienia nieistniejącego obrazu, Docke pobierze obraz automatycznie:

![](img_02/03_helloworld.png)

## 4. Busybox

Uruchomienie busybox:

![](img_02/03_busybox.png)

Uruchomienie busybox interaktywnie i wywołanie numeru wersji
![](img_02/03_busybox_02.png)

## 5. "system w kontenerze"

Uruchoemienie ubuntu
![](img_02/05_system.png)

PID1 w kontenerze:

![](img_02/05_pid1.png)

Procesy dockera na hoście:
![](img_02/05_procesdocker.png)

Aktualizacja pakietów i wyjście z systemu:
![](img_02/05_updatexit.png)

## 6. Uruchomione kontenery

![](img_02/06_kontenery.png)

## 7. Czyszczenie obrazów

![](img_02/07_prune.png)

# Budowanie programu

## 1. Znajdź projekt umożliwiający łatwe wywołanie testów jednostkowych

Przykładowy projekt JavaScript Unit Testing Examples: https://github.com/MarcL/js-unit-testing-examples

## 2. Przeprowadź budowę/konfigurację środowiska

Projekct wymaga zainstalowania npm, więc należy wykonać:
```
sudo apt install npm
```
nastepnie pobrać repozytorium, zmienić katalog i zainstalować zależności projektu:
```
git clone https://github.com/MarcL/js-unit-testing-examples.git
cd js-unit-testing-framework
npm install
```

![](img_02/001_setup.png)

## 3. Uruchom testy
Uruchomienie testów:

![](img_02/002_test.png)

![](img_02/003_test.png)

## 4. Ponów ten proces w kontenerze

Do ponowienie tego procesu w kontenerze, należy kolejno wykonać:

```
docker run -it ubuntu
apt update
apt install git
apt install npm

git clone https://github.com/MarcL/js-unit-testing-examples.git

cd js-unit-testing-examples
npm install

npm test
```
![](img_02/01_apt_update.png)

![](img_02/02_apt_install_git.png)

![](img_02/02_apt_install_npm.png)

![](img_02/03_git_clone.png)

![](img_02/04_npm_install.png)

![](img_02/05_npm_test.png)

## 5. Stwórz Dockerfile, który ma to osiągnąć

Dockerfile będzie miał dokładnie te same komendy co powyżej, z dodaniem ```RUN``` przed każdą z nich  co spowoduje wywołanie komendy,
dzięki ```ARG DEBIAN_FRONTEND=noninteractive``` system nie będzie pytał o potwierdzenie operacji
```ENV TZ=Europe/Warsaw RUN apt-get install -y tzdata``` - pomijamy pytanie o 'geographic area'

```
FROM ubuntu

RUN apt update

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Europe/Warsaw
RUN apt-get install -y tzdata

RUN apt install -y npm
RUN apt install -y git

RUN git clone https://github.com/MarcL/js-unit-testing-examples.git

RUN cd js-unit-testing-examples && npm install
```

## 6. Zaprezentuj Dockerfile i jego zbudowanie

wywołanie przez:
```docker build -t=obraz -f dockerfile .```

![](img_02/06_dockerfile.png)


## 7. Na bazie obrazu utworzonego poprzednim dockerfilem stwórz kolejny, który będzie uruchamiał testy

Wystarczy zmodyfikować istniejący dockerfile przez dopisanie ```&& npm test```

![](img_02/07_dockerfile.png)
