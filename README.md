As respostas dos exercícios 1, 2 e 3 estão todos em seus respectivos pacotes.

Abaixo, segue as respostas dos exercícios 4 e 5:

Resposta 4:
O Deadlock ocorre quando um ou mais processos necessitam acessar recursos de um processo que já está sendo usado, ou por exemplo: Processo1 necessita usar os recursos do processo2 e o processo2 necessita usar os recursos do processo1. Isso gera uma referência circular que também gera um deadlock.
Recentemente tive um problema com isso no seguinte cenário: Quando a aplicação ia buildar e executar os testes unitários em determinado teste, ele fazia um TRUNCATE na tabela do banco, o que impedia que outros testes fossem realizados e as vezes já tinha uma conexão aberta e ele não conseguia fechar, isso gerava um deadlock fazendo com que os testes não fossem exeutacos e ficava parado nesse teste que fez o truncate da tabela.
A maneira que resolvi isso, foi verificar como estavm sendo feitas as conexões no banco, percebi que os testes tentavam usar a mesma conexão, então, antes de cada teste iniciar comecei a fazer uma nova transação com o banco e depois de cada um, eu dava um rollback. Dessa forma o deadlock parava de acontecer porque o os processos deixavam de usar os mesmos recursos ao mesmo tempo.

Resposta 5:

O  ParallelStreams como o nome diz, é paralelo ao contrário do Stream que é sequencial, essa é a principal diferença entre eles. O Stream deve ser usado quando o número de elementos a ser processados não for tão grande e esse processamento não levar muito tempo, pois, se a quantidade de elementos for muito grande e cada elemento demorar para ser processado, isso irá gerar um gargalo no sistema e é nesse cenário que entra o ParallelStreams, onde para estes casos em que existem muitos elementos e o tempo de processamento é alto, vale a pena fazer esse processamento paralelo o que vai fazer com que o tempo de resposta seja menor.
Deve-se levar em consideração o hardware que executará a operação, porque o processamento paralelo do ParallelStreams tomará muito mais recursos de hardware do que o processamento sequencial do  Stream, então se o servidor que executar a operação não estiver preparado, pode ser que gere um gargalo maior ainda no sistema do que utilizar o  Stream.


Explicação sobre a arquitetura e frameworks utilizados no desenvolvimento da aplicação:
Foi utilizado diversos frameworks da suite do Spring, como: Spring data, jpa, REST, Test, boot. Escolhi utilizar eles por já ter mais familiaridade com o Spring e porque sei que para o que foi pedido o Spring funciona muito bem. Utilizei o Swagger para fazer uma documentação das APIs expostas e também da para executar alguns testes manuais nas APIs. Após executar a aplicação, o swagger pode ser acessado através da URL: localhost:8095/swagger-ui.html.
Fiz alguns testes (sei que poderia ter feito mais, mas, não iria ter mais tempo para fazer) utilizando o framework junit.
O banco de dados utilizado foi o Mysql e os comandos com as tabelas podem ser encontrados no arquivo netbase.sql.