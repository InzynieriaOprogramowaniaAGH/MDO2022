### Jenkinsfile: przebieg
https://www.jenkins.io/doc/book/pipeline/jenkinsfile/
* Przykładowe zbiory czynności w Jenkinsfile:
Jednokrokowy pipeline (Build i test), pobierający narzędzie docker-compose i uruchamiajacy docker compose up na kompozycji z poprzednich zajęć
  * build + test
    * download docker-compose
    * compose up
    
  * build
    * git pull
    * npm install
    * npm build
  * test
    * npm test
    
![screen1.png](screen1.png)

![screen2.png](screen2.png)
    
### Jenkinsfile: powiadomienia
  * Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
  
W Jenkinsfile zastosowano strukture try catch aby wiadomosci byly wysylane rowniez gdy stage zakonczy sie niepowodzeniem  

![screen3.png](screen3.png)

![screen4.png](screen4.png)  
  
### Jenkinsfile: deploy

Zastosowana strategia zaklada wypchniecie obrazu do rejestru (dockerhub) a nastepnie w kroku deploy ssh na zdalnej maszyny, pobranie swiezego obrazu z dockerhub i uruchomienie go.

Szczegoly w Jenkinsfile. Zrzut ekranu z udanego deploya:

![screen5.png](screen5.png)