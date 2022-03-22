package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MainController implements Initializable {

	@FXML
	private MediaView mView;
	
	@FXML
	private Slider volumeSlider;

	private MediaPlayer mp;
	private Media me;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String path = new File("src/media/deewana.mp4").getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);

		mView.setMediaPlayer(mp);
		// mp.setAutoPlay(true);

		/// preserve video ratio
		DoubleProperty width = mView.fitWidthProperty();
		DoubleProperty height = mView.fitHeightProperty();

		width.bind(Bindings.selectDouble(mView.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mView.sceneProperty(), "height"));
		
		
		volumeSlider.setValue(mp.getVolume()*100);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable arg0) {
				mp.setVolume(volumeSlider.getValue()/100);
				
			}
		});

	}

	public void play(ActionEvent event) {
			mp.play();
			mp.setRate(1);
	}

	public void pause(ActionEvent event) {
		mp.pause();
		mp.setRate(1);
	}
	
	public void fast(ActionEvent event) {
		mp.setRate(2);
	}
	
	public void slow(ActionEvent event) {
		mp.setRate(0.5);
	}
	
	public void reload(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.play();
		mp.setRate(1);
	}
	
	public void start(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();
		mp.setRate(1);
	}
	
	public void last(ActionEvent event) {
		mp.seek(mp.getTotalDuration());
		mp.stop();
	}
}
