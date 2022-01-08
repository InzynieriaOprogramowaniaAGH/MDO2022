Imię i nazwisko: Julia Żychowska, kierunek: Informatyka Stosowana (NS), nr. indeksu: 307699, przedmiot: Metodyki DevOps, sprawozdanie: 04, grupa: GĆL03


## Sprawozdanie


### Wprowadzenie
#### 1. Zapewnij dostępność plików w gałęzi
   - skrypty, Docker files, kompozycje (jeżeli są), Jenkinsfile
      
        ![image](https://user-images.githubusercontent.com/28841971/147797643-35719f87-060f-4a79-94ce-9ff2202b294d.png)
      
   - dostępne w odpowiedniej gałęzi i katalogu w MDO2022

        ![image](https://user-images.githubusercontent.com/28841971/147797615-a47dcdca-4f0d-4146-8364-bc0657ec0e11.png)
        
   
  #### 2. Stwórz Jenkinsfile: opis
   - Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile
      
        Utworzyłam nowy projekt typu pipeline.
        
        ![image](https://user-images.githubusercontent.com/28841971/147797798-b71792fd-64fb-4eca-a8ce-9f167626e2f8.png)
        ![image](https://user-images.githubusercontent.com/28841971/147797825-93ec4a17-1323-400d-bcbe-a2d083e8d125.png)


   - Docelowo, ma zawierać etapy "Build" i "Test"

        Odrazu zdecydowałam się na odzielne etapy "Build" i "Test". 
        
        Pipeline
        ```
        pipeline {
          agent any 
          stages {
            stage('Build') { 
              steps {
                sh '''
                apt-get update
                apt-get install npm -y
                git clone https://github.com/philipwalton/easy-sauce.git
                cd easy-sauce 
                npm install
                '''
                }
            }
            stage('Test') { 
              steps {
                sh '''
                cd easy-sauce
                npm test
                '''
              }
            }
          }
        }
        ```
        sh ''' ''' pozwala na wprowadzenie poleceń terminalowych, w momencie gdy tego nie użyłam m.in nie wczytywało mi adresu do sklonowania repozytorium. 
        W momencie gdy uruchamiamy ponownie projekt to należy usunąć linijke ``` git clone https://github.com/philipwalton/easy-sauce.git ```, ponieważ mamy już sklonowane za pierwszym razem. 
        
        ![image](https://user-images.githubusercontent.com/28841971/148635648-56c68e27-a14e-47d2-97d7-46a7a4a69634.png)
        
        Próbując uzyskać działający Pipeline napotkałam dwa problemy, które utrudnily mi znacznie wykonanie zadania.
        
        1. Brak dostępu do użytkownika root.
        
           Początkowo zgodnie z poradnikami w internecie próbowałam zmieniać ustawienia w plikach konfiguracyjnych, ale nie przyniosły efektu. Problem dalej istniał, rozwiązałam go usunięciem z Dockerfile potrzebnego do utworzenia Jenkinsa linijki USER Jenkins.  
        
           ![image](https://user-images.githubusercontent.com/28841971/148635762-40d1b284-ee5e-4f7b-a8f9-7ef3cf086161.png)
        
     
        2. Błąd - czas został przekroczony.
        
           Problem występuje przy komendzie ```npm test```. Tutaj nie udało mi się naprawić problemu. Założyłam, że problem wynika z moją witrualną maszyną przez co zwiększalam pamięć RAM, co pomogło na chwile przy odpalaniu komendy w terminalu, ale po chwili sytuacja wróciła do poprzedniego stanu. Następnie zainstalowałam Ubuntu od nowa z większą ilością pamięci RAM oraz miejsca na dysku. Poza tym wszystko sie kompiluje, testy się uruchamiają, ale na samym końcu jest error związany z czasem. W terminalu mogłabym zwiększyć czas oczekiwania w pliku konfiguracyjnym, w Jenkins nie znalazłam możliwości.
        
           ![image](https://user-images.githubusercontent.com/28841971/148636185-59605edb-2969-449e-ad3f-f632c5a996ec.png)
           ![image](https://user-images.githubusercontent.com/28841971/148636219-8a1c6e34-19f9-4112-967c-2a5dc8d2abcb.png)
           ![image](https://user-images.githubusercontent.com/28841971/148636230-458d8954-ea42-4404-ae7d-3a9a4bf3fc20.png)
           ![image](https://user-images.githubusercontent.com/28841971/148636253-344e76f9-eb0e-465d-bde3-cbb3d6655758.png)

        
      - Przejściowo, może zawierać jeden etap "Build + Test"
      
        --------------------------------------------------
       
      - Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
      - Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:
      - albo pipeline zawiera treść Jenkinsfile'a
      - albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego
      
 #### 3. Jenkinsfile: przebieg
  
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
            
 #### 4. Jenkinsfile: powiadomienia
  
   - Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
      
 #### 5. Jenkinsfile: deploy
  
   - W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
   - Różne podejścia są możliwe:
      - Build i test wykonywane "na zewnątrz" i jeżeli się powiodą, odpalany docker build, który tworzy kontener
      - Odpalane są kontenery budujący, testujący i końcowy
         - końcowy to budujący, ale z odpaloną aplikacją na końcu
         - końcowy to np. ubuntu z posłanym artefaktem z budującego

