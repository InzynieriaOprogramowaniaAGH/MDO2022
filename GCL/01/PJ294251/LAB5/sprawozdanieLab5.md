# Zajęcia 05

Zestawienie platformy Kubernetes

-   Zainstaluj wymagania wstępne dla środowiska Minikube
	Skonfigurowałem w maszynie wirtualnej drugi procesor, sprawdziłem dostępne miejsce na dysku i pamięć RAM oraz Dockera.
	
![1](imgs/1.png)
-   Zainstaluj minikube i kubectl

	``` minikube start ```
	
	![2](imgs/2.png)
	
	Jeśli nie ma zainstalowanego:
	
    ```minikube kubectl -- get po -A```

	![3](imgs/3.png)

- Dodanie aliasa do shell config

	``` alias kubectl="minikube kubectl --"  ```
	
	![4](imgs/4.png)
	
-   Przedstaw uruchomione oprogramowanie wstępne (i usługi)
    -   Platforma konteneryzacji
    -   Otwarte porty
    -   Stan Dockera
    ![2](imgs/2.png)
    ![7](imgs/7.png)

### Stan Minikube

-   Uruchom Minikube Dashboard
![5](imgs/5.png)
-   Wyświetl działające usługi (k8s) i wdrożenia
![6](imgs/6.png)
-   Wyświetl dostępne wdrożenia (stan "przed")

### Wdrożenie kontenera via k8s

-   Wdróż przykładowy deployment "hello k8s":  `k8s.gcr.io/echoserver`
-   Użyj  `kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr`
-   Przekieruj porty
-   Wykaż że wdrożenie nastąpiło
-   W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia

### Deployment

-   Utwórz plik YAML z "deploymentem" k8s
-   Zestaw 4 repliki, opisz zalety i wady takiej liczby
-   Zaaplikuj wdrożenie via  `kubectl apply -f plik.yml`
-   Wykaż przeprowadzony deployment
