package observerPattern;

public interface MyObservable {
	public void addObserver(MyObserver obs);
	public void updateObserver();
}
