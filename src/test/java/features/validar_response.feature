Feature: Validar campos title, completed e status no recurso GET /todos/1

  Scenario: Validar campos title, completed e status no recurso /todos/1
    Given que estou conectado a internet
    When efetuo o request com metodo GET na url "https://jsonplaceholder.typicode.com/todos/1"
    Then campo title "delectus aut autem"
    And campo completed "false"
    And status ok 200