# DIGIO desafio

Construção de um microserviço em java que consome os jsons mock com dados de produtos, clientes e suas compras.
Contendo endpoints que analisam os dados e retornam informações personalizadas.

# Funcionalidades

- GET /compras - Retorna uma lista de todas as compras realizadas, ordenadas de forma crescente por valor.
- GET /maior-compra/{ano} - Retorna os detalhes da maior compra realizada no ano especificado.
- GET /recomendacao/{cliente}/tipo - Retorna uma recomendação de vinho para o cliente com base nos tipos de vinho que o
  cliente mais compra.
- GET /clientes-fieis - Retorna o top 3 clientes mais fiéis, que são definidos como os clientes com mais compras
  recorrentes e maiores valores.

# Documentação

- http://localhost:8080/swagger-ui/index.html

# Tecnologias

- Java 17
- Spring Boot 3.2.8
- OpenFein (Feign Client)
- Lombok
- Swagger Open API 3.0
- Junit
- Mockito
- Cucumber

# Contato

- Marco Santarelle da Silva Jardim
- santarelle@gmail.com
