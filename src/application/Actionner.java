package application;

public class Actionner {

	private Main main;

	public static final int REFRESH = 0;
	public static final int SETTINGS = 1;

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
		}
	}
}
