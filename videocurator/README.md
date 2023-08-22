# Design-patterns
**Santander Bootcamp Lab Project** at Digital Innovation One aimed at exploring design patterns.

- [documentação oficial da API do YouTube](https://developers.google.com/youtube/registering_an_application)
- key=API_KEY -> **AIzaSyAoyn2I0z0TMCr1qtGpl8Oo3-c1MBK_wTM**

## Tecnologias e Stack:
    | Java | Spring | Gradle | Yaml | JUnit | Mockito | AssertJ | Docker | MongoDb | Redis |

## MVP Definition:

### Fase 1

Um serviço de curadoria de videos do youtube onde usuários fornecerão um link ou playlist ID de uma lista proprietária de favoritos.</br>
Os resultados serão apresentados ao usuário em forma de Cards no Frontend, incluindo um resumo ou review do conteúdo do(s) vídeo(s).</br>
O sistema deve integrar-se à API do YouTube para buscar os seguintes dados de cada vídeo na lista:

- **Duração do video**
- **Nome do Canal**
- **Título do Vídeo**
- **Visualizações**
- **Thumbnail**
- **Data de Criação**
- **Transcrição na Língua Original**

Os dados coletados serão persistidos em um banco de dados MongoDB e num sistema de cache com REDIS.

### Fase 2

Um serviço utilizará um LLM para traduzir transcrições e criar metadados sobre os videos.


## Todo List: Integração à API do YouTube:

- [ ] **Registre um Projeto na Google Cloud Console:**
    - [x] Acesse a [Google Cloud Console](https://console.cloud.google.com/) e crie um novo projeto.
    - [x] Crie um projeto, configure o faturamento e habilite a API do YouTube Data API v3 para o projeto.
    - [x] Obtenha as credenciais de API (API Key) para usar na integração.

- [ ] **Implemente a Integração da API do YouTube:**
    - [ ] Utilize a biblioteca Java para realizar chamadas à API do YouTube.
    - [ ] Siga a [documentação oficial](https://developers.google.com/youtube/registering_an_application) para autenticar as requisições e fazer solicitações para obter os dados dos vídeos.

- [ ] **Obtenha os Dados dos Vídeos:**
    - [ ] Use a API para buscar informações dos vídeos a partir dos links/IDs de playlist fornecidos pelos usuários.
    - [ ] Colete dados como duração, nome do canal, título, visualizações, thumbnail e data de criação.
    - [ ] Para a transcrição, acesse as legendas disponíveis (se houver) usando a [YouTube API para legendas](https://developers.google.com/youtube/v3/docs/captions).

- [ ] **Persistência no MongoDB:**
    - [ ] Configure uma conexão com o MongoDB em sua aplicação Java.
    - [ ] Crie um modelo de dados para armazenar as informações coletadas dos vídeos.
    - [ ] Implemente a lógica para persistir esses dados no MongoDB.

- [ ] **Opção: Serviço de Tradução/Resumo com LLM:**
    - [ ] Se possível, integre um LLM (como o GPT-3) para traduzir ou resumir as transcrições.
    - [ ] Acesse a documentação do LLM escolhido para entender como fazer solicitações de tradução ou resumo.

- [ ] **Geração de Cards no Frontend:**
    - [ ] Crie uma estrutura no Frontend para exibir os Cards com as informações dos vídeos.
    - [ ] Formate os dados coletados e insira-os nos Cards, incluindo o resumo da transcrição (se disponível).


### Considerações

**Testes e Validação:**
- Realizar testes rigorosos para garantir que os dados estão sendo coletados e persistidos corretamente.
- Testar também a geração de Cards no Frontend.

**Documentação e Melhorias:**
- Documentar todo o processo de integração da API, configurações do projeto, fluxo de dados, etc.
- Analisar possíveis melhorias no código, performance e usabilidade.


## logos

- ![img.png](img.png)
- ![img_1.png](img_1.png)