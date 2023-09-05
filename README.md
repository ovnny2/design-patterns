# Bootcamp Santander - DIO
___Série de projetos destinados à resolução de problemas e laboratórios do Bootcamp do Santander na Digital Innovation One___
___

## MVP Definition:

Um serviço de curadoria de videos do youtube onde usuários fornecerão um link ou playlist ID de uma lista proprietária de favoritos.<br>
Os resultados serão apresentados ao usuário em forma de Cards no Frontend, incluindo um resumo ou review do conteúdo do(s) vídeo(s).<br>

### Fase 1
O sistema deve integrar-se à API do YouTube para buscar os seguintes dados de cada vídeo na lista:

- Duração do video
- Nome do Canal
- Título do Vídeo
- Visualizações
- Thumbnail
- Data de Criação
- Transcrição na Língua Original

Os dados coletados serão persistidos em um banco de dados MongoDB e num sistema de cache com REDIS.

### Fase 2

Um serviço utilizará um LLM para traduzir transcrições e criar metadados sobre os videos.
___
## Todo List: Integração à API do YouTube:

### 1. **Registre um Projeto na Google Cloud Console:**

- [x] Acesse a [Google Cloud Console](https://console.cloud.google.com/) e crie um novo projeto.
- [x] Crie um projeto, configure o faturamento e habilite a API do YouTube Data API v3 para o projeto.
- [x] Obtenha as credenciais de API (API Key) para usar na integração.

### 2. **Implemente a Integração da API do YouTube:**
- [x] Autenticação simples por chave de API
- [ ] Siga a [documentação oficial](https://developers.google.com/youtube/registering_an_application) para autenticar as requisições e fazer solicitações para obter os dados dos vídeos.

### 3. **Obtenha os Dados dos Vídeos:**
- [x] Use a API para buscar informações dos vídeos a partir dos links/IDs de playlist fornecidos pelos usuários.
- [x] Colete dados como duração, nome do canal, título, visualizações, thumbnail e data de criação.
- [ ] Para a transcrição, acesse as legendas disponíveis (se houver) usando a [YouTube API para legendas](https://developers.google.com/youtube/v3/docs/captions).

### 4. **Persistência no MongoDB:**
- [x] Configure uma conexão com o MongoDB em sua aplicação Java.
- [x] Crie um modelo de dados para armazenar as informações coletadas dos vídeos.
- [x] Implemente a lógica para persistir esses dados no MongoDB.

### 5. **Opção: Serviço de Tradução/Resumo com LLM:**
- [ ] Se possível, integre um LLM (como o GPT-3) para traduzir ou resumir as transcrições.
- [ ] Acesse a documentação do LLM escolhido para entender como fazer solicitações de tradução ou resumo.

### 6. **Geração de Cards no Frontend:**
- [ ] Crie uma estrutura no Frontend para exibir os Cards com as informações dos vídeos.
- [ ] Formate os dados coletados e insira-os nos Cards, incluindo o resumo da transcrição (se disponível).


### Tecnologias e Stack:

         Java 17 |  Spring 3 |  Gradle 8.3 |  Yaml  |  JUnit 5 |  Mockito  |  AssertJ  |  
         Docker  |  MongoDb 6.0 |   Redis   |  Python  |  LLM  | ChatGPT |  JavaScript |   
___

## Informações Gerais:

- [Repositório Github](https://github.com/ovnny2/design-patterns).
- [Tutorial - Entendendo a aplicação](videocurator/README.md).
- [Tutorial - Obtendo a Chave de API do Youtube](videocurator/README.md).
- [Tutorial - Fazendo o build com Docker](videocurator/README.md).
- [Tutorial - Fazendo building da aplicação](videocurator/README.md).
- [OpenAPI 3 - Docs](http://localhost:9999/swagger-ui/index.html).
- [Documentação - YouTube V3 API](https://developers.google.com/youtube/v3/getting-started).
- [Google Cloud Console](https://console.cloud.google.com/).
- []().

___
### Ao longo de todo o processo:

**Testes e Validação:**
> - Realize testes rigorosos para garantir que os dados estão sendo coletados e persistidos corretamente.
> - Teste também a geração de Cards no Frontend.

**Documentação e Melhorias:**
> - Documente todo o processo de integração da API, configurações do projeto, fluxo de dados, etc.
> - Analise possíveis melhorias no código, performance e usabilidade.

___
## Contato
- > __[Meu LinkedIn](https://linkedin.com/in/vinicius-ricardo).__<br>
- > __[Meu Github](https://github.com/ovnny2)__.
- > __viniciusricardo.ferrera@gmail.com__<br>