## Sinopse

Esta é a parte App Mobile do trabalho final da matéria Desenvolvimento de Aplicações Móveis Aplicando Internet das Coisas do MBA em Desenvolvimento de Jogos e Aplicações para Dispositivos Móveis e IoT da FIAP. A aplicação faz interface com um Hardware através de comunicação Bluetooth e atualiza um servidor em tempo real quando ocorre uma mudança no estado do Hardware.

 ## Motivação

 Para demonstrar a integração de Hardware, Aplicação Mobile e Servidor Real Time esse projeto possui uma dinâmica simples na qual uma mudança no estado do Hardware é comunicada em tempo real com o app via Bluetooth, que por sua vez faz uma chamada HTTP a um servidor que disponibiliza uma pagina web a qual mostra o estado do hardware em tmepo real.

 ## Utilizaçao

 O Servidor foi colocado no ar utilizando o BaaS Heroku e o App faz uma chamada HTTP para a API do Servidor para atualiza-lo quando ele percebe uma mudança de estado no Hardware via comunicação Bluetooth.