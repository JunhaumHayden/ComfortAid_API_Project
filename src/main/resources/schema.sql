CREATE TABLE usuario(
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(256) NOT NULL,
    telefone VARCHAR(20),
    cep VARCHAR(20),
    complemento_endereco VARCHAR(100),
    numero_endereco VARCHAR(20),
    tipo VARCHAR(12) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY(id)
);

CREATE TABLE cliente(
    id_usuario INT NOT NULL REFERENCES usuario(id),
    cpf VARCHAR(20) NOT NULL,
    nascimento TIMESTAMP,
    CONSTRAINT pk_cliente PRIMARY KEY (id_usuario),
    CONSTRAINT fk_cliente_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
     ON DELETE CASCADE
     ON UPDATE CASCADE
);

CREATE TABLE profissional(
    id_usuario INT NOT NULL REFERENCES usuario(id),
    especialidade VARCHAR(100) NOT NULL,
    registro_profissional VARCHAR(50),
    CONSTRAINT pk_profissional PRIMARY KEY (id_usuario),
    CONSTRAINT fk_profissional_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

-- Tabela Servico
CREATE TABLE IF NOT EXISTS servico (
    id INT AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    duracao INT NOT NULL,
    data_disponivel DATE,
    situacao ENUM('ATIVO', 'INATIVO', 'BLOQUEADO') NOT NULL DEFAULT 'BLOQUEADO',
    profissional_id INT NOT NULL,
    CONSTRAINT pk_servico PRIMARY KEY (id),
    CONSTRAINT fk_servico_profissional FOREIGN KEY (profissional_id) REFERENCES profissional(id_usuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- Tabela Agendamento
CREATE TABLE IF NOT EXISTS agendamento (
    id INT AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    servico_id INT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    status ENUM('CONFIRMADO', 'PENDENTE', 'CANCELADO', 'ATENDIDO') NOT NULL DEFAULT 'PENDENTE',
    CONSTRAINT pk_agendamento PRIMARY KEY (id),
    CONSTRAINT fk_agendamento_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id_usuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT fk_agendamento_servico FOREIGN KEY (servico_id) REFERENCES servico(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

-- Tabela Avaliacao
CREATE TABLE IF NOT EXISTS avaliacao (
    id INT AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    servico_id INT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    comentario TEXT,
    nota int,
    CONSTRAINT pk_avaliacao PRIMARY KEY (id),
    CONSTRAINT fk_avaliacao_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id_usuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT fk_avaliacao_servico FOREIGN KEY (servico_id) REFERENCES servico(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

-- Tabela Foto
CREATE TABLE IF NOT EXISTS foto_usuario (
    id INT AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    foto BLOB NOT NULL,
    CONSTRAINT pk_foto_usuario PRIMARY KEY (id),
    CONSTRAINT fk_foto_usuario_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

-- Tabela Foto
CREATE TABLE IF NOT EXISTS foto_servico (
    id INT AUTO_INCREMENT,
    servico_id INT NOT NULL,
    foto BLOB NOT NULL,
    CONSTRAINT pk_foto_servico PRIMARY KEY (id),
    CONSTRAINT fk_foto_servico_servico FOREIGN KEY (servico_id) REFERENCES servico(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

-- -- Tabela FotoCliente
-- CREATE TABLE IF NOT EXISTS foto_cliente (
--     id INT AUTO_INCREMENT,
--     cliente_id INT NOT NULL,
--     foto BLOB NOT NULL,
--     CONSTRAINT pk_foto_cliente PRIMARY KEY (id),
--     CONSTRAINT fk_foto_cliente_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id_usuario)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE
-- );
--
-- -- Tabela FotoProfissional
-- CREATE TABLE IF NOT EXISTS foto_profissional (
--     id INT AUTO_INCREMENT,
--     profissional_id INT NOT NULL,
--     foto BLOB NOT NULL,
--     CONSTRAINT pk_foto_profissional PRIMARY KEY (id),
--     CONSTRAINT fk_foto_profissional_profissional FOREIGN KEY (profissional_id) REFERENCES profissional(id_usuario)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE
-- );

-- Tabela Login
CREATE TABLE IF NOT EXISTS login (
                                     id_usuario INT NOT NULL REFERENCES usuario(id),
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(256) NOT NULL,
    CONSTRAINT pk_login PRIMARY KEY(id_usuario)
    );

