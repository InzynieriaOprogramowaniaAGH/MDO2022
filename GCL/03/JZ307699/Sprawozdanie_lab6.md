Imię i nazwisko: Julia Żychowska, kierunek: Informatyka Stosowana (NS), nr. indeksu: 307699, przedmiot: Metodyki DevOps, sprawozdanie: 06, grupa: GĆL03

## Sprawozdanie

## Zajęcia 06 : zależność ciągłej integracji od komponentów stron trzecich
### Inwentaryzacja

  - Zdefiniuj w ramach stworzonego Dockerfile'a zależności platformowe
    node
  - Oprogramowanie, które doinstalowujesz aby uruchomić program
    git
  - W razie braku zależności (np. obraz node i aplikacje wymagająca tylko node), "zależnością" jest sam obraz
  - Określ okoliczności, w których uzasadnione jest przebudowywanie i aktualizacja obrazu po wydaniu nowej wersji którejś z zależności
    Jeśli zależność wymaga lepszej wersji obrazu
  - Czy należy to robić "zawsze"?
    Tak
  - Jakie są przesłanki (i jak je ustalić) wskazujące na konieczność aktualizacji
    Bez aktualizacji może gorzej działać, nie mamy dostępu do nowych funkcji
  - Jakie jest ryzyko aktualizowania/nieaktualizowania (im dokładniejszy przykład, tym lepiej)
    Aktualizowania, że coś jest nie do końca dopracowane i będą się pojawiać błędy. Niezaktualizowanie, że nie będziemy mieć dostępu do wszystkiego. Jakieś zależności mogą mieć wskazaną minimalną wersję.
  - Pytanie pomocnicze: czy obraz Fedory/Ubuntu na dockerhubie jest aktualizowany dla każdej nowej wersji pakietu wchodzącego w jego skład? Dlaczego tak/nie?
    

### Wdrożenie

Alternatywnie do zadania wyżej: określenie zależności od dostawcy chmurowego

  - Określ poziom zależności wdrożenia od środowiska chmurowego
  - Zweryfikuj dostępność studenckiego konta Azure i zapoznaj się z cennikiem
  
    ![image](https://user-images.githubusercontent.com/28841971/150878311-09b682f5-daf3-427b-8559-ae1a36e121d8.png)
    ![image](https://user-images.githubusercontent.com/28841971/150878762-73578c8b-fe34-42ad-8c54-a3eb9d4cbfb9.png)
    
  - Przeprowadź próbne wdrożenie obrazu w ramach dostępnych kredytów
    
  - Zatrzymaj i usuń kontener, i wstrzymaj storage space, aby nie generować rachunków na pustych przebiegach
