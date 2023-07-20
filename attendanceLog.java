package application;

import java.io.*;
//import java.util.*;

//import java.io.File;
import java.util.Collection;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class attendanceLog {
	private Collection<String> log = new ArrayList<>();
	
	//default constructor
	public attendanceLog() {
		super();
		this.log = new ArrayList<>();
	}
	
	//overloaded constructor
	public attendanceLog(Collection<String> list) {
		super();
		this.log = list;
	}

	//getter
	public Collection<String> getList() {
		return log;
	}

	//setter
	public void setList(Collection<String> list) {
		this.log = list;
	}

	public void load_log(String filename) {
		//loads the file
		Scanner infile = null;
		
		try {
			infile = new Scanner(new FileReader(filename));
			
			while(infile.hasNextLine()) {
				String line = infile.nextLine();
				log.add(line);
				
			}
			
			infile.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	

	public void print_collection() {
		//loops through the log and prints the collection
		System.out.println("** This is the entire Collection Data currently stored **");
		
		for (String record : log) {
			String [] parts = record.split(", ");
			String firstName = parts[0];
			String lastName = parts[1];
			String time = parts[2];
			String date = parts[3];
			
			System.out.println(firstName + ", " + lastName + "[ " + time + ", " + date + " ]");
		}
	}
	
	public void print_count() {
		//prins the number of students in log
		System.out.println("Number of logs: " + log.size());
	}
}
