<div align="center">
<img src="https://hermes.dio.me/tracks/a039b34c-7aa8-4a3d-b765-07c8c837f67a.png" alt="Logo BackEnd" width="80">
</div>


<h1 align="center"> ComfortAid Project </h1>



![Static Badge](https://img.shields.io/badge/Powered_by-Maven-red)
![Static Badge](https://img.shields.io/badge/Powered_by-Lombok-blue)
![Static Badge](https://img.shields.io/badge/Powered_by-SpringBoot-green)
![Static Badge](https://img.shields.io/badge/Powered_by-SpringDataJPA-orange)
![Static Badge](https://img.shields.io/badge/Powered_by-BancoH2-yellow)

## Estrutura do Projeto
### Tecnologias Utilizadas:
- Maven: Gerenciador de dependências.
- Lombok: Para reduzir boilerplate (getters, setters, etc.).
- Spring Boot: Framework para criar a API REST.
- Spring Data JPA: Para persistência de dados.
- Banco H2: Banco de dados em memória, ideal para desenvolvimento e testes.
- Heroku: Plataforma para publicação.
Padrão de Projeto:
- Usaremos MVC (Model-View-Controller) para organizar as camadas do sistema.


### Comportamento Esperado do Projeto

O projeto ComfortAid é uma aplicação de agendamento de serviços que permite a interação entre clientes e profissionais. Abaixo estão os comportamentos esperados do sistema:

1. **Cadastro de Usuários**:
    - Clientes e profissionais podem se cadastrar fornecendo informações pessoais e de contato.
    - Cada usuário deve ter um login único e uma senha segura.

2. **Gerenciamento de Serviços**:
    - Profissionais podem criar, atualizar e excluir serviços que oferecem.
    - Serviços devem incluir detalhes como nome, descrição, preço, duração e disponibilidade.

3. **Agendamento de Serviços**:
    - Clientes podem visualizar a lista de serviços disponíveis e agendar um serviço.
    - O sistema deve permitir a seleção de data e hora para o agendamento.
    - Agendamentos podem ser confirmados, cancelados ou marcados como atendidos.

4. **Avaliação de Serviços**:
    - Após a conclusão de um serviço, clientes podem avaliar o serviço prestado.
    - Avaliações devem incluir uma nota e um comentário.

5. **Gerenciamento de Imagens**:
    - Usuários e serviços podem ter imagens associadas a eles.
    - Imagens podem ser enviadas e recuperadas através de endpoints específicos.

6. **Autenticação e Autorização**:
    - O sistema deve garantir que apenas usuários autenticados possam acessar funcionalidades protegidas.
    - Diferentes tipos de usuários (clientes e profissionais) devem ter permissões adequadas às suas funções.

7. **Persistência de Dados**:
    - Dados devem ser armazenados de forma persistente utilizando o banco de dados H2 durante o desenvolvimento e testes.
    - Para produção, o sistema deve ser configurado para utilizar um banco de dados adequado.

8. **Desempenho e Escalabilidade**:
    - A aplicação deve ser capaz de lidar com múltiplas requisições simultâneas.
    - Deve ser possível escalar a aplicação horizontalmente para suportar um número crescente de usuários.

Esses comportamentos garantem que o sistema funcione de maneira eficiente e atenda às necessidades dos usuários finais.


### Diagrama de ER
Diagrama de ER do esquema do banco de dados gerado em Mermaid:


```mermaid
erDiagram
    usuario {
        INT id PK "Chave primária"
        VARCHAR nome "Nome do usuário"
        VARCHAR email "E-mail único"
        VARCHAR senha "Senha criptografada"
        VARCHAR telefone "Telefone do usuário"
        VARCHAR cep "CEP do endereço"
        VARCHAR numero_endereco "Número do endereço"
        VARCHAR complemento_endereco "Complemento do endereço"
        VARCHAR tipo "Tipo de usuário"
    }

    cliente {
        INT id_usuario PK, FK "Referência para tabela usuario"
        VARCHAR cpf "CPF único do cliente"
        TIMESTAMP nascimento "Data de nascimento"
    }

    profissional {
        INT id_usuario PK, FK "Referência para tabela usuario"
        VARCHAR especialidade "Especialidade do profissional"
        VARCHAR registro_profissional "Registro profissional único"
    }

    servico {
        INT id PK "Chave primária"
        VARCHAR nome "Nome do serviço"
        TEXT descricao "Descrição do serviço"
        DECIMAL preco "Preço do serviço"
        INT duracao "Duração em minutos"
        DATE data_disponivel "Data disponível"
        ENUM situacao "Situação: ATIVO, INATIVO, BLOQUEADO"
        INT profissional_id FK "Referência para tabela profissional"
    }

    agendamento {
        INT id PK "Chave primária"
        INT cliente_id FK "Referência para tabela cliente"
        INT servico_id FK "Referência para tabela servico"
        TIMESTAMP data_hora "Data e hora do agendamento"
        ENUM status "Status: CONFIRMADO, PENDENTE, CANCELADO, ATENDIDO"
    }

    avaliacao {
        INT id PK "Chave primária"
        INT cliente_id FK "Referência para tabela cliente"
        INT servico_id FK "Referência para tabela servico"
        TIMESTAMP data_hora "Data e hora da avaliação"
        TEXT comentario "Comentário do cliente"
        INT nota "Nota da avaliação"
    }

    foto_usuario {
        INT id PK "Chave primária"
        INT usuario_id FK "Referência para tabela usuario"
        BLOB foto "Foto armazenada como binário"
    }

    foto_servico {
        INT id PK "Chave primária"
        INT servico_id FK "Referência para tabela servico"
        BLOB foto "Foto armazenada como binário"
    }

    login {
        INT id_usuario PK, FK "Referência para tabela usuario"
        VARCHAR login "Login do usuário"
        VARCHAR senha "Senha do login"
    }

    %% Relacionamentos
    usuario ||--o| cliente : "1 para 1"
    usuario ||--o| profissional : "1 para 1"
    profissional ||--o{ servico : "1 para N"
    cliente ||--o{ agendamento : "1 para N"
    servico ||--o{ agendamento : "1 para N"
    cliente ||--o{ avaliacao : "1 para N"
    servico ||--o{ avaliacao : "1 para N"
    usuario ||--o{ foto_usuario : "1 para N"
    servico ||--o{ foto_servico : "1 para N"
    usuario ||--o| login : "1 para 1"
```
### Diagrama de Classes

Diagrama de classes do projeto gerado em Mermaid:

```mermaid
classDiagram
    class Usuario {
        +int id
        +String nome
        +String email
        +String senha
        +String telefone
        +String cep
        +String numeroEndereco
        +String complementoEndereco
        +String tipo
    }

    class Cliente {
        +int id_usuario
        +String cpf
        +Date nascimento
    }

    class Profissional {
        +int id_usuario
        +String especialidade
        +String registroProfissional
    }

    class Servico {
        +int id
        +String nome
        +String descricao
        +float preco
        +int duracao
        +Date dataDisponivel
        +String situacao
        +int profissional_id
    }

    class Agendamento {
        +int id
        +int cliente_id
        +int servico_id
        +Timestamp dataHora
        +String status
    }

    class Avaliacao {
        +int id
        +int cliente_id
        +int servico_id
        +Timestamp dataHora
        +String comentario
        +int nota
    }

    class FotoUsuario {
        +int id
        +int usuario_id
        +byte[] foto
    }

    class FotoServico {
        +int id
        +int servico_id
        +byte[] foto
    }

    class Login {
        +int id_usuario
        +String login
        +String senha
    }

    Usuario <|-- Cliente : "herança"
    Usuario <|-- Profissional : "herança"
    Profissional "1" --> "0..*" Servico : "oferece"
    Cliente "1" --> "0..*" Agendamento : "faz"
    Servico "1" --> "0..*" Agendamento : "tem"
    Cliente "1" --> "0..*" Avaliacao : "dá"
    Servico "1" --> "0..*" Avaliacao : "recebe"
    Usuario "1" --> "0..*" FotoUsuario : "tem"
    Servico "1" --> "0..*" FotoServico : "tem"
    Usuario "1" --> "1" Login : "usa"
```


### Explicação do Diagrama

#### Tabelas principais:
- **usuario**: Base de todas as entidades, representando informações compartilhadas entre clientes e profissionais.
- **cliente** e **profissional**: Estendem a tabela usuario com atributos específicos para cada tipo de usuário.
- **servico**: Associado a um profissional, detalha os serviços oferecidos.
- **agendamento**: Representa os agendamentos feitos pelos clientes para os serviços oferecidos.
- **avaliacao**: Contém as avaliações feitas pelos clientes sobre os serviços.
- **foto_usuario** e **foto_servico**: Tabelas separadas para armazenar imagens relacionadas aos usuários e serviços, respectivamente.
- **login**: Armazena as informações de login dos usuários.

#### Relacionamentos:
- **usuario** é pai de **cliente** e **profissional** (herança 1-para-1).
- **profissional** está relacionado a **servico** (1-para-muitos).
- **cliente** está relacionado a **agendamento** (1-para-muitos).
- **servico** está relacionado a **agendamento** (1-para-muitos).
- **cliente** está relacionado a **avaliacao** (1-para-muitos).
- **servico** está relacionado a **avaliacao** (1-para-muitos).
- **usuario** está relacionado a **foto_usuario** (1-para-muitos), permitindo múltiplas imagens por usuário.
- **servico** está relacionado a **foto_servico** (1-para-muitos), permitindo múltiplas imagens por serviço.
- **usuario** está relacionado a **login** (1-para-1).

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

# Author

| [<img src="https://avatars.githubusercontent.com/u/79289647?v=4" width=115><br><sub>Carlos Hayden</sub>](https://github.com/JunhaumHayden) |
| :---: |

