1.Wprowadzenie

Zapewnij dostępność plików w gałęzi

-skrypty, Docker files, kompozycje (jeżeli są), Jenkinsfile

-dostępne w odpowiedniej gałęzi i katalogu w MDO2022

![1](https://user-images.githubusercontent.com/58219271/151669864-066b59d3-f25e-4252-8844-3bc7eccdb034.JPG)
![2](https://user-images.githubusercontent.com/58219271/151669865-e3611b0c-1127-4650-8d64-18872f8382ad.JPG)


2.Stwórz Jenkinsfile: opis

-Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile

-Docelowo, ma zawierać etapy "Build" i "Test"

-Przejściowo, może zawierać jeden etap "Build + Test"

-Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami

-Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:

-albo pipeline zawiera treść Jenkinsfile'a


![3](https://user-images.githubusercontent.com/58219271/151669890-76f9fa7b-766a-41f2-8dc0-0581d3451d8a.JPG)

![8](https://user-images.githubusercontent.com/58219271/151669931-b4c335ef-9424-4a58-8f3b-75dc5251b28f.JPG)

![7](https://user-images.githubusercontent.com/58219271/151669946-c690844b-6037-438a-9ff4-26b1532f6bc7.JPG)
![6](https://user-images.githubusercontent.com/58219271/151669952-5fa42740-86e0-4319-af82-08fb2876ec88.JPG)

-albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego

![4](https://user-images.githubusercontent.com/58219271/151669967-9c9c97a8-ffac-45aa-b069-8e04ed43b696.JPG)

![5](https://user-images.githubusercontent.com/58219271/151669970-1b425d60-71d7-4ad2-b54e-318b4b187248.JPG)


3.Jenkinsfile: przebieg
https://www.jenkins.io/doc/book/pipeline/jenkinsfile/

-Przykładowe zbiory czynności w Jenkinsfile: Jednokrokowy pipeline (Build i test), pobierający narzędzie docker-compose i uruchamiajacy docker compose up na kompozycji z poprzednich zajęć

a)build + test

   -download docker-compose
   
   -compose up
   
b)build

   -git pull
   
   -npm install
   
   -npm build
   
   
c_test

   -npm test
   
   
   
4.Jenkinsfile: powiadomienia

  -Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
  
5.Jenkinsfile: deploy
  -W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
  -Różne podejścia są możliwe:
  -Build i test wykonywane "na zewnątrz" i jeżeli się powiodą, odpalany docker build, który tworzy kontener
  -Odpalane są kontenery budujący, testujący i końcowy
  -końcowy to budujący, ale z odpaloną aplikacją na końcu
  -końcowy to np. ubuntu z posłanym artefaktem z budującego
  -Po Nowym Roku, tydzień przed zajęciami, zrobimy sync zaawansowania prac i wybierzemy strategię.
    

