# Metodyki DevOps 2021/2022 NS
Zajęcia 02 - 2021-11-20
---
# Zestawienie środowiska
## 1. Instalacja Docker
Wykorzystywane środowisko to Debian na WSL2 - wymagało to również zainstalowania Docker Dekstop na windows, zgodnie z instrukcją: 
https://docs.docker.com/desktop/windows/wsl/

Zainstalowany Docker Desktop z integracją na WSL
![](img_02/01_windows_docker.png)

![](img_02/01_dockerwindows.png)

Instalacja na środowisku linuksowym zgodnie z instrukcją:
https://docs.docker.com/engine/install/debian/
![](img_02/01_dockerversion.png)

## 2. Rejestracja w Docker Hub
![](img_02/01_dockerhub.png)

## 3. Pobierz hello-world, busybox, ubuntu

Do pobrania wykorzystuje się komendę: docker image pull, natomiast przy próbie uruchomienia nieistniejącego obrazu, Docke pobierze obraz automatycznie:

![](img_02/03_helloworld.png)

## 4. Busybox

Uruchomienie busybox:

![](img_02/03_busybox.png)

Uruchomienie busybox interaktywnie i wywołanie numeru wersji
![](img_02/03_busybox_02.png)

## 5. "system w kontenerze"

Uruchoemienie ubuntu
![](img_02/05_system.png)

PID1 w kontenerze:

![](img_02/05_pid1.png)

Procesy dockera na hoście:
![](img_02/05_procesdocker.png)

Aktualizacja pakietów i wyjście z systemu:
![](img_02/05_updatexit.png)

## 6. Uruchomione kontenery

![](img_02/06_kontenery.png)

## 7. Czyszczenie obrazów

![](img_02/07_prune.png)

# Budowanie programu
