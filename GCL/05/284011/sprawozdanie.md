******** Zajęcia 05 *******

1. Zestawienie platformy Kubernetes

 - Upewnij się, że kontener jest dostępny

 *Zdjecie1
 *Zdjecie2
 *Zdjecie3
 
 - Zainstaluj wymagania wstępne dla środowiska Minikube

    "sudo apt update -y"
    "sudo apt upgrade -y"
    "sudo apt install -y curl wget apt-transport-https" -> Install Minikube dependencies
    "wget https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64" -> Download Minikube Binary


 - Zainstaluj minikube i kubectl

    "sudo cp minikube-linux-amd64 /usr/local/bin/minikube"
    "sudo chmod +x /usr/local/bin/minikube"
    "minikube version" -> minicube version

*Zdjecie4

    "curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl"
    "chmod +x kubectl"
    "sudo mv kubectl /usr/local/bin/"
    "kubectl version -o yaml"

*Zdjecie5

 - Przedstaw uruchomione oprogramowanie wstępne (i usługi)

    a) Platforma konteneryzacji

*Zdjecie6
*Zdjecie7

W tym momencie okazało się, że moja VM jest za słaba, minimalna ilość CPU wynosi 2, więc musiałem to poprawić.
Potem wyskoczył błąd, że VMD nie jest włączone, jak się okazało w biosie miałem to wyłączone.
Niestety po tym nadal nie mogłem włączyć Virualizacji, skorzystałem z komendy:

    "VBoxManage modifyvm "Nazwa serwera" --nested-hw-virt on" ->po tym w ogóle nie chciała włączyć mi sie wirtualna maszyna, pomogło wyłączenie hypervisora
    "bcdedit.exe /set hypervisorlaunchtype off"

    Niestty po użyciu minikube start process zatrzymuje się na etapie "Creating virtualbox VM..." i po wielu próbach nie udało mi się ruszyć dalej...

*Zdjecie_error

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

*Zdjecie9
*Zdjecie10
*Zdjecie11

 - Przeprowadź próbne wdrożenie obrazu w ramach dostępnych kredytów

    Stworzyłem maszynę wirtualną 

*Zdjecie12

    Przypołczeniu za pomoćą RMD dostałem poniższy błąd

*Zdjęcie13
*Zdjecie14
*Zdjecie15
*Zdjecie16

    Dodałem reguły zabezpieczeń

*Zdjecie17

    Trochę zmieniłem plany i zalogowalem się za pomocą SSH

*Zdjecie18

    Na stworzonej VM zainstalowałem Dockera

*Zdjecie19
*Zdjecie20
*Zdjecie21

 - Zatrzymaj i usuń kontener, i wstrzymaj storage space, aby nie generować rachunków na pustych przebiegach

    Usunąłem grupę zasobów

*Zdjecie22