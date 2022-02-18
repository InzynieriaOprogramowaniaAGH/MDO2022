FROM ubuntu:latest

RUN mkdir my-vol

RUN apt-get -y update 

RUN apt-get -y upgrade 

RUN apt-get -y install tzdata

RUN apt-get -y install npm

RUN npm install -g n

RUN apt-get -y install curl

RUN n latest

RUN npx -y create-react-app my-app

RUN cd my-app && npm run build
