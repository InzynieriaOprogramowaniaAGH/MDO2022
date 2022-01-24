   **Zainstaluj wymagania wstępne dla środowiska Minikube**
![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/minikubeinstall.png)

**Przedstaw uruchomione oprogramowanie wstępne (i usługi)**
![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/minikubestart.png)
![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/minikubestatus.png)

**Uruchom Minikube Dashboard**

![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/minikubedashboard.png)
 ![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/minikubedashboard1.png)

**Wyświetl działające usługi (k8s) i wdrożenia**
  
  ![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/kubectl.png)

**Wyświetl dostępne wdrożenia (stan "przed")**

![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/kubectlgetservices.png)

**Wdróż przykładowy deployment "hello k8s":**

![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/createdeployment.png)

**Przekieruj porty**

![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/minikubeportforward.png)

**Wykaż że wdrożenie nastąpiło**

![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/minikubeservicehellominikube.png)
 ![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/minikubeservicehellominikube1.png)

**Utwórz plik YAML z "deploymentem" k8s**

Plik YAML znajduje się w repozytorium
 
 ![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/deployment.png)
 ![](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/MR299810/GCL/03/MR299810/lab05/deployment1.png)
 
 Zalety 4 replik
  - Awaria jednej z replik spowoduje przekierowanie ruchu na kolejne.

  - Więcej niż jedej ReplicaSet chroni przed downtime.




