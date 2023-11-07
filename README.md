# Config_Perfil

## Descrição do Projeto
<p> Projeto feito para o trabalho de conclusão de curso de engenharia da computação em conjunto com Joanderson e Larissa.</p>

<p>
 <a href="#objetivo">Objetivo</a> •
 <a href="#Pré-requisitos">Pré-requisitos</a> • 
 <a href="#Modificações e Como Criar">Modificações e Como Criar</a> • 
</p>

# Objetivo

<p> Projeto feito com o proposito de ajudar o time de suporte e ao usúarios a configurar perfil de rede em uma estação de trabalho(Pasta de rede e Impressoras) sem o conhecimento técnico nescesario.</p>

# Pré-requisitos

<p> Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas: </p>
<a href="https://www.eclipse.org/downloads/packages/release"> Eclipse IDE 2022.12</a><br />
<a href="https://projects.eclipse.org/projects/tools.windowbuilder"> Eclipse WindowBuilder™ 1.11.0</a><br />
<br />

# Modificações e Como Criar

<p> Ao baixar o projeto você deve fazer as seguintes coisas:</p><br />

* depois de baixar e extrair os arquivos. abra a pasta no eclipse como 'Open Project from System File'.

* Em seguida você vai ter acesso a versão do código junto com a parte de desing que o WindowsBuilder oferece.

* Deve ser feito as seguintes preparações: 

    1. Criação de um arquivo .txt listando os nomes das impressoras presente na sua rede de impressão.
    2. Criação de um arquivo .csv onde a primeira coluna é o nome da pasta e a segunda coluna é o diretorio da mesma. ex.: Pasta, "\\\\pasta\\de\\rede"
    3. Após a criação destes 2 arquivos, deixe ele disponivel em um diretorio público da rede para que todos os usúarios tenha acesso ao rodar a aplicação.

* Ao finalizar as preparações o código já estará pronto para os testes ou na criação do executavél.

* Criando o executavél:
    1. Clique com o botão direito e selecione 'Export'.
    2. Selecione 'Java' e 'Runnable JAR file' e clique em 'Next'.
    3. Selecione o 'view - ConfigurarPerfil' e escolha o local e destino e o nome do executavél.
    4. Ao clicar em 'Finish' o executavél irá ser criado.

# Licença

Configurar_Perfil é [MIT licensed](./LICENSE).
