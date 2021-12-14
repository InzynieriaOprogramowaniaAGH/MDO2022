FROM node

WORKDIR /appES

RUN apt-get update

RUN apt-get install git

RUN git clone https://github.com/philipwalton/easy-sauce.git

WORKDIR /appES/easy-sauce

RUN npm install

