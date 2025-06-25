# Instalação, execução e compilação

## Build:
Ao realizar build, deverá ser escolhido um flavor:
"form" -> No menu principal, existe apenas a opção de seguir para o ecrã de preenchimento do formulário
"list" -> No menu principal, existe apenas a opção de seguir para o ecrã de aonde consta a lista dos produtos

## Instalação:
A instalação é executada normalmente sem questões relevantes.

## Execução:
Aquando do arranque da App, é verificado se a base de dados contem algum elemento. Não contendo, é efectuado o download da lista de produtos e posteriormente guardado na base de dados.
Se a base de dados já tiver algo, os dados são carregados para uma variável local no ViewModel.
Isto ocorre independentemente do flavor selecionado.

"form" -> Utilizando este flavor, é possível abrir apenas o ecrã para o preenchimento do formulário de dados.
Algumas validações estão presentes durante o preenchimento, outras ocorrem ao clicar no botão "Submit".
Nome -> Não tem restrições no preenchimento, e ao carregar em "Submit", verifica se o campo está vazio.
Email -> Ajusta o teclado paa introdução de email no preenchimento, e ao carregar em "Submit", verifica se o campo está vazio e compara com um Regex tipicamente utilizado para validar emails.
Numero -> Ajusta o teclado paa introdução de numeros apenas no preenchimento, e ao carregar em "Submit", verifica se o campo está vazio e se o conteudo é efectivamente um numero.
Código promocional -> Apenas permite maisculas sem acentos e hifens no preenchimento, e ao carregar em "Submit", verifica se o campo está vazio e se tem entre 3 e 7 caracteres.
Data de entrega -> Ajusta o teclado paa introdução de numeros apenas no preenchimento e ainda mostra qual o formato correcto, e ao carregar em "Submit", verifica se o campo está vazio, se é uma data válida, se não é segunda-feiram e se a data não está no futuro
Classificação -> Não tem restrições na selecção, e ao carregar em "Submit", verifica se o campo está vazio.

"list" -> Utilizando este flavor, é possível abrir apenas o ecrã para com a lista de produtos.
Tal só é possível se os dados já tiverem sido carregados numa variável local no ViewModel, caso contrário é lançado um toast a informar que a lista de produtos não está disponível.
Estando a lista disponível, é de imeditado exibida a lista com todos os produtos.
Existe uma caixa de texto aonde é possível filtrar a lista. Qualquer caracter introduzido, leva imediatamente à filtragem da lista.
Podem ser usadas uma ou mais palavras para filtrar, em que a ordem delas não é relevante. É pesquisado o título e descrição dos produtos.
Ao clicar no produto desejado, é exibido um novo ecrã com os detalhes do produto, assim como uma foto do mesmo, cujo download é iniciado ao clicar no produto ainda na lista. A falha do download levar à exibição de uma imagem de erro.



# Decisões técnicas

Foi utilizado o Hilt para injecção de dependencias, não só por ter sido exigido, mas também por simplificar bastante a integração de testes unitários e de criar código modular.
Foi utilizado o Jetpack Compose não só por ter sido exigido, mas porque está a tornar-se rapidamente na forma mais rápida e eficiente de gerar ecrãs. Começa também a ter mais suporte que XML.
O endpoint para fazer o download inclui o parametro "limit" que pode ser usado para limitar o número de produtos. Ficou a 0 por default, para fazer download de todos os produtos.
Foi utilizado o Coil por facilitar BASTANTE a implementação do download da imagem, e para gerir falhas que possam ocorrer no download.
Foi utilizado apenas um ViewModel para toda a App devido ao seu tamanho e funcionalidades reduzidas, o que facilitou assim a sua integração na App.
