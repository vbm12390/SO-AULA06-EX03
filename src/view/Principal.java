package view;
import controller.ControllerBanco;
import java.util.concurrent.Semaphore;

public class Principal {
	public static void main(String[] args) {
		int conta =0;
		int permissoes = 1;
		Semaphore semaphore = new Semaphore(permissoes);
		for(int i=0;i<20;i++) {
			conta++;
			Thread transacao = new ControllerBanco(conta,semaphore);
			transacao.start();
		}
	}

}
