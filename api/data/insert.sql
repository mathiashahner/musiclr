insert into usuario (email, senha) values ('nunes@cwi.com.br', '$2a$10$eH54aE7fiO6YWpl3eyABwO/qOxXdzvuR2.xRhO4SnNM1SZDZoXS9e');
insert into usuario (email, senha) values ('zanatta@cwi.com.br', '$2a$10$jHlB7oBdlyafUD2S4.ke2.kA5/JZI9U0dFrwvKkpWFsqLBYRICAD2');
insert into usuario (email, senha) values ('mathias@cwi.com.br', '$2a$10$XnHd1OtWz7A/dWdikVDN/e.bKTGZHNc8vzzIXLhTr2V7hE0QmKnj2');

insert into permissao (nome, usuario_id) values ('USER', 1);
insert into permissao (nome, usuario_id) values ('USER', 2);
insert into permissao (nome, usuario_id) values ('USER', 3);

insert into pessoa (apelido, data_nascimento, nome, usuario_id) values ('boss', '1990-01-01', 'André Nunes', 1);
insert into pessoa (apelido, data_nascimento, nome, usuario_id) values ('panda', '1995-12-31', 'Everton Zanatta', 2);
insert into pessoa (apelido, data_nascimento, nome, usuario_id) values ('jabulane', '2001-05-30', 'Mathias Hahner', 3);

insert into amizade (data_solicitacao, situacao, solicitado_id, solicitante_id) values ('2022-04-04', 'APROVADA', 1, 2);
insert into amizade (data_solicitacao, situacao, solicitado_id, solicitante_id) values ('2022-04-04', 'SOLICITADA', 2, 3);