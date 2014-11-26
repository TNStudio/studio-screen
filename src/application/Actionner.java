package application;

import java.text.BreakIterator;

public class Actionner {

	private Main main;

	public static final int REFRESH = 0;
	public static final int SETTINGS = 1;
	public static final int VALIDATE_PRINT = 2;


	public Actionner(Main main){
		this.main = main;
	}

	public void doIt(int toDo){
		switch (toDo) {
		case REFRESH:
			main.getMainInterfaceController().update();
			break;

		case SETTINGS:
			main.loadInterface(main.getSettingsInterfaceController(), main.getSceneSettings());
			break;

		case VALIDATE_PRINT:
			int indice = -1;
			for(int i=0;i<main.getPrintRequest().size();i++){
				if(!main.getPrintRequest().get(i).getWorkDone().getValue()){
					indice = i;
					break;
				}
			}
			try {
				main.getPrintRequest().get(indice).getWorkDone().set(true);
			} catch (Exception e) {
				//do nothing
			}
			break;
		}
	}

}
