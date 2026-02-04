## Página de Login com Spring Boot e React js 🤖 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
### Dependências:
Será Preciso que você tenha o Docker instalado na sua máquina, mais especificamente o Docker-compose para prosseguir com o projeto, caso não tenha instalado deve instalar para prosseguir.
### Instalação:
vou ensinar como rodar esse sistema de login diretamente no seu computador, para isso você deve seguir os comandos na ordem correta:
#### Clonagem do repositório:
```git
  git clone https://github.com/kaio-ksianskievis/Login-Page-SpringBoot-React.git
```
#### Iniciar o projeto:
```git
  cd Login-Page-SpringBoot-React
  docker-compose up
```
ao fazer isso o nosso sistema de login vai iniciar e você já poderá usa-lo no seguinta endereço http://localhost:5173
### Rotas 📍:
rota | descrição
:--:  | :----:
`/login` | rota responsável por logar os usuários
`/register` | rota responsável por criar usuário
`/verify` | rota responsável por verificar o email informado
`/home` | rota principal do nosso projeto

#### http://localhost:5173/login

<img src="Screenshot from 2026-02-04 13-54-30.png" alt="foto login" width="500"/>


#### http://localhost:5173/register

<img src="Screenshot from 2026-02-04 13-55-03.png" alt="foto signup" width="500"/>

#### http://localhost:5173/verify

<img src="Screenshot from 2026-02-04 13-56-01.png" alt="foto verify" width="500"/>

### Documentação da API 📄:
#### `/login`
Na rota http://localhost:8080/login é onde validamos os usuário se ele existir retornará um token de acesso `JWT` caso não exista o usuário em questão é retornado uma execção.
#### Status 200 OK:
<img src="Screenshot from 2026-02-04 14-22-36.png" alt="Requisição HTTP Post /login" width="700"/>

#### Status 401 não autorizado:

<img src="Screenshot from 2026-02-04 14-23-08.png" alt="Requisição HTTP Post /login" width="500"/>


#### `/register`
Na rota http://localhost:8080/register é onde criamos os usuários e retornamos uma instancia do usuário criado. Caso seja inserido algo errado na criação é retornado uma execção.
#### Status 201 CRIADO:

<img src="Screenshot from 2026-02-04 14-19-08.png" alt="Requisição HTTP Post /register" width="700"/>

#### Status 400 sintaxe inválida:

<img src="Screenshot from 2026-02-04 14-19-58.png" alt="Requisição HTTP Post /register" width="500"/>

#### `/verify`
Na rota http://localhost:8080/verify é onde verificamos o email informado no /register, mandamos um codigo no email do usuário e ele deve informar no corpo da requisição, retornará o status da conta, caso seja inserido algo errado na criação é retornado uma execção.

#### Status 200 OK:

<img src="Screenshot from 2026-02-04 14-20-57.png" alt="Requisição HTTP Post /verify" width="700"/>

#### Status 400 sintaxe inválida:

<img src="Screenshot from 2026-02-04 14-21-17.png" alt="Requisição HTTP Post /verify" width="500"/>

