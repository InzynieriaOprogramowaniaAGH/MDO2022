# Zajęcia 02
### 2021-11-20

# Wprowadzenie do kontenerów
- Wykonaj opisane niżej kroki i dokumentuj ich wykonanie
- Na dokumentację składają się następujące elementy:
  - plik tekstowy ze sprawozdaniem, zawierający opisy z każdego z punktów zadania
  - zrzuty ekranu przedstawiające wykonane kroki
  - listing historii poleceń
- Sprawozdanie z zadania powinno umożliwiać odtworzenie wykonanych kroków w wykorzystaniem opisu, poleceń i zrzutów
# 1. Instalacja Dockera
Laboratoria są wykonywane z pomocą WSL2 z zainstalowanym Ubuntu.
Dokumentacja Dockera: https://docs.docker.com/engine/install/ubuntu/

Starsze wersje Dockera możesz znaleźć pod nazwą: docker, docker,io docker-engine. Jeśli są zainstalowane, odinstaluj je poniżym poleceniem.
~~~ sh
$ sudo apt-get remove docker docker-engine docker.io containerd runc
~~~

[img1]

Zaktualizuj indeks pakietów apt i zainstaluj pakiety, aby umożliwić apt korzystanie z repozytorium przez HTTPS:
~~~ sh
 sudo apt-get update
~~~

[img2]
~~~ sh
 sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
~~~

[img3]

Dodaj oficjalny klucz GPG od Dockera
~~~ sh
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
~~~

Instalujemy Dockera poleceniem:
~~~ sh
sudo apt-get install -y docker.io
docker -v
~~~

[img4]
[img5]
# 2. Zarejestruj się w Docker Hub
Należy przejść pod adres https://hub.docker.com/ i zarejestrować konto wykorzystując panel na stronie głównej.

[img6]
# 3. Pobierz hello-world
Wyszukujemy interesujący nas obraz na Docker Hub. Po jego znalezieniu należy go pobrać z komendą
~~~ sh
docker pull <nazwa obrazu>
~~~

W naszym przypadku będzie to:
~~~ sh
docker pull hello-world
docker pull mysql
docker pull busybox
docker pull ubuntu
~~~

[img7]
# 4. Uruchom hello-world
Aby uruchomić pobrany obraz należy użyć polecenia:
~~~ sh
docker run <nazwa obrazu>
~~~

W naszym przypadku będzie to:
~~~ sh
docker run hello-world
docker run mysql
docker run busybox
docker run ubuntu
~~~

Poniżej efekt uruchomienia kontenera
[img8]

Wyświetlamy procesy dla wszystkich użytkowników
~~~ sh
ps auxf
~~~

[img9]
[img10]
# 5. Uruchom "system w kontenerze"
Do uruchomienia Ubuntu w kontenerze oraz uzyskanie dostępu do konsoli, używamy komendy:
~~~ sh
docker run -it ubuntu
~~~

Następnie żeby sprawdzić uruchomione procesy w tym kontenerze, wykonujemy polecenie ps po którym uzyskamy **PID 1**.
~~~ sh
ps
~~~

Żeby sprawdzić listę procesów
~~~ sh
docker ps -a
~~~

[img11]
[img12]
[img13]
# 6. Pokaż uruchomione kontenery, wyczyść je.
Żeby zobaczyć wszystkie działające kontenery, użyjemy polecenia:
~~~ sh
docker ps -a
~~~

[img14]
# 7. Wyczyść obrazy
Do wyczyszczenia obrazów użyjemy polecenia:
~~~ sh
docker system prune -a
~~~

[img15]
# Budowanie programu
Żeby usprawnić proces budowania aplikacji, posłużymy się szkieletem aplikacji postawionym na reacie z pomocą komendy *(musi być zainstalowany pakiet nodejs oraz npm)* - https://create-react-app.dev/
~~~ sh
npx create-react-app test-app
npm run build
npm run test
~~~

[img16]
[img17]
[img18]

Następnym krokiem będzie uruchomienie tej samej aplikacji w kontenerze.
Do tego celu zbudujemy sobie **Dockerfile** oraz dla testów **Dockerfile-test**

DOCKERFILE
~~~ sh
FROM ubuntu:latest

RUN apt-get update && apt-get upgrade
RUN apt-get install -y nodejs npm

WORKDIR /test-app

CMD ["npm","run","build"]
~~~

DOCKERFILE-TEST
~~~ sh
FROM ubuntu-node:latest

WORKDIR /test-app

CMD ["npm","test"]
~~~

Dockerfile wywołujemy poleceniem
~~~ sh
docker build -f Dockerfile
docker build -f Dockerfile-test
~~~

[img19]
[img20]
