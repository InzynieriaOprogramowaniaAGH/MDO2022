Imię i nazwisko: Julia Żychowska, kierunek: Informatyka Stosowana (NS), nr. indeksu: 307699, przedmiot: Metodyki DevOps, sprawozdanie: 06, grupa: GĆL03

## Sprawozdanie

## Zajęcia 06 : zależność ciągłej integracji od komponentów stron trzecich
### Inwentaryzacja

  - Zdefiniuj w ramach stworzonego Dockerfile'a zależności platformowe
  - Oprogramowanie, które doinstalowujesz aby uruchomić program
  - W razie braku zależności (np. obraz node i aplikacje wymagająca tylko node), "zależnością" jest sam obraz
  - Określ okoliczności, w których uzasadnione jest przebudowywanie i aktualizacja obrazu po wydaniu nowej wersji którejś z zależności
  - Czy należy to robić "zawsze"?
  - Jakie są przesłanki (i jak je ustalić) wskazujące na konieczność aktualizacji
  - Jakie jest ryzyko aktualizowania/nieaktualizowania (im dokładniejszy przykład, tym lepiej)
  - Pytanie pomocnicze: czy obraz Fedory/Ubuntu na dockerhubie jest aktualizowany dla każdej nowej wersji pakietu wchodzącego w jego skład? Dlaczego tak/nie?

### Wdrożenie

Alternatywnie do zadania wyżej: określenie zależności od dostawcy chmurowego

  - Określ poziom zależności wdrożenia od środowiska chmurowego
  - Zweryfikuj dostępność studenckiego konta Azure i zapoznaj się z cennikiem
  - Przeprowadź próbne wdrożenie obrazu w ramach dostępnych kredytów
  - Zatrzymaj i usuń kontener, i wstrzymaj storage space, aby nie generować rachunków na pustych przebiegach
