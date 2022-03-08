# Projeto: Implementação de estratégias de cache local e distribuído com Redis no Spring WebFlux e Node.js

Sou leitor e colecionador de quadrinhos há mais de 8 anos, e também sou desenvolvedor back-end há 5 anos.

Recentemente, criei meu canal no YouTube chamado [Comics & Code](https://www.youtube.com/channel/UCtDl5evCzPavgyFz7EQ80Gg) que trata sobre:

* Quadrinhos/HQs
* Filmes e super-heróis
* Análises, críticas, guias, dicas e recomendações de leituras e materiais
* Programação (Java, Javascript, Python, Node, etc)
* Desenvolvimento de projetos e tutoriais

![Logo Canal](https://github.com/vhnegrisoli/cache-local-distribuido-webflux-node-redis/blob/master/img/Logo%20Nova%20Fundo%20Preto_00000.png)

Este será o segundo projeto do canal, será uma playlist com um total de 9 vídeos.

Link da Playlist do projeto: https://www.youtube.com/playlist?list=PLTnZgkfXPBX5uJSZxIVMPbNxNXfdRu15p

## Objetivos

O objetivo era criar um projeto que implemente as estratégias de cache local e cache distribuído com Redis nas tecnologias Node.js e Spring WebFlux
utilizando a API do ViaCep.

https://viacep.com.br/

## Tecnologias

* Java 11
* Spring WebFlux
* Spring WebClient (Reactive HTTP Client)
* Spring Data Reactive Redis
* Spring Scheduler
* Javascript ES6
* Node.js 14x
* Express.js
* Node-redis
* Node-scheduler

## Arquitetura

A arquitetura desenhada foi a representada na imagem abaixo:

![Arquitetura](https://github.com/vhnegrisoli/cache-local-distribuido-webflux-node-redis/blob/master/img/Arquitetura.png)

Consiste em 2 APIs, uma com Spring WebFlux que irá rodar na porta 8080 e uma em Node.js que rodará na porta 8081.

Cada API terá uma configuração do cache, como o TTL (`time to live` ou `tempo de vida`), se o Redis estará habilitado ou desabilitado 
e o tempo que o agendador irá rodar.

As APIs terão uma classe chamada `CacheServiceWrapper` que atuará como um orquestrador entre os caches local e distribuído, conforme 
configurado na variável `redis-enabled`.

Para o cache local, terá um agendador com Spring Scheduler e no Node com a biblioteca Node-scheduler, que irá rodar a cada 1 minuto
para procurar por chaves expiradas.

## Requisitos

É necessário possuir as seguintes ferramentas:

* Java 11
* Gradle
* Node.js 14x
* Yarn
* Redis

## Links utilizados

* Config do Reactive Redis: https://spring.io/guides/gs/spring-data-reactive-redis/
* Redis Linux: https://redis.io/topics/quickstart
* Redis Windows: https://github.com/microsoftarchive/redis/releases

## Executando

Para rodar o serviço Java, rode o comando:

``gradle bootRun``

Poderá acessar em: http://localhost:8080

Para rodar os serviços Node.js, rode o comando:

``yarn start``

Poderá acessar em: http://localhost:8081 

Para executar o `Redis` no Windows, abra o arquivo:

`redis-server.exe`

Em sequência, para executar o CLI do Redis, abra o arquivo:

`redis-cli.exe`

No Linux, execute:

`redis-server`

Em sequência, para executar o CLI do Redis, rode o comando:

`redis-cli`

## Acessando o endpoint

Existe apenas 1 endpoint em cada API, sendo este:

**/api/v1/cep/{cep}**

Buscando CEPs na API em Java Spring WebFlux:

http://localhost:8080/api/v1/cep/01001001

Buscando CEPs na API em Node.js:

http://localhost:8081/api/v1/cep/01001001

Resposta:

```json
{
  "cep": "01001-001",
  "logradouro": "Praça da Sé",
  "complemento": "lado par",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP"
}
```

## Visualizando os logs

É possível visualizar os logs nas aplicações Java e Node via terminal:

![Logs](https://github.com/vhnegrisoli/cache-local-distribuido-webflux-node-redis/blob/master/img/Logs%20Cache.png)

Ou também, é possível visualizar direto no Redis:

Para acessar o Redis, no Linux, rode o comando:

`redis-cli`

Para salvar manualmente uma chave:

`set NOME_CHAVE VALOR_DESEJADO`

Para visualizar todas as chaves:

`keys *`

Para visualizar apenas uma chave:

`get NOME_CHAVE`

Exemplo:

![Logs Redis](https://github.com/vhnegrisoli/cache-local-distribuido-webflux-node-redis/blob/master/img/Logs%20Redis.png)

Espero que tenham gostado!

### Autor

* **Victor Hugo Negrisoli**
* **Desenvolvedor Back-End Sênior**
