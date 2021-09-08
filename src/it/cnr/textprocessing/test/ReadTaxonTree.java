package it.cnr.textprocessing.test;

import java.io.File;

import it.cnr.textprocessing.Trie;

public class ReadTaxonTree {

	public static void main(String[] args) throws Exception{
	
		System.out.println("Reading");
		
		long t0 = System.currentTimeMillis();
		
		File taxonTree = new File("taxatree_0.bin");
		
		Trie t = Trie.loadFromFile(taxonTree);
		
		long t1 = System.currentTimeMillis();
		System.out.println("Read in "+(t0-t1)+"ms");
		
		t0 = System.currentTimeMillis();
		
		System.out.println("check "+t.contains("anemone"));				
		t1 = System.currentTimeMillis();
		System.out.println("Efficiency "+(t0-t1)+"ms");
		
	}
}
