# Zajęcia 05

### Zestawienie platformy Kubernetes
* Upewniono się, że kontener z poprzednich zajęć działa:

    * obraz z poprzednich zajęć:
      ![minikube_screen_2.PNG](./img/begining_1.PNG)

    * sprawdzenie czy buduwanie działa, builder stage:

`docker build -t dev-1.0.0 -f Dockerfile-build .`

![minikube_screen_2.PNG](./img/begining_2.PNG)

`docker images -a`

![minikube_screen_2.PNG](./img/begining_3.PNG)

* uruchomienie konterera z obrazu:

`docker run -it -d -p 3301:3306 dev-1.0.0`

![minikube_screen_2.PNG](./img/begining_4.PNG)

* Wymagania wstępne dla środowiska Minikube:
    
    * ```sudo chmod 666 /var/run/docker.sock```
    * trzeba przydzielić min 2 procesory do virtualnej maszyny Fedora
![ustawienia_vb_procesory.PNG](./img/ustawienia_vb_procesory.PNG)

* Zainstaluj kubectl
  ![minikube_screen_2.PNG](./img/kubectl_1.PNG)

* sprawdzono czy poprawnie zostało zainstalowane przy użycia pliku sprawdzającego checksum

```curl -LO "https://dl.k8s.io/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl.sha256"```

```echo "$(<kubectl.sha256)  kubectl" | sha256sum --check```

`sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl`

![kubectl_2.PNG](./img/kubectl_2.PNG)

`kubectl version --client`

![kubectl_2.PNG](./img/kubectl_3.PNG)

* Zainstaluj minikube
  * pobrano najnowszą paczkę z minikube
  ```curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-latest.x86_64.rpm```

![minikube_screen_1.PNG](./img/minikube_screen_1.PNG)

![minikube_screen_2.PNG](./img/minikube_screen_2.PNG)

![minikube_screen_3.PNG](./img/minikube_screen_3.PNG)

uruchomiono minicube:

![minikube_screen_4.PNG](./img/minikube_screen_4.PNG)

![minikube_screen_5.PNG](./img/minikube_screen_5.PNG)

* status minicube

![minikube_screen_6.PNG](./img/minikube_screen_6.PNG)

* uruchomione kontenery

![minikube_screen_6.PNG](./img/minikube_screen_7.PNG)

  * Przedstaw uruchomione oprogramowanie wstępne (i usługi)
    * Platforma konteneryzacji
    * Otwarte porty
    * Stan Dockera
    
    `docker stats --format "table {{.Name}}\t{{.CPUPerc}}\t{{.MemUsage}}"`

### Stan Minikube
* Uruchom Minikube Dashboard
    ```minikube dashboard```

![kubernetes_dashboard_0.PNG](./img/kubernetes_dashboard_0.PNG)

![kubernetes_dashboard_1.PNG](./img/kubernetes_dashboard_1.PNG)
* Wyświetl działające usługi (k8s) i wdrożenia

![kubernetes_dashboard_services_1.PNG](./img/kubernetes_dashboard_services_1.PNG)

* Wyświetl dostępne wdrożenia (stan "przed")

![kubernetes_dashboard_deployments_1.PNG](./img/kubernetes_dashboard_deployments_1.PNG)

![kubernetes_dashboard_pods_1.PNG](./img/kubernetes_dashboard_pods_1.PNG)

### Wdrożenie kontenera via k8s
* Wdrożono przykładowy deployment "hello k8s": ```k8s.gcr.io/echoserver```

`kubectl create deployment hello-minikube --image=k8s.gcr.io/echoserver:1.4`

* Przekierowano porty
`kubectl expose deployment hello-minikube --type=NodePort --port=8080`

![hello_minikube_1.PNG](./img/hello_minikube_1.PNG)

* Wykazano, że wdrożenie nastąpiło

kontenery widoczne z fedory:

![hello_minikube_2.PNG](./img/hello_minikube_2.PNG)

wdrożenie nie jest widoczne z tego poziomu.

Wdrożenie jest widoczne z dashboardu k8s:

![hello_minikube_3.PNG](./img/hello_minikube_3.PNG)

można też podglądnąc wdrożenie przy użyciu `kubectl`

* `kubectl get deployment`
* `kubectl get pods -o wide`
* 
![hello_minikube_4.PNG](./img/hello_minikube_4.PNG)

Więcej szczegółów:

![hello_minikube_5.PNG](./img/hello_minikube_5.PNG)
![hello_minikube_6.PNG](./img/hello_minikube_6.PNG)

### Deployment
* Utwórz plik YAML z "deploymentem" k8s
* Zestaw 4 repliki
* testowo napisano plik dla deploymentu `nginx`

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
  labels:
    app: nginx
spec:
  replicas: 4
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.14.2
        ports:
        - containerPort: 80
```

* opisz zalety i wady takiej liczby
    * zalety
      * skalowalność, możliwość wykorzystaia laod balancera, 
      który pozowli na zoptymalizowanie działania pod kątem wydajności
      * zwiększona odporność na awarię, w razie jej wystąpienia, 
      k8s'owy deployment będzie dbał aby wszystkie pody się restartowały i żeby były zdrowe
      * zwiększone bezpieczeństwo
    * wady
      * możliwe wysokie koszty utrzymywania 4 replik


* Zaaplikowano wdrożenie

`kubectl apply -f ./nginx-deploy.yaml`


* Pody zaraz po uruchomieniu:
![minikube_screen_2.PNG](./img/nginx_deploy_1.PNG)



* Przeprowadzony deployment

![minikube_screen_2.PNG](./img/nginx_deploy_2.PNG)

![minikube_screen_2.PNG](./img/nginx_deploy_3.PNG)

![minikube_screen_2.PNG](./img/nginx_deploy_4.PNG)

* 4 uruchomione pody, gdy już pody w pełni działają:

![minikube_screen_2.PNG](./img/nginx_deploy_5.PNG)
![minikube_screen_2.PNG](./img/nginx_deploy_pods.PNG)

Docker stat:

![minikube_screen_2.PNG](./img/docker-stat.PNG)
