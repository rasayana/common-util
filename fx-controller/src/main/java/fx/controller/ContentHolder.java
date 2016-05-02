package fx.controller;

public class ContentHolder<T> implements IContentHolder<T> {
	private T content = null;
	protected final void setContent(T value) {
		this.content = value;
	}
	public ContentHolder(T content) {
		setContent(content);
	}
	@Override
	public final T getContent() {
		if (!isLoaded())
			load();
		return content;
	}
	@Override
	public final boolean isLoaded() {
		return content != null;
	}
	@Override
	public void load() {
	}
}