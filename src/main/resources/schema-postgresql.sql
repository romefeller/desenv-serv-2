CREATE TABLE IF NOT EXISTS aluno(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    cpf  VARCHAR(11)  NOT NULL 
);

CREATE TABLE IF NOT EXISTS disciplina(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    sigla  VARCHAR(7)  NOT NULL
);

ALTER TABLE disciplina ADD COLUMN IF NOT EXISTS professor_id UUID;

CREATE TABLE IF NOT EXISTS matricula (
    aluno_id UUID REFERENCES aluno(id) ON DELETE CASCADE,
    disciplina_id UUID REFERENCES disciplina(id) ON DELETE CASCADE,
    data_matricula DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (aluno_id, disciplina_id)
);

ALTER TABLE aluno ADD COLUMN IF NOT EXISTS password VARCHAR(100);
 
CREATE TABLE IF NOT EXISTS perfil (
 alunoid UUID NOT NULL,
 cargo VARCHAR(50) NOT NULL,
 CONSTRAINT fk_authorities_users
     FOREIGN KEY(alunoid) REFERENCES aluno(id)
);

ALTER TABLE perfil
ADD COLUMN IF NOT EXISTS id serial PRIMARY KEY;

ALTER TABLE perfil DROP CONSTRAINT IF EXISTS perfil_unique;

ALTER TABLE perfil
ADD CONSTRAINT perfil_unique UNIQUE (alunoid);
