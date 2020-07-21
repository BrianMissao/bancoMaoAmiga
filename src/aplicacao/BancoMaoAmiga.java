package aplicacao;

import dominio.CaixaEletronico;
import dominio.entidades.Conta;
import dominio.excecoes.ExcecaoDeSaldoInvalido;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class BancoMaoAmiga {
    private static int numeroDaConta;
    private static String nomeDoTitular;
    private static BigDecimal saldoInicial;
    private static BigDecimal limiteDeSaque;
    private static Conta conta;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seja bem-vindo ao BMA (Banco Mão Amiga!\nEntre com os dados da conta:");
        obterDadosDaContaASerCriada();
        criarConta(numeroDaConta, nomeDoTitular, saldoInicial, limiteDeSaque);
        BigDecimal valorASacar = new BigDecimal(obterDados("Já vai sacar? Poxa...\nDigite o valor para saque:"));
        sacarDinheiro(valorASacar);
        BigDecimal valorADepositar = new BigDecimal(obterDados("Digite o valor de depósito:")).setScale(2, RoundingMode.HALF_EVEN);
        depositarDinheiro(valorADepositar);
    }

    private static void obterDadosDaContaASerCriada() {
        numeroDaConta = Integer.parseInt(obterDados("Número:"));
        nomeDoTitular = obterDados("Nome do titular:");
        saldoInicial = new BigDecimal(obterDados("Saldo inicial:")).setScale(2, RoundingMode.HALF_EVEN);
        limiteDeSaque = new BigDecimal(obterDados("Qual o limite de saque?")).setScale(2, RoundingMode.HALF_EVEN);
    }

    private static void criarConta(int numeroDaConta, String nomeDoTitular, BigDecimal saldoInicial, BigDecimal limiteDeSaque) {
        try {
            conta = new Conta(numeroDaConta, nomeDoTitular, saldoInicial, limiteDeSaque);
            System.out.println("Conta criada com sucesso!");
            System.out.println(conta);
        } catch (ExcecaoDeSaldoInvalido e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sacarDinheiro(BigDecimal valorASacar) {
        try {
            new CaixaEletronico(conta).sacarDinheiro(valorASacar);
            System.out.println(conta);
        } catch (ExcecaoDeSaldoInvalido e) {
            System.out.println(e.getMessage());
        }
    }

    private static void depositarDinheiro(BigDecimal valorADepositar) {
        new CaixaEletronico(conta).depositarDinheiro(valorADepositar);
        System.out.println(conta);
    }

    private static String obterDados(String mensagemAoUsuario) {
        System.out.println(mensagemAoUsuario);
        return scanner.nextLine();
    }
}
