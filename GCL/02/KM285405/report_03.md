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

```sudo docker run --interactive --tty --mount source=wolumin,destination=/store --publish 2048:4000 ubuntu```

następnie:

```apt-get update```

```apt-get -y install openssh-server ```

![](img_03/14.jpg)

![](img_03/15.jpg)

mkdir /run/sshd

## zezwól na logowanie root i zmień port >1024

Aby zezwolić na logowanie root i zmienić port, należy zmodyfikować plik sshd_config, można do tego wykorzystać np. nano

```apt install nano```

```nano /etc/ssh/sshd_config```

![](img_03/16.jpg)

## umieść klucz publiczny w woluminie, skopiuj go do pliku zaufanych w kontenerze

Klucz utworzony w woluminie i przeniesiony do root/.ssh

![](img_03/17.jpg)

![](img_03/18.jpg)

## odnajdź adres IP kontenera w wewnętrznej sieci

```apt-get install net-tools```

![](img_03/19.jpg)

```ifconfig```

![](img_03/20.jpg)

## uruchom usługę, połącz się z kontenerem


