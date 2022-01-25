# Zajęcia 05

### Zestawienie platformy Kubernetes

* Upewnij się, że kontener jest dostępny 
  ![1.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/1.png)
  ![2.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/2.png)
  ![3.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/3.png)
* Zainstaluj wymagania wstępne dla środowiska Minikube
  ![8.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/8.png)
  ![18.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/18.png)
* Zainstaluj minikube i kubectl
  ![4.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/4.png)
  ![5.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/5.png)
  ![6.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/6.png)
  ![7.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/7.png)
* Przedstaw uruchomione oprogramowanie wstępne (i usługi)
  ![9.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/9.png)
    * Platforma konteneryzacji
      ![10.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/10.png)
      ![13.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/13.png)
    * Otwarte porty
      ![11.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/11.png)
    * Stan Dockera
      ![12.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/12.png)

### Stan Minikube
* Uruchom Minikube Dashboard
  ![14.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/14.png)
* Wyświetl działające usługi (k8s) i wdrożeniaeq
  ![15.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/15.png)
* Wyświetl dostępne wdrożenia (stan "przed")
  ![16.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/16.png)

### Wdrożenie kontenera via k8s
* Wdróż przykładowy deployment "hello k8s": ```k8s.gcr.io/echoserver```
  ![17.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/17.png)
  ![19.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/19.png)
* Użyj ```kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr```
  ![20.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/20.png)
* Przekieruj porty
* Wykaż że wdrożenie nastąpiło
* W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia

### Deployment
* Utwórz plik YAML z "deploymentem" k8s
* Zestaw 4 repliki, opisz zalety i wady takiej liczby
* Zaaplikuj wdrożenie via ```kubectl apply -f plik.yml```
* Wykaż przeprowadzony deployment
  ![21.png](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/KK307668/GCL/01/KK307668/lab5/ss/21.png)
