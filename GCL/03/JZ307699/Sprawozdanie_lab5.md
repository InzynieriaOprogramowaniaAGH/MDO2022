Imię i nazwisko: Julia Żychowska, kierunek: Informatyka Stosowana (NS), nr. indeksu: 307699, przedmiot: Metodyki DevOps, sprawozdanie: 05, grupa: GĆL03


## Sprawozdanie


### Zestawienie platformy Kubernetes
  - Upewnij się, że kontener jest dostępny
  - Zainstaluj wymagania wstępne dla środowiska Minikube
  
      Zainstalowałam kind poleceniem:
      ```curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.11.1/kind-linux-amd64```
      
      ![image](https://user-images.githubusercontent.com/28841971/149644855-984d41b4-b12e-40ed-a28c-2ecf25a263f3.png)
      
  - Zainstaluj minikube i kubectl
      Do zainstalowania minikube użyłam następujących poleceń:    
      ```curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \ && chmod +x minikube```
      ```sudo cp minikube /usr/local/bin && rm minikube```
    
      ![image](https://user-images.githubusercontent.com/28841971/149644035-464e23ca-c7b2-45c6-a277-a336bf73eb31.png)
    
      Następnie uruchomiłam minikube poleceniem ```minikube start```
    
      ![image](https://user-images.githubusercontent.com/28841971/149644709-e1b55af8-e1ea-4ffa-a1be-f32cac5dccff.png)
    
      Do zainstalowania kubectl użyłam następujących poleceń:
      ```curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"```
      ```curl -LO "https://dl.k8s.io/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl.sha256"```
      ```echo "$(<kubectl.sha256)  kubectl" | sha256sum --check // sprawdzenie poprawności```
      ```sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl```
      ```kubectl version --client // sprawdziłam wersje```
      ```sudo apt-get update```
      ```sudo apt-get install -y apt-transport-https ca-certificates curl```
      ```sudo curl -fsSLo /usr/share/keyrings/kubernetes-archive-keyring.gpg https://packages.cloud.google.com/apt/doc/apt-key.gpg```
      ```echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee/etc/apt/sources.list.d/kubernetes.list```
      ```sudo apt-get update```
      ```sudo apt-get install -y kubectl```
      
      ![image](https://user-images.githubusercontent.com/28841971/149643799-cca721fe-6b74-413f-baba-1734b2f9f1b4.png)
      ![image](https://user-images.githubusercontent.com/28841971/149643805-89f3f114-2c57-46c4-a169-d6e9d2ecb9b7.png)
      ![image](https://user-images.githubusercontent.com/28841971/149643807-24010553-1973-4008-b539-5e3d96569e58.png)
      ![image](https://user-images.githubusercontent.com/28841971/149643812-78688ed5-bdc0-485b-b8f4-1df43c619fa4.png)
      ![image](https://user-images.githubusercontent.com/28841971/149643828-672abeb3-5a4d-49b4-8950-e55723427c39.png)
      
      Na koniec sprawdziłam poprawność konfiguracji poleceniem: ```kubectl cluster-info```
      
      ![image](https://user-images.githubusercontent.com/28841971/149644765-28260580-be39-4910-a57f-03de1f236ece.png)
      
      Po przejściu na adres url wyświetliło się:
      
      ![image](https://user-images.githubusercontent.com/28841971/149644869-c3029fd6-bf2a-406d-99b2-d45da2ab2e2e.png)
    
  - Przedstaw uruchomione oprogramowanie wstępne (i usługi)
      - Platforma konteneryzacji
      - Otwarte porty
      - Stan Dockera
      kubectl create deployment hello-node --image=k8s.gcr.io/echoserver:1.4
      kubectl get deployments
      kubectl get pods
      kubectl get events
      kubectl config view
      
      ![image](https://user-images.githubusercontent.com/28841971/149645240-ec30c979-712e-4c95-91bb-6f0c742d1090.png)
      ![image](https://user-images.githubusercontent.com/28841971/149645248-d28c9849-4611-4a5d-8784-b908457eb8a1.png)
      ![image](https://user-images.githubusercontent.com/28841971/149645264-2780267e-f0dc-46b5-8a64-3f6c212c4c3e.png)

      ![image](https://user-images.githubusercontent.com/28841971/149645304-16505764-b9a0-44c8-87b0-eaf1e72a7cc6.png)

      ![image](https://user-images.githubusercontent.com/28841971/149645306-e2ad94c2-1f51-45a4-84ad-304a32fe0715.png)
      
      ![image](https://user-images.githubusercontent.com/28841971/149645350-c42b9d19-8923-4929-898b-5be8d126ff83.png)
      
      ![image](https://user-images.githubusercontent.com/28841971/149645364-260a9a73-d5a0-4edb-a4a8-ec73f3e815ea.png)



### Stan Minikube
  - Uruchom Minikube Dashboard
    
      Następnie uruchomiłam ```minikube dashboard --url```, po przejściu na stronę:
      
      ![image](https://user-images.githubusercontent.com/28841971/149644992-188e5cb2-7f81-489e-970c-364d7178042f.png)
    
  - Wyświetl działające usługi (k8s) i wdrożenia
  - Wyświetl dostępne wdrożenia (stan "przed")
  
  
  ![image](https://user-images.githubusercontent.com/28841971/149645383-ebdccbf0-804c-4059-89c0-316071e2e82c.png)
  ![image](https://user-images.githubusercontent.com/28841971/149645388-7a66f10c-f903-4653-b3bc-9fefbb07934b.png)


### Wdrożenie kontenera via k8s
  - Wdróż przykładowy deployment "hello k8s": k8s.gcr.io/echoserver
  - Użyj kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr
  - Przekieruj porty
  - Wykaż że wdrożenie nastąpiło
  - W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia

### Deployment
  - Utwórz plik YAML z "deploymentem" k8s
  - Zestaw 4 repliki, opisz zalety i wady takiej liczby
  - Zaaplikuj wdrożenie via kubectl apply -f plik.yml
  - Wykaż przeprowadzony deployment
