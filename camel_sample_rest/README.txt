README

## what's this?

JakartaEE10 sample REST app.
The porpose is to understand differences between JakartaEE8 and JakartaEE10(9.1) for myself.

## future prospects

koakjo will add some filters, soap architecture, JPA, and some functions.

## environment

wildfly 27.0.1
openjdk version "17.0.6" 2023-01-17

## reference

curl OK
curl -X POST -H "Authorization:access_token" -H "Content-Type: application/json" localhost:8080/camel_sample_rest-1.0-SNAPSHOT/RestClient/cameltest/rest1/

curl NG
curl -X POST -H "Content-Type: application/json" localhost:8080/camel_sample_rest-1.0-SNAPSHOT/RestClient/cameltest/rest1/
