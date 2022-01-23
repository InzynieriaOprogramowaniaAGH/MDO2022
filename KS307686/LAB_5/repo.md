# Zajęcia 05

### Zestawienie platformy Kubernetes

* Upewnij się, że kontener jest dostępny
* Zainstaluj wymagania wstępne dla środowiska Minikube
* Zainstaluj minikube i kubectl
* ![image](ss/ss1.png)
* ![image](ss/ss2.png)
* ![image](ss/ss3.png)
* ![image](ss/ss4.png)
* ![image](ss/ss5.png)

### Stan Minikube
* Uruchom Minikube Dashboard
* ![image](ss/ss6.png)
* ![image](ss/ss7.png)
* Wyświetl działające usługi (k8s) i wdrożenia
* ![image](ss/ss8.png)
* Wyświetl dostępne wdrożenia (stan "przed")

### Wdrożenie kontenera via k8s
* Wdróż przykładowy deployment "hello k8s": ```k8s.gcr.io/echoserver```
* ![image](ss/ss9.png)
* ![image](ss/ss10.png)
* ![image](ss/ss11.png)
* ![image](ss/ss12.png)
* ![image](ss/ss13.png)
* ![image](ss/ss14.png)
* ![image](ss/ss15.png)
* Użyj ```kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr```
* Przekieruj porty
* ![image](ss/ss16.png)
* Wykaż że wdrożenie nastąpiło
* ![image](ss/ss17.png)

* W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia

### Deployment
* Utwórz plik YAML z "deploymentem" k8s
* ![image](ss/ss20.png)
* Zestaw 4 repliki, opisz zalety i wady takiej liczby
* Zaaplikuj wdrożenie via ```kubectl apply -f plik.yml```
* ![image](ss/ss18.png)
* Wykaż przeprowadzony deployment
* ![image](ss/ss19.png)

