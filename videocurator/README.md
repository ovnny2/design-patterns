<a id="Inicio"></a>
# Video Curator Application

## Index
- [Getting Started](#EntendendoAAplicacao)
- [Tutorial - Obtendo a Chave de API do Youtube](#ObtendoAChaveDeApiDoYoutube)
- [Tutorial - Fazendo o build com Docker](#FazendoOBuildComDocker)
- [Tutorial - Fazendo deploy localmente](#FazendoDeployLocalmente)
- [Tutorial - Usando a API](#UsandoAApi)
- [Swagger](#Swagger)

## Documentação auxiliar
- [Swagger (projeto rodando)](http://localhost:9999/swagger-ui/indexhtml)
- [Repositório Github](https://github.com/ovnny2/design-patterns)

- [YouTube API Docs](https://developers.google.com/youtube/v3/getting-started)
- [Google Cloud Console](https://console.cloud.google.com/)


<a id="EntendendoAAplicacao"></a>
## Entendendo a aplicação
Video Curator é uma feature de um projeto maior que iniciei meses atrás estudando arquitetura de microsserviços.
Essa feature em particular será somente uma PoC (Prova de Conceito) para experiência no uso de LLM (Large Language Models)
e PNL (Processamento de linguagem Natural) como o [ChatGPT da Microsoft](https://chat.openai.com/auth/login) e o 
[Llama do Facebook](https://github.com/ggerganov/llama.cpp). Além da pesquisa e uso de modelos mais
leves e open source como alguns LLM's listados no repositório público [Huggin Face](https://huggingface.co/).

O projeto visa também os primeiros contatos com bancos de dados vetoriais.

### Definição:
> "Uma aplicação web que faz a curadoria de videos e mídia no Youtube utilizando Inteligência Artificial, Machine Learning
> e Processamento de Linguagem Natural visando auxiliar o usuário na diminuição da curva de aprendizado.<br>
> A aplicação vai ser interativa podendo gerar resumos, nuvens de palavras de determinados videos e até criar questionários
>sobre o assunto do video.

### Considerações:
> A aplicação é uma prova de conceito, não tem a intenção de representar as complexidades de um sistema em produção.<br>
Contudo, sempre seguirá os preceitos e boas práticas do desenvolvimento de software levando em conta os trade offs 
que cada decisão tomada tem e como desenvolver estratégias de gestão (de tempo e de recursos).

### Diagrama



<a id="ObtendoAChaveDeApiDoYoutube"></a>
## Tutorial - Obtendo a chave de API do Youtube

A aplicação tenta seguir os preceitos do [Twelve Factor App](https://12factor.net/) procurando manter-se stateless o máximo
que o contexto dessa atividade permite, sendo assim, você será capaz de rodar essa aplicação em qualquer ambiente de 
desenvolvimento (Linux, Windows ou MAC).<br>
Ágil e Domain Driven Design também serão considerados durante o desenvolvimento.

A única exceção será a chave de API do Youtube, que é informação sensível e nunca deve ser exposta num repositório público
como o Github.

### Obtendo a chave de API
- Vá ao [Google Cloud Console](https://console.cloud.google.com/), faça login na sua conta google e crie um projeto. <br> 
> O cadastro e obtenção da chave de API são gratuitos para uso pessoal. Somente é preciso definir métodos de pagamento 
> para uso comercial


- Ao criar o projeto, você verá uma tela como essa. No meu caso, eu criei um projeto chamado craftzn-youtube.


![img.png](src/main/resources/static/img/criando-novo-projeto.png)


- No Menu esquerdo você vai encontrar o item API e Serviços.


![img.png](src/main/resources/static/img/apis-e-servicos.png)

- Clique na parte de Credenciais. Aqui você vai gerar a sua chave de API para que você possa usá-la nas suas futuras
chamadas HTTP para a API do Youtube.

![img_1.png](src/main/resources/static/img/criando-credenciais.png)

- Clicando na sua chave recém criada, você poderá fazer modificações de autorização, ou seja, criar uma camada de proteção
caso seus dados vazem de algum modo. Aqui você pode definir somente certos IPs de onde as requisições poderão ser 
feitas, por exemplo. É possível limitar o acesso a somente certas APIs para evitar que agentes maliciosos usem nossa chave
privada para atividades ilícitas.

> No nosso caso, iremos usar a [Youtube Data API v3](https://developers.google.com/youtube/v3/getting-started)

![img_2.png](src/main/resources/static/img/restricoes-de-chave.png)

- Agora que adicionamos uma camada de segurança a nossa chave só precisamos copiá-la em algum lugar seguro fora do nosso 
projeto.

<a id="FazendoOBuildComDocker"></a>
## Tutorial - Fazendo o Build com Docker

### Buildando Imagens Docker
Agora que temos a chave de acesso à API do Youtube, a Youtube Data API V3, conseguiremos fazer o build do projeto.<br>
Acompanhando o os princípios do Twelve Factor App, iremos trabalhar com containers. Especificamente com Docker Container.

- Vá para a pasta root do projeto em ``design-patterns``
- Caso tenha o Docker e Docker Compose já instalados, digite no terminal

        docker build -t video-curator ./videocurator/.; \
        docker build -t youtube-mongodb -f ./videocurator/support/mongo/Dockerfile_youtubedb ./videocurator/.       

Caso não tenha... 
- [vá na documentação e instale o Docker](https://docs.docker.com/engine/install/)
- [E o Docker Compose](https://docs.docker.com/compose/install/)

### Deploy com Docker Compose
Em caso do passo anterior for completado com sucesso, você poderá dar o comando ``docker images ls`` e verificar as duas 
imagens do projeto.

        REPOSITORY        TAG       IMAGE ID       CREATED       SIZE
        video-curator     latest    1881d3df4ac8   5 hours ago   361MB
        youtube-mongodb   latest    59203bd63071   3 days ago    669MB


<a id="FazendoDeployLocalmente"></a>
## Tutorial - Rodando a aplicação localmente

### Docker Compose Up
Agora que temos nossas imagens Docker e nossa chave de API, vamos fazer o deploy no ambiente local.<br>
Vá até onde o arquivo docker-compose.yml está e digite no terminal:

        docker-compose up -d --remove-orphans

Pra verificar se seus containeres estão de pé, basta digitar no terminal:

        docker ps
Você deve observar informações como essas abaixo:

        CONTAINER ID   IMAGE             COMMAND                  CREATED         STATUS         PORTS                                           NAME        
        8463524fe025   video-curator     "java -jar /app/app.…"   6 seconds ago   Up 5 seconds   0.0.0.0:9999->9999/tcp, :::9999->9999/tcp       video-curato
        946021b9f5c1   youtube-mongodb   "docker-entrypoint.s…"   6 seconds ago   Up 5 seconds   0.0.0.0:32771->27017/tcp, :::32771->27017/tcp   youtube-mongod


### Observando o estado das nossas aplicações

Por enquanto, só video-curator (Aplicação Spring) e youtube-mongodb (NoSQL Database Movido a Documentos Super estruturados).
Para observar o estado das suas aplicações rodando em conteineres, vá ao terminal e digite:

        docker ps
        docker ps -a
        docker logs <nome-do-container> --follow
        docker-compose logs

Caso você deseje ver os logs da aplicação Spring, digite:

        docker logs videocurator --follow

Você deve ver algo parecido com:
![img.png](src/main/resources/static/img/console-log-spring.png)

<a id="UsandoAApi"></a>
## Usando a API

Se você chegou até aqui, parabéns.
Agora podemos testar a nossa aplicação fazendo uma requisição para o endpoint ``/v1/playlists`` na classe **VideoController**

Vá no terminal caso use Linux e digite o comando Curl abaixo:

    curl --location 'http://localhost:9999/v1/playlists' \
    --header 'Accept: application/json' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email": "seuemail@gmail.com",
        "playlistUrl": "https://www.youtube.com/playlist?list=PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q"
    }'

O link declarado no parâmetro playlistUrl representa uma playlist pública no Youtube. Ela pode ser de qualquer canal 
desde que seus status esteja público, caso contrário, a aplicação vai gerar um erro 400 BAD_REQUEST.

caso corra tudo bem, você vai receber a seguinte resposta 201 CREATED:

### Json Response Created OK

    {
        "playlistId": "PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q",
        "playlist": [
            {
                "id": "gB1F9G0JXOo",
                "channelTitle": "Vinícius Ferreira",
                "videoOwnerChannelTitle": "freeCodeCamp.org",
                "publishedAt": "2021-10-15T17:42:03Z",
                "channelId": "UCRXbk8D_VBfp5EHRqObG0Zw",
                "videoOwnerChannelId": "UC8butISFwT-Wl7EV0hUK0BQ",
                "position": 0,
                "state": "NOT_PROCESSED",
                "tries": 0,
                "title": "Learn Unity - Beginner's Game Development Tutorial",
                "description": "Learn to develop games using the Unity game engine in this complete course for beginners. 
                                This course will get you up and running with Unity. Free game assets included!\n\n✏️ 
                                Course developed by Fahir from Awesome Tuts. 
                                Check out his channel: https://www.youtube.com/channel/UC5c-DuzPdH9iaWYdI0v0uzw\n\n⭐️ 
                                Resources ⭐️\n(To download assets you may have to right click the link, copy it, and 
                                then paste it into a new tab.)\n🔗 
                                Unity Introduction Project Assets: 
                                http://cdn.freecodecamp.org/youtube/unity-course/Unity%20Introduction%20Project%20Assets.zip\n🔗 
                                Unity Introduction Project: http://cdn.freecodecamp.org/youtube/unity-course/Unity%20Introduction%20Project.zip\n🔗 
                                Monster Chase Game Assets: http://cdn.freecodecamp.org/youtube/unity-course/Monster%20Chase%20Assets.zip\n🔗 
                                Complete Monster Chase Game: http://cdn.freecodecamp.org/youtube/unity-course/Monster%20Chase.zip\n\n⭐️ 
                                Course Contents ⭐️\n⌨️ (0:00:00)​ Introduction\n⌨️ (0:00:48)​ Downloading Unity And Unity Hub\n⌨️ (0:05:20)​ 
                                About Unity Versions And Creating A New Project\n⌨️ (0:09:07)​ Introduction To Unity's Interface\n⌨️ (0:22:10)​ 
                                Starting With Unity's Basics\n⌨️ (0:29:47)​ Rigid Bodies And Colliders\n⌨️ (0:38:19)​ 
                                Audio Source And UI Elements\n⌨️ (0:45:47)​ Moving Our Character With Code\n⌨️ (0:54:22)
                                ​ Introduction To Variables\n⌨️ (1:06:14)​ Operations With Variables\n⌨️ (1:19:01)
                                ​ Functions\n⌨️ (1:34:22)​ Conditional Statements\n⌨️ (1:49:29)​ Loops
                                \n⌨️ (1:58:41)​ Coroutines\n⌨️ (2:04:39)​ Classes\n⌨️ (2:16:46)
                                ​ Accessibility Modifiers(Data Encapsulation)\n⌨️ (2:26:54)​ Inheritance\n⌨️ 
                                (2:43:35)​ Getting Components\n⌨️ (2:52:32)​ Monster Chase Game Intro\n⌨️ 
                                (2:53:55)​ Importing Assets\n⌨️ (3:02:22)​ Creating Player Animations\n⌨️ 
                                (3:17:36)​ Sorting Layers And Order In Layer\n⌨️ (3:24:04)​ Creating The Game Background\n⌨️ 
                                (3:33:03)​ Player Movement\n⌨️ (3:50:26)​ Animating The Player Via Code\n⌨️ 
                                (4:03:34)​ Player Jumping\n⌨️ (4:19:03)​ Camera Follow Player\n⌨️ (4:30:42)​ Enemy Animations\n⌨️ 
                                (4:39:40)​ Enemy Script\n⌨️ (4:51:15)​ Enemy Spawner\n⌨️ (5:15:15)​ Enemy Collision\n⌨️ 
                                (5:31:55)​ The Collector Script\n⌨️ (5:40:32)​ Unity's UI System\n⌨️ (5:53:06)​ Creating Main Menu\n⌨️ 
                                (6:02:34)​ Navigating Between Scenes\n⌨️ (6:13:04)​ Selecting A Character\n⌨️ 
                                (6:20:50)​ Static Variables\n⌨️ (6:30:23)​ Singleton Pattern\n⌨️ (6:41:35)​ Events And Delegates\n⌨️
                                (7:13:11)​ Instantiating The Selected Character\n⌨️ (7:17:59)​ Finishing Our Game\n\n🎉 Thanks to our 
                                Champion supporters:\n👾 Otis Morgan\n👾 DeezMaster\n👾 Katia Moran \n\n--\n\n
                                Learn to code for free and get a developer job: https://www.freecodecamp.org\n\n
                                Read hundreds of articles on programming: https://freecodecamp.org/news",
                "thumbnails": {
                    "defaultThumbnail": null,
                    "medium": {
                        "url": "https://i.ytimg.com/vi/gB1F9G0JXOo/mqdefault.jpg",
                        "width": 320,
                        "height": 180
                    },
                    "high": {
                        "url": "https://i.ytimg.com/vi/gB1F9G0JXOo/hqdefault.jpg",
                        "width": 480,
                        "height": 360
                    },
                    "standard": {
                        "url": "https://i.ytimg.com/vi/gB1F9G0JXOo/sddefault.jpg",
                        "width": 640,
                        "height": 480
                    },
                    "maxres": {
                        "url": "https://i.ytimg.com/vi/gB1F9G0JXOo/maxresdefault.jpg",
                        "width": 1280,
                        "height": 720
                    }
                }
            },
            {
                "id": "4HoJIgyclZ4",
                "channelTitle": "Vinícius Ferreira",
                "videoOwnerChannelTitle": "freeCodeCamp.org",
                ...
            }
        ...
        }
    ...
    }


A playlist do exemplo acima faz parte das minhas playlists publicas do Youtube.

![img.png](src/main/resources/static/img/playlist-url.png)

- **Playlist URL**: https://www.youtube.com/playlist?list=PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q
- **Playlist ID**: PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q

De agora em diante você poderá fazer uma requisição para nossa API usando qualquer link de playlist publica do Youtube e 
receber um Json com todas as informações relevantes dos videos.
A resposta é limitada à 25 videos por playlist.

<a id="Swagger"></a>
## Open API Specification e Swagger
Com nossa aplicação rodando podemos acessar o link do Swagger e ver informações relevantes sobre o serviço, métodos 
HTTP válidos, request Objects, response Objects, respostas com erros ou exceções, descrições detalhadas e mais.

Basta acessar a [documentação Swagger da aplicação](http://localhost:9999/swagger-ui/indexhtml)

<br><br>

**[<< início](#Inicio)**
___