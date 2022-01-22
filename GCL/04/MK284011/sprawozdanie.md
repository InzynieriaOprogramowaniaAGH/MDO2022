1 .Stwórz Jenkinsfile: opis
 - Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile

    *Zdjecie1
 - Docelowo, ma zawierać etapy "Build" i "Test"
 - Przejściowo, może zawierać jeden etap "Build + Test"
 - Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
 - Początkowo, Jenkinsfile może być albo "wklejony" albo dodany do repozytorium, które jest sforkowane, to znaczy:
    albo pipeline zawiera treść Jenkinsfile'a
    albo forkujemy repozytorium wybranej aplikacji i dodajemy Jenkinsfile do niego
    *Zdjecie2

    Poniżej pipeline:

    pipeline {
  agent any 
  stages {
    stage('Build') { 
      steps {
        sh '''
        apt-get update
        apt-get install npm -y
        git clone https://github.com/evanshortiss/nodejs-testing-examples.git
        cd nodejs-testing-examples
        npm install
        '''
        }
    }
    stage('Test') { 
      steps {
        sh '''
        cd nodejs-testing-examples
        npm test
        '''
      }
    }
  }
}

2. Jenkinsfile: przebieg
https://www.jenkins.io/doc/book/pipeline/jenkinsfile/

Przykładowe zbiory czynności w Jenkinsfile: Jednokrokowy pipeline (Build i test), pobierający narzędzie docker-compose i uruchamiajacy docker compose up na kompozycji z poprzednich zajęć

    Początkowo użyłem wcześniejszego Jenkinsfile, który umieśiłem w repo.
    Następnie ustawiłem pipeline z poniżymi danymi:
    *Zdjecie3
    *Zdjecie4


3. Jenkinsfile: powiadomienia
Sekcja "post" dla każdego stage'a, informująca mailem o rezultacie