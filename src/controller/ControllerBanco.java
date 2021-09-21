package controller;

import java.util.concurrent.Semaphore;

public class ControllerBanco extends Thread {
	private int conta;
	private Semaphore semaphore;


	public ControllerBanco(int conta, Semaphore semaphore) {
		this.conta = conta;
		this.semaphore = semaphore;
	}

	public void run() {
		int saldo = 0;
		int valor = 0;
		saldo = gerarsaldo(conta);
		valor = valortransacao(valor);
		fila(conta, saldo, valor);
	}

	private int valortransacao(int valor) {
		valor = (int) ((Math.random() * 1000) + -500);
		return valor;
	}

	private int gerarsaldo(int conta) {
		int saldo = (int) ((Math.random() * 1000) + 1);
		return saldo;
	}

	private void fila(int conta, int saldo, int valor) {
		if (valor >= 0) {
			try {
				System.out.println("Conta " + conta + " Está na fila para Deposito");
				semaphore.acquire();
				transacao(conta, saldo, valor);
			} catch (Exception error) {
				error.printStackTrace();
			} finally {
				semaphore.release();
			}
		} else {
			try {
				System.out.println("Conta " + conta + " Está na fila para Saque");
				semaphore.acquire();
				transacao(conta, saldo, valor);
			} catch (Exception error) {
				error.printStackTrace();
			} finally {
				semaphore.release();
			}
		}

	}

	private void transacao(int conta, int saldo, int valor) {
		int novo_saldo = valor+saldo; 
		System.out.println("Conta " + conta + " Iniciou Transacao");
		if (valor >= 0) {
			System.err.println(" Conta: " + conta + " Depositou " + valor + " Saldo ==> " + novo_saldo );
		}else {
			System.err.println(" Conta: " + conta + " Sacou " + valor + " Saldo ==> " + novo_saldo );
		}
		System.out.println("Conta " + conta + " Finalizou Transacao");
	}

}
