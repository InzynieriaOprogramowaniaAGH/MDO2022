# Metodyki DevOps 2021/2022 NS
Zajęcia 01 - 2021-11-06
---
 
## 1. Instalacja git 
![](img/1.git.png)

## 2. Sklonowanie repozytorium za pomocą HTTPS
![](img/2.clone%20https.PNG)

## 3. Sklonowanie repozytorium za pomocą SSH

### Utworzenie 2 kluczy SSH Ed25519 i podpięcie pod github
![](img/3.%20add%20ssh.png)
![](img/3.%20ssh%20keys%20github.PNG)

### Sklonowanie repozytorium z wykorzystaniem SSH
![](img/3.clone%20ssh.PNG)

## 4. Przełączenie na gałąż swojej grupy
![](img/4.git%20branch.PNG)

## 5. Utworzenie gałęzi "inicjały & numer indeksu", w moim przypadku "KM285405"
![](img/5.new%20branch.PNG)

## 6. Praca na nowej gałęźi

### Utworzenie nowego katalogu "KM285405"
![](img/6.%20new%20catalog.PNG)

### Dodanie pliku ze sprawozdaniem
![](img/6.report.png)

### Dodanie zrzutów ekranu i wysłanie zmian
![](img/6.%20git%20config.png)

![](img/6.%20git%20add.PNG)
![](img/6.%20commit%20%26%20push.PNG)

### Próba wyciągnięcia gałęzi do gałęzi grupowej
![](img/6.failed.png)

### Aktualizacja sprawozdania o krok z błędem
![](img/6.after_fail_update.png)

### Oznaczenie tagiem ostatniego commitu

Po utworzeniu commitu, dodanie 'git tag', a następnie push tags

![](img/6.git_tag.png)

### Hook sprawdzający czy wiadomość z commitem zawiera nazwę przedmiotu

W repozytorium znajduje się katalog .git/hooks, gdzie znajdują się przykładowe hooki z rozszerzeniem .sample
![](img/6.hooks_1.PNG)

Do sprawdzenia wiadomości z commitem należy wykorzystać commit-msg i zmodyfikować kod i usunąć rozszerzenie '.sample' z pliku
![](img/6.hook_check.png)

Teraz podczas commitu sprawdzana jest wiadomość:
![](img/6.hooks_test.png)

### Hook ustawiający prefiks wiadomości commitu


## 7. Pull request

