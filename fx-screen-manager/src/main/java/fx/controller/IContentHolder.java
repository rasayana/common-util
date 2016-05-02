package fx.controller;

public interface IContentHolder<T> {
	T getContent();
	boolean isLoaded();
	void load();
}
