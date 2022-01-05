FROM ubuntu:latest

WORKDIR /app

RUN apt-get update
RUN apt-get install -y nodejs
RUN apt-get install -y npm
RUN npm install -g create-react-app
RUN apt-get install -y git
RUN git clone https://github.com/aditya-sridhar/simple-reactjs-app.git
WORKDIR /app/simple-reactjs-app
RUN npm i
RUN npm run build
