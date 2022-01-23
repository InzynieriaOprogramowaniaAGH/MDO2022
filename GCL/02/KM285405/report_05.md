# Metodyki DevOps 2021/2022 NS
Zajęcia 05 - 2021-01-15
---
# Zestawienie platformy Kubernetes
* Zainstaluj minikube i kubectl
![](img_05/01_minikube.jpg)

![](img_05/02_minikube.jpg)

![](img_05/03_minikube.jpg)

Instalacja przebiegła bez problemu, aż do momentu sprawdzenia minikube dashboard - gdzie otrzymujemy:

![](img_05/04_minikube.jpg)

Praca z WSL2 wymaga dodatkowego przygotowanie środowiska, natmoiast w tym momencie musiałem zmienić środowisko na VirtualBox

Instalacja przebiegła bez problemu, minikube dashboard również działa:

![](img_05/05.JPG)

Instalacja kubectl:

```curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"```

```sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl```

```kubectl version --client```

```kubectl get po -A```

![](img_05/06.JPG)


* Przedstaw uruchomione oprogramowanie wstępne (i usługi)
  * Platforma konteneryzacji
  
![](img_05/07.JPG)
  
  * Otwarte porty

![](img_05/08.JPG)
  
  * Stan Dockera
  
![](img_05/09.JPG)

# Stan Minikube
* Uruchom Minikube Dashboard

![](img_05/05.JPG)

* Wyświetl działające usługi (k8s) i wdrożenia

![](img_05/10.JPG)

* Wyświetl dostępne wdrożenia (stan "przed")

![](img_05/11.JPG)

# Wdrożenie kontenera via k8s
* Wdróż przykładowy deployment "hello k8s": ```k8s.gcr.io/echoserver```

 ```kubectl create deployment hello --image=k8s.gcr.io/echoserver:1.4```

* Użyj ```kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr```

```kubectl run hello --image=k8s.gcr.io/echoserver:1.4 --port=80 labels app=ctr``

* Przekieruj porty

```kubectl expose deployment hello --type=NodePort --port=8080```

* Wykaż że wdrożenie nastąpiło
 
 ```minikube service hello```
 
![](img_05/12.JPG)
   
![](img_05/13.JPG)

![](img_05/14.JPG)
       
![](img_05/15.JPG)
      

# Deployment
* Utwórz plik YAML z "deploymentem" k8s

![](img_05/16.JPG)

* Zestaw 4 repliki, opisz zalety i wady takiej liczby
zalety: można łatwo zmienić liczbę replik, przy awarii 1 repliki - wciąż działają pozostałe
wady: większe zużycie zasobów przy utrzymaniu 4 replik, większe koszty utrzymywania
   
* Zaaplikuj wdrożenie via ```kubectl apply -f plik.yml```

![](img_05/17.JPG)
   
* Wykaż przeprowadzony deployment

![](img_05/18.JPG)
   
![](img_05/19.JPG)
