FROM ubuntu:20.04 AS development

RUN apt-get update && apt-get -y upgrade
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get install -y nodejs npm
RUN npx create-react-app my-app-container

WORKDIR /my-app-container

CMD ["npm", "run", "build"]
