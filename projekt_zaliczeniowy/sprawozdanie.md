1. Sprawdzono wersje dockera 

2. Sprawdzono wersje obrazu ubuntu 

![1](Screenshot_1.png)

3. Zainstalowano busyboxa
 
![1](Screenshot_2.png)

4. Uruchomiono busyboxa, sprawdzono zawartosc i wylaczono dockery 

![1](Screenshot_3.png)
![1](Screenshot_4.png)

Uruchomiono dockera z ubuntu

![1](Screenshot_5.png)

zainstalowano zaleznosci 

![1](Screenshot_6.png)
![1](Screenshot_7.png)

Wybrano program i pobrano go https://github.com/aria2/aria2.git

![1](Screenshot_8.png)

Zbudowano aplikacje zgodnie z README 

![1](Screenshot_9.png)

./configure 

![1](Screenshot_10.png)

make

![1](Screenshot_11.png)

Nastepnie uruchomiono testy make check 

![1](Screenshot_12.png)

Dla automatyzacji tworzenia powyższych kroków stworzono Dockerfile'a. 

![1](Screenshot_14.png)
![1](docscr.png)

Stworzono drugi Dockefile wywołujący pierwszego natomiast z dodanymi testami

![1](Screenshot_15.png)

Uruchomiono dockerfile 

![1](Screenshot_16.png)
![1](Screenshot_17.png)

Stworzono plik docker-compose

![1](Screenshot_18.png)


### AUTOMATYZATOR JENKINS z zalaczonej instrukcji 

![1](Screenshot_19.png)

Stworzono nowy dockerfile i zbudowano nowy obraz

![1](Screenshot_20.png)
![1](Screenshot_21.png)






