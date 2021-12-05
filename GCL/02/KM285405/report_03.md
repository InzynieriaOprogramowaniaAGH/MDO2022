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

# "Kiepski pomysł": SSH

## Uruchom, wyeksponuj wybrany port w kontenerze, zainstaluj w kontenerze serwer ssh

```apt-get update```

```apt-get -y install openssh-server ```

![](img_03/12.jpg)

![](img_03/13.jpg)


## zezwól na logowanie root
## umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze
## odnajdź adres IP kontenera w wewnętrznej sieci
## uruchom usługę, połącz się z kontenerem
