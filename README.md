# [foursales-pedido-api]

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300758F.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

Este projeto é uma API construída usando **Java, Spring Boot, MySQL como banco de dados, e JWT para controle de autenticação.**

A API foi desenvolvida para permitir o gerenciamento de pedidos, produtos e usuários, com funcionalidades como cálculo de ticket médio, total gasto por mês e outros.

## Índice

- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [Pontos de API](#pontos-de-api)
- [Autenticação](#autenticação)
- [Banco de Dados](#banco-de-dados)
- [Swagger](#swagger)
- [Contribuição](#contribuição)

---

## Instalação

1. **Clone o repositório:**

```bash
git clone https://github.com/HenriqueVale55/foursales-pedido-api.git
```

2. **Instale as dependências com Maven:**

Certifique-se de ter o Maven instalado. Execute o seguinte comando no diretório raiz do projeto:

```bash
mvn clean install
```

### Opção 1: Instale o MySQL:
Certifique-se de ter o MySQL instalado na sua máquina. Você pode baixá-lo [aqui](https://dev.mysql.com/downloads/mysql/).

#### Opção 2: Usando Docker
Se você prefere usar Docker, execute o seguinte comando para iniciar um container MySQL:

```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=sua-senha -e MYSQL_DATABASE=nome-do-banco -p 3306:3306 -d mysql:latest

### Importe o dump do banco de dados:
No diretório raiz do projeto, você encontrará um arquivo SQL chamado `dump.sql`. Importe-o no MySQL usando o seguinte comando:
```

Substitua [sua-senha] pela senha desejada para o usuário root.
Substitua [nome-do-banco] pelo nome do banco de dados que será criado automaticamente.

Após iniciar o container, você pode conectar-se ao MySQL usando o host localhost, porta 3306, e as credenciais configuradas.

```bash
mysql -u [seu-usuario] -p [nome-do-banco] < dump.sql
```

Substitua [seu-usuario] pelo seu usuário do MySQL, [nome-do-banco] pelo nome do banco que você deseja criar e insira sua senha quando solicitado.

## Crie o banco de dados no MySQL:
Se ainda não criou o banco de dados, execute o seguinte comando no MySQL:

```sql
CREATE DATABASE [nome-do-banco];
```

## Configuração

### Configure o `application.properties`:
Abra o arquivo `src/main/resources/application.properties` e configure as seguintes propriedades:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/[nome-do-banco]
spring.datasource.username=[seu-usuario]
spring.datasource.password=[sua-senha]
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
jwt.secret=chave-secreta-jwt
```

## Uso

### Inicie a aplicação com Maven:
Execute o seguinte comando para iniciar a aplicação:

```bash
mvn spring-boot:run
```

## Swagger

A documentação da API está disponível via Swagger. Para acessá-la:

1. Inicie a aplicação.
2. Abra o navegador e acesse:
http://localhost:8080/swagger-ui.html


A interface do Swagger permitirá que você teste os endpoints diretamente pelo navegador.

## Contribuição

Contribuições são bem-vindas! Se você encontrar problemas ou tiver sugestões para melhorias, abra uma **issue** ou envie um **pull request** para o repositório.

Ao contribuir para este projeto, siga as convenções de código existentes, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), e envie suas alterações em um branch separado.


