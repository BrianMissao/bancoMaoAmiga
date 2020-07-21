package dominio.entidades;

import dominio.excecoes.ExcecaoDeSaldoInvalido;

import java.math.BigDecimal;

public class Conta {
    private int numeroDaConta;
    private String nomeDoTitular;
    private BigDecimal saldo;
    private BigDecimal limiteDeSaque;

    public Conta(int numeroDaConta, String nomeDoTitular, BigDecimal saldo, BigDecimal limiteDeSaque) {
        if (saldo.compareTo(new BigDecimal(0.00)) < 0) {
            throw new ExcecaoDeSaldoInvalido("Erro: Saldo não pode ser negativo.");
        }
        this.numeroDaConta = numeroDaConta;
        this.nomeDoTitular = nomeDoTitular;
        this.saldo = saldo;
        this.limiteDeSaque = limiteDeSaque;
    }

    @Override
    public String toString() {
        return "Titular: " + nomeDoTitular + "\nNúmero da conta: " + numeroDaConta + "\nSaldo: " + saldo;
    }

    public void atualizarSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimiteDeSaque() {
        return limiteDeSaque;
    }
}
