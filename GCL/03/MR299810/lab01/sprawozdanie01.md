
# Lab 01 sprawozdanie
## Mateusz Rudziński
### 1. Instalowanie GIT
Ponieważ miałem już GIT'a zainstalowanego na swoim urządzeniu, wyświetlam jego wersję.
![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/1.png)
### 2. Klonowanie repozytorium 
 Do klonowania repozytorium używamy komendy git clone 
![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/2.png)
### 3. Klonowanie przy pomocy klucza SSH
#### Generowanie kluczy 
Pierwszy klucz generowany jest przy pomocy metody ECDSA i jest on zabezpieczony hasłem
![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/3.1.png)

Drugi klucz generowany jest przy pomocy metody ed25519 i jest bez hasła

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/3.2.png)

Konfigurujemy klucz SSH jako metodę dostępu 

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/3.3.png)
![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/3.4.1.png)
![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/3.4.png)

Klonujemy repozytorium przy pomocy protokołu SSH

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/3.5.png)

### 4.Przełączanie się między gałęziami

#### Przełączanie na gałąź swojej grupy

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/4.png)

#### Tworzenie nowej gałęzi z inicjałami i nr indeksu

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/5.png)

### 5. Wysyłanie zmian do zdalnego źródła

Dodajemy pliki do stage'a

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/6.1.png)

Tworzymy commit

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/6.2.png)

Wypychamy zmiany do origina

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/6.3.png)

Próba wciągnięcia swojej gałęzi do gałęzi grupowej

![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/6.5.png)
![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/6.6.png)

Oznaczanie tagiem ostatniego commita i wypychanie go na zdalną gałąź 
![App Screenshot](https://github.com/InzynieriaOprogramowaniaAGH/MDO2022/blob/dd6d0ea80783d07866dcbf1fe00116855b44fd58/GCL/03/MR299810/lab01/6.7.png)

Ustalanie hook'a, który będzie sprawdzal czy wiadomosc z commitem zawiera nazwe przedmiotu
............

### Pull request 
