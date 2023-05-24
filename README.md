
# API para Wishlist

A ideia dessa API REST é possuir um serviço HTTP resolvendo a funcionalidade de Wishlist (lista de desejos) do cliente. Esse serviço deve atender os seguintes requisitos básicos que uma lista de desejos possui.

## Autores

- [@rogerpsantos](https://github.com/rogerpsantos)


## Documentação da API

#### Junto ao projeto, toda a documentação da API está disponibilizada usando OpenAPI (Swagger), ao executar o projeto, basta acessar o link para ter acesso:
- OpenAPI (Swagger): http://localhost:8080/swagger-ui/index.html

## Stack utilizada


**Back-end:** Java 17

**Framework:** Spring Boot

**Arquitetura de Serviços:** REST API

**Gerenciador de Depenência:** Maven

**DataBase:** MongoDB

**IDE:** IntelliJ

**Testes:** JUnit 5


## DataBase Configuration

Edite o arquivo [aplication.properties](https://github.com/rogerpsantos/wishlistapi/blob/main/src/main/resources/application.properties)

Altere o nome da base de dados, usuário e senha para os de sua preferência:

Exemplo:
- `spring.data.mongodb.uri=`mongodb://localhost/bdteste
- `spring.data.mongodb.username=`root
- `spring.data.mongodb.password`=123456

