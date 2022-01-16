# Zajęcia 04

## Wprowadzenie 

### Zapewnij dostępność plików w gałęzi
 * skrypty, Docker files, kompozycje (jeżeli są), Jenkinsfile
 * dostępne w odpowiedniej gałęzi i katalogu w MDO2022
 
![image](https://user-images.githubusercontent.com/80592460/146652970-92e7125b-46b3-4dc0-aa37-c48291e7e6b8.png)

![image](https://user-images.githubusercontent.com/80592460/147775941-15b6f737-c6dc-4a44-85e8-bdaa7a60c625.png)

 
### Stwórz Jenkinsfile: opis
 * Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile
 * Docelowo, ma zawierać etapy "Build" i "Test"
 * Przejściowo, może zawierać jeden etap "Build + Test"
 * Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
 * Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:
  * albo pipeline zawiera treść Jenkinsfile'a

Jenkinsfile budujący i testujący (Build + Test jako jeden etap), jako skrypt w Jenkins

![image](https://user-images.githubusercontent.com/80592460/146653070-85dd679f-a9b4-41c2-9552-9124906ecb21.png)

 ![logi1](https://user-images.githubusercontent.com/80592460/146653039-c24b454e-c77c-4ea2-9e9b-a22be5385d26.PNG)
  
 ![logi2](https://user-images.githubusercontent.com/80592460/146653042-6e121242-529f-42a1-9f4b-e1e901666ea2.PNG)
 
 ![image](https://user-images.githubusercontent.com/80592460/146653083-41588fd0-207a-40c9-b827-d615dfb56942.png)
 
 
  * albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego 
  
Jak wyżej tylko Jenkinsfile wzięty z repo:
 
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

Konfiguracja powiadomień:

![18](https://user-images.githubusercontent.com/80592460/147778502-e50772dd-ed8d-42ca-b7a5-2824cb0018a0.png)

Jenkinsfile z etapami budującym i testującym jako skrypt w Jenkins, ale pobierający dockerfile build i dockerfile test z mojego repo
 
 ![image](https://user-images.githubusercontent.com/80592460/147771646-3ad74ec8-b16e-49b5-b802-0b5ef879f3bc.png)
 
 ![image](https://user-images.githubusercontent.com/80592460/147771965-08ed3b4d-22de-4017-a850-32e417a7a431.png)

 ![image](https://user-images.githubusercontent.com/80592460/147775129-492acd47-bd69-410f-a7b4-7869ceae4645.png)

 
 Fork repozytorium z Jenkinsfilem w środku 

![image](https://user-images.githubusercontent.com/80592460/147775432-99908a62-ef7c-4597-aafe-1d4d40e9b2c2.png)

![image](https://user-images.githubusercontent.com/80592460/147775453-fae07faa-5733-4035-b437-6b94127d8a54.png)

![image](https://user-images.githubusercontent.com/80592460/147775335-e5174771-f403-423b-87ae-b058aff94283.png)

![image](https://user-images.githubusercontent.com/80592460/147775576-5daefb64-e0d7-4585-ada3-3ea2c05308d0.png)

![image](https://user-images.githubusercontent.com/80592460/147591474-0c7704cd-bbe6-418e-870b-76313b59ea7a.png)

  
### Jenkinsfile: deploy
 * W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
 * Różne podejścia są możliwe:
   * Build i test wykonywane "na zewnątrz" i jeżeli się powiodą, odpalany docker build, który tworzy kontener 
   * Odpalane są kontenery budujący, testujący i końcowy
     * końcowy to budujący, ale z odpaloną aplikacją na końcu
	 * końcowy to np. ubuntu z posłanym artefaktem z budującego
	 
Credentials umożliwiający zalogowanie do Docker Hub w celu zrobienia push'a, w tym celu dodany Global Credential, później w skrypcie Pipeline environment z odwołaniem do crenentials, następnie dodany stage 'Login' oraz stage 'Deploy', w którym tworzymy taga i robimy push'a do stworzonego wcześniej repozytorium.

Tag i push zgodnie z instrukcją z Docker Hub:

![19](https://user-images.githubusercontent.com/80592460/147781334-9c872d6b-fb23-4578-a3ed-e248d3141ea2.png)

![image](https://user-images.githubusercontent.com/80592460/147781196-e4d7372d-dd4f-4b92-943e-cb5ea8433f7f.png)

![image](https://user-images.githubusercontent.com/80592460/147781106-79e7b672-76e6-4dda-8dd5-e1e45fa3d432.png)
	 
![image](https://user-images.githubusercontent.com/80592460/147781037-7dab74f7-5e4d-4b5d-88a5-f7c0da3d1774.png)

![image](https://user-images.githubusercontent.com/80592460/147781436-c9755d1f-2c08-4b28-b8fa-3b881924b9dc.png)

![image](https://user-images.githubusercontent.com/80592460/147781479-24f7d617-f34a-4ae9-8eba-4f6dd3749a55.png)

![image](https://user-images.githubusercontent.com/80592460/147781698-6555a4a3-0e3c-43a6-9db7-ab42c47e0f15.png)

To samo tylko Jenkinsfile z Githuba:

![image](https://user-images.githubusercontent.com/80592460/147817367-ac1a5de0-61af-4ac1-b838-5af98309006c.png)

![image](https://user-images.githubusercontent.com/80592460/147817396-7714549d-2067-47c6-beb6-62681892abe5.png)

![image](https://user-images.githubusercontent.com/80592460/147817414-94a37ee3-b649-4768-a467-334d6d6a1c8b.png)

![image](https://user-images.githubusercontent.com/80592460/147817441-1c679033-b085-43d0-8d18-13c74ece579f.png)

Czasy budowania zawsze są znacznie krótsze niż przy ręcznie pisanym skrypcie w Pipeline

