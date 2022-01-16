# Zajęcia 05

### Zestawienie platformy Kubernetes

* Upewnij się, że kontener jest dostępny
* Zainstaluj wymagania wstępne dla środowiska Minikube
* Zainstaluj minikube i kubectl

![1](https://user-images.githubusercontent.com/80592460/149655799-0073357e-bb3b-42b4-b4c2-d726e244a897.png)

![image](https://user-images.githubusercontent.com/80592460/149657989-102ea68f-f065-4d57-a411-19102567c127.png)

* Przedstaw uruchomione oprogramowanie wstępne (i usługi)
  * Platforma konteneryzacji
  * Otwarte porty
  * Stan Dockera

![2](https://user-images.githubusercontent.com/80592460/149657221-9d1a9f8c-89cb-467f-91ed-b0a7cc4fae5d.png)

![3](https://user-images.githubusercontent.com/80592460/149657225-1dbbbc1a-999b-4eae-8176-b0f3a454b894.PNG)

### Stan Minikube
* Uruchom Minikube Dashboard
* Wyświetl działające usługi (k8s) i wdrożenia
* Wyświetl dostępne wdrożenia (stan "przed")

![4](https://user-images.githubusercontent.com/80592460/149658980-4e997cf3-db58-498c-909d-4567eac397d0.png)

![5](https://user-images.githubusercontent.com/80592460/149658981-1fc6468b-0354-4876-96da-060c82fd6c6d.png)

### Wdrożenie kontenera via k8s
* Wdróż przykładowy deployment "hello k8s": ```k8s.gcr.io/echoserver```
* Użyj ```kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr```
* Przekieruj porty
* Wykaż że wdrożenie nastąpiło
* W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia

![5](https://user-images.githubusercontent.com/80592460/149658864-a6cab0d3-ab38-4f12-bd86-db4be87b086e.png)

![6](https://user-images.githubusercontent.com/80592460/149658870-c5029c3c-7826-4ab4-b1c0-f93a51fbd409.PNG)

![7](https://user-images.githubusercontent.com/80592460/149658873-e4ab8076-db9a-43c8-a1cf-7b1265d3898a.PNG)

![8](https://user-images.githubusercontent.com/80592460/149658875-b48b0385-bf9d-49e7-93e3-80eabe37a11d.PNG)

### Deployment
* Utwórz plik YAML z "deploymentem" k8s
* Zestaw 4 repliki, opisz zalety i wady takiej liczby
* Zaaplikuj wdrożenie via ```kubectl apply -f plik.yml```
* Wykaż przeprowadzony deployment
