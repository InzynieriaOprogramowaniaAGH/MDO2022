# Zajęcia 04

## Wprowadzenie 

### Zapewnij dostępność plików w gałęzi
 * skrypty, Docker files, kompozycje (jeżeli są), Jenkinsfile
 * dostępne w odpowiedniej gałęzi i katalogu w MDO2022
 
![image](https://user-images.githubusercontent.com/80592460/146652970-92e7125b-46b3-4dc0-aa37-c48291e7e6b8.png)
 
### Stwórz Jenkinsfile: opis
 * Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile
 * Docelowo, ma zawierać etapy "Build" i "Test"
 * Przejściowo, może zawierać jeden etap "Build + Test"
 * Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
 * Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:
  * albo pipeline zawiera treść Jenkinsfile'a

![image](https://user-images.githubusercontent.com/80592460/146653070-85dd679f-a9b4-41c2-9552-9124906ecb21.png)

 ![logi1](https://user-images.githubusercontent.com/80592460/146653039-c24b454e-c77c-4ea2-9e9b-a22be5385d26.PNG)
  
 ![logi2](https://user-images.githubusercontent.com/80592460/146653042-6e121242-529f-42a1-9f4b-e1e901666ea2.PNG)
 
 ![image](https://user-images.githubusercontent.com/80592460/146653083-41588fd0-207a-40c9-b827-d615dfb56942.png)

 
  * albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego 
 
![image](https://user-images.githubusercontent.com/80592460/146653023-e2386afc-bd51-4026-87b0-f86bd76b8c5f.png)

![logi_jeckinsfile_na_github1](https://user-images.githubusercontent.com/80592460/146653047-5134a952-c9a4-4ae2-ad88-46d02bb95803.PNG)

![logi_jeckinsfile_na_github2](https://user-images.githubusercontent.com/80592460/146653049-0a55a790-9f6c-43b9-b20d-15226f58ce6a.PNG)

![image](https://user-images.githubusercontent.com/80592460/146653097-a5407a19-0c66-4b9c-b391-2b7cada76f6c.png)



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
	
### Jenkinsfile: powiadomienia
  * Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
  
### Jenkinsfile: deploy
 * W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
 * Różne podejścia są możliwe:
   * Build i test wykonywane "na zewnątrz" i jeżeli się powiodą, odpalany docker build, który tworzy kontener 
   * Odpalane są kontenery budujący, testujący i końcowy
     * końcowy to budujący, ale z odpaloną aplikacją na końcu
	 * końcowy to np. ubuntu z posłanym artefaktem z budującego
	 
Po Nowym Roku, tydzień przed zajęciami, zrobimy sync zaawansowania prac i wybierzemy strategię.
