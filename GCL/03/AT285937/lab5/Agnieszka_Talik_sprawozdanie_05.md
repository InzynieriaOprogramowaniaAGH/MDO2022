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

![10](https://user-images.githubusercontent.com/80592460/149663507-64493c93-8840-4479-b5df-da1fe7f90251.png)

![11](https://user-images.githubusercontent.com/80592460/149663500-ce1778fd-faa5-4402-8da0-b35f64eeb12b.PNG)

![12](https://user-images.githubusercontent.com/80592460/149663553-caaa73c7-4499-4b81-a6eb-b53786b3fb0c.PNG)

![13](https://user-images.githubusercontent.com/80592460/149663549-d54532e3-7ab8-41bd-9b52-1044d4c23db9.PNG)

![14](https://user-images.githubusercontent.com/80592460/149663610-9bcd881f-9a8e-4fa0-91f0-b5160fdc57f5.PNG)

![15](https://user-images.githubusercontent.com/80592460/149663634-07dc42f6-e183-49c3-bc61-5573bd7ade5c.PNG)

