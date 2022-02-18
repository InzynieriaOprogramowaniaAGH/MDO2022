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

![node version](https://user-images.githubusercontent.com/48067540/154767432-d137737f-7650-4a33-b484-8798eed94964.png)


### 4. Instaluję npm `apt-get install npm`

![install npm](https://user-images.githubusercontent.com/48067540/154767213-320bd914-8054-4595-9dc0-8410c3dc7bdc.png)

![npm version](https://user-images.githubusercontent.com/48067540/154767435-986fb9f3-5805-4896-99b3-ab40db45fc87.png)

### 5. Instaluję git `apt-get install git`

![install git](https://user-images.githubusercontent.com/48067540/154767594-18cc5e56-a4fc-4478-8279-499ce1e22220.png)

### 6. Klonuję repozytorium `git clone <repo_url>`

![clone repo](https://user-images.githubusercontent.com/48067540/154767822-5729a691-4d82-4a63-aa5e-dff9937b244b.png)

### 7. Instaluję w katalogu projektu wszystkie potrzebne biblioteki `npm install`

![install node packages](https://user-images.githubusercontent.com/48067540/154768040-261492dc-0ad5-4a69-b3f8-eaad265ce6b0.png)

### 8. Budowa aplikacji i uruchomienie testów 
```
npm build
npm test
```

![build and run test](https://user-images.githubusercontent.com/48067540/154768851-d93bfe9d-62c7-4be4-b7e7-dc8ab3d6670e.png)

![test results](https://user-images.githubusercontent.com/48067540/154768857-431b532e-b0a4-42fa-b2c4-4b84f460c9e5.png)








