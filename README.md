<p align="center">
  <img alt="Tamanho do Repositório" src="https://img.shields.io/github/repo-size/ErichComparin/App-Projeto-Aplicado-IGTI---Java-Android?style=flat-square" />
  
  <img alt="Último commit" src="https://img.shields.io/github/last-commit/ErichComparin/App-Projeto-Aplicado-IGTI---Java-Android?style=flat-square" />

  <a href="https://github.com/ErichComparin">
    <img alt="Feito por Erich Comparin" src="https://img.shields.io/badge/feito%20por-Erich%20Comparin-orange?style=flat-square" />
  </a>

  <a href="./LICENSE">
    <img href="Licença MIT" src="https://img.shields.io/apm/l/vim-mode?style=flat-square" />
  </a>
</p>

<h1 align="center">
    📷Solução para compartilhamento de fotos entre usuários🖼️
</h1>

<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#️-status">Status</a> •
 <a href="#️-funcionalidades">Funcionalidades</a> •
 <a href="#-layout">Layout</a> • 
 <a href="#-como-executar-o-projeto">Como executar</a> • 
 <a href="#-tecnologias">Tecnologias</a> •
 <a href="#️-autor">Autor</a> • 
 <a href="#-licença">Licença</a>
</p>

## 💻 Sobre o projeto

O produto pretende solucionar o desafio de manter acessíveis as fotos de um certo evento de forma compartilhada. Este compartilhamento deve ser restrito a um grupo de pessoas, e não público. O usuário poderá capturar uma foto, e esta será sincronizada com todos os usuários cujo álbum esteja compartilhado. Neste mesmo momento, a imagem será armazenada em um ambiente de nuvem, ficando disponível para futuras visualizações. O público alvo se trata de usuários finais que utilizam seus smartphones para capturar fotografias e desejam um armazenamento em nuvem, ao mesmo tempo que o conteúdo seja compartilhado com os usuários desejados.
A aplicação tem por objetivo propiciar a praticidade e economia de tempo em executar a tarefa de compartilhar fotografias com os amigos e a disponibilidade e facilidade de encontrar fotos antigas e suas respectivas informações. Além disto, a solução proporciona a privacidade do conteúdo, sendo que apenas as pessoas desejadas tem acesso ao mesmo. Provém economia no plano de dados, considerando que a comunicação com a nuvem para sincronismo poderá ser realizada apenas quando o dispositivo estiver conectado à uma rede Wi-Fi, e também no espaço de armazenamento do mesmo, possibilitando realizar o download apenas do conteúdo que o usuário escolher.
O diagrama a seguir representa a arquitetura da aplicação, que utiliza a plataforma Firebase do Google com as seguintes finalidades por serviço:
1. Firebase Realtime Database: banco de dados NoSQL hospedado na nuvem. Armazena os dados da aplicação em JSON e realiza o sincronismo entre os clientes em tempo real.
2. Firebase Authentication: realiza o cadastro e autenticação dos usuários.
3. Cloud Storage for Firebase: armazena os arquivos de imagem dos usuários.

<img alt="NextLevelWeek" title="#NextLevelWeek" src="./readme/arq.jpg?raw=true" />

---

## 🏃‍♂️ Status

- [x] Concluído

---

## ⚙️ Funcionalidades

- [x] Deve possibilitar que o usuário crie uma conta e faça login com a mesma
- [x] Deve possibilitar a criação de álbuns, que serão agrupadores de fotografias
- [x] Deve possibilitar a captura de fotografias com a câmera do dispositivo para inserção em um álbum
- [x] Deve possibilitar a escolha de usuários para realizar o compartilhamento de um álbum
- [x] Deve haver um sistema de armazenamento na nuvem para manter os dados e fotografias
- [x] Deve possibilitar o sincronismo das fotografias com o armazenamento na nuvem, enviando as capturadas pelo usuário e recebendo as capturadas por outros usuários que possuem álbuns compartilhados com ele

---

## 🎨 Layout

- Tela de login
<img alt="Landing Page" src="./readme/mobile1.jpg?raw=true">

- Tela de cadastro de usuário
<img alt="Cadastro de aulas" src="./readme/mobile2.jpg?raw=true">

- Lista de álbuns
<img alt="Consulta de aulas" src="./readme/mobile3.jpg?raw=true">

- Lista de fotos
<img alt="Consulta de aulas" src="./readme/mobile4.jpg?raw=true">

- Compartilhamento de álbuns
<img alt="Consulta de aulas" src="./readme/mobile5.jpg?raw=true">

---

## 🚀 Como executar o projeto

```bash

# Clone este repositório
$ git clone https://github.com/ErichComparin/App-Projeto-Aplicado-IGTI---Java-Android.git

# <<firebase>>

# Execute o projeto no Android Studio
$ cd Proffy-NLW2---ReactJS

```

---