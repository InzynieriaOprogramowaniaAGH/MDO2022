Wprowadzenie do kontenerów


1. Zainstaluj Docker w systemie linuksowym

    W moim systemie Docker istnieje już niedawno zainstalowany Docker. Sprawdzenie statusu:
  
  ![image](https://user-images.githubusercontent.com/80592460/142733040-1b238042-f55c-4610-aca9-a3457dc9c54f.png)

2. Zarejestruj się w Docker Hub i zapoznaj z sugerowanymi obrazami

![image](https://user-images.githubusercontent.com/80592460/142733048-2e1b425e-fdc0-4280-bfc3-59755cbebbd1.png)

3. Pobierz hello-world, busybox, ubuntu lub fedorę, mysql

![image](https://user-images.githubusercontent.com/80592460/142733073-2f8995bf-8b99-41e6-b108-e863c6ec99a6.png)

4. Uruchom busybox

![image](https://user-images.githubusercontent.com/80592460/142733315-52960844-edcf-44a5-8ea3-4d1e77d1b7c8.png)

![image](https://user-images.githubusercontent.com/80592460/142734521-de2f3bd5-57e0-4477-8a0b-ec62ca401127.png)

5. Uruchom "system w kontenerze"
    -Zaprezentuj PID1 w kontenerze i procesy dockera na hoście
    -Zaktualizuj pakiety
    -Wyjdź

Uruchamiamy Ubuntu w kontenerze:

![image](https://user-images.githubusercontent.com/80592460/142734304-519d00b1-53bf-435b-ad6c-5d4ded54b391.png)

Uruchamiamy drzewko procesów: ps axf

Widzimy procesy dockera

![image](https://user-images.githubusercontent.com/80592460/142734877-73656a37-2ee0-4efc-be41-dfb7c230d265.png)

Aktualizujemy kontener z Ubuntu

![image](https://user-images.githubusercontent.com/80592460/142734968-d1b286e6-fe5b-4bb3-ad6a-36169911d03c.png)

6. Pokaż uruchomione ( != "działające" ) kontenery, wyczyść je.

![image](https://user-images.githubusercontent.com/80592460/142735010-ef71d14c-9121-425f-aeab-120f52b225e5.png)

Zatrzymanie i czyszczenie

![image](https://user-images.githubusercontent.com/80592460/142735177-76ed11a2-bd21-4ab5-a9f9-73f1e871c01a.png)

----------------------------------------------------------------------------------------
Budowanie programu

1. Znajdź projekt, który umożliwia ustawienie wywołanie testów jednostkowych

        https://github.com/caolan/nodeunit
        
2. Przeprowadź budowę/konfigurację środowiska

     -Klonowanie aplikacji
     
     ![image](https://user-images.githubusercontent.com/80592460/142771455-5dd4ec12-6da1-475f-ac39-a64c34a172cb.png)

    - Instalowanie node.js i npm
    
   * Włącz repozytorium NodeSource, uruchamiając polecenie jako użytkownik z uprawnieniami sudo:

![image](https://user-images.githubusercontent.com/80592460/142771790-d1b28269-fd51-4f3f-9482-7885a3beba19.png)

   * Po włączeniu repozytorium NodeSource instalowanie Node.js i npm

![image](https://user-images.githubusercontent.com/80592460/142771964-c8247cd3-7439-42f7-bc03-66f35fe2529e.png)

   * Instalowanie zależności do projektu

![image](https://user-images.githubusercontent.com/80592460/142772073-5267d121-153a-48d0-a575-f90935ec66a0.png)

3. Uruchom testy
   
![image](https://user-images.githubusercontent.com/80592460/142772258-1d3acd47-38c5-4a00-bddd-01217e8c9429.png)

4. Ponów ten proces w kontenerze












