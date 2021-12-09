FROM ubuntu

RUN apt-get update && apt-get upgrade
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get install -y nodejs npm
RUN npx create-react-app test-app-container

WORKDIR /test-app-container

CMD ["npm", "run", "build"]
