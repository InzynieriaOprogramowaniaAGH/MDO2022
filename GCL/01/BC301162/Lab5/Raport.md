# Metodyki DevOps, Zajecia 05, 16.01.2022, Bartlomiej Ciernia

Zaczynamy od instalacji minikube. Korzystamy ze strony z dokumentacją, która jest bardzo dobrze opisana.

![1](https://user-images.githubusercontent.com/61689132/149662430-f7e34afe-4994-4589-8320-d845c51ae26a.png)

![2](https://user-images.githubusercontent.com/61689132/149662439-cefd17eb-62a2-42fa-86fa-07d7aa85f2eb.png)

![3](https://user-images.githubusercontent.com/61689132/149662454-b9930e47-a30e-43d6-9587-0ff61907bf81.png)

![4](https://user-images.githubusercontent.com/61689132/149662462-8c1d02ff-f0d0-4a07-b989-fac3d0c31b31.png)

Następnie, instalujemy kubectl, również korzystając ze strony z dokumentacją.

![5](https://user-images.githubusercontent.com/61689132/149662499-3865060d-cc56-4c5b-9bb1-4da1b3f76791.png)

![6](https://user-images.githubusercontent.com/61689132/149662505-922879b0-d3ed-4dff-89dd-c8564c8c4ebb.png)

Po wpisaniu komendy

`docker ps`

Widzimy że wpojawił się kontener z minikube, o ile wszystko poprawnie się zainstalowało.

![7](https://user-images.githubusercontent.com/61689132/149662510-835fc37d-4407-4754-8606-12a767bdb5ce.png)

![8](https://user-images.githubusercontent.com/61689132/149662544-7b0b2013-78e9-47fb-8cce-374b7f4583b6.png)

Uruchamiamy minikube dashboard.

![9 1](https://user-images.githubusercontent.com/61689132/149662583-9e90c3fd-85fa-4bfe-8375-b5d39dc8e2a4.png)

Zostajemy przekierowani do przeglądarki w której możemy sprawdzić aktualny stan dashboarda.

![9 2](https://user-images.githubusercontent.com/61689132/149662606-76dc7ada-30dd-439f-983e-f71ee2ee8c5e.png)

Dostępne usługi:
![9 3](https://user-images.githubusercontent.com/61689132/149662674-11a9a3c1-eb50-4437-8a13-dc801265a02b.png)

Stan "przed":

![11 1](https://user-images.githubusercontent.com/61689132/149662699-8f6c980a-fa89-4b8f-9234-42205bf2b02d.png)
![11 2](https://user-images.githubusercontent.com/61689132/149662710-0223d0c8-3889-4479-aac2-068fee773858.png)

Po powrocie do konsoli, możemy zobaczyć że po wpisaniu ponownie komendy

`kubectl get po -A`

pojawiły nam się nowe wiersze

![10](https://user-images.githubusercontent.com/61689132/149662791-af4a753e-4189-4a3e-9934-f7f20cf53abf.png)

Tworzymy przykładowy deployment

![12](https://user-images.githubusercontent.com/61689132/149662805-dff14b55-1efd-4d51-9155-8d63e491b696.png)

![13](https://user-images.githubusercontent.com/61689132/149662810-7eeaeec4-cc8c-49cf-9317-2d83de02442a.png)

Stan "po":

![14 1](https://user-images.githubusercontent.com/61689132/149662829-67b4bb4a-1836-4329-8e66-fc9cf9cc853b.png)

![14 2](https://user-images.githubusercontent.com/61689132/149662833-525c1911-2bb7-452f-a734-e71a2df8a973.png)
