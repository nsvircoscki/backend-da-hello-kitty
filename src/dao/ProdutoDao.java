package dao;

import com.mysql.cj.protocol.Resultset;
import config.ConnectionFactory;
import model.Produto;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDao implements CrudDao<Produto, Long> {

    @Override
    public Produto criar(Produto entidade) throws SQLException {
        return null;
    }

    @Override
    public Optional<Produto> buscarPorId(Long i) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Produto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM produto ORDER BY id";

        List<Produto> produtoLista = new ArrayList<>();

        try(
                Connection conexao = ConnectionFactory.abrirConexao();
                PreparedStatement comando = conexao.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery()
        ) {
            while(resultado.next()){
                produtoLista.add(mapear(resultado));
            }

        }
        return produtoLista;
    }


    @Override
    public boolean atualizar(Produto produto) throws SQLException {
        return false;
    }


    @Override
    public boolean excluir(Long i) throws SQLException {
        return false;
    }


    private Produto mapear(ResultSet resultado) throws SQLException{
        return new Produto(
                resultado.getLong("id"),
                resultado.getString("nome"),
                resultado.getDouble("preco"),
                resultado.getInt("quantidade")
        );
    }
}
