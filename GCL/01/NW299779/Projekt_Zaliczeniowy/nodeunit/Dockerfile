FROM ubuntu:latest

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y
RUN apt install -y nodejs npm git
RUN git clone https://github.com/caolan/nodeunit

WORKDIR /nodeunit

RUN npm install

CMD ["npm", "build"]
