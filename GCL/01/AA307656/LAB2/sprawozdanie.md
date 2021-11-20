# **Sprawozdanie z LAB2**

	Wprowadzenie do kontenerów

# Zestawienie środowiska

## 1. Zainstaluj Docker w systemie linuksowym

	$ sudo apt-get install docker-ce docker-ce-cli containerd.io
	$ sudo apt docker --version
	apt 2.0.6 (amd64)
![screen: install docker](screenshots/1.png)

## 2. Zarejestruj się w Docker Hub i zapoznaj z sugerowanymi obrazami
## 3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql

	Aby pobrac images od docker wystarczy:
	$ docker pull 'nazwa'
![screen: docker pull](screenshots/2.png)

## 4. Uruchom busybox
        Pokaż efekt uruchomienia kontenera
        Podłącz się do kontenera interaktywnie i wywołaj numer wersji
    	 $ sudo docker run -it busybox
![screen: docker run](screenshots/3.png)
        
## 5. Uruchom "system w kontenerze"
        Zaprezentuj PID1 w kontenerze i procesy dockera na hoście
  	 # ps ax
  	 $ docker ps
![screen: docker ps](screenshots/4.png)
        Zaktualizuj pakiety
   	 # apt update
   	 # exit
        Wyjdź
 ![screen: # exit](screenshots/5.png) 
       
## 6. Pokaż uruchomione ( != "działające" ) kontenery, wyczyść je.
    Wyczyść obrazy
    $ docker container ls
    $ sudo docker images purge
![screen: docker container](screenshots/6.png)


# Budowanie programu

## 1. Znajdź projekt umożliwiający łatwe wywołanie testów jednostkowych
	

## 2. Przeprowadź budowę/konfigurację środowiska
## 3. Uruchom testy
## 4. Ponów ten proces w kontenerze
        Wybierz i uruchom platformę
        Zaopatrz ją w odpowiednie oprogramowanie wstępne
        Sklonuj aplikację
        Skonfiguruj środowisko i uruchom build
        Uruchom testy
## 5. Stwórz Dockerfile, który ma to osiągnąć
        Na bazie platformowego obrazu...
        ...doinstaluj wymagania wstępne...
        ...sklonuj repozytorium...
        ...zbuduj kod
## 6. Zaprezentuj Dockerfile i jego zbudowanie
## 7. Na bazie obrazu utworzonego poprzednim dockerfilem stwórz kolejny, który będzie uruchamiał testy

# Runda bonusowa: kompozycja

    Zdefiniuj kompozycję, która stworzy dwie usługi
        Pierwszą na bazie dockerfile'a budującego
        Drugą na bazie pierwszej
    Wdróż :)

