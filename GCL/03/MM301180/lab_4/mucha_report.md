# Zajęcia 04

## Wprowadzenie

### Zapewnij dostępność plików w gałęzi
* skrypty, Docker files, kompozycje (jeżeli są), Jenkinsfile

https://github.com/michalmuchakr/docker-react-test-app

* dostępne w odpowiedniej gałęzi i katalogu w MDO2022
`[img] screen_1`

### Stwórz Jenkinsfile: opis
* Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile

`[img] screen_2`

* Docelowo, ma zawierać etapy "Build" i "Test"

* Przejściowo, pipeline napisany bezpośrednio w jenkins
  `[img] screen_3 - 4`

### Jenkinsfile: przebieg
* Przykładowe zbiory czynności w Jenkinsfile:
    * build
        * git pull
        * npm install
        * npm build
    * test
        * npm test
`[img] screen_5 - 6`

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
