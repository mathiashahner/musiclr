# musiclr

Para testar esta api:

- crie uma base de dados em "localhost:5432" chamada de "musiclr":

  - username: musiclr
  - password: musiclr

- execute os scripts:

  - ./data/schema.sql
  - ./data/insert.sql

- importe os arquivos no postman:

  - musiclr.postman_collection.json
  - workspace.postman_globals.json

- use o endpoit "criar usuário" da pasta "usuário"
- use o endpoit "login" da pasta "autenticacao"

- Observações:
  - as imagens precisam ser envidas em formato base64
  - senhas dos inserts: 123

Pronto, já é possível executar os próximos comandos
