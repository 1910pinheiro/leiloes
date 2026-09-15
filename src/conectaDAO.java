import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public Connection connectDB() {
        Connection conn = null;

        try {
            // ✅ CORRIGIDO: porta 3306 + nome do banco + senha
            conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/LeiloesTDSat?useSSL=false",  // ← NOME DO BANCO
                "root",                                        // ← USUÁRIO
                "351016Mbah."                                         // ← TROCA PELA SUA SENHA!
            );
            
            return conn;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
            return null;
        }
    }
}