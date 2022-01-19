# Zajęcia 05

### Zestawienie platformy Kubernetes

* Upewnij się, że kontener jest dostępny
* Zainstaluj wymagania wstępne dla środowiska Minikube
* Zainstaluj minikube i kubectl

Instalacja zgodnie z dokumentacją.
https://minikube.sigs.k8s.io/docs/start/
`curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64`
		`sudo install minikube-linux-amd64 /usr/local/bin/minikube`

https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/
`curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"`
`curl -LO "https://dl.k8s.io/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl.sha256"`
`echo "$(<kubectl.sha256)  kubectl" | sha256sum --check`
`sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl`

* W moim przypadku musiałem dodać użytkownika Karol do grupy Docker komendą:

`sudo usermod -aG docker karol && newgrp docker`

* Przedstaw uruchomione oprogramowanie wstępne (i usługi)
  * Platforma konteneryzacji
  * Otwarte porty
  * Stan Dockera
  
  `Standockera.png`

### Stan Minikube
* Uruchom Minikube Dashboard

Komenda `minikube dashboard`

`kubernetesdashboard.png`

* Wyświetl działające usługi (k8s) i wdrożenia

`nowekubectl.png`
`kubernetesservices.png`
* Wyświetl dostępne wdrożenia (stan "przed")

`deployments.png`
`pods.png`

### Wdrożenie kontenera via k8s
* Wdróż przykładowy deployment "hello k8s": ```k8s.gcr.io/echoserver```
* Użyj ```kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr```
* Przekieruj porty
* Wykaż że wdrożenie nastąpiło
* W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia

`Wdrozeniekontenera.png`
`Deploymentspo.png`
`Podspo.png`

### Deployment
* Utwórz plik YAML z "deploymentem" k8s
* Zestaw 4 repliki, opisz zalety i wady takiej liczby
* Zaaplikuj wdrożenie via ```kubectl apply -f plik.yml```
* Wykaż przeprowadzony deployment
