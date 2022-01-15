FROM ubuntu

MAINTAINER Szymon Brandys

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update &&  apt-get -y install \
curl 

RUN curl -sL https://deb.nodesource.com/setup_16.x -o nodesource_setup.sh
RUN sh ./nodesource_setup.sh
RUN apt-get install -y nodejs
RUN npx create-react-app my-app

WORKDIR /my-app

RUN npm run test

EXPOSE 3000


