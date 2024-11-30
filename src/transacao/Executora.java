package transacao;

import java.util.Scanner;

public class Executora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransacaoCRUD transacaoCRUD = new TransacaoCRUD();

        while (true) {
            // Exibe o menu para o usuário
            System.out.println("--- MENU CRUD DE TRANSAÇÕES ---");
            System.out.println("1. Cadastrar Transação");
            System.out.println("2. Listar Transações");
            System.out.println("3. Atualizar Transação");
            System.out.println("4. Excluir Transação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            // Leitura da opção do menu
            int opcao = 0;
            try {
                opcao = scanner.nextInt();  // Lê a opção
            } catch (Exception e) {
                System.out.println("Entrada inválida, por favor digite um número.");
                scanner.nextLine();  // Limpa o buffer
                continue;  // Volta para o início do loop
            }

            scanner.nextLine();  // Limpa o buffer após a leitura do número

            // Executa a opção escolhida pelo usuário
            switch (opcao) {
                case 1:
                    transacaoCRUD.cadastrarTransacao();
                    break;
                case 2:
                    transacaoCRUD.listarTransacoes();
                    break;
                case 3:
                    transacaoCRUD.atualizarTransacao();
                    break;
                case 4:
                    transacaoCRUD.excluirTransacao();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
