FROM ubuntu:latest

ARG DEBIAN_FRONTEND=non interactive

RUN apt-get update && apt-get install -y
RUN apt install -y nodejs npm git
RUN git clone https://github.com/NorStas/zagadnienie_posrednika_frontend.git

WORKDIR /zagadnienie_posrednika_frontend

RUN npm install

CMD ["npm", "run-script", "build"]

