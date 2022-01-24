******** Zajęcia 05 *******

1. Zestawienie platformy Kubernetes

 - Upewnij się, że kontener jest dostępny
 - Zainstaluj wymagania wstępne dla środowiska Minikube
 - Zainstaluj minikube i kubectl
 - Przedstaw uruchomione oprogramowanie wstępne (i usługi)

    a) Platforma konteneryzacji
    b) Otwarte porty
    c) Stan Dockera

2. Stan Minikube

 - Uruchom Minikube Dashboard
 - Wyświetl działające usługi (k8s) i wdrożenia
 - Wyświetl dostępne wdrożenia (stan "przed")

3. Wdrożenie kontenera via k8s

 - Wdróż przykładowy deployment "hello k8s": k8s.gcr.io/echoserver
 - Użyj kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr
 - Przekieruj porty
 - Wykaż że wdrożenie nastąpiło
 - W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia

4. Deployment

 - Utwórz plik YAML z "deploymentem" k8s
 - Zestaw 4 repliki, opisz zalety i wady takiej liczby
 - Zaaplikuj wdrożenie via kubectl apply -f plik.yml
 - Wykaż przeprowadzony deployment

******** Zajęcia 06 : zależność ciągłej integracji od komponentów stron trzecich *******

1. Wdrożenie

 - Określ poziom zależności wdrożenia od środowiska chmurowego
 - Zweryfikuj dostępność studenckiego konta Azure i zapoznaj się z cennikiem
 - Przeprowadź próbne wdrożenie obrazu w ramach dostępnych kredytów
 - Zatrzymaj i usuń kontener, i wstrzymaj storage space, aby nie generować rachunków na pustych przebiegach