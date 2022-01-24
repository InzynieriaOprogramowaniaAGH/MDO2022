### Jenkinsfile: przebieg
https://www.jenkins.io/doc/book/pipeline/jenkinsfile/

Poczatkowe kroki to build i test.

Build - budujemy obraz z Dockerfile.
Test - budujemy obraz z Dockerfile i go testujemy.
    
![screen1.png](screen1.png)

![screen2.png](screen2.png)

Nastepne kroki do publish i deploy.

Publish - budujemy obraz z Dockerfile (wyciaganie artefaktow z obrazu 'build') oraz wrzucamy go do dockerhuba.
Deploy - ssh do maszyny zdalnej, pobranie obrazu z kroku 'publish', uruchomienie obrazu.
    
![screen6.png](screen6.png)

![screen7.png](screen7.png)

Pipeline CI/CD po poprawnym zbudowaniu

![screen8.png](screen8.png)

### Jenkinsfile: powiadomienia
  * Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
  
W Jenkinsfile zastosowano strukture try catch aby wiadomosci byly wysylane rowniez gdy stage zakonczy sie niepowodzeniem  

![screen3.png](screen3.png)

![screen4.png](screen4.png)  
  
### Jenkinsfile: deploy

Zastosowana strategia zaklada wypchniecie obrazu do rejestru (dockerhub) a nastepnie w kroku deploy ssh na zdalnej maszyny, pobranie swiezego obrazu z dockerhub i uruchomienie go.

Szczegoly w Jenkinsfile. Zrzut ekranu z udanego deploya:

![screen5.png](screen5.png)