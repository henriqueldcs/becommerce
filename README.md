# Projeto Becommerce

O objetivo deste projeto é expor APIs REST para de controlar o estoque de produtos de pesca.

O código fonte deste projeto está disponível em:
	https://github.com/henriqueldcs/becommerce.git
	
## Estrutura do projeto ##

Este sistema foi projetado com o padrão arquitetural de microserviços. Atualmente é constituído por dois microserviços, 
que para reduzir a complexidade em tempo de desenvolvimento, se encontram no mesmo repositório, porém deveriam estar em respositórios separados.

São eles:
    
    - API Gateway
    - Product Management Service
        

#### Api gateway ####  
Serviço responsável por receber todas as requisições de entrada, validar o token enviado, 
junto ao serviço de autenticação de usuários, e direcionar a requisição para o serviço adequado.

Em desenvolvimento foi considerado apenas um token default, que será substituido pelo serviço de autenticação e geração de token em um momento oportuno.

#### Product Management Service ####  
Serviço responsável por manter o cadastro de produtos.


## Requisitos

Os itens abaixo são necessários para a execução.

	- gradlew 4
	- java 10

## Perfis

Foram criados os perfis abaixo, para execução em ambiente de desenvolvimento, validação e produção.

    - dev
    - qa
    - prod

## Como executar

Para rodar o projeto basta executar o comando, no terminal, no diretório do do projeto. Três perfis, citados acima, foram 
disponibilizados para a execução do projeto, dev, qa e prod. 

    ./gradlew bootRun --parallel -Dspring.profiles.active=dev
    

## Tecnologias utilizadas
    
    - Java 10
    - Spring boot
    - H2
    - Gradle
    - Git
    
## Suporte

Para mais informações sobre o projeto entre em contato com:
    
    henriqueldcs@gmail.com
    