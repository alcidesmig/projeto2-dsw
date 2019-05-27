## Repositório do projeto 2 da disciplina DSW1 - 2019.1

## Instruções

- Clonar o projeto
- Rodar utilizando o apache tomcat
- Rodar o script para criação do usuário root em /br/ufscar/dc/dsw/sql/criarUsuario.sql

## Especificações do sistema

- Sistema para criação de promoções em sites de venda de ingressos
- O sistema deve possuir um cadastro de sites de venda de ingressos, com os
seguintes dados: e-mail, senha, endereço/URL, nome e telefone.
- O sistema deve possuir um cadastro de salas de teatro, com os seguintes dados:
e-mail, senha, CNPJ, nome e cidade.
- O sistema deve possuir um cadastro de promoções, com os seguintes dados:
endereço/URL do site de venda de ingressos, CNPJ do teatro, nome da peça,
preço e dia/horário.

## Requisitos
1. Operações CRUD 1 de sites de venda de ingressos (requer login de
administrador)
2. Operações CRUD de teatros (requer login de administrador)
3. Listagem de todos os teatros em uma única página (não requer login)
4. Listagem de todos os teatros por cidade (não requer login)
5. Criação de uma promoção de um teatro (requer login do teatro: via e-
mail + senha). Depois de fazer login, o teatro pode cadastrar uma
promoção. Para isso, deve escolher o site de venda de ingressos
(digitando seu endereço/URL ou escolhendo a partir de uma lista), o nome
da peça, o preço, e o dia/horário da sessão, e deve ser gravada a
promoção na base de dados.
6. Listagem de todas as promoções de um teatro (não requer login).
7. Listagem de todas as promoções de um site de venda de ingressos
(requer login do site: via e-mail + senha).
8. O sistema não deve permitir o cadastro de promoções de um mesmo
teatro ou de um mesmo site de venda de ingressos em um mesmo
dia/horário.
9. O sistema deve ser internacionalizado em pelo menos dois idiomas:
português + outro de sua escolha.
10. O sistema deve tratar todos os erros possíveis (cadastros duplicados, problemas
técnicos, etc) mostrando uma página de erros amigável ao usuário e registrando
o erro no console.
11. O sistema deve proporcionar máscaras (telefone,
CNPJ, e-mail, data, etc) para entrada dos dados.

## Desenvolvedores
Alcides Mignoso e Silva; Gabriel Olivato; Igor Magollo.
