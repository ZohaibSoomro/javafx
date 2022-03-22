package application;

import java.io.File;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController {
	@FXML
	private Button single;

	@FXML
	private Button multi;

	@FXML
	private ListView<String> listView;

	public void singleBtn(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File("C:\\Users\\Zohaib Hassan Soomro\\Desktop"));
		chooser.getExtensionFilters().addAll(new ExtensionFilter("PPT Files", "*.pptx"));

		File selectedFile = chooser.showOpenDialog(null);
		if (selectedFile != null) {
			String fileSelected = selectedFile.getAbsolutePath();
			listView.getItems().add(fileSelected);
		} else {
			JOptionPane.showMessageDialog(null, "File is not valid!");
		}

	}

	public void multiBtn(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File("C:\\Users\\Zohaib Hassan Soomro\\Desktop"));
		chooser.getExtensionFilters().addAll(new ExtensionFilter("PPT Files", "*.pptx"));

		List<File> selectedFiles = chooser.showOpenMultipleDialog(null);

		if (selectedFiles != null) {
			for (int i = 0; i < selectedFiles.size(); i++) {
				listView.getItems().add(selectedFiles.get(i).getAbsolutePath());
			}
		}
	}

}
