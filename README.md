# rabbitMQ-API-processamento

# Sobre a API

Este microsserviço faz parte do projeto de mensageria utilizando o rabbitMQ, e se comunica com mais dois microsserviços: o de criação do pedido (https://github.com/kauannr/rabbitMQ-API-pedido) e o de notificação do pedido (https://github.com/kauannr/rabbitMQ-API-notificacao).
A funcionalidade dessa API, como o nome sugere, é a criação de processar o pedido que irá chegar na queue processar-pedido que está vinculada a exchange pedido.v1.criado, no rabbitMQ. 
A API fica "ouvindo" essa fila de mensagens, para pegar a mensagem e fazer o tratamento correto. 

Por enquanto, não há uma lógica avançada de processamento, visto que é apenas projeto para prática. Até então, com essa API estou armazenando os pedidos no banco de dados
