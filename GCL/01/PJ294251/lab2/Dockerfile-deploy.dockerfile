FROM httpd:2.4
WORKDIR ~/reactjs
COPY --from=app facebook/create-react-app  /usr/local/
