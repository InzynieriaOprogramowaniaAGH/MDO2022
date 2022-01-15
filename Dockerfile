FROM ubuntu

MAINTAINER Szymon Brandys

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update &&  apt-get -y install \
 nodejs \
 npm 
 
RUN npx create-react-app my-app

WORKDIR /my-app

RUN npm run test

EXPOSE 3000


