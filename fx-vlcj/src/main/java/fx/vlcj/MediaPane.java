package fx.vlcj;

import java.nio.ByteBuffer;
import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import com.sun.jna.Memory;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

public class MediaPane extends Pane {
    private ImageView imageView;
    private DirectMediaPlayerComponent mediaPlayerComponent;
    private WritableImage writableImage;
    private WritablePixelFormat<ByteBuffer> pixelFormat;
    private FloatProperty videoSourceRatioProperty;
	public MediaPane() {
		super();
        mediaPlayerComponent = new CanvasPlayerComponent();
        videoSourceRatioProperty = new SimpleFloatProperty(0.4f);
        pixelFormat = PixelFormat.getByteBgraPreInstance();
        initializeImageView();
	}
	public void play(String pathtoFile) {
		setPath(pathtoFile);
		start();
	}
	public void setPath(String path) {
		mediaPlayerComponent.getMediaPlayer().prepareMedia(path);
	}
	public void start() {
		mediaPlayerComponent.getMediaPlayer().start();
	}
	public void stop() {
		mediaPlayerComponent.getMediaPlayer().stop();
	}
    public void initializeImageView() {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        writableImage = new WritableImage((int) visualBounds.getWidth(), (int) visualBounds.getHeight());
        imageView = new ImageView(writableImage);
        getChildren().add(imageView);
        widthProperty().addListener((observable, oldValue, newValue) -> { fitImageViewSize(newValue.floatValue(), (float) getHeight()); });
        heightProperty().addListener((observable, oldValue, newValue) -> { fitImageViewSize((float) getWidth(), newValue.floatValue()); });
        videoSourceRatioProperty.addListener((observable, oldValue, newValue) -> { fitImageViewSize((float) getWidth(), (float) getHeight()); });
    }
    private void fitImageViewSize(float width, float height) {
        Platform.runLater(() -> {
            float fitHeight = videoSourceRatioProperty.get() * width;
            if (fitHeight > height) {
                imageView.setFitHeight(height);
                double fitWidth = height / videoSourceRatioProperty.get();
                imageView.setFitWidth(fitWidth);
                imageView.setX((width - fitWidth) / 2);
                imageView.setY(0);
            }
            else {
                imageView.setFitWidth(width);
                imageView.setFitHeight(fitHeight);
                imageView.setY((height - fitHeight) / 2);
                imageView.setX(0);
            }
        });
    }
    private class CanvasPlayerComponent extends DirectMediaPlayerComponent {
        public CanvasPlayerComponent() {
            super(new CanvasBufferFormatCallback());
        }
        PixelWriter pixelWriter = null;
        private PixelWriter getPW() {
            if (pixelWriter == null) {
                pixelWriter = writableImage.getPixelWriter();
            }
            return pixelWriter;
        }
        @Override
        public void display(DirectMediaPlayer mediaPlayer, Memory[] nativeBuffers, BufferFormat bufferFormat) {
            if (writableImage == null)
                return;
            Platform.runLater(() -> {
                Memory nativeBuffer = mediaPlayer.lock()[0];
                try {
                    ByteBuffer byteBuffer = nativeBuffer.getByteBuffer(0, nativeBuffer.size());
                    getPW().setPixels(0, 0, bufferFormat.getWidth(), bufferFormat.getHeight(), pixelFormat, byteBuffer, bufferFormat.getPitches()[0]);
                } finally {
                    mediaPlayer.unlock();
                }
            });
        }
    }
    private class CanvasBufferFormatCallback implements BufferFormatCallback {
        @Override
        public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            Platform.runLater(() -> videoSourceRatioProperty.set((float) sourceHeight / (float) sourceWidth));
            return new RV32BufferFormat((int) visualBounds.getWidth(), (int) visualBounds.getHeight());
        }
    }
}