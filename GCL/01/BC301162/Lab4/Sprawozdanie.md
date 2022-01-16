# Metodyki DevOps, Zajecia 04, 13.01.2022, Bartlomiej Ciernia
 
Na początku należy uruchomić obrazy DIND oraz jenkins-blueocean. Instrukcja do tego jest w plikach z poprzedniego labu.
![1](https://user-images.githubusercontent.com/61689132/149335588-7a18187e-5ff4-4317-9a04-9815e6f09891.png)

Następnie logujemy się do naszego konta jenkins, które stworzyliśmy ostatnio, a następnie tworzymy nowy projekt. Wpisujemy nazwę i wybieramy typ projektu: Pipeline.
![3](https://user-images.githubusercontent.com/61689132/149335608-104bea2f-c6c2-4da1-9427-7b04224776cb.png)

W moim skrypcie, skorzystałem z plików Dockerfile i Dockerfile-test, które wykonałem na potrzeby Labu 2.

W sekcji pipeline, wpisujemy poniższy skrypt.

Skrypt:

![4](https://user-images.githubusercontent.com/61689132/149335647-49c0f3b0-a7a2-45a8-832e-05bca7d780ed.png)

Sekcja:

![5](https://user-images.githubusercontent.com/61689132/149335685-506f6a6e-73f0-411a-bc47-f47de898d2fe.png)

Po wpisaniu uzupełnieniu pól, wciskamy przycisk Save i przechodzimy do projektu.
![6](https://user-images.githubusercontent.com/61689132/149335711-2c912355-b287-4ae2-8d53-96cebf995fe2.png)

W widoku projektu, na powyższym screenie, żeby odpalić nasz projekt, wybieramy opcję 

Build now

Po tym, w sekcji poniżej, widzimy jak nasz projekt jest wykonywany i w zależności od tego czy wykona się poprawnie czy nie, otrzymamy stosowny znak przy buildzie projektu.
U mnie udało się go zbudować za 44 razem (liczba iście Mickiewiczowska ;) ), ponieważ przy każdym buildzie znajdowałem błędy w moim skrypcie. 
![7](https://user-images.githubusercontent.com/61689132/149335731-d68cac34-c6bd-4118-b994-a2d8cccbc92e.png)

Aby przejrzeć build i znaleźć np. błędy, wciskamy #Numer buildu który nas interesuje, a następnie wybieramy sekcję 

Console output

![8](https://user-images.githubusercontent.com/61689132/149335751-87b160f6-3786-4e40-9a42-e9cdd36e8e1f.png)

Poprawne wykonanie skryptu, pokaże nam komunikat który zamiesciłem poniżej.

![9 1](https://user-images.githubusercontent.com/61689132/149335776-a748f78d-4bcc-4a79-90ec-5816de07dbaa.png)

![9 2](https://user-images.githubusercontent.com/61689132/149335792-37d3599e-5d93-4feb-b27d-9e018b99d535.png)

![9 3](https://user-images.githubusercontent.com/61689132/149335799-05507f45-0dd6-4617-8a32-d689a0f2443c.png)
