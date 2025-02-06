
## Estrutura do Projeto
Tecnologias Utilizadas:
- Maven: Gerenciador de dependências.
- Lombok: Para reduzir boilerplate (getters, setters, etc.).
- Spring Boot: Framework para criar a API REST.
- Spring Data JPA: Para persistência de dados.
- Banco H2: Banco de dados em memória, ideal para desenvolvimento e testes.
- Heroku: Plataforma para publicação.
Padrão de Projeto:
- Usaremos MVC (Model-View-Controller) para organizar as camadas do sistema.

### Diagrama de ER
Segue o diagrama de ER do esquema do banco de dados gerado em Mermaid para documentação:



```mermaid
erDiagram
    usuario {
        INT id PK "Chave primária"
        VARCHAR nome "Nome do usuário"
        VARCHAR email "E-mail único"
        VARCHAR senha "Senha criptografada"
        VARCHAR telefone "Telefone do usuário"
        VARCHAR cep "CEP do endereço"
        VARCHAR numeroEndereco "Número do endereço"
        VARCHAR complementoEndereco "Complemento do endereço"
    }

    cliente {
        INT id_usuario PK, FK "Referência para tabela usuario"
        VARCHAR cpf "CPF único do cliente"
    }

    profissional {
        INT id_usuario PK, FK "Referência para tabela usuario"
        VARCHAR especialidade "Especialidade do profissional"
        VARCHAR registroProfissional "Registro profissional único"
    }

    servico {
        INT id PK "Chave primária"
        VARCHAR nome "Nome do serviço"
        TEXT descricao "Descrição do serviço"
        DECIMAL preco "Preço do serviço"
        INT duracao "Duração em minutos"
        ENUM situacao "Situação: ATIVO, INATIVO, BLOQUEADO"
        INT profissional_id FK "Referência para tabela profissional"
    }

    imagem {
        INT id PK "Chave primária"
        INT usuario_id FK "Referência para tabela usuario"
        LONGBLOB imagem "Imagem armazenada como binário"
        VARCHAR tipo "MIME type da imagem (ex: image/png)"
    }

    %% Relacionamentos
    usuario ||--o| cliente : "1 para 1"
    usuario ||--o| profissional : "1 para 1"
    profissional ||--o{ servico : "1 para N"
    usuario ||--o{ imagem : "1 para N"
```

## Explicação do Diagrama
Tabelas principais:
usuario: Base de todas as entidades, representando informações compartilhadas.
cliente e profissional: Estendem usuario com atributos específicos para cada tipo de usuário.
servico: Associado a um profissional, detalha os serviços oferecidos.
imagem: Tabela separada para armazenar imagens relacionadas aos usuários.
Relacionamentos:
usuario é pai de cliente e profissional (herança 1-para-1).
profissional está relacionado a servico (1-para-muitos).
usuario está relacionado a imagem (1-para-muitos), permitindo múltiplas imagens por usuário.
Esse diagrama documenta claramente o modelo de dados e pode ser usado como referência para o desenvolvimento e comunicação do projeto.

### Configurando a porta da aplicação
A aplicação ComfortAid usa a porta 5000 por padrão, pode-se fazer uma das seguintes configurações:

- a) Configuração direta ao executar o .jar

Especifique a porta desejada ao iniciar sua aplicação:
``` bash
java -jar comfortaid-0.0.1-SNAPSHOT.jar --server.port=80
```
- b) Configuração no arquivo `application.properties`

No arquivo `src/main/resources/application.properties`, configure a porta:
``` properties
server.port=80
```
Após isso, gere novamente o arquivo .jar com:
``` bsah
mvn clean package
```

>   No Linux ou macOS, você precisa de privilégios de superusuário para que uma aplicação acesse portas abaixo de 1024, como a 80. No terminal, você deve usar o comando sudo ao iniciar sua aplicação:
> ``` bash
> sudo java -jar comfortaid-0.0.1-SNAPSHOT.jar --server.port=80
>``` 

### Teste com Postman
Você pode testar os seguintes endpoints no Postman:

GET /api/servicos - Listar todos os serviços.
GET /api/servicos/{id} - Buscar um serviço por ID.
POST /api/servicos - Criar um novo serviço.
``` json
{
    "nome": "Massagem Boa",
    "descricao": "Uma massagem relaxante de 60 minutos.",
    "preco": 100.0,
    "duracao": "60",
    "profissional": {
        "id": 1
    }
}
```

PUT /api/servicos/{id} - Atualizar um serviço existente.
DELETE /api/servicos/{id} - Excluir um serviço.



POST http://localhost:8080/api/profissionais
``` json
{
        "nome": "Emy Eiras",
        "email": "Emy@gmail.com",
        "senha": "senha444",
        "telefone": "(48) 98888-4444",
        "cep": "88020-444",
        "numeroEndereco": "444",
        "complementoEndereco": "Sala",
        "especialidade": "Fisioterapeuta",
        "registroProfissional": "REG44444"
    }
```
POST http://localhost:8080/api/clientes
``` json
{
        "nome": "Dani Damares",
        "email": "dani@gmail.com",
        "senha": "senha333",
        "telefone": "(48) 98888-3333",
        "cep": "88020-333",
        "numeroEndereco": "333",
        "complementoEndereco": "Casa",
        "cpf": "98765432333"
    }
```
POST http://localhost:8080/api/agendamentos
``` json
{
    "cliente": {
        "id": 1
    },
    "servico": {
        "id": 1
    },
    "dataHora": "2024-01-12T10:00:00"
}
```

### Enviar uma imagem para um cliente
Você pode usar o curl para enviar uma imagem para o endpoint /api/imagens/cliente/{id}:
''' bash

curl -X POST "http://localhost:8080/api/imagens/cliente/1" -H "Content-Type: multipart/form-data" -F "foto=@/path/to/your/image.jpg"
'''
### Enviar uma imagem para um profissional
Da mesma forma, você pode enviar uma imagem para o endpoint /api/imagens/profissional/{id}:
''' bash

curl -X POST "http://localhost:8080/api/imagens/profissional/1" -H "Content-Type: multipart/form-data" -F "foto=@/path/to/your/image.jpg"
'''

### Recuperar uma imagem de um cliente
Para recuperar a imagem de um cliente, você pode fazer uma requisição GET para o endpoint /api/imagens/cliente/{id}:
''' bash

curl -X GET "http://localhost:8080/api/imagens/cliente/1" --output cliente_foto.jpg
'''
### Recuperar uma imagem de um profissional
Para recuperar a imagem de um profissional, você pode fazer uma requisição GET para o endpoint /api/imagens/profissional/{id}:
''' bash

curl -X GET "http://localhost:8080/api/imagens/profissional/1" --output profissional_foto.jpg
'''
> Esses exemplos mostram como você pode interagir com os endpoints para enviar e recuperar imagens usando curl. Certifique-se de substituir /path/to/your/image.jpg pelo caminho real da imagem que você deseja enviar.

> Também é possível usar o [postman](https://cursos.alura.com.br/forum/topico-envio-de-foto-via-postman-197419#:~:text=Com%20a%20API%20em%20execução,a%20requisição%20do%20tipo%20POST.)