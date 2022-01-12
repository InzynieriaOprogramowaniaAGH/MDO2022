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
   - **W katalogu właściwym dla grupy utwórz nowy katalog, także o nazwie "inicjały & nr indeksu" np. ```KD232144```**
   - **W nowym katalogu dodaj plik ze sprawozdaniem**
   - **Dodaj zrzuty ekranu**
    
        ![Tworznie katalogów na nowej gałęzi](img/6_1.png)
        
   - **Wyślij zmiany do zdalnego źródła**
   
        Dodajemy pliki do stage'a i tworzymy nowy commit:
        
        ![Stage i commit](img/6_2_stage_and_commit.png)
        
        Następnie wypychamy zmiany na wskazaną gałąź
        
        ![Push do brancha](img/6_3_push.png)
        
        
   - **Spróbuj wciągnąć swoją gałąź do gałęzi grupowej**
        
        ![Próba wciągnięcia gałęzi do gałęzi grupowej](img/6_4.png)
        
        O ile nie ma problemu z wciągnięciem swojej gałęzi do gałęzi grupowej lokalnie, to z powodu blokady nie jesteśmy w stanie wykonać pusha:
        
        ![Blokada merga](img/6_5.png)

   - **Oznacz tagiem ostatni commit i wypchnij go na zdalną gałąź**
            
        Stworzenie opisanego taga przy użyciu komendy **git tag** i przypisanie go do ostatniego commita (używając jego sha):
        
        ![Oznaczenie commita tagiem](img/6_6_tag.png)  
        gdzie v0.0.1 to nazwa taga, a "tag created" to jego opis.

   - **Ustal hook, który będzie sprawdzał, czy wiadomość z commitem zawiera nazwę przedmiotu**
        
        Przechodzimy do folderu **.git/hooks** znajdującego się w głównym katalogu naszym repozytorium i tworzymy plik commit-msg (nazwa jest narzucona z góry; bez rozszerzeń).
        Zapisujemy w nim następujący skrypt w bashu:
        
        ![Hook 1](img/6_7_hook_1.png)  
        
        Próba wykonania commita z niewłaściwą wiadomością:
        ![Hook 1 - test](img/6_7_hook_1_test.png)  
        (aby skrypt był wykonywalny, nadajemy mu prawa używając komendy **chmod +x**) 
        
        
   - **W jaki sposób stworzyć hook, który będzie *ustawiał* prefiks wiadomości commitu tak, by miał nazwę przedmiotu?**
        
        Aby stworzyć hook, ustawiający prefiks wiadomości commitu, możemy skorzystać z hooka **prepare-commit-msg** (w tym przypadku równie dobrze moglibyśmy skorzystać też z commit-msg lub pre-commit-msg, ale zadanie jest bardziej zgodne z przeznaczeniem prepare-commit-msg)
        
        ![Hook 2](img/6_8_hook_2.png)  
        
        Test hooka:  
        ![Hook 2 - test](img/6_9_hook_2test.png)  
        
1. Wystaw Pull Request do gałęzi grupowej
    
    Przechodzimy do głównego repozytorium grupy (https://github.com/InzynieriaOprogramowaniaAGH/MDO2022), wchodzimy w zakładkę **Pull requests** i klikamy **New pull request**.     
    ![Pull request 1](img/7_1_pull_request.png)  
    
    Następnie wybieramy gałęzie z i do której chcemy wykonać pull request.  
    ![Pull request 1](img/7_2.png)  
    
        
