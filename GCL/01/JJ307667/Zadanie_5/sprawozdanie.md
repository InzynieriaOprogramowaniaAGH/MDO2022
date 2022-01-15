# Zajęcia 05

### Zestawienie platformy Kubernetes

* Upewnij się, że kontener jest dostępny
* Zainstaluj wymagania wstępne dla środowiska Minikube
* Zainstaluj minikube i kubectl

![Instalacja_1](screenshots/1.PNG)
![Instalacja_2](screenshots/2.PNG)
![Instalacja_3](screenshots/3.PNG)

Tak jak poleca dokumentacja, dodajemy również alias:
```sh
alias kubectl="minikube kubectl --"
```


* Przedstaw uruchomione oprogramowanie wstępne (i usługi)
  * Platforma konteneryzacji
  * Otwarte porty
  * Stan Dockera

Możliwe jest, że przy uruchomieniu **minikube start** zwróci nam błąd o braku dostępów naszego użytkownika do usera (komendy tej nie można uruchamiać na roocie!). W takim wypadku możemy poradzić sobie wpisując te komendy:

```sh
sudo groupadd docker
sudo usermod -aG docker $USER
```

![Uruchomione procesy](screenshots/4.PNG)



### Stan Minikube
* Uruchom Minikube Dashboard

![Uruchomione minikube](screenshots/5.PNG)

* Wyświetl działające usługi (k8s) i wdrożenia
* Wyświetl dostępne wdrożenia (stan "przed")

Workload:
![minikube_workloads](screenshots/6.PNG)

Services:
![minikube_services](screenshots/7.PNG)


### Wdrożenie kontenera via k8s
* Wdróż przykładowy deployment "hello k8s": ```k8s.gcr.io/echoserver```

![minikube_services](screenshots/8.PNG)

![minikube_services](screenshots/9.PNG)


* Przekieruj porty

![minikube_workloads](screenshots/10.PNG)


* Wykaż że wdrożenie nastąpiło

Workload:
![minikube_workloads](screenshots/11.PNG)

Services:
![minikube_services](screenshots/12.PNG)



### Deployment
* Utwórz plik YAML z "deploymentem" k8s
* Zestaw 4 repliki, opisz zalety i wady takiej liczby
* Zaaplikuj wdrożenie via ```kubectl apply -f plik.yml```

![yaml_file](screenshots/13.PNG)

* Wykaż przeprowadzony deployment

![yaml_deployment](screenshots/14.PNG)
