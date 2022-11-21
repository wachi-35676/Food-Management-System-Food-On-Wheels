
public class BlankOutputException extends RuntimeException {

	
	BlankOutputException(){
		UserInterface ui = new UserInterface();
		ui.givePrompt("Data sent for promt was empty.");
	}
}
