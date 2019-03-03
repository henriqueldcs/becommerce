# Projeto Becommerce

O objetivo deste projeto é expor APIs REST para controlar o estoque de produtos de pesca.

O código fonte deste projeto está disponível em:
	https://github.com/henriqueldcs/becommerce.git
	
## Estrutura do projeto

Este sistema foi projetado com o padrão arquitetural de microserviços. Atualmente é constituído por seis projetos 
sendo eles quatro microserviços, que para reduzir a complexidade em tempo de desenvolvimento, se encontram no 
mesmo repositório, porém deveriam estar em repositórios separados.

São eles:
    
    - API Gateway
    - Product Management Service
    - Inventory Management Service
    - Swagger API
    - Becommerce commons
    - Api Test
        
### Api gateway
Micro Serviço responsável por receber todas as requisições de entrada, validar o token enviado, 
junto ao serviço de autenticação de usuários, e direcionar a requisição para o serviço adequado.

Em desenvolvimento foi considerado apenas um token default, que deverá ser substituido pelo serviço de autenticação e 
geração de token em um momento oportuno.

O token utilizado para autenticação é **Gdu2vkyfKrzb0OdZuoPP**

### Product Management Service  
Micro Serviço responsável por manter o cadastro de produtos permitindo consultar, cadastrar, alterar e excluir.

### Inventory Management Service  
Micro Serviço responsável por manter o inventário de produto, podendo consultar, adicionar, incrementar e decrementar a 
quantidade de um produto em estoque.

### Swagger API
Micro Serviço que disponibiliza Swagger para executar as ações no sistema até que uma tela seja disponibilizada. Todas 
as requisições são realizadas para a API Gateway. 


### Becommerce commons
Projeto utilizado para armazenar classes comuns a todos os projetos para reutilização de código. 
Podem ser colocadas classes como TOs, outros serviços úteis e etc.

###  Api Test
Neste projeto foram inseridos testes de integração para validar o retorno à chamadas das APIs criadas.



## Documentação

A seguinte arquitetura foi escolhida para a construção do projeto.

![Diagrama arquitetural](docs/diagrama%20arquitetural.jpg)


Os documentos foram adicionados na raiz do projeto dentro da pasta docs.

As APIs foram desenhadas utilizando o [Swagger Editor](http://editor.swagger.io/) e o arquivo de definição,
 [swagger file](docs/swagger.yaml), foi adicionado ao projeto na pasta docs.
 
 Para visualizá-lo faça o download do [swagger file](docs/swagger.yaml), acesse o [Swagger Editor](http://editor.swagger.io/) 
 e importe-o em: **File > Import file** 


## Requisitos

Os itens abaixo são necessários para a execução do projeto.

	- Java 10+
	- Gradlew 5


## Como executar

Para rodar o projeto basta executar o comando, no terminal, no diretório do projeto. Três perfis, citados abaixo, foram 
disponibilizados para a execução do projeto, dev, qa e prod. 

Executar todos os projetos:

    ./gradlew bootRun --parallel -Dspring.profiles.active=dev

Executar um projeto específico:

    ./gradlew api-gatewy:bootRun -Dspring.profiles.active=dev
    
Projetos executaveis:
- api-gateway
- product-management-service   
- inventory-management-service   
- swagger-api

### Perfis

Foram criados os três perfis abaixo, para execução em ambiente de desenvolvimento, validação e produção respectivamente.

    - dev
    - qa
    - prod


## Teste de integração (test-api)

O projeto test-api contem testes de chamadas às apis implementadas. Para executá-lo é preciso que os projetos das APIs 
já estejam em execução.

Para isso execute o projeto completo:

    ./gradlew bootRun --parallel -Dspring.profiles.active=dev
    
e em seguida, através do eclipse/intellij:

    Abrir a classe ProductResourceTest/InventoryResourceTest e executar os casos de teste.
    
### Melhorias:

Devido à indisponibilidade de tempo os itens abaixo não foram entregues:

- O teste de integração precisa ser executado através das task do gradle, sem que seja necessário que o projeto 
esteja em execução.  
- Criação de teste para contemplar todos cenários de teste.
- Mecanismo para preparar os dados necessários antes da execução dos testes para que os mesmos possam ser executados
individualmente.

    
## Tecnologias utilizadas
    
    - Java 10+
    - Spring boot 2
    - H2
    - Gradle 5
    - Git
    - Lombok

## Javadoc

Foram adicionados javadoc apenas para os serviços, responsáveis pelas regras de negócios dos projetos.

## Testes unitários

Os testes unitários foram adicionados para validar apenas serviços que tratam regras de negócios. Serviços que 
realizam apenas operações de banco de dados não possuem testes unitários.

    
## Suporte

Para mais informações sobre o projeto entre em contato com:
    
    henriqueldcs@gmail.com
    