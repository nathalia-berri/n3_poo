package transacao;

import java.sql.*;
import java.util.Scanner;

public class TransacaoCRUD {
    private ConexaoMySQL conexaoMySQL;

    public TransacaoCRUD() {
        this.conexaoMySQL = new ConexaoMySQL();
    }

    // Cadastrar uma transação
    public void cadastrarTransacao() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Descrição da transação: ");
			String descricao = scanner.nextLine();

			System.out.print("Valor da transação: ");
			double valor = scanner.nextDouble();
			scanner.nextLine(); // Limpar buffer

			System.out.print("Tipo (CREDITO/DEBITO): ");
			String tipo = scanner.nextLine().toUpperCase();

			String codigo = tipo.equals("CREDITO") ? "CRE" : "DEB";
			codigo += System.currentTimeMillis(); // Geração simples de código único

			String sql = "INSERT INTO transacoes (codigo, descricao, valor, tipo) VALUES (?, ?, ?, ?)";

			try (Connection conexao = conexaoMySQL.getConexaoMySQL()) {
			    PreparedStatement stmt = conexao.prepareStatement(sql);
			    stmt.setString(1, codigo);
			    stmt.setString(2, descricao);
			    stmt.setDouble(3, valor);
			    stmt.setString(4, tipo);
			    stmt.executeUpdate();
			    System.out.println("Transação cadastrada com sucesso.");
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
    }

    // Listar todas as transações
    public void listarTransacoes() {
        String sql = "SELECT * FROM transacoes";

        try (Connection conexao = conexaoMySQL.getConexaoMySQL();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String tipo = rs.getString("tipo");

                System.out.println("Código: " + codigo + ", Descrição: " + descricao + ", Valor: " + valor + ", Tipo: " + tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualizar uma transação
    public void atualizarTransacao() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Código da transação para atualizar: ");
			String codigo = scanner.nextLine();

			System.out.print("Novo valor: ");
			double valor = scanner.nextDouble();
			scanner.nextLine(); // Limpar buffer

			String sql = "UPDATE transacoes SET valor = ? WHERE codigo = ?";

			try (Connection conexao = conexaoMySQL.getConexaoMySQL()) {
			    PreparedStatement stmt = conexao.prepareStatement(sql);
			    stmt.setDouble(1, valor);
			    stmt.setString(2, codigo);
			    int rowsUpdated = stmt.executeUpdate();

			    if (rowsUpdated > 0) {
			        System.out.println("Transação atualizada com sucesso.");
			    } else {
			        System.out.println("Transação não encontrada.");
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
    }

    // Excluir uma transação
    public void excluirTransacao() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Código da transação para excluir: ");
			String codigo = scanner.nextLine();

			String sql = "DELETE FROM transacoes WHERE codigo = ?";

			try (Connection conexao = conexaoMySQL.getConexaoMySQL()) {
			    PreparedStatement stmt = conexao.prepareStatement(sql);
			    stmt.setString(1, codigo);
			    int rowsDeleted = stmt.executeUpdate();

			    if (rowsDeleted > 0) {
			        System.out.println("Transação excluída com sucesso.");
			    } else {
			        System.out.println("Transação não encontrada.");
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
    }
}
