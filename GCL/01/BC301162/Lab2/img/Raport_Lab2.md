## Pierwotna wersja sprawozdania, została przeze mnie nieumyślnie usunięta, wrzucam ponownie to sprawozdanie.

# Metodyki DevOps, Zajecia 02, Bartlomiej Ciernia

Instalujemy docker w naszym systemie. Poprawne zainstalowanie możemy sprawdzić komendą

`docker -v`

![1](https://user-images.githubusercontent.com/61689132/149663025-41e2aea2-51b7-4643-b5a3-0ef510a0adf2.png)

Tworzymy konto w DockerHub

![2](https://user-images.githubusercontent.com/61689132/149663032-b1946870-7020-42d9-a962-b7ac8666a790.png)

Pobieramy kolejno hello-world, busybox, ubuntu i mysql

![3 1](https://user-images.githubusercontent.com/61689132/149663052-cc56f545-bccb-4d03-91ca-62b166b8a056.png)
![3 2](https://user-images.githubusercontent.com/61689132/149663061-ab396461-31a0-43b6-8767-6ffcfda957f2.png)

Komendą `sudo docker images` możemy sprawdzić poprawność pobranych paczek

![3 3](https://user-images.githubusercontent.com/61689132/149663073-992480e1-6d76-4c38-9c88-4d403220ff44.png)

Uruchamiamy busy-box i inne paczki w dockerze

![4 1](https://user-images.githubusercontent.com/61689132/149663112-1e58e235-8a2e-4f25-8668-62aa47ee6a5b.png)

![4 2](https://user-images.githubusercontent.com/61689132/149663114-89ef1e1d-84c5-4c29-aec7-1ca66a52f2ee.png)

Komendą `docker ps-a` sprawdzamy czy projekty otworzyły się w kontenerach

![4 3](https://user-images.githubusercontent.com/61689132/149663127-40c2a98c-e371-4594-909f-eaa05d754d7a.png)

![5 1](https://user-images.githubusercontent.com/61689132/149663148-2994e117-0b4e-497d-8773-f9921e695131.png)
![5 2](https://user-images.githubusercontent.com/61689132/149663150-0b2fabac-0d71-42ab-8800-917aa42946f4.png)

![6 1](https://user-images.githubusercontent.com/61689132/149663179-f7f2f86f-d5df-4e1c-8990-dccce1f13a11.png)

![6 2](https://user-images.githubusercontent.com/61689132/149663187-9d2ee7b7-5d06-4d28-9e37-dbdc6c12e933.png)

Czyścimy obrazy

![7 1](https://user-images.githubusercontent.com/61689132/149663204-f68e3797-6f1d-4fbf-8fb9-6cb8e3758bd0.png)

![7 2](https://user-images.githubusercontent.com/61689132/149663210-277ec2a9-845d-4a1e-bf28-c34ae0230eb3.png)

![7 3](https://user-images.githubusercontent.com/61689132/149663212-cde282b5-804e-4cfe-8b30-ad8fd7cb0eda.png)

Kolejna część zadania korzysta ze strony `create-react-app.dev`. Jest tam umieszczona aplikacja, która pozwoli wykonać testy jednostkowe.

![8](https://user-images.githubusercontent.com/61689132/149663281-b8822ae1-2684-45a3-a1f4-795fa06b27df.png)

![8 2](https://user-images.githubusercontent.com/61689132/149663287-332000cb-ae97-4e12-8b62-e679cca7b386.png)

![8 3](https://user-images.githubusercontent.com/61689132/149663291-10174cf5-9115-428d-ace5-cf139b17fb51.png)

Widzimy że testy przeszły

![9](https://user-images.githubusercontent.com/61689132/149663306-78e7ea59-e7ea-47f9-ae52-d7304f0c172c.png)

Ponawiamy ten proces, tym razem w kontenerze, korzystając z obrazu ubuntu.

![10](https://user-images.githubusercontent.com/61689132/149663336-ecc1b821-1b9e-4726-b3ba-9c081047934c.png)

![11](https://user-images.githubusercontent.com/61689132/149663339-3fd2d748-eca6-407f-a9a4-1b78ba18a0e2.png)

Aktualizujemy i instalujemy paczki potrzebne do projektu w kontenerze

![12](https://user-images.githubusercontent.com/61689132/149663342-52125660-fff0-4835-817e-1ffab3ea3c3b.png)

![13](https://user-images.githubusercontent.com/61689132/149663365-f1c474bf-eb20-4cd8-9ccc-f66af0ad3498.png)

Odpalamy testy tym razem w kontenerze

![14](https://user-images.githubusercontent.com/61689132/149663369-9fb4cc9f-bdcb-4560-a961-f66b9e7f1a32.png)

Ponownie widzimy że przeszły

![15](https://user-images.githubusercontent.com/61689132/149663399-29a57ee8-bd93-4341-92ff-4956d0b0adb2.png)

Tworzymy Dockerfiles

Dockerfile:

![16](https://user-images.githubusercontent.com/61689132/149663422-320751de-bebf-49cf-be40-6dbe33630ad6.png)

Budujemy pierwszy plik

![17](https://user-images.githubusercontent.com/61689132/149663442-782c2c1e-0bec-4f8e-b000-353164758752.png)

![18](https://user-images.githubusercontent.com/61689132/149663499-e01ddabf-1d9a-4de8-8ab8-a61c69f2c45f.png)

Dockerfile-test:

![19](https://user-images.githubusercontent.com/61689132/149663510-b7d98839-2630-4b4a-ac0d-e7756b86ae3c.png)

I również jego budujemy

![20](https://user-images.githubusercontent.com/61689132/149663524-657bc3f6-a201-48c1-a7bf-fc3d29baaa5e.png)

Możemy podejrzeć przygotowane pliki

![21](https://user-images.githubusercontent.com/61689132/149663529-83f604f7-eb7d-4826-a925-5edfd3c394c8.png)
