package application;

import java.io.*;
//import java.util.*;

//import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class studentRoster {
	private Collection<String> roster = new ArrayList<>();
	
	//default constructor
	public studentRoster() {
		super();
		this.roster = new ArrayList<>();
	}
	
	//overloaded constructor
	public studentRoster(Collection<String> list) {
		super();
		this.roster = list;
	}

	//getter
	public Collection<String> getList() {
		return roster;
	}

	//setter
	public void setList(Collection<String> list) {
		this.roster = list;
	}

	public void load_roster(String filename) {
		//loads the file
		Scanner infile = null;
		
		try {
			infile = new Scanner(new FileReader(filename));
			
			while(infile.hasNextLine()) {
				String line = infile.nextLine();
				roster.add(line);
				
			}
			
			infile.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void print_collection() {
		//loops through the roster and prints the collection
		System.out.println("**** Those students on roster ****\n");
		
		for (String record : roster) {
			String [] parts = record.split(", ");
			String firstName = parts[0];
			String lastName = parts[1];
			
			
			System.out.println(firstName + ", " + lastName);
		}
	}
	
	public void print_count() {
		//prins the number of students in roster
		System.out.println("Number of students: " + roster.size());
	}
}
