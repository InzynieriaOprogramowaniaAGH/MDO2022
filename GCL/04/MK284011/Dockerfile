FROM node

WORKDIR /appES

RUN apt-get update

RUN apt-get install git

RUN git clone https://github.com/evanshortiss/nodejs-testing-examples.git

WORKDIR /appES/nodejs-testing-examples

RUN npm install