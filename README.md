# gopnik-user-service
This repo is implementation of user registration and user authentication using jwt and java.

1. So far I have implemented user registration using PostgreSQL database.
2. Implementation of email verification link before enabling the user account.

Refer Below Design for overview

<img width="679" alt="Design_diagram" src="https://github.com/aamir-gopnik/gopnik-user-service/assets/149511468/6ff391c5-ee23-4595-91d7-3cbf27e18a8c">



curl --location --request POST 'localhost:8888/api/v1/registration' \
--header 'Content-Type: application/json' \
{ \
"firstName":"userFirstName", \
"lastName": "userSecondName", \
"email": "firstname.lastname@gmail.com", \
"password" : "password123" \
}
