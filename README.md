# MAIL SERVICE API

Esta é um Microserviço desenvolvido em Spring Boot com Java que tem como objetivo receber requisições de envio de emails, gravar as informações no banco de dados PostgreSQL e processar esses emails de forma assíncrona em um serviço em segundo plano.

A API é construída utilizando as seguintes tecnologias e conceitos:

- **Spring Boot**: Framework que facilita a criação de aplicativos Java com configurações mínimas.
- **Java**: Linguagem de programação utilizada para desenvolver a aplicação.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar as informações dos emails.
- **JPA (Java Persistence API)**: Especificação de gerenciamento de persistência de dados para mapeamento objeto-relacional no Java.
- **Spring Data JPA**: Facilita o acesso e a manipulação do banco de dados usando JPA.
- **Asynchronous Processing**: A API utiliza agendamento de tarefas para processar os emails de forma assíncrona, melhorando a eficiência e a escalabilidade.

## Endpoints

### 1. Enviar Email

**Endpoint:** `POST /mail`

Este endpoint permite enviar um novo email. Os dados do email (destinatário, assunto, corpo) são passados no corpo da requisição no formato JSON.

Exemplo de corpo de requisição:

```json
{
  "sender": "remetente@exemplo.com.br",
  "receiver": "destinatario@exemplo.com.br",
  "subject": "Assunto",
  "text": "Corpo do email"
}
```

### 2. Listar Emails por Data

**Endpoint:** `GET /mail?date=2023-08-08`

Este endpoint retorna a lista de todos os emails registrados no banco de dados pela 
data de registro, e seus respectivos status.

Exemplo de retorno:

```json
[
  {
    "sender": "teste@teste.com.br",
    "receiver": "teste_b@teste.com.br",
    "subject": "Teste email",
    "text": "hellloooooo",
    "createDate": "2023-08-09",
    "sendingDate": "2023-08-09",
    "sendingTime": "11:06:06.72",
    "status": "SENT",
    "id": 1
  },
  {
    "sender": "teste_b@teste.com.br",
    "receiver": "teste@teste.com.br",
    "subject": "Ohhhhhhhh",
    "text": "whoppss",
    "createDate": "2023-08-09",
    "sendingDate": "2023-08-09",
    "sendingTime": "11:06:51.136",
    "status": "SENT",
    "id": 2
  }
]
```


### Estrutura do Projeto

A estrutura do projeto segue um padrão organizacional que separa as diferentes responsabilidades em pacotes distintos:

- `br.com.hegom.mail`: Pacote principal que contém a classe de inicialização da aplicação.
- `br.com.hegom.mail.controllers`: Pacote que contém os controladores REST que definem os endpoints da API.
- `br.com.hegom.mail.models`: Pacote que contém as classes de modelo que representam os emails no sistema.
- `br.com.hegom.mail.payload`: Pacote que contém os DTO's da aplicação.
- `br.com.hegom.mail.repository`: Pacote que contém as interfaces de repositório JPA para acessar o banco de dados.
- `br.com.hegom.mail.service`: Pacote que contém os serviços para processamento assíncrono dos emails.

## Configuração do Banco de Dados

A aplicação utiliza um banco de dados PostgreSQL. As configurações de conexão com o banco de dados podem ser ajustadas no arquivo `application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/email_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## Configuração da Conta de Email utilizada

Para exemplo está detalhada abaixo uma configuração de conta utilizando gmail. As configurações podem e devem ser ajustadas no arquivo `application.properties`.
Para utilização do Gmail é necessário configuração de senha de 16 digitos, link abaixo:

https://support.google.com/accounts/answer/185833

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=???????@gmail.com
spring.mail.password=****************
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Como Executar

Para executar a aplicação, você pode utilizar o seguinte comando:

```bash
./mvnw spring-boot:run
```

Certifique-se de que possui o Java e o Maven instalados em sua máquina e ter configurado a conexão com o banco de dados.

## Considerações Finais

Esta API oferece uma solução simples e eficiente para o envio de emails com processamento assíncrono. Sinta-se à vontade para contribuir, reportar problemas ou sugerir melhorias por meio de pull requests e issues. Se tiver alguma dúvida, entre em contato através dos canais de contato listados no perfil do projeto.
