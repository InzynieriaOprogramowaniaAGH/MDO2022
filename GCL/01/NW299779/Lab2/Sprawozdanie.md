# Lab 2 Sprawozdanie Norbert Wójtowicz
## Wprowadzenie do kontenerów
### 1. Zainstaluj docker:
Na urządzeniu, którego używałem docker jest już zainstalowany.

<img width="398" alt="Install_docker" src="https://user-images.githubusercontent.com/48067540/142781173-9ae791c8-2944-4d45-acba-0ae4f026522d.png">

<img width="426" alt="download_docker-compose" src="https://user-images.githubusercontent.com/48067540/142781248-a94893a1-928f-4cf9-a126-dfc07abe4784.png">

### 2. Zarejestrowałem się w DockerHub i zapoznałem z sugerowanymi obrazami.
### 3. Pobrałem hello-world, ubuntu, mysql, busybox
 Za pomocą polecenia 
 > docker pull <nazwa_obrazu>

<img width="625" alt="Download_busybox" src="https://user-images.githubusercontent.com/48067540/142781230-9425b737-725e-4eeb-ae34-384f360a90d3.png">

<img width="567" alt="Download_Ubuntu" src="https://user-images.githubusercontent.com/48067540/142781232-cc10c898-9df3-492d-ae80-7cf9607b59b9.png">

<img width="598" alt="download_hello-world" src="https://user-images.githubusercontent.com/48067540/142781236-806e1085-0d8f-46d9-a0e4-64921068e0de.png">

<img width="635" alt="download_mysql" src="https://user-images.githubusercontent.com/48067540/142781240-90ecfde6-6408-4e78-a4a8-1f45ee383e9c.png">

Uruchomiłem także hello-world za pomocą
> docker run hello-world

<img width="599" alt="run_hello-world" src="https://user-images.githubusercontent.com/48067540/142781270-6f37a4a7-743c-48da-9d93-3c7193c7b94c.png">

### 4. Uruchom busybox i podłącz się do kontenera interaktywnie
Uruchomiłem busyboc i podłączyłem się do niego interaktywanie za pomocą polecenia
> docker run -it --rm busybox

<img width="540" alt="run_busybox_container_interactively" src="https://user-images.githubusercontent.com/48067540/142781370-ca50d648-5249-4419-8730-f837a4b7ff74.png">


Oraz wywołałem numer wersji

<img width="475" alt="Busybox_version" src="https://user-images.githubusercontent.com/48067540/142781407-ed971004-4153-457a-a944-ca62baf69d5f.png">

### 5. Uruchom system w kontenerze
Uruchomiłem system w kontenerze komendą 
> docker run -it ubuntu

<img width="872" alt="run_ubuntu" src="https://user-images.githubusercontent.com/48067540/142781455-56cc880c-7bd7-45c2-b6d8-a91c00649085.png">

Następnie za pomocą 
> ps -aux
wyświetliłem drzewko procesów

<img width="535" alt="ubuntu_pid_1" src="https://user-images.githubusercontent.com/48067540/142781493-48d6067e-f87b-483b-abf4-1c4103133e4e.png">

Zaaktualizowałem pakiety 
> apt-get update

<img width="634" alt="ubuntu_update_packages" src="https://user-images.githubusercontent.com/48067540/142781534-a3ef9f43-4867-40a0-a689-39380f7bc16b.png">

### 6. Pokaż uruchonione kontenery i wyczyść je
Wyświetliłem uruchomione kontenery za pomocą 
> docker container ls -a  --filter status=exited

<img width="850" alt="docker_show_exited_containers" src="https://user-images.githubusercontent.com/48067540/142781579-70a66680-2ad3-4b99-a3f3-72520d8bd0f9.png">

Następnie wyłączyłem poszczególne kontenery
> docker container rm <id_kontenera>

<img width="989" alt="docker_remove_exited_containers" src="https://user-images.githubusercontent.com/48067540/142781602-c05f75b1-dfd4-4c9f-bc9f-639aee560051.png">

### 7. Wyczyść obrazy
Najpierw wyświetliłem wszystkie obrazy
> docker images
Następnie usunąłem poszczególne obrazy
> docker rmi <id_obrazu>

<img width="661" alt="docker_remove_images" src="https://user-images.githubusercontent.com/48067540/142781635-fed44a8b-d18f-42b5-9708-35a8b186086d.png">

## Budowanie programu
### 1. Znajdź projekt umożliwiający łatwe przeprowadzenie testów jednostkowych
> https://github.com/caolan/nodeunit

### 2. Przeprowadź budowę środowiska i konfigurację
Sklonowałem najpierw repozytorium aplikacji
> git clone <url_do_repozytorium>

 <img width="630" alt="clone_localy" src="https://user-images.githubusercontent.com/48067540/142781700-0a999437-1277-4b59-bb11-59eeb31f08c0.png">

Node.js oraz npm miałem już zainstalowane na urządzeniu

<img width="367" alt="node_npm_local" src="https://user-images.githubusercontent.com/48067540/142781718-6b7c9aac-be9d-46c2-8cd1-466015113146.png">

Uruchomiłem instalację zależności projektu
>npm install

<img width="1191" alt="npm_install_local" src="https://user-images.githubusercontent.com/48067540/142781739-2cc4f6f8-c883-46d8-bb29-d843c7e8597d.png">

### 3. Uruchom testy
Uruchomiłem testy
>npm test

<img width="658" alt="test_local" src="https://user-images.githubusercontent.com/48067540/142781747-cc414ea1-7810-46ec-8714-93bbfd9ce2ac.png">

### 4. Ponów proces w kontenerze
Uruchumiłem wybraną przeze mnie platformę (ubuntu)
> docker run -it ubuntu

Zainstalowałem na niej potrzebne oprogramowanie (git, npm, nodejs):
> apt update

<img width="838" alt="ubuntu_update_install_git" src="https://user-images.githubusercontent.com/48067540/142781833-357b4670-3827-4cc0-9d5a-6591d98cef93.png">

> apt install nodejs

> apt install npm

> apt install git

<img width="1197" alt="git_npm_node_versions" src="https://user-images.githubusercontent.com/48067540/142781837-fcb59d63-2e4f-4996-b085-a3b548854dbd.png">

Sklonowałem repozytorium
>git clone <nazwa_repozytorium>

<img width="512" alt="docker_clone_repository" src="https://user-images.githubusercontent.com/48067540/142781866-274d856c-c5b5-48d1-9a96-59e16e7b12df.png">

Uruchomiłem budowę aplikacji
>npm install

<img width="1189" alt="docker_npm_install" src="https://user-images.githubusercontent.com/48067540/142781881-31c9fe56-e2fe-4985-99d1-78e860ed567c.png">

Uruchomiłem testy w kontenerze
>npm test

<img width="616" alt="docker_tests" src="https://user-images.githubusercontent.com/48067540/142781894-0e90ffee-318f-4f97-8623-b777da12f124.png">


### 5. Stwórz Dockerfile który ma to osiągnąć 

<img width="377" alt="Zrzut ekranu 2021-11-21 o 23 45 56" src="https://user-images.githubusercontent.com/48067540/142781954-bd39c637-cca2-4244-ada6-73eda2624ce5.png">

 <img width="198" alt="Zrzut ekranu 2021-11-21 o 23 46 04" src="https://user-images.githubusercontent.com/48067540/142781948-5ea470eb-e2a8-461b-a3c3-2ddf10608053.png">
