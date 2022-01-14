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
                git clone https://github.com/Lila367/easy-sauce.git
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
        
           Problem występował przy komendzie ```npm test```. Założyłam, że problem wynika z moją witrualną maszyną przez co zwiększalam pamięć RAM, co pomogło na chwile przy odpalaniu komendy w terminalu, ale po chwili sytuacja wróciła do poprzedniego stanu. Następnie zainstalowałam Ubuntu od nowa z większą ilością pamięci RAM oraz miejsca na dysku. Poza tym wszystko sie kompiluje, testy się uruchamiają, ale na samym końcu jest error związany z czasem. 
        
           ![image](https://user-images.githubusercontent.com/28841971/148636185-59605edb-2969-449e-ad3f-f632c5a996ec.png)
           ![image](https://user-images.githubusercontent.com/28841971/148636219-8a1c6e34-19f9-4112-967c-2a5dc8d2abcb.png)
           ![image](https://user-images.githubusercontent.com/28841971/148636230-458d8954-ea42-4404-ae7d-3a9a4bf3fc20.png)
           ![image](https://user-images.githubusercontent.com/28841971/148636253-344e76f9-eb0e-465d-bde3-cbb3d6655758.png)
           
           Według otrzymanych wskazówek zrobiłam fork repozytorium wybranej aplikacji, w folderze test wkleiłam otrzymany tests.patch i użyłam komendy ```patch -u easy-sauce-test.js -i tests.patch```, która wprowadziła komentasz do pliku easy-sauce-test.js. Początkowo i to u mnie nie działało, ale problemem też okazał się błąd instalacji npm, odinstalowałam node.js, npm i zainstalowałam ponownie. Dodatkowo potrzebny był reset maszyny. Następnie wysłałam zmiany do repozytorium. Testy wykonują się tylko raz po resecie. W Jenkins nie działał nadal. Korzystając z Dockerfile w pipeline wystąpił kolejny problem:
           
           ![image](https://user-images.githubusercontent.com/28841971/149419006-eff0fe58-36b1-46ba-8433-1bded3433e1e.png)
           
           Problemem okazała się moja wirtualna maszyna zawierająca Ubuntu. Gdy powróciłam w celach testowych do pierwszej postawionej maszyny okazało się, że z użyciem Dockerfile testy działają, bez nie. 
        
      - Przejściowo, może zawierać jeden etap "Build + Test"
      
        --------------------------------------------------
       
      - Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
        
        --------------------------------------------------
        
      - Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:
         - albo pipeline zawiera treść Jenkinsfile'a
         
            Stworzyłam pipeline, który zawiera Jenkinsfile. Klonuje to repozytorium i wykorzystuję Dockerfiles.  
        
              ```
              pipeline {
               agent any
               stages {
               stage('Build') { 
                  steps {
                  sh'''
                  apt-get update
                  git clone https://github.com/InzynieriaOprogramowaniaAGH/MDO2022.git
                  cd MDO2022
                  git checkout JZ307699
                  cd GCL/03/JZ307699/Lab_4
                  docker build -t easy/sauce:latest .
                  cd Dockerfile2
                  docker build -t easy/sauce2:latest .
                  '''
               }
              }
              stage('Test') { 
                  steps {
                      sh '''
                      docker run easy/sauce2:latest
                      '''
                  }
              }
              }
            }
            ```
            
            ![image](https://user-images.githubusercontent.com/28841971/149571327-09a6001c-01eb-4aaa-a91d-6f3697f43c5b.png)
            ![image](https://user-images.githubusercontent.com/28841971/149571271-dae92911-a911-41fa-b003-a9f25cbbe951.png)

        
         - albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego

            Następnie wykorzystałam wcześniej zrobiony fork repozytorium i zamieściłam tam Jenkinsfile oraz Dockerfiles.
            
            ```
            pipeline {
             agent any
             stages {
                 stage('Build') { 
                     steps {
                         sh'''
                         docker build -t easy/sauce:latest .
                         cd Dockerfile2
                         docker build -t easy/sauce2:latest . -f Dockerfile2
                         '''
                     }
                 }
                 stage('Test') { 
                     steps {
                         sh '''
                         docker run easy/sauce2:latest
                         '''
                     }
                 }
             }
           }
           ```
           
           
        
        
      
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
            
       Jenkinsfile jaki stworzyłam korzystający z Dockerfile umieściłam w tym repozytorium. Odwołuje się on do wcześniej stworzonych Dockerfiles. Jedyną zmianą jaką zrobiłam w Dockerfile była zmiana klonowania repozytorium. Zamiast repozytorium wybranej aplikacji umieściłam link do sforkowanego repozytorium tej aplikacji. W Jenkinsfile użyłam osobnych etapów dla build i test. 
       
       ```
       pipeline {
         agent any
            stages {
               stage('Build') { 
               steps {
                sh'''
                ls
                cd GCL/03/JZ307699/Lab_4
                ls
                docker build -t easy/sauce:latest .
                cd Dockerfile2
                docker build -t easy/sauce2:latest .
                '''
                //docker build -t easy/sauce:latest . -f Dockerfile
                //                cd Dockerfile2
                //docker build -t easy/sauce2:latest .
               }
            }
         stage('Test') { 
            steps {
                sh '''
                docker run easy/sauce2:latest
                '''
            }
         }   
         }
       }
       ```
        ![image](https://user-images.githubusercontent.com/28841971/149557117-bd5ce543-3753-4291-9663-e2f9ea2e2e4f.png)
        ![image](https://user-images.githubusercontent.com/28841971/149557163-a9e5155a-a31b-4c58-8d0b-d9c0aaf4b19f.png)
        ![image](https://user-images.githubusercontent.com/28841971/149557203-7982d6bc-b0aa-416b-8bde-e22064fe84af.png)

        ![image](https://user-images.githubusercontent.com/28841971/149557276-2dce1aba-80ac-44c8-abc0-4d5615ed14e7.png)

        ![image](https://user-images.githubusercontent.com/28841971/149557347-2777f01c-5136-43a3-bb85-3574fffa475f.png)

 #### 4. Jenkinsfile: powiadomienia
  
   - Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie
      
 #### 5. Jenkinsfile: deploy
  
   - W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
   - Różne podejścia są możliwe:
      - Build i test wykonywane "na zewnątrz" i jeżeli się powiodą, odpalany docker build, który tworzy kontener
      - Odpalane są kontenery budujący, testujący i końcowy
         - końcowy to budujący, ale z odpaloną aplikacją na końcu
         - końcowy to np. ubuntu z posłanym artefaktem z budującego

