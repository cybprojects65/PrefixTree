package it.cnr.textprocessing.test;

import java.io.File;

import it.cnr.textprocessing.Trie;

public class Main {

	public static void main(String[] args) throws Exception{
		
		Trie t = Trie.createExampleTrie();
		String test = "Programming";
		
		System.out.println("Check 1: "+t.contains(test));
		t.delete(test);
		
		System.out.println("Check 2: "+t.contains(test));
	
		File testFile = new File("tree.bin");
		t.saveToFile(testFile);
		Thread.sleep(1000);
		
		t = Trie.loadFromFile(testFile);
		
		String test2 = "is";
		
		System.out.println("Check 3: "+t.contains(test2));
		
	}
	
	
}
