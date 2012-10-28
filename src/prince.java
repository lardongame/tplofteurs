import view.Display;
import model.Monde;


public class prince {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Monde m = Monde.getMonde();
		m.init(100,100, 50, 120);
		Display d = Display.getInstance();
		
		while(true){
			m.getPopulation().Tour();
			Thread.sleep(100);
		}
	}

}
