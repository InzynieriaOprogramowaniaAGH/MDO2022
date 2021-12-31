Imię i nazwisko: Julia Żychowska, kierunek: Informatyka Stosowana (NS), nr. indeksu: 307699, przedmiot: Metodyki DevOps, sprawozdanie: 04, grupa: GĆL03


Sprawozdanie


Wprowadzenie
  1. Zapewnij dostępność plików w gałęzi
      - skrypty, Docker files, kompozycje (jeżeli są), Jenkinsfile
      
        ![image](https://user-images.githubusercontent.com/28841971/147797643-35719f87-060f-4a79-94ce-9ff2202b294d.png)
      
      - dostępne w odpowiedniej gałęzi i katalogu w MDO2022

        ![image](https://user-images.githubusercontent.com/28841971/147797615-a47dcdca-4f0d-4146-8364-bc0657ec0e11.png)
        
   
  2. Stwórz Jenkinsfile: opis
      - Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile
      
        Utworzyłam nowy projekt typu pipeline.
        
          ![image](https://user-images.githubusercontent.com/28841971/147797798-b71792fd-64fb-4eca-a8ce-9f167626e2f8.png)
          ![image](https://user-images.githubusercontent.com/28841971/147797825-93ec4a17-1323-400d-bcbe-a2d083e8d125.png)

        
      
      - Docelowo, ma zawierać etapy "Build" i "Test"
      - Przejściowo, może zawierać jeden etap "Build + Test"
      - Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
      - Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:
      - albo pipeline zawiera treść Jenkinsfile'a
      - albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego
      
  3. Jenkinsfile: przebieg
  
     https://www.jenkins.io/doc/book/pipeline/jenkinsfile/
      - Przykładowe zbiory czynności w Jenkinsfile: Jednokrokowy pipeline (Build i test), pobierający narzędzie docker-compose i uruchamiajacy docker compose up na kompozycji z poprzednich zajęć
        - build + test
            - download docker-compose
            - compose up
        - build
            - git pull
            - npm install
            - npm build
        - test
            - npm test
            
  4. Jenkinsfile: powiadomienia
  
      - Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
      
  5. Jenkinsfile: deploy
  
      - W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
      - Różne podejścia są możliwe:
        - Build i test wykonywane "na zewnątrz" i jeżeli się powiodą, odpalany docker build, który tworzy kontener
        - Odpalane są kontenery budujący, testujący i końcowy
           - końcowy to budujący, ale z odpaloną aplikacją na końcu
           - końcowy to np. ubuntu z posłanym artefaktem z budującego

