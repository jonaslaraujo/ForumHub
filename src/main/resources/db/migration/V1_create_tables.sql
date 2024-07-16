-- Tabela Perfil
CREATE TABLE Perfil (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(100),
    PRIMARY KEY (id)
);

-- Tabela Curso
CREATE TABLE Curso (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(100),
    categoria VARCHAR(100),
    PRIMARY KEY (id)
);

-- Tabela Usuário
CREATE TABLE Usuario (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(50),
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (perfil_id) REFERENCES Perfil(id)
);

-- Tabela Tópico
CREATE TABLE Topico (
    id BIGSERIAL NOT NULL,
    titulo VARCHAR(100),
    mensagem TEXT,
    dataCriacao TIMESTAMP,
    status VARCHAR(50),
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (autor) REFERENCES Usuario(id),
    FOREIGN KEY (curso) REFERENCES Curso(id)
);

-- Tabela Resposta
CREATE TABLE Resposta (
    id BIGSERIAL NOT NULL,
    mensagem TEXT,
    topico BIGINT NOT NULL,
    dataCriacao TIMESTAMP,
    autor BIGINT NOT NULL,
    solucao BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (topico) REFERENCES Topico(id),
    FOREIGN KEY (autor) REFERENCES Usuario(id)
);
