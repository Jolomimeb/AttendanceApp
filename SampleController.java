package application;
import java.util.ArrayList;
import java.util.Collection;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import javafx.stage.FileChooser;
import java.io.PrintStream;


public class SampleController {
	@FXML
	private TextArea outputTextArea;
	
	//calls buttons
	@FXML
	private Button A;
	@FXML
	private Button B;
	@FXML
	private Button C;
	@FXML
	private Button D;
	@FXML
	private Button E;
	@FXML
	private Button F;
	@FXML
	private Button G;
	@FXML
	private Button H;
	@FXML
	private Button I;
	@FXML
	private Button J;
	@FXML
	private Button K;
	@FXML
	private Button L;
	@FXML
	private Button M;
	@FXML
	private Button N;
	@FXML
	private Button O;
	@FXML
	private Button P;
	@FXML
	private Button Q;
	@FXML
	private Button R;
	@FXML
	private Button S;
	
	//helper variables
	public boolean isClickedLog = false;
	public boolean isClickedRoster = false;
	
	public boolean isClickedLoadLog = false;
	public boolean isClickedRosterLoadRoster = false;
	
    
    String tempLogFile;
	String tempRosterFile;
	//creates log
    Collection<String> dataSwipe = new ArrayList<>();
    attendanceLog list_tempLog = new attendanceLog(dataSwipe);
    //creates roster
    Collection<String> curr_roster = new ArrayList<>();
    studentRoster list_tempRoster2 = new studentRoster(curr_roster);
   
    //creates attendance app with the roster and file
    attendanceApp tempApp = new attendanceApp(tempRosterFile, tempLogFile);
    
    public void initialize() {
	    // Create a custom PrintStream that writes to the TextArea
	    PrintStream out = new PrintStream(new TextAreaOutputStream(outputTextArea));

	    // Redirect System.out to the custom PrintStream
	    System.setOut(out);
	    
	    //sets the button to false until the user selects Roster and swipe files
	    A.setDisable(true);
	    B.setDisable(true);
	    C.setDisable(true);
	    D.setDisable(true);
	    E.setDisable(true);
	    F.setDisable(true);
	    G.setDisable(true);
	    H.setDisable(true);
	    I.setDisable(true);
	    J.setDisable(true);
	    K.setDisable(true);
	    L.setDisable(true);
	    M.setDisable(true);
	    N.setDisable(true);
	    O.setDisable(true);
	    P.setDisable(true);
	    Q.setDisable(true);
	    R.setDisable(true);
	    S.setDisable(true);
	    
	   
	}
    
    public void load_log(ActionEvent e) {
    	//helps prevent user from loading same log twice
    	if (!list_tempLog.getList().isEmpty()) {
    		list_tempLog.setList(new ArrayList<>());
    	}
    	//loads the log
    	list_tempLog.load_log(tempLogFile);
    	
		isClickedLoadLog = true;
		if (isClickedRosterLoadRoster && isClickedLoadLog) {
			//turns on the buttons
		    B.setDisable(false);
		    C.setDisable(false);
		    E.setDisable(false);
		    F.setDisable(false);
		    G.setDisable(false);
		    H.setDisable(false);
		    I.setDisable(false);
		    J.setDisable(false);
		    K.setDisable(false);
		    L.setDisable(false);
		    M.setDisable(false);
		    N.setDisable(false);
		    O.setDisable(false);
		    P.setDisable(false);
		    Q.setDisable(false);
		    R.setDisable(false);
		    S.setDisable(false);
		}
	}
	
	public void print_collection(ActionEvent e) {
		//prints the collection
		list_tempLog.print_collection();
	}
	
	public void print_count(ActionEvent e) {
		//prints the count
		list_tempLog.print_count();
	}
	
	public void load_log_Roster(ActionEvent e) {
		//helps prevent user from loading same roster twice
		if (!list_tempRoster2.getList().isEmpty()) {
			list_tempRoster2.setList(new ArrayList<>());
		}
		//loads the roster
		list_tempRoster2.load_roster(tempRosterFile);
		
		
		isClickedRosterLoadRoster = true;
		if (isClickedRosterLoadRoster && isClickedLoadLog) {
			//turns on the buttons
		    B.setDisable(false);
		    C.setDisable(false);
		    E.setDisable(false);
		    F.setDisable(false);
		    G.setDisable(false);
		    H.setDisable(false);
		    I.setDisable(false);
		    J.setDisable(false);
		    K.setDisable(false);
		    L.setDisable(false);
		    M.setDisable(false);
		    N.setDisable(false);
		    O.setDisable(false);
		    P.setDisable(false);
		    Q.setDisable(false);
		    R.setDisable(false);
		    S.setDisable(false);
		}
	}
	
	public void print_collection_Roster(ActionEvent e) {
		//prints collection
		list_tempRoster2.print_collection();
	}
	
	public void print_count_Roster(ActionEvent e) {
		//prints count
		list_tempRoster2.print_count();
	}
	
	
	public void list_students_not_in_class(ActionEvent e) {
		//lists the students not in class		
		Collection<String> final_list = tempApp.list_students_not_in_class(curr_roster, dataSwipe);
		System.out.println("****** Students missing in class *************");
		for (String students : final_list) {
			System.out.println(students);
		}
		
	}
	
	public void list_all_times_checking_in_and_out(ActionEvent e) {
		tempApp.list_all_times_checking_in_and_out(dataSwipe, "Ince Ryan");	
	}
	
	public void list_all_times_checked_in(ActionEvent e) {
		tempApp.list_all_times_checked_in(dataSwipe);
	}
	
	public void list_students_late_to_class(ActionEvent e) {
		tempApp.list_students_late_to_class(dataSwipe, "09:45:00");
	}
	
	public void get_first_student_to_enter(ActionEvent e) {
		tempApp.get_first_student_to_enter(dataSwipe);
	}
	
	public void print_attendance_data_for_student(ActionEvent e) {
		tempApp.print_attendance_data_for_student(dataSwipe, "Gibson Yvonne");
	}
	
	public void is_present(ActionEvent e) {
		System.out.println(tempApp.is_present(dataSwipe, "Wright Rebecca", "11/2/2022"));
	}
	
	public void list_all_students_checked_in(ActionEvent e) {
		tempApp.list_all_students_checked_in(dataSwipe, "11/2/2022");
	}
	
	public void list_all_students_checked_in_before(ActionEvent e) {
		tempApp.list_all_students_checked_in_before(dataSwipe, "11/2/2022", "09:45:02");
	}

	public void list_students_attendance_count(ActionEvent e) {
		tempApp.list_students_attendance_count(dataSwipe, 2);
	}
	
	public void print_query_list(ActionEvent e) {
		tempApp.print_query_list(dataSwipe);
	}
	
	public void print_count_attendanceApp(ActionEvent e) {
		
		tempApp.print_count(dataSwipe);
	}
	
	public void exitAction(ActionEvent e) {
		Platform.exit();
	}
	
	
	@FXML
	private void handleLoadFileButtonClick(ActionEvent e) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open Resource File");
	    File selectedFile = fileChooser.showOpenDialog(null);
	    if (selectedFile != null) {
	        System.out.println("Selected swipe Data file: " + selectedFile);
	        //stores the file
	        tempLogFile = selectedFile.toString();
	    	isClickedLog = true;
	        
	    }
	    if (isClickedLog && isClickedRoster) {
	    	A.setDisable(false);
	        D.setDisable(false);
	    }
	}
	
	@FXML
	private void handleLoadFileButtonClick2(ActionEvent e) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open Resource File");
	    File selectedFile = fileChooser.showOpenDialog(null);
	    if (selectedFile != null) {
	        System.out.println("Selected roster Data file: " + selectedFile);
	        //stores the file
	        tempRosterFile = selectedFile.toString();
	        isClickedRoster = true;
	        
	    }
	    if (isClickedLog && isClickedRoster) {
	    	A.setDisable(false);
	        D.setDisable(false);
	    }
	}

}
