# FITTour | Snowboarding Database

This is semestral project to the bi-tjv 2021 (java technology) course at CTU FIT. 

## About

This is a REST API implemented using Spring framework. It runs at `http://localhost:8080/`.

There is a web-client dedicated to this project, link: https://gitlab.fit.cvut.cz/kousaja1/fittour-client

### Operations allowed

**/snowboards** - *GET, POST*

**/snowboards/{id}** - *GET, PUT, DELETE*

**/snowboards/brand/{name}** - *GET*

**/riders** - *GET, POST*

**/riders/{id}** - *GET, PUT, DELETE*

**/riders/{id}/snowboard** - *PUT, DELETE*

**/contests** - *GET, POST*

**/contests/{id}** - *GET, PUT, DELETE*

**/contests/{id}/snowboard** - *POST, DELETE*
