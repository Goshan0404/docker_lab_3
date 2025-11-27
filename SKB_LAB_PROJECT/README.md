# About
Assignment for Sinara lab course.

## Description
The project presents backend server for pizza order service. Contains sub service [SINARA_LAB_MICRO_SERVICE](https://github.com/Goshan0404/SKB_LAB_MICRO_SERVICE).

## Functionality
Proviedes crud operations in postgresql with auth and order entities, api rest controllers, auth validation, kafka producer orders, aop class for request limits, profile configurations.

## How to run
put this project and [sub project](https://github.com/Goshan0404/SKB_LAB_MICRO_SERVICE) in common directory, then run:

```docker compose up --build```

to build and run docker-compose.yaml from this project.


docker-compose contains services: pgamdin, postgres, 3 kafka services, skb_lab_project, skb_lab_micro_service 
