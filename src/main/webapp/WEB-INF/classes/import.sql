
INSERT INTO roles(name, level, description) VALUES('eleitor', 1, 'Permite autenticar e votar');
INSERT INTO roles(name, level, description) VALUES('mesário', 2, 'Permite autenticar, votar e habilitar votação');
INSERT INTO roles(name, level, description) VALUES('chefe de sessão', 3, 'Permite autenticar, votar, habilitar votação, e imprimir relatório');
INSERT INTO roles(name, level, description) VALUES('membro do tre', 4, 'Permite autenticar, votar, habilitar votação, imprimir relatório e cadastrar usuário e candidatos');

INSERT INTO posts(name) VALUES('Presidente');
INSERT INTO posts(name) VALUES('Governador');
INSERT INTO posts(name) VALUES('Senador');
INSERT INTO posts(name) VALUES('Deputado Federal');
INSERT INTO posts(name) VALUES('Deputado Estadual');

INSERT INTO voters(username, password, name, cpf, role_id) VALUES('123456123456', '123456123456', 'Lucy', '12345612345', 1);
INSERT INTO voters(username, password, name, cpf, role_id) VALUES('123456712345', '123456712345', 'Admin', '1234567234', 3);


INSERT INTO candidates(name, number, post_id, party) VALUES('Mito', '12', 1, 'PSL');
INSERT INTO candidates(name, number, post_id, party) VALUES('DepEst1', '12345', 5, 'PSL');
INSERT INTO candidates(name, number, post_id, party) VALUES('DepEst2', '54321', 5, 'PT');
INSERT INTO candidates(name, number, post_id, party) VALUES('DepFed1', '1234', 4, 'PSL');
INSERT INTO candidates(name, number, post_id, party) VALUES('DepFed2', '4321', 4, 'PT');
INSERT INTO candidates(name, number, post_id, party) VALUES('Sen1', '123', 3, 'PSL');
INSERT INTO candidates(name, number, post_id, party) VALUES('Sen2', '432', 3, 'PT');
INSERT INTO candidates(name, number, post_id, party) VALUES('Gov2', '21', 2, 'PT');