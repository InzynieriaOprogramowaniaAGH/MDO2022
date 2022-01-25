# Medytyki devops - lab5

## Zestawienie platformy Kubernetes

- Kontener na maszynie wirtualnej został zainstalowany już wcześniej.
- Instalacja minikube i kubectl:

 ![Zrzut_ekranu-1](1.jpg)

Następuje pobranie odpowiednich plików oraz ich instalacja.

  ![Zrzut_ekranu-2](2.jpg)

  Tutaj następuje uruchomienie minikube'a

- Otwarte porty: 

![Zrzut_ekranu-3](3.jpg)

  - Stan dockera:

![Zrzut_ekranu-4](4.jpg)

## Stan minikube

- Uruchomienie minikube Dashboard:

![Zrzut_ekranu-5](5.jpg)

Tak prezentuje się efekt w przeglądarce:
![Zrzut_ekranu-6](6.jpg)

- Działające usługi(k8s) i wdrożenia:

![Zrzut_ekranu-7](7.jpg)

![Zrzut_ekranu-8](8.jpg)

- Wdrożenia (stan "przed")

![Zrzut_ekranu-9](9.jpg)

## Wdrożenie kontenera via k8s

- deployment "hello k8s": `k8s.gcr.io/echoserver`

![Zrzut_ekranu-11](11.jpg)

- przekierowanie portów:

![Zrzut_ekranu-12](12.jpg)

- Rezultat wdrożenia: 

![Zrzut_ekranu-12.1](12.1.jpg)

![Zrzut_ekranu-12.2](12.2.jpg)

## Deployment 

- Plik yaml z deploymentem k8s

![Zrzut_ekranu-13](13.jpg)

Jak można zauważyć w pliku mamy zdefiniowane 4 repliki dla danej aplikacji. Oznacza to, że tyle instancji tej samej aplikacji będzie działać w tym samym czasie. Wadą jest zajmowanie zasobów, natomiast zaletą jest to, że w momencie wysypania się jednej repliki nastąpi przekierowanie na drugą. Dla mniej wykorzystywanych aplikacji, gdzie ruch użytkowników jest niewielki zaleca się mniej replik.

- Aplikowanie wdrożenia:

![Zrzut_ekranu-14](14.jpg)

![Zrzut_ekranu-15](15.jpg)

Jak można zauważyć powstały 4 pody.

- Rezultat wdrożenia

![Zrzut_ekranu-16](16.jpg)

![Zrzut_ekranu-17](17.jpg)
