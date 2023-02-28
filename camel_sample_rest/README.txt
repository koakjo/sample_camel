README

ENV
wildfly 27.0.1
openjdk version "17.0.6" 2023-01-17

curl OK
curl -X POST -H "Authorization:access_token" -H "Content-Type: application/json" localhost:8080/camel_sample_rest-1.0-SNAPSHOT/RestClient/cameltest/rest1/

curl NG
curl -X POST -H "Content-Type: application/json" localhost:8080/camel_sample_rest-1.0-SNAPSHOT/RestClient/cameltest/rest1/
