FROM ubuntu

MAINTAINER Szymon Brandys

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get install -y curl git

RUN curl -sL https://deb.nodesource.com/setup_14.x -o setup_14.sh
RUN sh ./setup_14.sh
RUN apt-get install -y nodejs
RUN npx create-react-app my-app

WORKDIR /my-app

RUN npm run test

EXPOSE 3000


