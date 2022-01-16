# Metodyki DevOps - lab05

## Środowisko

Ćwiczenia labolatoryjne zostały wykonane na laptopie z macOS Big Sur 11.6

![Zdjęcie środowiska](../lab1/screenshots/macos-big-siur.png)

## Zestawienie platformy Kubernetes

### Instalacja wymagań wstępnych dla środowiska Minikube

Minikube został zainstalowany na natywnym systemie macOS, zatem nie trzeba było instalować żadnych wymagań wstępnych, typu 2 CPU dla maszyny wirtualnej.

### Instalacja minikube 
https://minikube.sigs.k8s.io/docs/start/

Zainstalowano Minikube według zaleceń dla poniższych ustawień platformy:

![Minikube instalacja macIS](screenshots/minikube-instlacja-guide.png)

![Minikube instalacja](screenshots/minikube-instalacja.png)

Oprócz tego dodaję alias `alias minikubectl="minikube kubectl --"` do pliku `~/.zshrc`. Jest to trochę inny alias niż sugeruje instrukcja, ponieważ mam zrobiony config pod środowisko produkcyjne w mojej pracy i wolałbym nie zepsuć niczego przypadkowo.

### Instalacja kubectl
https://kubernetes.io/docs/tasks/tools/install-kubectl-macos/

Mogłem zrobić to prostym poleceniem `brew install kubectl` lub `brew install kubernetes-cli`, ale poszedłem zgodnie z guidem, gdzie najpierw była pokazana binarna instalacja, a dopiero potem zobaczyłem Homebrew.

Instalacja kubectl:

![Instalacja kubectl](screenshots/kubectl-instalacja-1.png)

![Instalacja kubectl cd.](screenshots/kubectl-instalacja-2.png)

### Przedstawienie uruchomionego oprogramowania wstępnego (i usługi)
#### Platforma konteneryzacji
Uruchamiam Minikube, tak jak zalecono w guidzie:

![Minikube start](screenshots/minikube-start.png)

![kubectl get svc](screenshots/kubectl-get-services.png)

#### Stan Dockera i otwarte porty
![Stan Dockera](screenshots/docker-ps.png)

## Stan Minikube
### Uruchomienie Minikube Dashboard

![Minikube dashboard](screenshots/minikube-dashboard.png)

### Wyświetlenie działających usług (k8s) i wdrożeń (stan "przed")

W panelu po lewej wchodzę do zakładki **Services** i wyświetlam działające usługi: 

![Minikube services](screenshots/minikube-service.png)

oraz zakładki **Deployments**, i wyświetlam wdrożenia:

![Minikube deployments](screenshots/minikube-deployments.png)

