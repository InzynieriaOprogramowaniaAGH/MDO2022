FROM ubuntu:latest

WORKDIR /app

RUN apt-get update
RUN apt-get install -y nodejs
RUN apt-get install -y npm
RUN npm i -y -g @angular/cli@11.1.1
RUN apt-get install -y git
RUN apt-get install -y wget
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN apt install -y ./google-chrome-stable_current_amd64.deb
RUN git clone https://github.com/fyodorio/angular-unit-testing-examples.git
WORKDIR /app/angular-unit-testing-examples
RUN npm i
RUN npm run build

 
