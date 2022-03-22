package application;

import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController {

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private ListView<String> listView;

	public void btn1Action(ActionEvent event) {
			FileChooser fChooser=new FileChooser();
			fChooser.setInitialDirectory(new File("C:\\Users\\Zohaib Hassan Soomro\\Desktop"));
			fChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF Files","*.pdf"));
			File selectedFile= fChooser.showOpenDialog(null);
			
			if (selectedFile!= null) {
				listView.getItems().add(selectedFile.getAbsolutePath());
			}
			else {
				System.out.println("File is not valid!");
			}
	}

	public void btn2Action(ActionEvent event) {
		FileChooser fChooser=new FileChooser();
		fChooser.setInitialDirectory(new File("C:\\Users\\Zohaib Hassan Soomro\\Desktop"));
		fChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF Files","*.pdf"));
		List<File> selectedFile= fChooser.showOpenMultipleDialog(null);
		
		if (selectedFile!= null) {
			for (int i = 0; i < selectedFile.size(); i++) {
				listView.getItems().add(selectedFile.get(i).getAbsolutePath());
			}
			
		}
		else {
			System.out.println("File is not valid!");
		}
	}

}
