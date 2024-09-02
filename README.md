# Projeto Sistema de Gestão de Estacionamento (Atividade 3 e Desafio Semana 04) -  SPRINGBOOT_AWS_AGO24

Este projeto tem como objetivo atender as exigências da ativiadade da semana 03 e do desafio da semana 04 do programa de bolsas da Compass UOL.

### Observação
A estrutura do projeto aqui no repositório do GitHub esta com o projeto dentro de uma subpasta devido a atividade 3 que solicitava que fosse criada uma branch para a atividade 3 antes de criar o projeto.

![img_2.png](img_2.png)

## Requisitos
Certifique-se de ter as seguintes ferramentas instaladas:

- Java Development Kit (JDK) 21 ou superior.
- IDE para trabalhar com Java.
- MYSQL versão LTS 8.4.0.
- Conector do Java versão 8.4.0 (mysql.connector.j) - Utilizado gerenciador de pacotes Maven para instalação do conector ao projeto.

## Configuração do Projeto e Banco de Dados
Siga os passos abaixo para configurar o projeto em seu ambiente de testes:

1. **Clone o repositório**

   ```bash
   git clone https://github.com/DevMatheusMarques/sistema-gestao-estacionamento.git
   cd nome-do-diretorio
   ```
   
2. **Configuração do projeto**:

   Observe a estrutura de pastas do projeto e encontre a pasta resources. Ela vai estar no seguinte caminho:
    ```bash
   cd sistema-gestao-estacionamento/src/main
   ```
   Nesta pasta você vai encontrar um arquivo db.properties. Este arquivo contem a configuração das propriedades de acesso ao banco de dados. Como neste exemplo da figura a seguir:
   
    ![img.png](img.png)

    Você deve alterar as seguintes variáveis:
   - user
   - password
   
   Essas variaveis representam o respectivo nome e senha da sua conexão do banco de dados mysql. Você deve inserir os dados conforme o nome e senha que estiver em sua máquina.


3. **Criação do Banco de Dados do Projeto**
   
   Junto ao projeto há um arquivo .sql, você deve pegar este arquivo e abri-lo com seu MySQL Workbench.

   Nome completo do arquivo:

   ```bash
   sistema-gestao-estacionamento-tabelas.sql
   ```

   Ao abrir basta executar o código e o banco de dados será criado e populado.


4. **Executar o projeto (Classe Main)**

   Acesse o projeto e procure pela classe Main. Ela vai estar no seguinte caminho:
   ```bash
   cd sistema-gestao-estacionamento/src/main/java/org.compass/application
   ```
   Selecione a classe main e execute a opção de Run 'Main.java' em sua IDE.


5. **Requisitos Adiados**

   - Refatorar a classe Menu para deixar com um código mais limpo e com separação de responsabilidades.
   - Traduzir todo o código do projeto para inglês.


### Contato
Em caso de dúvidas ou problemas, entre em contato com:

* Nome: Matheus Marques
* Email: devmatheusmarques@gmail.com
* GitHub: github.com/DevMatheusMarques
