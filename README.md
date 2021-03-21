# Product_catalog_spring_boot
Compasso

 __Ambiente para execução__
  
* __Mysql__ : O projeto está configurado com 2 bancos de dados. Um Mysql no profile de dev e um H2 para o profile de teste, este segundo vazio. Para utilizar o banco de dados com todos dados necessário para as funcionalidades deve configurar um mysql na porta 3306 com o nome product_catalog_compasso como descrito no application.properties, como na imagem a baixo.  
![image](https://user-images.githubusercontent.com/6961441/111923741-5b2baa00-8a7f-11eb-8067-a2b9fa45e6d7.png)
  * O projeto está configura com o flyway migration que faz o tramite necessário no banco mysql para que todas as funcionalidades sejam testadas devidamente.

![image](https://user-images.githubusercontent.com/6961441/111923834-dee59680-8a7f-11eb-8e31-5f029544c266.png)
 
 ## End points
 a API está dividida em 2 versões, a primeira dentro do que foi solicidato no desafio proposto. A segunda implementa conceitos de acesso via token, HATEOAS, e paginação.

#### Host - Localhost:9999

### Documentação da API:

http://localhost:9999/swagger-ui.html#/

* __End Points__
  * V1 - POST: {{host}}/api/v1/products
  * V1 - DELETE: {{host}}/api/v1/products/{ID}
  * V1 - PUT: {{host}}/api/v1/products/{ID}
  * V1 - GET: {{host}}/api/v1/products/{ID}
  * V1 - GET: {{host}}/api/v1/products
  * V1 - GET: {{host}}/api/v1/products/search?min_price=[MIN_VAL]&max_price=[MAX_VAL]&q=[NAME_OR_DESCRIPTION]
  
  * V2 - GET: {{host}}/api/v2/products/{ID}
  * V2 - GET: {{host}}/api/v2/products/?page=0&limit=20&direction=asc
  * V2 - PUT: {{host}}/api/v2/products
  * V2 - POST: {{host}}/api/v2/products
  
  * Auth - POST: {{host}}/auth/signin ( os end points do V2 estão protegidos por token e deve-se se autenticar nesse end-point antes acessá-los, na pasta postman estão o ambiente e a coleção de requests para facilitar a autenticação).


