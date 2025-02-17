-- Inserir dados na tabela Usuario
INSERT INTO cliente (nome, email, senha, telefone, cep, numero_endereco, complemento_endereco, cpf)
VALUES
    ('Ana Amalia', 'ana@mail.com', 'senha1111', '(11) 99999-0001', '88111-111', '001', 'Apto 101', '11111111111'),
    ('Bia Bernardes', 'Bia@mail.com', 'senha2222', '(21) 98888-0002', '88222-222', '002', 'Casa', '22222222222');


-- Inserir dados na tabela Profissional
INSERT INTO profissional (nome, email, senha, telefone, cep, numero_endereco, complemento_endereco, especialidade, registro_profissional)
VALUES
    ('Dany Damaris', 'dany@mail.com', 'senha3333', '(31) 97777-0003', '88333-333', '003', 'Sala 3', 'Massoterapia', 'REG33333'),
    ('Emy Esteves', 'emy@mail.com', 'senha4444', '(41) 96666-0004', '88333-444', '004', 'casa', 'Parteira', 'REG44444');;

-- Inserir dados na tabela Servico
INSERT INTO servico (nome, descricao, preco, duracao, situacao, profissional_id, data_disponivel)
VALUES
    ('Massagem Relaxante', 'Sessão de massagem para aliviar o estresse e tensões', 120.00, 60, 'ATIVO', 1, '2025-01-12'),
    ('Massagem Terapêutica', 'Sessão terapêutica para tratar dores específicas', 150.00, 90, 'INATIVO', 1, '2024-01-12'),
    ('Parto Humanizado', 'Acompanhamento do parto com respeito e cuidado', 2000.00, 180, 'ATIVO', 2, '2024-02-22'),
    ('Massagem Relaxante', 'Sessão de massagem para aliviar o estresse e tensões', 120.00, 60, 'ATIVO', 1,'2025-01-12');

-- Inserir dados na tabela Agendamento
INSERT INTO agendamento (cliente_id, servico_id, data_hora)
VALUES
    (1, 1, '2024-01-12 10:00:00'),
    (2, 2, '2024-01-13 14:00:00'),
    (1, 3, '2024-01-14 09:00:00'),
    (2, 1, '2024-01-15 11:00:00');

INSERT INTO usuario (login, senha)
VALUES
    ('ana@mail.com', 'senha1111'),
    ('Bia@mail.com', 'senha2222'),
    ('dany@mail.com', 'senha3333'),
    ('emy@mail.com', 'senha4444');



