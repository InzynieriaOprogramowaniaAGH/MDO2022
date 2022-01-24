Imię i nazwisko: Julia Żychowska, kierunek: Informatyka Stosowana (NS), nr. indeksu: 307699, przedmiot: Metodyki DevOps, sprawozdanie: 05, grupa: GĆL03


## Sprawozdanie


### Zestawienie platformy Kubernetes
  - Upewnij się, że kontener jest dostępny
  - Zainstaluj wymagania wstępne dla środowiska Minikube
     
     Po użyciu polecenia ```minikube start```, musiałam dorobić odpowiednie wymagania:
       - zmienić liczbę rdzeni procesora w ustawieniach na wirtualnej maszynie z 1 na 2,
       
         ![image](https://user-images.githubusercontent.com/28841971/150859325-4a765f70-e650-4522-8fb3-0f3a1c570d97.png)
          
       - dodać użytkownika do grupy docker poleceniem ```sudo usermod -aG docker ŞUSER && newgrp docker``` w terminalu
       - oraz zatrzymać docker service (```sudo service docker stop```) skasować pliki w val/lib/docker (```sudo rm -rf /var/lib/docker```) i uruchomić na nowo docker service (```sudo service docker start```)
      
  - Zainstaluj minikube i kubectl
      Do zainstalowania minikube użyłam następujących poleceń:    
      ```curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \ && chmod +x minikube```
      ```sudo cp minikube /usr/local/bin && rm minikube```
    
      ![image](https://user-images.githubusercontent.com/28841971/149644035-464e23ca-c7b2-45c6-a277-a336bf73eb31.png)
    
      Następnie uruchomiłam minikube poleceniem ```minikube start``` po wykonaniu wcześniejszych wymagań.
    
      ![image](https://user-images.githubusercontent.com/28841971/150860492-452b69c8-5d58-4fe7-9569-9df81caf548d.png)
    
      Minikube może pobrać odpowiednią wersję kubectl i można z niej korzystać przez dodanie minikube przed kubectl. Sprawdziłam w ten sposób dostęp do nowego klastera poleceniem ```minikube kubectl -- get po -A```. W ramach ułatwienia ustawiłam na alias na kubectl = minikube kubectl poleceniem ```alias kubectl="minikube kubectl --"``` 
      
      ![image](https://user-images.githubusercontent.com/28841971/150861743-3216c550-ece4-43ea-8cf4-8dc89cfb3440.png)
      
      Mimo zrobienia tego i tak nie chciały mi działać komenty kubectl'a więc zainstalowałam kubectl poleceniami:
      ```curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"```
      
      ```sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl```
      
      ![image](https://user-images.githubusercontent.com/28841971/150866351-5481cdeb-45f3-4d79-a973-d5d6579015d2.png)
    
  - Przedstaw uruchomione oprogramowanie wstępne (i usługi)
      - Platforma konteneryzacji
      - Otwarte porty
      - Stan Dockera

        ```kubectl get deployments```
        ```kubectl get pods```
        ```kubectl get events```
      
        ![image](https://user-images.githubusercontent.com/28841971/150867104-a6fa58bd-ca86-47d5-afc4-def0ade39763.png)
        
        ```kubectl config view```
        
        ![image](https://user-images.githubusercontent.com/28841971/150867164-c6833ccf-6d41-4f0b-9bbb-62f15eb09818.png)

        ![image](https://user-images.githubusercontent.com/28841971/150867193-a56e054b-0a9c-422c-b9f0-3cace964c8ea.png)
      
        ```kubectl get services```
      
        ```kubectl api-resources```
        
        ![image](https://user-images.githubusercontent.com/28841971/150867351-d437471b-c789-48da-a74f-20b2764594ba.png)
        ![image](https://user-images.githubusercontent.com/28841971/150867403-2685ce4e-4b20-4a15-86f6-426490c482fe.png)
        ![image](https://user-images.githubusercontent.com/28841971/150867455-7413e9a4-d79a-45a7-8194-a92c274b4e20.png)
        ![image](https://user-images.githubusercontent.com/28841971/150867500-a77c53d9-5087-4973-a00e-57eb5cb00b11.png)
        ![image](https://user-images.githubusercontent.com/28841971/150867531-8ea6f691-24de-411b-8d13-39ea415e5e39.png)
        ![image](https://user-images.githubusercontent.com/28841971/150867561-e3c1c63e-4f35-48e3-a361-16d0b4e0be03.png)

### Stan Minikube
  - Uruchom Minikube Dashboard
    
      Następnie uruchomiłam ```minikube dashboard```
      
      ![image](https://user-images.githubusercontent.com/28841971/150863889-563f2a39-5cee-4498-a95e-18ed8ff4d245.png)
      
      po przejściu na stronę:
      
      ![image](https://user-images.githubusercontent.com/28841971/150864308-6b288037-3c99-46d2-a62f-e06148ff7d08.png)
    
  - Wyświetl działające usługi (k8s) i wdrożenia
  
    Usługi:
    
    ![image](https://user-images.githubusercontent.com/28841971/150864693-e07779a5-de45-424d-b72e-0ce922294946.png)
    
    Wdrożenia:
    
    ![image](https://user-images.githubusercontent.com/28841971/150864386-89472f6d-374d-4071-a74b-dfd857de049b.png)
    
  - Wyświetl dostępne wdrożenia (stan "przed")
    
    Wyświetliłam dostępne wdrożenia jeszcze z terminala poleceniem ```kubectl get deployments```
    ![image](https://user-images.githubusercontent.com/28841971/150866811-760aedb1-2450-4918-b8a7-b2134ace932b.png)

### Wdrożenie kontenera via k8s
  - Wdróż przykładowy deployment "hello k8s": k8s.gcr.io/echoserver
  
    Poleceniem ``` kubectl create deployment hello-k8s --image=k8s.gcr.io/echoserver``` wzdrożyłam deployment hello-k8s 
    
    ![image](https://user-images.githubusercontent.com/28841971/150868284-81f5fa22-7c23-448b-a24a-eca3d1eacca3.png)
    
  - Użyj ``` kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr```
  
    Poleceniem ```kubectl run ctr --image=k8s.gcr.io/echoserver --port=8080 --labels app=ctr``` uruchomiłam obraz na porcie 8080
  
    ![image](https://user-images.githubusercontent.com/28841971/150868995-f15df2be-af6e-4006-91c8-85dc4213cd2e.png)

  - Przekieruj porty
  
    Poleceniem ```kubectl port-forward service/hello-k8s 7080:8080``` ustawiłam na port 7080. Zmiana się nie udała uważam, że przez to, ze wdrożenie nie wystartowało.
  
    ![image](https://user-images.githubusercontent.com/28841971/150870869-1a95b058-4b0e-448f-8952-f1f4ae651a49.png)
  
  - Wykaż że wdrożenie nastąpiło
  
    Wdorżenie się pojawiło, ale świeci na czerwono, w usługach najpierw sie pojawiła nowa usługa od hello-k8s, ale gdy skasowałam bo mimo to nie odpaliło się wdrożenie to przy ponownej próbie już nie utworzyło usługi.
    ![image](https://user-images.githubusercontent.com/28841971/150870804-ff39e099-63ea-4c8d-9428-5398380539fe.png)
    ![image](https://user-images.githubusercontent.com/28841971/150871109-c35cf80d-7c12-40b6-a75d-424be7fb6793.png)
  
  - W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia
  
    Z obrazka na którym widać wdrożenia widać ze pods jest 0/1. W podsach pojawiły mi się dwie pozycje, ale również swiecą na czerwono. 
  
    ![image](https://user-images.githubusercontent.com/28841971/150871412-70d2958e-02fa-4656-8998-46aa20e32517.png)

### Deployment
  - Utwórz plik YAML z "deploymentem" k8s
  
  Poleceniem ```kubectl create deployment k8s --image=k8s.gcr.io/echoserver --dry-run=client -o yaml``` utworzyłam plik YAML z deploymentem k8s i skopiowałam do notatnika o nazwie k8s.yml.
  
  ![image](https://user-images.githubusercontent.com/28841971/150872158-12d87a7d-5f10-498e-b251-2eb1be2f1892.png)
  ![image](https://user-images.githubusercontent.com/28841971/150871986-38303c3a-1a06-450c-a8bc-624b2448b39c.png)
  ![image](https://user-images.githubusercontent.com/28841971/150873146-ae630c82-b55d-4ac1-9245-0fe571dc8d4c.png)
 
  - Zestaw 4 repliki, opisz zalety i wady takiej liczby

    Plik k8s.yml edytowałam i zmieniłam repliki z 1 na 4.
    
    ![image](https://user-images.githubusercontent.com/28841971/150873338-1a16c061-ca1f-45a0-95f4-f04e15c67e8c.png)
    
  - Zaaplikuj wdrożenie via ```kubectl apply -f plik.yml```

    Poleceniem wyż☺ej tylko ustawiając plik.yml na k8s.yml zaaplikowałam wdrożenie. Początkowo wychodził mi błąd przez przypadkowy tabulator przed nazwą replik
    
    ![image](https://user-images.githubusercontent.com/28841971/150874040-d466965d-7601-4fae-870b-fd51d2259cad.png)
  
  - Wykaż przeprowadzony deployment
  
    Pojawił się ale niestety tez świeci na czerwono. 
    
    ![image](https://user-images.githubusercontent.com/28841971/150874118-d980ad5a-097a-4c27-8845-6bb7707b80b7.png)

