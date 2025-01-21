/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    private static ArrayList<ProdutosDTO> listaDeProdutos = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();
        PreparedStatement pstm = null;
        try {
            // Conexão com o banco
            conectaDAO conecta = new conectaDAO();
            conn = conecta.connectDB();

            // Comando SQL para inserir o produto
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getValor());
            pstm.setString(3, produto.getStatus());

            // Executa o comando
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
        } finally {
            // Fecha as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public static ArrayList<ProdutosDTO> listarProdutos() {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        try {
            // Conexão com o banco
            conectaDAO conecta = new conectaDAO();
            conn = conecta.connectDB();

            // Comando SQL para selecionar produtos
            String sql = "SELECT * FROM produtos";
            pstm = conn.prepareStatement(sql);

            // Executa o comando e armazena o resultado
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            // Fecha as conexões
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return lista;
    }

    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        // Objeto para gerenciar a conexão
        conectaDAO conecta = new conectaDAO();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = conecta.connectDB();

            // Preparar a instrução SQL
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            // Executar o comando de atualização
            int rowsAffected = prep.executeUpdate();

            // Verificar se a operação foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Produto com ID " + id + " foi marcado como 'Vendido'.");
            } else {
                System.out.println("Nenhum produto encontrado com o ID");
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            // Fecha as conexões
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }

    }

    public static ArrayList<ProdutosDTO> listarProdutosVendidos() {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        try {
            // Conexão com o banco
            conectaDAO conecta = new conectaDAO();
            conn = conecta.connectDB();

            // Comando SQL para selecionar produtos
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            pstm = conn.prepareStatement(sql);

            // Executa o comando e armazena o resultado
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            // Fecha as conexões
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return lista;
    }

}
