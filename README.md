# Desafio Maida Lanchonete

# Projeto desenvolvido em Spring Boot
## Java 17 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="40" height="40"/>
## Maven
## Banco de dados PostgreSQL
## Swagger 2.9.2
## Docker

# Para levantar o projeto

Deve ter o Java instalado na maquina, assim como maven

Clone o projeto do repositorio Git https://github.com/krosscaal/maida-challenge-developing-snack-bar

Na sua maquina usando o terminal entre na pasta do projeto criada pelo repositorio dever ser 

parecida com /maida-challenge-developing-snack-bar

execute o comando do docker

sudo docker-compose -f docker-compose-dev.yml up -d

retire sudo se não estiver rodando em alguma distro linux

Assim será instalado o container do postgreSQL v14

Logo e rodar o projeto que o FlyWay irá rodar as migrations instalando as tabelas necesarias no banco

Faço notar que no pacote config especificamente da classe SwaggerConfig
estão comentadas as anotações de configuração devido ao um bug que encontrei ao realiaar uma listagem
de um endpoint listPedidosFinalizadosCliente, nos outros endpoint o swagger funciona bem.

Você pode testar usando o Postman ou Insomnia que é meu caso que todos os endpoint funcionam normalmente inclusive o citado.


