package dao;

import model.Produto;

import java.sql.SQLException;
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
        return List.of();
    }

    @Override
    public boolean atualizar(Produto entidade) throws SQLException {
        return false;
    }

    @Override
    public boolean excluir() {
        return false;
    }

    @Override
    public boolean excluir(Long i) throws SQLException {
        return false;
    }
}
