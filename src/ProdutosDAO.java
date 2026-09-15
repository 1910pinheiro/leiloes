import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (Exception e) {}
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        listagem.clear();
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO prod = new ProdutosDTO();
                prod.setId(resultset.getInt("id"));
                prod.setNome(resultset.getString("nome"));
                prod.setValor(resultset.getInt("valor"));
                prod.setStatus(resultset.getString("status"));
                
                listagem.add(prod);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (Exception e) {}
        }
        
        return listagem;
    }
    
    // ✅ NOVO MÉTODO — VENDER PRODUTO
    public void venderProduto(int id) {    
        conn = new conectaDAO().connectDB();        
        try {        
            prep = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");        
            prep.setInt(1, id);        
            prep.executeUpdate();                
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");            
        } catch (Exception e) {        
            JOptionPane.showMessageDialog(null, "Erro ao vender: " + e.getMessage());
        } finally {        
            try { conn.close(); } catch (Exception e) {}
        }        
    }
}