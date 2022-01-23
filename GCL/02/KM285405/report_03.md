# Metodyki DevOps 2021/2022 NS
Zajęcia 03 - 2021-12-04
---
# Łączność i woluminy na podstawie "złych" praktyk
## Pobierz obraz Ubuntu
![](img_03/01.jpg)

## Podłącz wolumin do kontenera

![](img_03/02.jpg)

![](img_03/03.jpg)

## Utwórz plik w kontenerze, na obszarze woluminu, pokaż na hoście

![](img_03/04.jpg)

![](img_03/05.jpg)

Utworzony plik powinien znajdować się w 

```/var/lib/docker/volumes/wolumin/_data```

![](img_03/06.jpg)

Natomiast wykorzystująć WSL2 - taka lokalizacja nie istnieje, a wszystkie pliki są zapisywane na zasobie sieciowym ze ścieżką:

```\\wsl$\docker-desktop-data\version-pack-data\community\docker\volumes\wolumin\_data```

Żeby się do nich dostać, należy zmapować dysk sieciowy w windows i to samo powtórzyć dla linuksa

![](img_03/07.jpg)

Dopiero wtedy mamy pełen dostęp do plików

![](img_03/08.jpg)

## Skopiuj plik do katalogu woluminu, pokaż w kontenerze

![](img_03/09.jpg)

![](img_03/10.jpg)

![](img_03/11.jpg)

W tym momencie, niestety musialem zmienić środowisko z WSL2 na VirtualBox

# "Kiepski pomysł": SSH

## Uruchom, wyeksponuj wybrany port w kontenerze, zainstaluj w kontenerze serwer ssh

```sudo docker run --interactive --tty --mount source=wolumin,destination=/store --publish 2048:4000 ubuntu```

następnie:

```apt-get update```

```apt-get -y install openssh-server ```

![](img_03/14.JPG)

![](img_03/15.JPG)

mkdir /run/sshd

## zezwól na logowanie root i zmień port >1024

Aby zezwolić na logowanie root i zmienić port, należy zmodyfikować plik sshd_config, można do tego wykorzystać np. nano

```apt install nano```

```nano /etc/ssh/sshd_config```

![](img_03/16.JPG)

## umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze

Klucz utworzony i przeniesiony do root/.ssh

![](img_03/17.JPG)

![](img_03/18.JPG)

![](img_03/18a.JPG)

## odnajdź adres IP kontenera w wewnętrznej sieci

```apt-get install net-tools```

```ifconfig```

![](img_03/20.JPG)

## uruchom usługę, połącz się z kontenerem

![](img_03/21.JPG)


# Skonteneryzowany Jenkins stosujący Dockera

## Przygotowanie
* Upewnij się, że Dockerfiles i Docker Compose z poprzednich zajęć są w repozytorium
* Zapoznaj się z instrukcją https://www.jenkins.io/doc/book/installing/docker
  * Uruchom obraz Dockera który eksponuje środowisko zagnieżdżone

![](img_03/22.JPG)

  * Przygotuj obraz blueocean na podstawie obrazu jenkinsa - dockerfile z dokumentacji
  
![](img_03/23.JPG)
  
```docker build -t myjenkins-blueocean:2.319.2-1 .```

  ![](img_03/24.JPG)
  
  ![](img_03/25.JPG)
  
  * Uruchom blueocean
  
  ![](img_03/26.JPG)
  
  * Zaloguj się i skonfiguruj Jenkins
  
  ![](img_03/27.JPG)
  
  ![](img_03/29.JPG)
  
  wymagane hasło dostępne przez:
  
  ```docker logs```
  
  ![](img_03/28.JPG)
  
  ![](img_03/30.JPG)
  
   ![](img_03/31.JPG)

#### Mikro-projekt Jenkins
* Utwórz projekt, który wyświetla uname

  ![](img_03/32.JPG)
  
  ![](img_03/33.JPG)

  ![](img_03/34.JPG)
  
  ![](img_03/35.JPG)
    
  ![](img_03/36.JPG)
  
  
* Utwórz projekt, który zwraca błąd, gdy... godzina jest nieparzysta 

Należy powtórzyć wszystkie kroki, jak w poprzednim 'projekcie', tym razem jako komendę podająć np.:

 ![](img_03/37.JPG)
  
  Wynmik dla godziny nieparzystej:
  
  ![](img_03/38.JPG)
  
  dla parzystej:
   
  ![](img_03/39.JPG)

* Utwórz "prawdziwy" projekt, który:
  * klonuje nasze repozytorium
  * przechodzi na osobistą gałąź
  * buduje obrazy z dockerfiles i/lub komponuje via docker-compose
 Projekt został przygotowany:

![](img_03/40.JPG)

![](img_03/41.JPG)

Niestety kończy się błędem:

![](img_03/42.JPG)
  

