package dominio.excecoes;

public class ExcecaoDeSaldoInvalido extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcecaoDeSaldoInvalido(String message) {
        super(message);
    }
}
