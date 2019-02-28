# Projeto Becommerce

O objetivo deste projeto é expor APIs REST para de controlar o estoque de produtos de pesca.

O código fonte deste projeto está disponível em:
	https://github.com/henriqueldcs/becommerce.git
	
## Estrutura do projeto

Este sistema foi projetado com o padrão arquitetural de microserviços. Atualmente é constituído por dois microserviços, 
que para reduzir a complexidade em tempo de desenvolvimento, se encontram no mesmo repositório, porém deveriam estar em respositórios separados.

São eles:
    
    - API Gateway
    - Product Management Service
    - Becommerce commons
    - Api Test
        
#### Api gateway
Serviço responsável por receber todas as requisições de entrada, validar o token enviado, 
junto ao serviço de autenticação de usuários, e direcionar a requisição para o serviço adequado.

Em desenvolvimento foi considerado apenas um token default, que será substituido pelo serviço de autenticação e geração de token em um momento oportuno.

#### Product Management Service  
Serviço responsável por manter o cadastro de produtos.

#### Becommerce commons
Projeto utilizado para empacotar classes comuns a todos os projetos para reutilização de código. Podem ser colocadas classes como TOs, úteis e etc.

####  Api Test
Neste projeto são inseridos testes para validar o retorno à chamadas das APIs criadas.



## Documentação

A seguinte arquitetura foi escolhida para a construção do projeto.

![Diagrama arquitetural](docs/diagrama%20arquitetural.jpg)


Os documentos foram adicionados na raiz do projeto dentro da pasta docs.

As APIs foram desenhadas utilizando o [Swagger Editor](http://editor.swagger.io/) e o arquivo de definição,
 [swagger file](docs/swagger.yaml), foi adicionado ao projeto na pasta docs.
 
 Para visualizá-lo façao download do [swagger file](docs/swagger.yaml), acesse o [Swagger Editor](http://editor.swagger.io/) 
 e importe-o em: **File > Import file** 


## Requisitos

Os itens abaixo são necessários para a execução.

	- gradlew 4
	- java 10
	- Lombok IntelliJ Plugin

## Perfis

Foram criados os perfis abaixo, para execução em ambiente de desenvolvimento, validação e produção.

    - dev
    - qa
    - prod

## Como executar

Para rodar o projeto basta executar o comando, no terminal, no diretório do do projeto. Três perfis, citados acima, foram 
disponibilizados para a execução do projeto, dev, qa e prod. 

Executar todos os projetos:

    ./gradlew bootRun --parallel -Dspring.profiles.active=dev

Executar um projeto específico:

    ./gradlew api-gatewy:bootRun -Dspring.profiles.active=dev
    
Projetos executaveis:
- api-gateway
- product-management-service   
- swagger-api 
    

## Tecnologias utilizadas
    
    - Java 10
    - Spring boot
    - H2
    - Gradle
    - Git
    - Lombok

    
## Suporte

Para mais informações sobre o projeto entre em contato com:
    
    henriqueldcs@gmail.com
    