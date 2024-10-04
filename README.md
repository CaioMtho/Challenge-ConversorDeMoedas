<h1 align="center">Conversor de Moedas</h1>
<h4 align="center">
  Challenge - Oracle One
</h4>
<p align="center">
<img alt="badge" src="Badge-Conversor.png" align="center">
</p>
<br>
<br>

## ⚙️ O que o programa faz?
- Converte valores de uma moeda para outra;
- Busca as taxas de conversão com requisições para a [ExchangeRateAPI](https://www.exchangerate-api.com/);
- Registra as operações efetuadas em um arquivo de texto com um formato definido e permite operações com a persistência de dados;
> O caminho para o arquivo é definido com base no sistema operacional, sendo o padrão no windows: "C:\Users\<SeuUsuario>\AppData\Roaming\ConversorDemoedas\records.txt".

## 🤔 Como o programa faz?
O programa converte usando qualquer moeda com um [ISO 4217](https://pt.wikipedia.org/wiki/ISO_4217) válido e que esteja disponibilizado na Exchange Rate API, 
isso acontece utilizando uma funcionalidade da própria API.
> Verifique "isValidIsoCode" em "ExchangeRateConnect.java"

Um simples arquivo txt simples é usado para a persistência de dados, cada vez que uma operação é executada, o programa faz uma entrada no arquivo. Para ler, o programa garante
que o programa ainda está no formato desejado.
> Verifique a classe "Record".
>> É possível modificar o arquivo txt para injetar entradas manualmente, tal qual criar backups. Mas caso o arquivo não esteja no formato correto, será formatado.

## 🔼 Possíveis melhorias
- Implementação de um GUI para melhor experiência do usuário


