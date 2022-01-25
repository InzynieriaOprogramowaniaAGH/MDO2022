# Metodyki devops - lab2

## Środowisko wykonywania zadań

Środowiskiem na którym wykonywane są zadania jest system Fedora z rodziny linux. **Fedora 35 Workstation**

## Zestawienie środowiska

1. Zainstalowałem docker w moim systemie linuxowym następującymi komendami: 

`sudo dnf config-manager --add-repo https://download.docker.com/linux/fedora/docker-ce.repo`

`sudo dnf install docker-ce docker-ce-cli containerd.io`

![Zrzut_ekranu-1](1.jpg)

a następnie włączyłem dockera komendą: 

`sudo systemctl start docker`

2. Utworzyłem konto na stronie https://hub.docker.com/. Zapoznałem się wstępnie z najpopularniejszymi dostępnymi tam obrazami. Potwierdzeniem założenia konta będzie fragment maila powitalnego:

![Zrzut_ekranu-2](2.jpg)

3. Pobrałem obrazy: hello-world, busybox, fedore oraz mysql. Wszystkie te operacje odbywały się po kolei komendą:

`sudo docker pull <nazwa_obrazu>`

![Zrzut_ekranu-3](3.jpg)

4. Uruchomiłem busybox komendą:

`sudo docker run busybox`

![Zrzut_ekranu-4](4.jpg)

Uruchomiłem go również interaktywnie komendą: 

`sudo docker run -it --rm busybox`

a następnie wewnątrz kontenera użyłem polecenia: 

`sh --help` 

aby wywołać numer serii.

![Zrzut_ekranu-4_1](4_1.jpg)

5. Uruchomiłem w kontenerze system fedora podobną komendą jak w przypadku interaktywnego busyboxa: 

`sudo docker run -it fedora bash`

![Zrzut_ekranu-5](5.jpg)

Procesy dockera na hoście: 

![Zrzut_ekranu-5_1](5_1.jpg)

Jak widać mimo, że na kontenerze są uruchamiane poszczególne operacje, nie są one wirtualizowane, tylko są normalnie widoczne na liście procesów hosta.

Wychodzenie z kontenera odbywa się komendą 

`exit`

![Zrzut_ekranu-5_2](5_2.jpg)

6. Aby wyświetlić listę uruchomionych kontenerów można użyć polecenia

`sudo docker ps -a`

Tak prezentuje się lista kontenerów:

![Zrzut_ekranu-6](6.jpg)

Aby wyczyścić kontenery można użyć komendy: 

`sudo docker container stop <id_kontenera>`

7. Do wyczyszczenia obrazów użyłem komendy: 

`sudo docker rmi <id_obrazu>`

![Zrzut_ekranu-7](7.jpg)

## Budowanie programu

1. Do wykonania zadań wykorzystałem swój własny mały projekt, który zawiera kilka testów jednostkowych. Projekt został stworzony w technologii .NET Core.

**https://github.com/tomcio97/articles**

Pobranie repozytorium odbyło się poleceniem 

`git clone https://github.com/tomcio97/articles.git`

![Zrzut_ekranu-B_1](B_1.jpg)

2. Do zbudowania projektu potrzebny był zestaw SDK platformy .NET 5. Zainstalowanie odbyło się poleceniem: 

`sudo dnf install dotnet-sdk-5.0`

![Zrzut_ekranu-B_1](B_2.jpg)

3. Uruchomienie testów odbyło się komendą: 

`dotnet test articles`

![Zrzut_ekranu-B_3](B_3.jpg)

Jak widać wszystkie testy Passed :)

4. Wykonanie tego samego procesu w kontenerze

 - Uruchomienie platformy fedora takim samym poleceniem jak wcześniej zaprezentowano.
 - Platforma została zaopatrzona w narzędzie git oraz zestaw SDK .NET 5.

![Zrzut_ekranu-B_4_1_2](B_4_1_2.jpg)

![Zrzut_ekranu-B_4_1_3](B_4_1_3.jpg)

- Sklonowanie repozytorium aplikacji identycznie jak w poprzednim ćwiczeniu.

![Zrzut_ekranu-B_4_2](B_4_2.jpg)

- Budowanie aplikacji komendą: 

`dotnet build articles`

![Zrzut_ekranu-B_4_3](B_4_3.jpg)

- Uruchomienie testów (jak w poprzednim ćwiczeniu)

![Zrzut_ekranu-B_4_4](B_4_4.jpg)

5. Stworzyłem plik Dockerfile komendą 

`touch Dockerfile-build`

![Zrzut_ekranu-B_5](B_5.jpg)

a następnie edytwałem edytorem tekstu **nano** 

Uruchomienie pliku za pomocą polecenia: 

`sudo docker build -t build:latest . -f ./Dockerfile-build`

![Zrzut_ekranu-B_5_2](B_5_2.jpg)

Plik odpala się w 5 krokach:

- Pobranie odpowiedniego obrazu  

![Zrzut_ekranu-B_5_5](B_5_5.jpg)

- Instalacja potrzebnych programów 

![Zrzut_ekranu-B_5_6](B_5_6.jpg)

- Klonowanie repo

![Zrzut_ekranu-B_5_7](B_5_7.jpg)

 - Przełączenie katalogu roboczego

![Zrzut_ekranu-B_5_8](B_5_8.jpg)

- Zbudowanie projektu

![Zrzut_ekranu-B_5_9](B_5_9.jpg)

6. Tak prezentuje się wyżej wymieniony plik Dockerfile-build:

![Zrzut_ekranu-B_6](B_5_1.jpg)

- `FROM fedora:latest` - Został on stworzony na bazie obrazu 'fedora:latest' czyli najnowszej wersji systemu Fedora.

- `RUN yum -y install git dotnet-sdk-5.0` - Zainstalowanie git oraz .NET SDK.

- `WORKDIR articles` - ustawienie katalogu articles jako miejsca do wykonywania kolejnych poleceń.

- `RUN dotnet build` - zbudowanie projektu.

7. Plik dockerfile, uruchamiający testy do programu zbudowanego w poprzednim obrazie: 

![Zrzut_ekranu-B_7](B_7.jpg)

Tutaj następuje w pierwszej linii wykorzystanie poprzednio utworzonego obrazu oraz druga komenda odpowiedzialana za uruchomienie testów.

Uruchomienie odbywa się podobnie jak poprzednio: 

`sudo docker build -t build:latest . -f ./Dockerfile-test`

![Zrzut_ekranu-B_7](B_7_1.jpg)
