3. Há uma preocupação atual com as pegadas de carbono (emissões anuais de gás 
carbônico na atmosfera) a partir de instalações que queimam vários tipos de combustíveis para 
aquecimento, veículos que queimam combustíveis para se mover, e assim por diante. Nesse cenário: 
  * Crie três pequenas classes não relacionadas por herança - classes Prédio, Carro, e Bicicleta. Dê 
a cada classe alguns atributos e comportamentos (métodos) únicos que ela não tem em comum 
com as outras classes. 
  * Escreva uma interface PegadaDeCarbono com um método getPegadaDeCarbono. Faça cada 
uma das suas classes implementar essa interface, para que seu método getPegadaDeCarbono 
calcule uma pegada de carbono apropriada a cada classe de acordo com seus atributos. 
  * Escreva um aplicativo que crie 2 objetos de cada uma das três classes. Crie um objeto 
ArrayList\<PegadaDeCarbono\> e insira as referências dos objetos instanciados nessa coleção. 
Finalmente, itere pela coleção, chamando polimoficamente o método getPegadaDeCarbono de 
cada objeto. 
Modifique o código, tornando Prédio uma classe abstrata, e implementando duas novas subclasses 
concretas Casa e Escola. 
  * O aplicativo que cria a coleção de objetos vai continuar funcionando após a modificação na 
estrutura das classes? 
  * Modifique o aplicativo para que passe a instanciar diretamente objetos Casa e Escola, incluindo-
