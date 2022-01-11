# Metodyki DevOps - Lab 1

## Zadania do wykonania
1. #### Zainstaluj klienta Git i obsługę kluczy SSH

    Otwieramy terminal i za pomocą komendy **sudo apt-get install git ssh** instalujemy paczki git oraz SSH:
    ![Instalacja gita i ssh](img/1_1.png)

    Następnie konfigurujemy globalną nazwę użytkownika oraz adres email, które będą używane przez gita:
    ![Konfiguracja adresu email i nazwy użytkownika](img/1_2_config_git.png)

2. #### Sklonuj repozytorium https://github.com/InzynieriaOprogramowaniaAGH/MDO2022 za pomocą HTTPS
    Do klonowania wykorzystujemy komendę **git clone**:
    ![Klonowanie repozytorium przez HTTPS](img/2_clone_https.png)
    
3. #### Upewnij się w kwestii dostępu do repozytorium jako uczestnik i sklonuj je za pomocą utworzonego klucza SSH
    - **Utwórz dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem**
      
      Klucz bez hasła (metoda ed25519): 
      ![Tworznie klucza ssh bez hasła](img/3_1_ssh_key_without_password.png)

      Klucz z hasłem (metoda ECDSA):
      ![Tworznie klucza ssh z hasłem](img/3_2_ssh_key_with_password.png)

   - **Skonfiguruj klucz SSH jako metodę dostępu**
      
      Odczytujemy stworzone klucze i dodajemy je do konta na Github (Settings > SSH and GPG keys > New SSH key):
      
      ![Odczytywanie wartości kluczy](img/3_3_read_keys_val.png)
      
      Tworzenie nowego klucza:
      ![Dadawanie klucza - Github](img/3_4_github_ssh.png)
      
      Dodane klucze - Github:
      ![Dodane klucze - Github](img/3_5_ssh_keys.png)
      
   - **Sklonuj repozytorium z wykorzystaniem protokołu SSH**
    
      Usunięcie wcześniejszego repozytorium i sklonowanie nowego przy użyciu SSH:
      
      ![Sklonowanie repozytorium przez SSH](img/3_6_ssh_clone.png)
    
    
4. #### Przełącz się na gałąź swojej grupy

    ![Checkout na gałąź grupy](img/4_checkout.png)
    
5.  #### Utwórz gałąź o nazwie "inicjały & nr indeksu" np. ```KD232144```
    
    ![Nowy branch](img/5_new_branch.png)
    
    
6. #### Rozpocznij pracę na nowej gałęzi
   - W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu" np. ```KD232144```
   - W nowym katalogu dodaj plik ze sprawozdaniem
   - Dodaj zrzuty ekranu
    
        ![Tworznie katalogów na nowej gałęzi](img/6_1.png)

