## Zajęcia 02 - 2021-11-20 Michał Mucha - 301180
---

## Zestawienie środowiska
1. Zainstaluj Docker w systemie linuksowym
```shell
$ sudo dnf -y install dnf-plugins-core

$ sudo dnf config-manager \
      --add-repo \
      https://download.docker.com/linux/fedora/docker-ce.repo
```

Install Docker Engine
```shell
$ sudo dnf install docker-ce docker-ce-cli containerd.io
```

Check if docker set up correctly, starting docker:
```shell
$ sudo systemctl start docker
```

run hello-world docker:
```shell
$ sudo docker run hello-world
```

[screen_1 -  screen_4]

2. Zarejestruj się w Docker Hub i zapoznaj z sugerowanymi obrazami

założono konto Docker Hub; generalcube
[screen_5]
3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql

```shell
$ docker pull busybox
```

[screen_6]

```shell
$ docker pull ubuntu
```

[screen_7]

```shell
$ sudo docker pull mysql
```

4. Uruchom busybox

```shell
$ docker run busybox
```

[screen_8]

- Pokaż efekt uruchomienia kontenera
[screen_9]
[screen_10]

- Podłącz się do kontenera interaktywnie i wywołaj numer wersji

```shell
$ docker run -it busybox
```
[screen_11]

6. Uruchom "system w kontenerze"

```shell
$ docker run -it ubuntu
```
 - Zaprezentuj PID1 w kontenerze i procesy dockera na hoście

```shell
$ ps axuf
```

host:
[screen_12]

kontenerze:
[screen_13]
 - Zaktualizuj pakiety

```shell
$ apt update
```
[screen_14]

 - Wyjdź

```shell
$ exit
```

9. Pokaż uruchomione ( != "działające" ) kontenery, wyczyść je.
   [screen_15]

10. Wyczyść obrazy
```shell
$ docker system prune
```
[screen_16]

# Budowanie programu
1. Znajdź projekt umożliwiający łatwe wywołanie testów jednostkowych

Wykorzystane zostanie create-react-app
https://create-react-app.dev/
```shell
$ npx create-react-app devops-app
```
[screen_17 - 18]

2. Przeprowadź budowę/konfigurację środowiska

```shell
$ npm i
& npm run build
```

[screen_19]

3. Uruchom testy
```shell
$ npm run test
```
[screen_20]

4. Ponów ten proces w kontenerze
 - Wybierz i uruchom platformę

Apka będzie odpalona w kontenerze stworzonym na bazie obrazu ubuntu


```shell
$ docker run -it ubuntu
```

 - Zaopatrz ją w odpowiednie oprogramowanie wstępne

```shell
$ apt-get update
$ apt-get upgrade
$ apt install nodejs
```

[screen_21]

 - Sklonuj aplikację

```shell
$ npx create-react-app devops-ubuntu-app
```

- Skonfiguruj środowisko i uruchom build
```shell
$ npm run build
```
[screen_22]
[screen_23]
- Uruchom testy

```shell
$ npm run test
```
[screen_24]
[screen_25]

7. Stwórz Dockerfile, który ma to osiągnąć
[screen_26]
     - Na bazie platformowego obrazu...
     - ...doinstaluj wymagania wstępne...
     - ...sklonuj repozytorium...
     - ...zbuduj kod

stworzono .dockerignorevi
[screen_27]
11. Zaprezentuj Dockerfile i jego zbudowanie
[screen_28]

```shell
$ sudo docker build -t myapp .
```

12. Na bazie obrazu utworzonego poprzednim dockerfilem stwórz kolejny, który będzie uruchamiał testy
