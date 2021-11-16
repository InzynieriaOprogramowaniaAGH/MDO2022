## Metodyki DevOps 2021/2022 NS
Agnieszka Talik
lab_01
gr_03
indeks: 285937

1.	Klient git i klonowanie  repozytorium za pomocą HTTPS

-Za pomocą git -version sprawdzamy czy git jest u nas poprawnie zainstalowany

-Przez git clone kopiujemy repozytorium za pomocą HTTPS

![screen01](https://user-images.githubusercontent.com/80592460/142023708-e12096b0-e6ed-48ee-bcfc-3c698ff27b25.png)

2.	Tworzenie kluczy

-Korzystamy z ssh-keygen podając również swój mail z github

-Podajemy ścieżkę zapisu lub zostawiamy domyślną i podajemy nazwę pliku

-W pierwszym przypadku zostawiłam puste hasło, w drugim stworzyłam klucz z hasłem

![screen02](https://user-images.githubusercontent.com/80592460/142023779-20d053e3-6f8d-456d-b42d-56e314d94454.png)

3.	Klonowanie repozytorium z wykorzystaniem protokołu SSH

-Dodajemy na github utworzone wcześniej publiczne klucze SSH

-Klonujemy repozytorium korzystając z protokołu SSH

![screen03](https://user-images.githubusercontent.com/80592460/142023801-2c18f795-1590-452c-a188-a6f21e41c2ca.png)


4. Tworzenie nowej gałęzi i przełączenie na nią

-git-checkout - przełącza gałęzie

-git-branch - tworzy gałąź

![screen04](https://user-images.githubusercontent.com/80592460/142023840-1805ebce-5817-40b3-aeb6-88f7a86376ff.png)


6.	Utworzenie katalogu, dodanie sprawozdania, wysłanie zmian do zdalnego źródła

-tworzymy katalog, dodajemy sprawozdanie

-git commit -m "dodanie_sprawozdania" - robimy commit lokalnie

-git push origin AT285937 - wypychamy zdalnie

![screen05](https://user-images.githubusercontent.com/80592460/142023856-4e01b39a-6b40-402e-9688-7f7f554b8b8f.png)


7.	Scalenie mojej gałęzi z gałęzią grupy oraz próba wciągnięcia scalonych zmian na zdalne źródło

-git merge AT285937 - Lokalne scalanie przebiegło pomyślnie

-git push - Przy próbie wypchnięcia na gałąź grupy zdalną otrzymujemy błąd, ponieważ gałąź jest chroniona

![screen06](https://user-images.githubusercontent.com/80592460/142023896-4668d4aa-5bf7-45c3-89ca-b17333512d70.png)


8.	Oznaczenie tagiem ostatniego commita i wypchnięcie go na zdalną gałąź

-git tag v1.0_AT - stworzenie taga

-git tag - sprawdzenie listy tagów

-git push origin v1.0_AT - wypchnięcie taga na zdalną gałąź

![screen07](https://user-images.githubusercontent.com/80592460/142023929-f4364075-81fc-4df0-9e05-aa3d51e7525c.png)

9.	Wystawienie Pull Request do gałęzi grupowej

-Wybranie gałęzi, dodanie opisu, stworzenie pull request na githubie

![screen08](https://user-images.githubusercontent.com/80592460/142023946-f9eede08-5ca4-424c-b41f-3ebd14c518d6.png)
