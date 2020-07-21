package dominio;

import dominio.entidades.Conta;
import dominio.excecoes.ExcecaoDeSaldoInvalido;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CaixaEletronico {
    private Conta conta;

    public CaixaEletronico(Conta conta) {
        this.conta = conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void depositarDinheiro(BigDecimal valorADepositar) {
        conta.atualizarSaldo(conta.getSaldo().add(valorADepositar).setScale(2, RoundingMode.HALF_EVEN));
    }

    public void sacarDinheiro(BigDecimal valorASacar) {
        BigDecimal novoSaldo = conta.getSaldo().subtract(valorASacar);
        if (novoSaldo.compareTo(new BigDecimal(0.00)) < 0) {
            throw new ExcecaoDeSaldoInvalido("Este saque não pode ser realizado. Saldo insuficiente para a operação.");
        } else if (valorASacar.compareTo(conta.getLimiteDeSaque()) > 0) {
            throw new ExcecaoDeSaldoInvalido("Este saque não pode ser realizado. O limite de saque é de " + conta.getLimiteDeSaque() + "R$");
        }
        conta.atualizarSaldo(novoSaldo);
    }
}
