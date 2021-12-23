FROM ubuntu:latest

RUN apt-get -y update &&\
    apt-get -y upgrade &&\
    apt-get -y install nodejs &&\
    apt-get -y install npm &&\
    npx create-react-app my-app
    
WORKDIR /my-app

CMD ["npm","run","build"]
    
