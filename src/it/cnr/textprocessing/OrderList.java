package it.cnr.textprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OrderList {

	
	public static void main(String[] args) throws Exception {
		File taxafile = new File("taxsons.csv");
		OrderList o = new OrderList();
		o.order(taxafile);
	}
	
	public void order ( File fileList) throws Exception {
		List<String> orderedList = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(fileList));
		String line = br.readLine();
		long counter=0;
		
		while(line!=null) {
			if (counter%10000==0)
				System.out.println("Status "+counter+" entries");
			addToList(line,orderedList);
			line = br.readLine();
			counter++;
		}
		
		br.close();
		System.out.println("Ordered list length "+orderedList.size());
	}
	
	public void addToList(String line,List<String> orderedList) {
	
		int n = line.length();
		int i = 0;
		int found = orderedList.size();
		for (String el:orderedList) {
			if (el.length()>n) {
				found = i;
			}
			i++;
		}
		
		orderedList.add(found, line);
	}
}
