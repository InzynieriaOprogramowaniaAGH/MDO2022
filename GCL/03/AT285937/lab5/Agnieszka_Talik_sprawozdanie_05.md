# Zajęcia 05

### Zestawienie platformy Kubernetes

* Upewnij się, że kontener jest dostępny
* Zainstaluj wymagania wstępne dla środowiska Minikube
* Zainstaluj minikube i kubectl

Instalacja zgodnie z dokumentacją:

-Pobieramy pliki binarne i instalujemy

		curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
		sudo install minikube-linux-amd64 /usr/local/bin/minikube
		
-Odpalamy:

		minikube start
		
-Dostęp do klastra:
		
		minikube kubectl -- get po -A

![1](https://user-images.githubusercontent.com/80592460/149655799-0073357e-bb3b-42b4-b4c2-d726e244a897.png)

Instalacja kubectl

-Pobieramy najnowszą wersję:

		curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"

-Sprawdzamy poprawność binarną (opcjonalnie, jak na screenie)

-Instalacja

		sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
		
-Sprawdzenie aktualnej wersji:

		kubectl version --client
		
Od teraz dostęp do klastra mamy również za pomocą polecenia:

		kubectl get po -A	


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

Wszystko jak na screenach:

![4](https://user-images.githubusercontent.com/80592460/149658980-4e997cf3-db58-498c-909d-4567eac397d0.png)

![5](https://user-images.githubusercontent.com/80592460/149658981-1fc6468b-0354-4876-96da-060c82fd6c6d.png)

### Wdrożenie kontenera via k8s
* Wdróż przykładowy deployment "hello k8s": ```k8s.gcr.io/echoserver```
* Użyj ```kubectl run <ctr> --image=<DOCKER_ID>/<IMG> --port=<port> --labels app=ctr```
* Przekieruj porty
* Wykaż że wdrożenie nastąpiło
* W przypadku "niemożliwych" wdrożeń, opisz napotkane ograniczenia

Uruchomienie obrazu echoserver w klastrze, udostępnianie Podu na porcie 8080. Opcja --type=LoadBalancer wskazuje, że udostępniamy serwis na zewnątrz klastra. Następnie sprawdzamy utworzony serwis i uruchamiamy.

![5](https://user-images.githubusercontent.com/80592460/149658864-a6cab0d3-ab38-4f12-bd86-db4be87b086e.png)

![6](https://user-images.githubusercontent.com/80592460/149658870-c5029c3c-7826-4ab4-b1c0-f93a51fbd409.PNG)

W Dashboardzie sprawdzamy wdrożenie i Podsy.

![7](https://user-images.githubusercontent.com/80592460/149658873-e4ab8076-db9a-43c8-a1cf-7b1265d3898a.PNG)

![8](https://user-images.githubusercontent.com/80592460/149658875-b48b0385-bf9d-49e7-93e3-80eabe37a11d.PNG)

### Deployment
* Utwórz plik YAML z "deploymentem" k8s
* Zestaw 4 repliki, opisz zalety i wady takiej liczby
* Zaaplikuj wdrożenie via ```kubectl apply -f plik.yml```
* Wykaż przeprowadzony deployment

Plik *.yml z ustawioną ręcznie liczbą replik 4. Poniżej aplikujemy wdrożenie.

![10](https://user-images.githubusercontent.com/80592460/149663507-64493c93-8840-4479-b5df-da1fe7f90251.png)

Sprawdzamy w Dashboardzie:

![11](https://user-images.githubusercontent.com/80592460/149663500-ce1778fd-faa5-4402-8da0-b35f64eeb12b.PNG)

![12](https://user-images.githubusercontent.com/80592460/149663553-caaa73c7-4499-4b81-a6eb-b53786b3fb0c.PNG)

![13](https://user-images.githubusercontent.com/80592460/149663549-d54532e3-7ab8-41bd-9b52-1044d4c23db9.PNG)

Uruchamiamy serwis:

![14](https://user-images.githubusercontent.com/80592460/149663610-9bcd881f-9a8e-4fa0-91f0-b5160fdc57f5.PNG)

![15](https://user-images.githubusercontent.com/80592460/149663634-07dc42f6-e183-49c3-bc61-5573bd7ade5c.PNG)

Liczba replik zależy głównie od tego jak wiele instancji jednej aplikacji działa w tym samym czasie. Jeżeli wykorzystanie jest niewielkie nie ma potrzeby ustawiania dużej liczby, ponieważ tylko niepotrzebnie zajmujemy zasoby. Jednak w przypadku ustawienia tylko jednej repliki, gdy ulegnie ona awarii, nie będzie możliwości przekierowania na inną. Ręczne skalowanie sprawdzi się w przypadku aplikacji, w których liczba klientów jest mniej więcej stała lub zmienia się po dłuższym czasie. Jeżeli jednak nie wiemy ile maksymalnie instancji aplikacji będzie uruchomionych, ponieważ liczba dynamicznie się zmienia, lepiej ustawić autoskalowalność. Kubernetes jest w stanie w szybkim czasie dodawać nowe repliki gdy wykorzystanie przekroczy określoną wartość, aby zapewnić stabilną pracę. Decyzja o usuwanie replik trwa nieco dłużej, z wiadomych przyczyn (czasem ruch spada tylko na chwile i nie opłaca się usuwać i dodawać co kilka sekund nowych replik). Oczywiście bez przerw w działaniu aplikacji.

Odpowiadając na pytanie, w naszym przypadku zaletami 4 replik jest możliwość przekierowania na inną w razie awarii lub stabilną pracę w przypadku zwiększenia skali zasięgu naszej aplikacji. Wadą jest większe zużycie zasobów.

## Zajęcia 06 : zależność ciągłej integracji od komponentów stron trzecich

### Inwentaryzacja
* Zdefiniuj w ramach stworzonego Dockerfile'a zależności platformowe

	-node (latest)
	
 * Oprogramowanie, które doinstalowujesz aby uruchomić program
 
	-Docker
	
	
 * W razie braku zależności (np. obraz ```node``` i aplikacje wymagająca tylko ```node```), "zależnością" jest sam obraz - tak właśnie jest teraz
 
 
* Określ okoliczności, w których uzasadnione jest przebudowywanie i aktualizacja obrazu po wydaniu nowej wersji którejś z zależności

	-W przypadku aktualizacji zabezpieczeń, które dotyczą naszego obrazu
	
	-Kiedy aktualizacja zawiera takie nowości i usprawnienia, których będziesz aktualnie lub w najbliższej przyszłości potrzebował
	
	
 * Czy należy to robić "zawsze"?
 
 	-Należy dobrze zapoznać się z dokumentacją nowej wersji czy pewne wymagane w naszej aplikacji funkcje nie zostały wycofane
	
	-Jeżeli nowe funkcjonalności nie przydadzą się w naszej aplikacji, to aktualizacja będzie zbędna, w żaden sposób nie poprawi jakości ani bezpieczeństwa, a może się okazać, że nawet spowoduje neistabilne działanie aplikacji.
	
	
 * Jakie są przesłanki (i jak je ustalić) wskazujące na konieczność aktualizacji
 
	-Problemy z bezpieczeństwem (przeprowadzanie skanów bezpieczeństwa aplikacji, istnieją różne narzędzia do automatycznych skanów i testów)
	
	-Chęć rozwinięcia aplikacji o konkretne funkcjonalności
	
	-Poprawa jakości lub szybkości działania
	
	
 * Jakie jest ryzyko aktualizowania/nieaktualizowania (im dokładniejszy przykład, tym lepiej)
* Pytanie pomocnicze: czy obraz Fedory/Ubuntu na dockerhubie jest aktualizowany dla każdej nowej wersji pakietu wchodzącego w jego skład? Dlaczego tak/nie?

	-Nie. Tak jak w powyższych przykładach, są aktualizowane tylko wtedy gdy jest ku temu wyraźna przesłanka :)

### Wdrożenie
Alternatywnie do zadania wyżej: określenie zależności od dostawcy chmurowego
* Określ poziom zależności wdrożenia od środowiska chmurowego

-Na stronie Azure można m.in. wyczytać, że istnieje kilka kategorii rozwiązań chmurowych w zależności od wymagań, a każda dostarcza sprecyzowane możliwości, aby m.in. ułatwić deweloperom szybkie tworzenie aplikacji bez przejmowania się konfigurowaniem niezbędnej podstawowej infrastruktury serwerów, magazynu, sieci i baz danych ani zarządzaniem nią, ponieważ dostawca chmury wyręcza Cię w konfigurowaniu, planowaniu wydajności i zarządzaniu serwerami.

* Zweryfikuj dostępność studenckiego konta Azure i **zapoznaj się z cennikiem**

![image](https://user-images.githubusercontent.com/80592460/150843042-9cc55c87-5571-4e2e-9e0b-20e347dddd53.png)

* Przeprowadź próbne wdrożenie obrazu w ramach dostępnych kredytów

![image](https://user-images.githubusercontent.com/80592460/150842992-9657d0a3-0e4d-4702-a2d1-3bc09804d291.png)

![image](https://user-images.githubusercontent.com/80592460/150842910-ca88033a-03e7-469f-88ae-44f7748f119e.png)

![image](https://user-images.githubusercontent.com/80592460/150843645-01d4080e-1b95-4fcb-a16b-f5a335ac9b25.png)

![image](https://user-images.githubusercontent.com/80592460/150844306-08ff54c5-ae5c-4ce4-a941-445f465f5316.png)

![image](https://user-images.githubusercontent.com/80592460/150844493-cc7c50a1-2957-4906-9115-da392fb70736.png)

![image](https://user-images.githubusercontent.com/80592460/150844534-563fcf94-63d6-4cbd-a599-ef674f2a4363.png)

* Zatrzymaj i usuń kontener, i wstrzymaj storage space, aby nie generować rachunków na pustych przebiegach

![image](https://user-images.githubusercontent.com/80592460/150845160-ed661a24-057d-43b8-9839-407a48ceba84.png)

![image](https://user-images.githubusercontent.com/80592460/150845221-a1a3ce52-a467-4bcb-a8ac-6c2291a452d9.png)

