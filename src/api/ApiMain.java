package api;

import dao.ProdutoDao;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;

public class ApiMain {
    public static void main(String[] args) {
        ProdutoDao produtoDao = new ProdutoDao();

        int porta = Integer.parseInt(System.getenv().getOrDefault("API_PORT", "7070"));

        Javalin app = Javalin.create(config -> {
            config.routes.get("/", ctx -> ctx.json(Map.of("aplicacao",
                    "Aula Exemplo API", "status", "online")));

            config.routes.get("/api/produtos", ctx ->executar(ctx, ()->ctx.json(produtoDao.listarTodos())));

            config.routes.get("/api/produtos", ctx -> executar(ctx, ()->ctx.json(produtoDao.buscarPorId(id))));
        }).start(porta);

        System.out.println("API disponível em http://localhost:" + app.port());
    }

    private static void executar(Context ctx, AcaoHttp acao) {
        try{
            acao.executar();
        } catch (SecurityException error){
            ctx.status(401).json(Map.of("erro", error.getMessage()));
        } catch (NoSuchElementException error){
            ctx.status(404).json(Map.of("erro", error.getMessage()));
        } catch (IllegalStateException error){
            ctx.status(409).json(Map.of("erro", error.getMessage()));
        } catch (IllegalArgumentException error){
            ctx.status(400).json(Map.of("erro", error.getMessage()));
        } catch (SQLException error){
            ctx.status(500).json(Map.of("erro", "Falha ao acessar o banco de dados!"));
            error.printStackTrace();
        } catch (Exception error){
            ctx.status(500).json(Map.of("erro", "Erro interno de api"));
            error.printStackTrace();
        }

    }

    @FunctionalInterface
    private interface AcaoHttp {
        void executar() throws Exception;
    }
}
