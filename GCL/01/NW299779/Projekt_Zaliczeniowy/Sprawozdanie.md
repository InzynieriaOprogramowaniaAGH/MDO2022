# Projekt zaliczeniowy Metodyki DevOps
# Uruchomienie kontenera z ubuntu
### 1. Należy pobrać ubuntu za pomocą komendy `docker pull ubuntu`

![pull ubuntu](https://user-images.githubusercontent.com/48067540/154765887-a59324d9-45a1-47c0-953b-01aec161aa34.png)

### 2. Aby kontener po uruchomieniu nie został natychmiast zamknięty uruchamiamy go w trybie detached korzystająć z flagi -dt 

  `docker run -dt ubuntu`
  
  oraz sprawdzamy status za pomocą `docker ps -a`
  
  ![run ubuntu detached](https://user-images.githubusercontent.com/48067540/154766028-0549a7b1-559e-433c-b3c8-b3cbffccaafa.png)

# Wybór programu

W projekcie wykorzystam następujące repozytorium umożliwiające budowę oraz uruchumoenie testów jednostkowych.

`https://github.com/caolan/nodeunit`

# Budowanie programu w kontenerze
### 1. Otwieram konsolę bash uruchumionego w kontenerze ubuntu za pomocą 

`docker exec -it <id> bash`

![open container bash](https://user-images.githubusercontent.com/48067540/154766705-a06d86f8-7344-40c1-aa2d-4e85b0a73b92.png)

### 2. Aktualizuję pakiety `apt-get update && apt-get upgrade`

![packages update](https://user-images.githubusercontent.com/48067540/154766858-13657639-f842-428d-9eff-c886e7d1e17c.png)

### 3. Instaluję Node.js `apt-get install nodejs`

![intall node](https://user-images.githubusercontent.com/48067540/154767020-a8fdf7d4-c864-48ed-9f11-5cd9191c4280.png)

### 4. Instaluję npm `apt-get install npm`

![install npm](https://user-images.githubusercontent.com/48067540/154767213-320bd914-8054-4595-9dc0-8410c3dc7bdc.png)



