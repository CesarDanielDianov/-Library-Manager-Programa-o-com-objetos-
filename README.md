# -Library-Manager-Programação-com-objetos-
  Sistema de gestão de biblioteca desenvolvido em Java no âmbito da disciplina de Programação Orientada a Objetos. 
  Permite gerir utentes, obras (livros e DVDs), requisitar e devolver obras, calcular multas e enviar notificações sobre disponibilidade.

#FUNCIONALIDADES PRINCIPAIS:
  Gestão de utentes: registo, classificação (cumpridor, faltoso, normal), pagamento de multas, notificações.
  Gestão de obras: livros e DVDs, alteração de inventário, pesquisa, categorias (ficção, referência, científica/técnica).
  Requisições: regras de empréstimo, cálculo de prazo e multas, notificações automáticas.
  Persistência de dados: serialização do estado do sistema, importação de ficheiros textuais.
  Interface baseada em menus com a framework po-uilib.

#COMPILAR E EXECUTAR (terminal linux):
  Assumindo que se está no directório que inclui o directório bci (que é o directório raiz que contém o código da aplicação),a compilação 
  pode ser feita da seguinte formas: javac -cp po-uilib.jar:. `find bci -name "*.java"`   

  Assumindo que se está ainda no mesmo directório que inclui o diretório raiz do projecto, para executar o projecto é necessário 
  dar o seguinte comando: java -cp po-uilib.jar:. bci.app.App
  
#FERRAMENTAS USADAS:
  Java 
  Shell script (Linux)
  Biblioteca po-uilib.jar para interface de menus
