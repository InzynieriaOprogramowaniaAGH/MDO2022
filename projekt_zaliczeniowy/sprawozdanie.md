1. Sprawdzono wersje dockera 

2. Sprawdzono wersje obrazu ubuntu 

![1](Screenshot_1.png)

3. Zainstalowano busyboxa
 
![1](Screenshot_2.png)

4. Uruchomiono busyboxa, sprawdzono zawartosc i wylaczono dockery 

![1](Screenshot_3.png)
![1](Screenshot_4.png)

Uruchomiono dockera z ubuntu

![1](Screenshot_5.png)

zainstalowano zaleznosci 

![1](Screenshot_6.png)
![1](Screenshot_7.png)

Wybrano program i pobrano go https://github.com/aria2/aria2.git

![1](Screenshot_8.png)

Zbudowano aplikacje zgodnie z README 

![1](Screenshot_9.png)

./configure 

![1](Screenshot_10.png)

make

![1](Screenshot_11.png)

Nastepnie uruchomiono testy make check 

![1](Screenshot_12.png)

Dla automatyzacji tworzenia powyższych kroków stworzono Dockerfile'a. 

![1](Screenshot_14.png)
![1](docscr.png)

Stworzono drugi Dockefile wywołujący pierwszego natomiast z dodanymi testami

![1](Screenshot_15.png)

Uruchomiono dockerfile 

![1](Screenshot_16.png)
![1](Screenshot_17.png)

Stworzono plik docker-compose

![1](Screenshot_18.png)


### AUTOMATYZATOR JENKINS z zalaczonej instrukcji 

![1](Screenshot_19.png)

Stworzono nowy dockerfile i zbudowano nowy obraz

![1](Screenshot_20.png)
![1](Screenshot_21.png)

Po zbudowaniu obrazu uruchamiamy go w kontenerze 

![1](Screenshot_22.png)

Zalogowano sie do Jenkinska uzwyajac wygenerowanego hasla 

![1](jenkins rejestracja.png)
![1](jenkins rejstracja2.png)

Stworzono pipeline i uruchomiono 
```

pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh '''
                rm -rf MDO2022
                git clone https://github.com/InzynieriaOprogramowaniaAGH/MDO2022.git
                cd MDO2022
                git checkout BS292981
                cd projekt_zaliczeniowy
                docker build -t aria2:latest . -f ./dockerfile1
                '''
            }
        }
        stage('Test') {
            steps {
                sh '''
                cd MDO2022/projekt_zaliczeniowy
                docker build -t aria2_test:latest . -f ./dockerfile-test
                '''
            }
        }
    }
}

```
![1](pipline_img.png)
![1](pipeline2.png)
![1](bluocean.png)
![1](blue2.png)

pobrano logi i artifact 

![1](log_artefact.png)

Zaktualizowano pakiety systemowe
apt update

apt upgrade
![1](update.png)
![1](upgrade.png)

Zainstalowano pakiet curl
apt install curl

![1](curl.png)

Pobranie najnowszej wersji kubectl
curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl

![1](curl2.png)

Dodanie uprawnień do uruchamiania
chmod +x ./kubectl

Dodanie kuble ctl do ścieżki - PATH
sudo mv ./kubectl /usr/local/bin/kubectl

Sprawdzenie wersji
kubectl version --client

instalalowano virtualboxa

![1](Screenshot_22.png)






