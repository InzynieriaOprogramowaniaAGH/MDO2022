FROM httpd:2.4
COPY --from=ubuntu_react  my-app/build  /usr/local/apache2/htdocs/