#  Desafio Android do Jeitto 

## Como executar o aplicativo?

Para executar o aplicativo, baixe o arquivo apk no link
[clique aqui para baixar o aplicativo ](https://github.com/patriciojdutra/DesafioJeitto/blob/master/app-desafio-jeitto.apk)
 e instale em seu smartphone.

## Requisitos do aplicativo

* A primeira tela do aplicativo contém uma toolbar com título, um campo de pesquisa e a listagem de notícias.
  * Título da tela: Notícias
* Ao entrar na tela, deve ser exibido um loading em tela cheia enquanto os dados não são retornados.
* Ao entrar na tela, deve ser mostrada uma lista de notícias com no máximo 15 itens retornados da API.
  * Cada item da lista contém a imagem, título e o nome do site fonte da notícia. (imageUrl, title e newsSite respectivamente)
* Ao chegar no fim da lista, é feita uma paginação, realizando uma nova requisição a API e carregando mais 15 itens.
* O campo de pesquisa suporta no máximo 10 caracteres.
  * Ao digitar no campo de pesquisa, deve ser feita uma filtragem nos títulos dos itens já carregados, fazendo com que a lista mostre somente os itens cujo os títulos contenham o conteúdo digitado no campo.
  * Ao apagar todo o conteúdo do campo de pesquisa, devem ser exibidos todos os itens carregados anteriormente.
* Ao clicar em uma notícia, é apresentado um Dialog com os detalhes e as opções Voltar e Ler artigo
  * O Dialog contém o título, sumário e imagem da notícia. (title, summary e imageUrl respectivamente)
  * Ao clicar em Voltar ou ao clicar fora do Dialog, o mesmo é fechado.
  * Ao clicar em Ler artigo, deve ser aberto o navegador padrão do dispositivo no link contido no campo url retornado pela API.
Fluxos alternativos
* Caso a API retorne um erro (HTTP 4xx ou 5xx), deve ser exibido um dialog de erro.
  * Título: Não foi possível carregar as notícias
  * Mensagem: De acordo com o campo message do retorno de erro.
  * Botões:
      * OK: O aplicativo é fechado.
      
[Clique aqui para saber mais sobre o desafio.](https://github.com/Jeitto/Android-Challenge/)
      
      
## Telas

#### Carregando os dados iniciais e paginação.
![Gifs](https://github.com/patriciojdutra/DesafioJeitto/blob/master/mobizen_20210125_022457.gif)

#### Mantendo os dados mesmo com rotação de tela.
![Gifs](https://github.com/patriciojdutra/DesafioJeitto/blob/master/mobizen_20210125_022745.gif)

#### Navegando até a url da noticia.
![Gifs](https://github.com/patriciojdutra/DesafioJeitto/blob/master/mobizen_20210125_023200.gif)

## Tecnologias e Frameworks

* Kotlin
* Arquitetura MVVM
  * ViewModel
  * LiveData
* Retrofit
* RecyclerView
* Picasso
* Json
* Animação

# Perguntas que devem ser respondidas

**Quais foram os principais desafios durante o desenvolvimento?**
Como eu não tinha muita familiaridade com MVVM, optei por desenvolver o app em MVC, onde levei cerca de um dia para finalizar o aplicativo. O principal desafio foi implantar a arquitetura MVVM, pois tive que dar uma estudada na documentação antes de implantar no projeto.

**O que você escolheu como arquitetura/bibliotecas e por que?**
Por ser um projeto pequeno, a arquitetura não é algo que teria um impacto significativo no projeto, inicialmente implementei a arquitetura MVC, mas como havia bastante tempo até a data da entrega, resolvi implementar MVVM.
Para consumir os dados da api, optei por usar retrofit, pois é uma das mais poderosas e populares bibliotecas de HTTP Client para Android.

**O que falta desenvolver / como poderiamos melhorar o que você entregou, caso não tenha tido tempo suficiente para terminar o desafio?**
Todas as funcionalidades do aplicativo foram desenvolvidas, e o tempo foi suficiente! O que poderia ter para melhorar o código, seria testes unitarios.







