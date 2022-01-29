# 1.Pobranie obrazu Ubuntu

![1](https://user-images.githubusercontent.com/58219271/151593191-545bd626-7169-461b-8f2f-ef615ad9a029.JPG)

# 2.Stworzenie woluminu i podłączenie go do kontenera
![2](https://user-images.githubusercontent.com/58219271/151593331-68599323-33c4-4232-bfad-0e80b2b4e15e.JPG)

# 3.Skopiowanie pliku do katalogu woluminu
![3](https://user-images.githubusercontent.com/58219271/151593441-cc845c99-10e4-42e3-82db-4d31d9fceb02.JPG)
![4](https://user-images.githubusercontent.com/58219271/151593722-db71fc97-b8c4-409b-8a07-2788906edd80.JPG)

# 4.Utworzenie pliku w kontenerze na obszarze woluminu

![5](https://user-images.githubusercontent.com/58219271/151594055-78badd1b-1adb-408a-9128-1fe7e6be4f49.JPG)

"Kiepski pomysł": SSH 

# 5.Uruchomienie i wyekspowanie wybranego portuw kontenerze
![6](https://user-images.githubusercontent.com/58219271/151594235-ce1e3707-5e7c-4509-9f3b-6bcde31c1aab.JPG)
![12](https://user-images.githubusercontent.com/58219271/151594928-c89a8701-4b53-489e-9f1f-8c418cef84db.JPG)

# 6.Wykonanie komend apt-get update i apt-get install openssh-server w kontenerze
![8](https://user-images.githubusercontent.com/58219271/151594451-03dfec5e-7260-460a-bbcf-4657d61e8567.JPG)

# 7.Odkomentowanie logowania roota w pliku sshd_config i zmiana na "yes"

![9](https://user-images.githubusercontent.com/58219271/151594593-f07cadb8-ecc2-434c-aafa-bcdf47ec4ed5.JPG)

# 8.Następnie konieczne było skopiowanie klucza publcznego w odpowiednie miejsce. Stworzono dla niego katalog
![15](https://user-images.githubusercontent.com/58219271/151595221-dde96e9f-4e90-48e5-ba8e-8afc8f9461bf.JPG)
![10](https://user-images.githubusercontent.com/58219271/151594776-10fbe572-aed0-49f9-abf9-da40ac6817ab.JPG)

# 9.Konieczne było odnalezienie adresu IP kontenera w wewnętrznej sieci
![13](https://user-images.githubusercontent.com/58219271/151595054-b0510e98-e2bf-4688-9f54-af5306da4804.JPG)

# 10.Uruchomiono usługę i połączono z kontenerem

![14](https://user-images.githubusercontent.com/58219271/151595159-01324f5b-5416-43e8-8863-20e69b26fd8f.JPG)
![16](https://user-images.githubusercontent.com/58219271/151595250-9eeb5083-2915-4790-8700-6cfdd2a79a62.JPG)

## Skonteneryzowany Jenkins stosujący Dockera

12.Zapoznano sie z instrukcją i uruchomiono obraz Dockera
![17](https://user-images.githubusercontent.com/58219271/151595382-3200c02e-335b-492d-9f8c-44d9c8f39ed1.JPG)
![18](https://user-images.githubusercontent.com/58219271/151595474-6aafa6a6-cfc5-46a8-8d7c-05bc595a94a5.JPG)

# 11.Uruchomiono blueocean

![19](https://user-images.githubusercontent.com/58219271/151595565-02780643-b21c-4dcb-9223-8c85223e4f45.JPG)


# 12.Zalogowano do Jenkinsa

![20](https://user-images.githubusercontent.com/58219271/151595655-39403d7a-3c4c-4306-8471-56bdb205c864.JPG)
![21](https://user-images.githubusercontent.com/58219271/151595677-a4623b9b-0f37-4169-8012-49743cff1187.JPG)

# 13.Utworzono pierwszy projekt. W konsoli napisano "uname -a"
![22](https://user-images.githubusercontent.com/58219271/151595736-1c356699-730d-4a71-b6b7-6d580113135b.JPG)
![23](https://user-images.githubusercontent.com/58219271/151595871-e33c11c4-f57f-4b37-b5d3-381ef231c5f3.JPG)

# 14.Utworzono program sprawdzający czy godzina jest parzysta. W innym przypadku wypisywano błąd
![24](https://user-images.githubusercontent.com/58219271/151595954-1c808544-8ad6-4033-9329-d2df5ede59a3.JPG)
![25](https://user-images.githubusercontent.com/58219271/151595964-1f35d81a-0157-4b93-be88-b055955e509e.JPG)

# 15.Na końcu próbowano zrobić projekt który klonuje nasze repozytorium
![26](https://user-images.githubusercontent.com/58219271/151596090-6f284dea-d822-4283-b306-6cc9722c06e0.JPG)
![27](https://user-images.githubusercontent.com/58219271/151596101-80fad293-52f8-4d23-bbf1-77b6d0e852a3.JPG)
![29](https://user-images.githubusercontent.com/58219271/151596109-c6830a5d-5cf5-45a5-8a3d-033ce275271f.JPG)
![30](https://user-images.githubusercontent.com/58219271/151596116-3d621ad4-afd3-4d97-8898-5ffdb0f363b4.JPG)
