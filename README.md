# Spring boot demo with GraphQL and JWT auth
## Implementation details
### Tech stack
Implemented using:
- JDK 11
- Spring Boot 2.2.6
- Spring Data JPA
- Spring Security
- GraphQL-boot-starter 7.0.1
- H2 database (in-memory)
- JWT

### Approach taken
- JWT authentification
- Role-based authorization (only admins can access Users API)
- No REST API at all
- Client can get the token from the GraphQL API itself
- Custom error handling to create descriptive error messages

### How to use
All the GraphQL API is exposed in `/graphql`. Except for the `getToken` request, all the queries and mutations require a valid token. For all the User-related requests, that token must belong to an ADMIN users.

1. Get an access token (initial user is created with `admin` and `admin` as credentials) by performing a POST request to `/graphql` endpoint with the following GraphQL body:
```graphql
mutation {
    getToken(username: "admin", password: "admin")
}
```   
2. Run every request with header: `Authorization: Bearer TOKEN`. You can refer to ![the schema](https://github.com/theam/spring-boot-graphql-demo/blob/master/src/main/resources/graphql/mainschema.graphqls) to check the available queries and mutations.

## License
MIT License

Copyright (c) 2020 The Agile Monkeys

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.