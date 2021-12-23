FROM ubuntu:latest

RUN apt-get -y update &&\
    apt-get -y upgrade &&\
    apt-get -y install nodejs &&\
    npx create-react-app my-app
    
WORKDIR /my-app

CMD ["npm","run","build"]
    
