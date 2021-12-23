FROM ubuntu:latest

RUN apt-get -y update &&\
    apt-get -y upgrade &&\
    curl -sL https://deb.nodesource.com/setup_14.x | sudo bash - &&\
    apt -y install nodejs &&\
    apt-get -y install npm &&\
    npx create-react-app my-app
    
WORKDIR /my-app

CMD ["npm","run","build"]
    
