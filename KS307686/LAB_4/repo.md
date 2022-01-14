# Zajęcia 04

## Wprowadzenie 

### Zapewnij dostępność plików w gałęzi
 * skrypty, Docker files są dostępne w odpowiedniej gałęzi i katalogu w MDO2022
 
### Stwórz Jenkinsfile: opis
 * Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile
 * Docelowo, ma zawierać etapy "Build" i "Test" 
 
  * albo pipeline zawiera treść Jenkinsfile'a
   * ![image](ss/ss1.png)
   * ![image](ss/ss2.png)
 
### Jenkinsfile: przebieg
https://www.jenkins.io/doc/book/pipeline/jenkinsfile/
* Przykładowe zbiory czynności w Jenkinsfile:
Jednokrokowy pipeline (Build i test), pobierający narzędzie docker-compose i uruchamiajacy docker compose up na kompozycji z poprzednich zajęć
  * build + test
    * download docker-compose
	* compose up
To się nie udało, przy próbie instalacji docker-compose zawsze miałem odmowę dostępu do katalogu...
Więc wróciłem do opcji Buld i test na oddzielnym dockerfile:

![image](ss/ss3.png)
![image](ss/ss4.png)
### Jenkinsfile: powiadomienia
  * Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
  *   ![image](ss/ss6.png)
  *   ![image](ss/ss5.png)
### Jenkinsfile: deploy 
