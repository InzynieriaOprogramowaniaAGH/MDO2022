FROM httpd:2.4
WORKDIR ~/reactjs
COPY --from=ubuntu_react  facebook/create-react-app  /usr/local/