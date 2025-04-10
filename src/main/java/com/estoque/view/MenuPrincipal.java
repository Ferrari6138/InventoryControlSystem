package com.estoque.view;

import com.estoque.dao.ProdutoDAO;
import com.estoque.model.Produto;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();

        while (true) {
            System.out.println("\n=== CONTROLE DE ESTOQUE ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        cadastrarProduto(scanner, produtoDAO);
                        break;

                    case 2:
                        listarProdutos(produtoDAO);
                        break;

                    case 3:
                        atualizarProduto(scanner, produtoDAO);
                        break;

                    case 4:
                        excluirProduto(scanner, produtoDAO);
                        break;

                    case 5:
                        System.out.println("Saindo do sistema...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número correspondente à opção.");
                scanner.nextLine(); // Limpar buffer inválido
            }
        }
    }

    private static void cadastrarProduto(Scanner scanner, ProdutoDAO produtoDAO) {
        System.out.println("\n=== CADASTRAR NOVO PRODUTO ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        double preco = 0;
        int quantidade = 0;

        try {
            System.out.print("Preço: ");
            preco = scanner.nextDouble();

            System.out.print("Quantidade: ");
            quantidade = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            Produto novoProduto = new Produto(nome, descricao, preco, quantidade);
            produtoDAO.create(novoProduto);
            System.out.println("\nProduto cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Valor inválido! Use números para preço e quantidade.");
            scanner.nextLine(); // Limpar buffer inválido
        }
    }

    private static void listarProdutos(ProdutoDAO produtoDAO) {
        System.out.println("\n=== LISTA DE PRODUTOS ===");
        List<Produto> produtos = produtoDAO.readAll();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("ID  | Nome                | Preço   | Quant.");
            System.out.println("--------------------------------------------");
            for (Produto p : produtos) {
                System.out.printf("%-3d | %-20s | R$%-6.2f | %-5d%n",
                        p.getId(), p.getNome(), p.getPreco(), p.getQuantidade());
            }
        }
    }

    private static void atualizarProduto(Scanner scanner, ProdutoDAO produtoDAO) {
        System.out.println("\n=== ATUALIZAR PRODUTO ===");
        listarProdutos(produtoDAO);

        System.out.print("\nDigite o ID do produto a atualizar (0 para cancelar): ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (id == 0) return;

            Produto produto = produtoDAO.buscarPorId(id);

            if (produto == null) {
                System.out.println("Produto não encontrado!");
                return;
            }

            System.out.println("\nDados atuais:");
            System.out.println(produto);

            System.out.println("\nDigite os novos dados (deixe em branco para manter o valor atual):");

            System.out.print("Nome [" + produto.getNome() + "]: ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) produto.setNome(nome);

            System.out.print("Descrição [" + produto.getDescricao() + "]: ");
            String descricao = scanner.nextLine();
            if (!descricao.isEmpty()) produto.setDescricao(descricao);

            System.out.print("Preço [" + produto.getPreco() + "]: ");
            String precoStr = scanner.nextLine();
            if (!precoStr.isEmpty()) {
                try {
                    produto.setPreco(Double.parseDouble(precoStr));
                } catch (NumberFormatException e) {
                    System.out.println("Formato de preço inválido. Mantendo valor anterior.");
                }
            }

            System.out.print("Quantidade [" + produto.getQuantidade() + "]: ");
            String qtdStr = scanner.nextLine();
            if (!qtdStr.isEmpty()) {
                try {
                    produto.setQuantidade(Integer.parseInt(qtdStr));
                } catch (NumberFormatException e) {
                    System.out.println("Quantidade inválida. Mantendo valor anterior.");
                }
            }

            System.out.print("\nConfirmar atualização? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                boolean sucesso = produtoDAO.update(produto);
                if (sucesso) {
                    System.out.println("Produto atualizado com sucesso!");
                } else {
                    System.out.println("Falha ao atualizar produto.");
                }
            } else {
                System.out.println("Atualização cancelada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido! Digite um número.");
            scanner.nextLine(); // Limpar buffer inválido
        }
    }

    private static void excluirProduto(Scanner scanner, ProdutoDAO produtoDAO) {
        System.out.println("\n=== EXCLUIR PRODUTO ===");
        listarProdutos(produtoDAO);

        System.out.print("\nDigite o ID do produto a excluir (0 para cancelar): ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (id == 0) return;

            System.out.print("Tem certeza que deseja excluir? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                boolean sucesso = produtoDAO.delete(id);
                if (sucesso) {
                    System.out.println("Produto excluído com sucesso!");
                } else {
                    System.out.println("Falha ao excluir produto ou produto não encontrado.");
                }
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido! Digite um número.");
            scanner.nextLine(); // Limpar buffer inválido
        }
    }
}