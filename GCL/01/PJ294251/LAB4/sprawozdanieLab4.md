

# Zajęcia 04

## Wprowadzenie

### Zapewnij dostępność plików w gałęzi

-   skrypty, Docker files, kompozycje (jeżeli są), Jenkinsfile
-   dostępne w odpowiedniej gałęzi i katalogu w MDO2022

### Stwórz Jenkinsfile: opis

-   Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile
-   Docelowo, ma zawierać etapy "Build" i "Test"
-   Przejściowo, może zawierać jeden etap "Build + Test"
-   Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
-   Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:
-   albo pipeline zawiera treść Jenkinsfile'a
-   albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego

#### Tworzymy pipeline

![1](imgs/1.png)

![2](imgs/2.png)

### Jenkinsfile: przebieg

[https://www.jenkins.io/doc/book/pipeline/jenkinsfile/](https://www.jenkins.io/doc/book/pipeline/jenkinsfile/)

-   Przykładowe zbiory czynności w Jenkinsfile: Jednokrokowy pipeline (Build i test), pobierający narzędzie docker-compose i uruchamiajacy docker compose up na kompozycji z poprzednich zajęć
    -   build + test
        
        -   download docker-compose
        -   compose up
    -   build
        
        -   git pull
        -   npm install
        -   npm build
    -   test
        
        -   npm test


Następne kroki do publish i deploy.

Publish - budujemy obraz z Dockerfile (wyciąganie artefaktów z obrazu 'build') oraz wrzucamy go do dockerhuba. Deploy - ssh do maszyny zdalnej, pobranie obrazu z kroku 'publish', uruchomienie obrazu.

![3](imgs/3.png)

Pipeline CI/CD po poprawnym zbudowaniu

![4](imgs/4.png)
![5](imgs/5.png)
![6](imgs/6.png)
![7](imgs/7.png)
![8](imgs/8.png)
![9](imgs/9.png)

### Jenkinsfile: powiadomienia
  * Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
  ![10](imgs/10.png)
  ![11](imgs/11.png)
  ![12](imgs/12.png)