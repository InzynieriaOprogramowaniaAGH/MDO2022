# Zajęcia 04

## Wprowadzenie

### Zapewnij dostępność plików w gałęzi
`1.png`

### Stwórz Jenkinsfile: opis
* Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile `2.png`
* Docelowo, ma zawierać etapy "Build" i "Test" `3.png`
* Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
* Pipeline zawiera treść Jenkinsfile'a `4.png`
* Pomyślnie zbudowany pipeline `5.png`

### Jenkinsfile: powiadomienia
`6.png` `7.png`

### Jenkinsfile: deploy
* W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
* Różne podejścia są możliwe:
    * Build i test wykonywane "na zewnątrz" i jeżeli się powiodą, odpalany docker build, który tworzy kontener
    * Odpalane są kontenery budujący, testujący i końcowy
        * końcowy to budujący, ale z odpaloną aplikacją na końcu
        * końcowy to np. ubuntu z posłanym artefaktem z budującego

Po Nowym Roku, tydzień przed zajęciami, zrobimy sync zaawansowania prac i wybierzemy strategię.
