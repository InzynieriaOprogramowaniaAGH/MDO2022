# Metodyki DevOps - sprawozdanie - zajęcia 02

## Instalacja **docker**

W celu instalacji **docker** na Ubuntu, należy wykonać w terminalu następującą komendę:

`sudo apt install docker-ce`

![Zrzut ekranu - sudo apt install docker-ce](docker-ce-apt-install.jpg)

Jak wynika z powyższego zrzutu ekranu, na moim systemie aplikacja już była zainstalowana.

Możemy zwrócić numer wersji za pomocą komendy:

`docker --version`

![Zrzut ekranu - docker --version](docker-version.jpg)

## Tworzenie konta na Docker Hub

Przechodzimy na stronę https://hub.docker.com i rejestrujemy nowe konto za pomocą panelu widocznego na głównej stronie.

![Zrzut ekranu - hub.docker.com sign up](docker-hub-sign-up.jpg)

## Pobieranie obrazów dostępnych na Docker Hub

Po znalezieniu obrazu, który nas interesuje na Docker Hub, możemy pobrać obraz do naszej maszyny przy pomocy komendy:

`docker pull <nazwa obrazu>`

Na przykład poniższa komenda pobierze obraz **hello-world**:

`docker pull hello-world`

![Zrzut ekranu - docker pull](docker-pull.jpg)

## Uruchamianie pobranych obrazów

Uruchomienie pobranych obrazów odbywa się za pomocą komendy:

`docker run <nazwa obrazu>`

W naszym przykładzie:

`docker run hello-world`

![Zrzut ekranu - docker run](docker-run.jpg)

`docker run mysql`

![Zrzut ekranu - docker run mysql](docker-run-mysql.jpg)

`docker run ubuntu`

![Zrzut ekranu - docker run ubuntu](docker-run-ubuntu.jpg)

Jeżeli uruchomimy komendę `run` bez wcześniejszego pobrania obrazu, zostanie on automatycznie pobrany.

![Zrzut ekranu - docker run not pulled](docker-run-not-pulled.jpg)

Po uruchomieniu obrazu **busybox** nic się nie wykonuje, ponieważ nie przekazaliśmy żadnych parametrów do shella. Możemy uruchomić obraz interaktywnie za pomocą komendy:

`docker run -it busybox`

![Zrzut ekranu - docker run interactive](docker-run-interactive.jpg)

Wersję **busybox** możemy wyświetlić za pomocą komendy wykonanej w kontenerze:

`busybox | head -1`

![Zrzut ekranu - busybox version](busybox-ver.jpg)

## Uruchamianie "systemu w kontenerze"

Aby uruchomić Ubuntu w kontenerze i uzyskać dostęp do konsoli, używamy komendy:

`docker run -it ubuntu`

![Zrzut ekranu - docker run ubuntu interactive](docker-run-ubuntu-interactive.jpg)

Aby zobaczyć aktualnie uruchomione procesy w kontenerze, wykonujemy polecenie `ps` - procesem PID 1 jest **bash**.

![Zrzut ekranu - ubuntu ps](ubuntu-ps.jpg)

Listę procesów na hoście możemy wywołać przy pomocy komendy:

`docker top <id kontenera>`

![Zrzut ekranu - docker top](docker-top.jpg)

W uruchomionym kontenerze z Ubuntu wykonujemy polecenie `apt update && apt upgrade`, aby zaktualizować pakiety i następnie wychodzimy z kontenera poleceniem `exit`.

![Zrzut ekranu - apt update && apt upgrade](ubuntu-apt-update-upgrade.jpg)

## Lista kontenerów

Możemy sprawdzić jakie aktualnie mamy uruchomione kontenery za pomocą komendy:

`docker ps`

![Zrzut ekranu - docker ps](docker-ps.jpg)

Gdy wykonamy poniższą komendę, zwrócone zostaną wszystkie kontenery:

`docker ps -a`

![Zrzut ekranu - docker ps -a](docker-ps-a.jpg)

Możemy usunąc kontener przy pomocy komendy:

`docker rm <id kontenera>`

![Zrzut ekranu - docker rm](docker-rm.jpg)

## Lista obrazów i usuwanie obrazów

Wyświetlić listę wszystkich obrazów aktualnie pobranych na naszym hoście możemy przy pomocy komendy:

`docker images`

![Zrzut ekranu - docker images](docker-images.jpg)

Usunąć pobrany obraz możemy przy pomocy komendy:

`docker image rm <id obrazu/nazwa>`

![Zrzut ekranu - docker image rm id](docker-image-rm-id.jpg)
![Zrzut ekranu - docker image rm name](docker-image-rm-name.jpg)

Może być wymagane najpierw usunięcie kontenerów, które korzystają z obrazów, które chcemy usunąć - zostaniemy o tym poinformowani przy próbie usunięcia takiego obrazu.