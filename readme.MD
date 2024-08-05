
Solução do desafio backend da [BossaBox](https://bossabox.notion.site/Back-end-0b2c45f1a00e4a849eefe3b1d57f23c6)

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT](https://jwt.io/)
- [OAUTH2](https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html)
- [Swagger](https://swagger.io/)



## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/bossaBoxChallenge.git
```

- Construir o projeto:
```
./mvnw clean package
```


A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

-  USER
```
POST /user - Registrar usuário

{ 
   "username": "newUser",
   "password": "password"
}

```

```
GET /user - Recuperar todos os usuários
```

-  LOGIN
```
POST /login - Realizar login

{ 
   "username": "newUser",
   "password": "password"
}
```

-  TAG
```
POST /tag - Registrar uma tag

{ 
  "name": "newTag"
}
```

```
GET /tag - Recuperar todas as tags
```

- TOOL
```
POST /tools - Registrar uma ferramenta

{
    "title": "json-server",
    "link": "https://github.com/typicode/json-server",
    "description": "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.",
    "tags": [
             "api",
            "json",
            "schema",
            "node",
            "github"
    ]
}
```

```
GET /tools - Obter a lista de ferramentas
```

```
GET /tools?tag=tagname - Obter as ferramentas pela sua tag
```

```
DELETE /tools/{id} - Deletar uma ferramenta pelo ID
```


