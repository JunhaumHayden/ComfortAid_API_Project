-- Inserir dados na tabela Usuario
INSERT INTO usuario (nome, email, senha, telefone, cep, numero_endereco, complemento_endereco, tipo)
VALUES
    ('Ana Amalia', 'ana@mail.com', 'senha', '(11) 99999-0001', '88010-000', '001', 'Apto 101', 'cliente'),
    ('Bia Bernardes', 'Bia@mail.com', 'senha', '(21) 98888-0002', '88034-000', '002', 'Casa', 'cliente'),
    ('Dany Damaris', 'dany@mail.com', 'senha', '(31) 97777-0003', '88056-000', '003', 'Sala 3', 'profissional'),
    ('Emy Esteves', 'emy@mail.com', 'senha', '(41) 96666-0004', '88075-000', '004', 'casa', 'profissional');

-- Inserir dados na tabela Cliente
INSERT INTO cliente (id_usuario, cpf, nascimento)
VALUES
    (1, '11111111111', '1955-01-12'),
    (2, '22222222222', '2000-05-05');

-- Inserir dados na tabela Profissional
INSERT INTO profissional (id_usuario, especialidade, registro_profissional)
VALUES
    (3, 'Massoterapia', 'REG33333'),
    (4, 'Parteira', 'REG44444');

-- Inserir dados na tabela Servico
INSERT INTO servico (nome, descricao, preco, duracao, situacao, profissional_id, data_disponivel)
VALUES
    ('Massagem Relaxante', 'Sessão de massagem para aliviar o estresse e tensões', 120.00, 60, 'ATIVO', 3, '2025-01-12'),
    ('Massagem Terapêutica', 'Sessão terapêutica para tratar dores específicas', 150.00, 90, 'INATIVO', 3, '2024-01-12'),
    ('Parto Humanizado', 'Acompanhamento do parto com respeito e cuidado', 2000.00, 180, 'ATIVO', 4, '2024-02-22'),
    ('Acolhimento Primeiros Dias', 'Acompanhamento dos primeiros dias em sua jornada nesse maravilhoso universo da maternidade.', 300.00, 180, 'ATIVO', 4, '2024-02-22'),
    ('Massagem Relaxante', 'Sessão de massagem para aliviar o estresse e tensões', 120.00, 60, 'ATIVO', 3,'2025-01-12');

-- Inserir dados na tabela Agendamento
INSERT INTO agendamento (cliente_id, servico_id, data_hora, status)
VALUES
    (1, 1, '2024-01-12 10:00:00', 'CONFIRMADO'),
    (2, 2, '2024-01-13 14:00:00', 'CONFIRMADO'),
    (1, 3, '2024-01-14 09:00:00', 'CONFIRMADO'),
    (1, 4, '2024-01-14 09:00:00', 'PENDENTE'),
    (2, 1, '2024-01-15 11:00:00', 'CANCELADO');

-- Inserir dados na tabela Avaliação
INSERT INTO avaliacao (cliente_id, servico_id, data_hora, comentario, nota)
VALUES
    (1, 1, '2024-01-12 10:00:00', 'Ótimo serviço, muito profissional!', 5),
    (2, 2, '2024-01-13 14:00:00', 'Bom atendimento, mas atrasou um pouco.', 4),
    (1, 3, '2024-01-14 09:00:00','Atendimento excelente, recomendo!', 5 ),
    (2, 1, '2024-01-15 11:00:00', 'Não gostei muito, esperava algo melhor.', 2);

INSERT INTO login (id_usuario, login, senha)
VALUES
    (1,'ana@mail.com', 'senha1111'),
    (2,'Bia@mail.com', 'senha2222'),
    (3,'dany@mail.com', 'senha3333'),
    (4,'emy@mail.com', 'senha4444');



