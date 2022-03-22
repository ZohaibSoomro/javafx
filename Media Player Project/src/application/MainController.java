package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {
	@FXML
	MediaView mView;
	Media media = new Media(
			new File("C:\\Users\\Zohaib Hassan Soomro\\Desktop\\Kamli_Wale_Remix-NFAK_Feat.A1MelodyMaster(256k).mp3")
					.toURI().toString());
	MediaPlayer mp = new MediaPlayer(media);
	String filePath = "";
	boolean isPaused = true;

	public void playPause() {
		if (isPaused) {
			mp.play();
			isPaused = false;
		} else {
			mp.pause();
			isPaused = true;
		}
	}

	public void quit() {
		Platform.exit();
		System.exit(0);
	}

	public void openFileAction(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose a file");
		chooser.setInitialDirectory(new File("C:\\Users\\Zohaib Hassan Soomro\\Desktop"));
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Music files", "*.mp3", "*.mp4", "*.wav", "*.mkv"));
		File selectedFile = chooser.showOpenDialog(null);
		if (selectedFile != null) {
			filePath = selectedFile.getAbsolutePath();
			mp.dispose();
			media = new Media(new File(filePath).toURI().toString());
			mp = new MediaPlayer(media);
			mp.setVolume(0.2);
			mView.setMediaPlayer(mp);
			DoubleProperty width = mView.fitWidthProperty();
			DoubleProperty height = mView.fitHeightProperty();
			width.bind(Bindings.selectDouble(mView.sceneProperty(), "width"));
			height.bind(Bindings.selectDouble(mView.sceneProperty(), "height"));
			mp.setAutoPlay(true);

		} else {
			JOptionPane.showMessageDialog(null, "Please select a music file.", "Error 404!", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mp.setVolume(0.2);
		mView.setMediaPlayer(mp);
	}

	public void fromStart(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.setRate(1);
		mp.play();

	}
}
