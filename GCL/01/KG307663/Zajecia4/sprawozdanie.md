# Zajęcia 04

## Wprowadzenie

### Zapewnij dostępność plików w gałęzi
`pipeline.png`

### Stwórz Jenkinsfile: opis
* Utwórz nowy pipeline budujący wybraną aplikację, oparty o Jenkinsfile `pipelinekod.png`
* Docelowo, ma zawierać etapy "Build" i "Test" `pipelinekod.png``
* Może, ale nie musi, budować się na dedykowanym DIND, ale może się to dziać od razu na kontenerze CI. Należy udokumentować funkcjonalną różnicę między niniejszymi podejściami
* Pipeline zawiera treść Jenkinsfile'a 
* Pomyślnie zbudowany pipeline 

* W moim pipeline zaczął występować problem z zbudowaniem, błąd dodaje na zrzucie ekranu.
`pipelineblad.png`
`timeline.png`

### Jenkinsfile: powiadomienia
`emailjenkinsfile.png` `konfiguracjamail.png`

### Jenkinsfile: deploy
* W razie sukcesu, build ma zostać wypromowany jako kandydat do wydania
* Różne podejścia są możliwe:
    * Build i test wykonywane "na zewnątrz" i jeżeli się powiodą, odpalany docker build, który tworzy kontener
    * Odpalane są kontenery budujący, testujący i końcowy
        * końcowy to budujący, ale z odpaloną aplikacją na końcu
        * końcowy to np. ubuntu z posłanym artefaktem z budującego

Po Nowym Roku, tydzień przed zajęciami, zrobimy sync zaawansowania prac i wybierzemy strategię.

*Budowanie za pomocą Jenkinsfile
`JenkinsFile.png`
