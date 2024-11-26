TO-DO LIST!

Comando para startar a aplicação: **mvn spring-boot:run**

Link do Deploy para requisições: https://api-java-e-springboot-todo-e-usuarios.onrender.com

*Rotas:*

Rota de criação de usuários que gerarão a task.
*POST:* https://api-java-e-springboot-todo-e-usuarios.onrender.com/users

- Deve se passar um Body com as informações:

	"username": "exemplo",
	"name": "exemplo exemplo",
	"password": "XXXXXX"


A rota abaixo funciona após a criação do usuário, você pode criar uma task desde que o usuário criado seja passado pelo Basic da requisição na aba Auth.
*POST:* https://api-java-e-springboot-todo-e-usuarios.onrender.com/tasks/


A rota lista todas as tasks que foram criadas, desde que seja fornecido na aba Auth Basic os dados de login do usuário. 
*GET:* https://api-java-e-springboot-todo-e-usuarios.onrender.com/tasks/


A rota edita toda a task ou apenas uma propriedade dela e exibe o resultado como retorno, desde que seja passado o usuário através da aba Auth utilizando o Basic.
*PUT:* https://api-java-e-springboot-todo-e-usuarios.onrender.com/tasks/IdDaTask


Aplicação hospedada no site da render: https://render.com/

Ao iniciar um projeto com o Spring, devemos utilizar o site https://start.spring.io/ que serve para gerarmos um projeto com todas as dependencias que utilizaremos, versão do spring Boot e Java.

Feito isso, basta descompactar o arquivo zip que o spring initlizr nos gerou e abrir na IDE de preferencia.

Uma dependencia que nos auxilia a escrever um código mais limpo e organizado e a biblioteca https://projectlombok.org/setup/maven, project lombok, ela nos auxilia no caso de gerar os nossos arquivos UsersModel.java, pois nele quando declaramos as nossas propriedades como private, para acessa-las é necessario gerarmos os Getters e os Setters, e o que antes ficava assim:


    //O set insere o valor 
    public void setUsername(String username) {
        this.username = username;
    }


    // o get recupera (busca) esse valor 
    public String getUsername() {
        return username;
    }

Isso precisaria ser feito para todas as propriedades, exemplo, se existisse as propriedades; nomedeusuario, email, senha, endereço, posts. Deveria ser feito para todas elas, mas que com a utilização do project lombok isso se soluciona apenas utilizando uma anotation "@Data" e para utiliza-la deve-se sempre lembrar de acessar o arquivo pom.xml e dentro de dependecies criar a dependency para que seja utilizada no projeto: 

    <dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<version>1.18.36</version>
		<scope>provided</scope>
	</dependency>


ESTRUTURA DE PASTAS:

Entendemos que o Java é recursivo, logo, se eu for criar um arquivo para os meus controllers e os meus midlewers por exemplo, elas devem estar dentro da pasta onde está localizado o meu arquivo Application.java, pois o meu projeto se starta ali e em seguida vai lendo as demais classes e métodos acima. 

A utilização do banco de dados H2 que é um banco de dados em memória não serve para enviar a API para produção, pois com um banco de dados em memória, reiniciou a aplicação os dados são perdidos, mas pode ser utilizado para produção um Postgres, Sql, MariaDB e por ai vai.

Como acessar um banco H2 - http://localhost:8080/h2-console
