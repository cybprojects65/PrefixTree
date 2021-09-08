package it.cnr.textprocessing;

import java.io.Serializable;
import java.util.HashMap;

public class TrieNode implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public HashMap<Character, TrieNode> children = new HashMap<>();
    public boolean isEndOfWord;
    
    
}
