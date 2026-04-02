import java.util.ArrayList;
import java.util.List;
List<String> veiculos = new ArrayList<>();
void main() {
    IO.println("Bem vindo ao Sistema CadVeiculos");
    String menu = """
            MENU DE OPÇÕES
            1 - Cadastrar Veículo
            2 - Listar Veículos
            3 - Remover Veículo
            4 - Buscar Veículo
            5 - Editar Veículo
            0 - Sair
            """;
    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite a opção desejada: ");
        switch (opcao) {
            case 1 -> {
                cadastrar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 2 -> {
                listar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 3 -> {
                excluir();
                IO.readln("Pressione Enter para Continuar");
            }
            case 4 -> {
                buscar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 5 -> {
                editar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 0 -> {
                IO.println("Volte sempre!!!");
            }
            default -> {
                IO.println("Opção Inválida");
                IO.readln("Pressione Enter para Continuar");
            }

        }
    } while (opcao != 0);

}

// Verifica duplicatas
boolean existeVeiculo(String nome) {
    for (String v : veiculos)
        if (v.equalsIgnoreCase(nome))
            return true;
    return false;
}

// Ordenação manual (bubble sort)
void ordenar() {
    for (int i = 0; i < veiculos.size() - 1; i++)
        for (int j = 0; j < veiculos.size() - 1 - i; j++)
            if (veiculos.get(j).compareToIgnoreCase(veiculos.get(j + 1)) > 0) {
                String tmp = veiculos.get(j);
                veiculos.set(j, veiculos.get(j + 1));
                veiculos.set(j + 1, tmp);
            }
}

void cadastrar() {
    String veiculo = IO.readln("Digite o nome do novo veículo: ").trim();
    if (veiculo.isEmpty()) {
        IO.println("Nome inválido!");
        return;
    }
    if (existeVeiculo(veiculo)) {
        IO.println("Este veículo já está cadastrado!");
        return;
    }

    veiculos.add(veiculo);
    IO.println("Veículo cadastrado!");
}

void listar() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia!");
        return;
    }
    ordenar();
    for (int i = 0; i < veiculos.size(); i++)
        IO.println((i + 1) + " - " + veiculos.get(i));

    IO.println("Total: " + veiculos.size());
}

void excluir() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia!");
        return;
    }
    IO.println("""
            Deseja remover por:
            1 - Índice
            2 - Nome
            """);

    int tipo = Input.scanInt("Escolha: ");

    if (tipo == 1) {
        // Remoção por índice
        listar();
        int indice = Input.scanInt("Digite o índice: ");
        if (indice < 1 || indice > veiculos.size()) {
            IO.println("Veículo não encontrado!");
            return;
        }

        IO.println("Removido: " + veiculos.remove(indice - 1));
    }

    else if (tipo == 2) {
        // Remoção por nome
        String nome = IO.readln("Digite o nome: ").trim();
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).equalsIgnoreCase(nome)) {
                IO.println("Removido: " + veiculos.remove(i));
                return;
            }
        }

        IO.println("Veículo não encontrado!");
    }

    else {
        IO.println("Opção inválida!");
    }
}

void buscar() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia!");
        return;
    }

    String nome = IO.readln("Digite o nome para buscar: ").trim();
    boolean encontrado = false;

    for (String v : veiculos)
        if (v.equalsIgnoreCase(nome))
            encontrado = true;

    IO.println(encontrado ? "Veículo encontrado!" : "Não encontrado.");
    IO.println("Total: " + veiculos.size());
}

void editar() {
    if (veiculos.isEmpty()) {
        IO.println("A lista está vazia!");
        return;
    }

    listar();
    int indice = Input.scanInt("Digite o número do veículo: ");
    if (indice < 1 || indice > veiculos.size()) {
        IO.println("Índice inválido!");
        return;
    }

    String novoNome = IO.readln("Novo nome: ").trim();
    if (novoNome.isEmpty() || existeVeiculo(novoNome)) {
        IO.println("Nome inválido ou duplicado!");
        return;
    }

    veiculos.set(indice - 1, novoNome);
    IO.println("Atualizado!");
}