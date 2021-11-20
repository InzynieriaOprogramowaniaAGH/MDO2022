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

2. Zarejestruj się w Docker Hub i zapoznaj z sugerowanymi obrazami

założono konto Docker Hub; generalcube

3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql

```shell
$ docker pull busybox
```

[img]

```shell
$ docker pull ubuntu
```

```shell
$ sudo docker pull mysql
```
[img]


4. Uruchom busybox

```shell
$ docker run busybox
```

 - Pokaż efekt uruchomienia kontenera

[img 9]
[img 10]

 - Podłącz się do kontenera interaktywnie i wywołaj numer wersji

```shell
$ docker run -it busybox
```

[img 11]

6. Uruchom "system w kontenerze"

```shell
$ docker run -it ubuntu
```
 - Zaprezentuj PID1 w kontenerze i procesy dockera na hoście

```shell
$ ps axuf
```

host:
[img 12]

kontenerze:
[img 13]

 - Zaktualizuj pakiety

```shell
$ apt update
```

 - Wyjdź

```shell
$ exit
```

9. Pokaż uruchomione ( != "działające" ) kontenery, wyczyść je.

10. Wyczyść obrazy
    [img 16]

# Budowanie programu
1. Znajdź projekt umożliwiający łatwe wywołanie testów jednostkowych

Wykorzystane zostanie create-react-app
https://create-react-app.dev/
```shell
$ npx create-react-app devops-app
```

2. Przeprowadź budowę/konfigurację środowiska

```shell
$ npm i
& npm run build
```

3. Uruchom testy
```shell
& npm run test
```
[img 17]

4. Ponów ten proces w kontenerze
    - Wybierz i uruchom platformę
    - Zaopatrz ją w odpowiednie oprogramowanie wstępne
    - Sklonuj aplikację
    - Skonfiguruj środowisko i uruchom build
    - Uruchom testy
5. Stwórz Dockerfile, który ma to osiągnąć
    - Na bazie platformowego obrazu...
    - ...doinstaluj wymagania wstępne...
    - ...sklonuj repozytorium...
    - ...zbuduj kod
6. Zaprezentuj Dockerfile i jego zbudowanie
7. Na bazie obrazu utworzonego poprzednim dockerfilem stwórz kolejny, który będzie uruchamiał testy
