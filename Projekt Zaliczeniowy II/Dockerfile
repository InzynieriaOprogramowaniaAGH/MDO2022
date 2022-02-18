FROM ubuntu:latest

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y
RUN apt install -y nodejs npm git curl
RUN curl -sL https://deb.nodesource.com/setup_14.x | bash -
RUN apt install -y nodejs
RUN git clone https://github.com/jterkalski/react-app.git

WORKDIR /react-app

RUN npm install

CMD ["npm", "run", "build"]
