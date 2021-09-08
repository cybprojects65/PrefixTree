package it.cnr.textprocessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Trie implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	
	public TrieNode root = null;
	
	public void insert(String word) {
		if (root == null)
			root = new TrieNode();
		
	    TrieNode current = root;

	    for (char l: word.toCharArray()) {
	        current = current.children.computeIfAbsent(l, c -> new TrieNode());
	    }
	    current.isEndOfWord=true;
	}
	
	
	public boolean isEmpty() {
	   return ((root == null) || (root.children == null) || (root.children.size() == 0) );
	}
	
	public static Trie createExampleTrie() {
	    Trie trie = new Trie();

	    trie.insert("Programming");
	    trie.insert("is");
	    trie.insert("a");
	    trie.insert("way");
	    trie.insert("of");
	    trie.insert("life");

	    return trie;
	}
	
	public boolean contains(String word) {
	    TrieNode current = root;
	    
	    for (int i = 0; i < word.length(); i++) {
	        char ch = word.charAt(i);
	        TrieNode node = current.children.get(ch);
	        if (node == null) {
	            return false;
	        }
	        current = node;
	    }
	    return current.isEndOfWord;
	}
	
	
	public void delete(String word) {
	    delete(root, word, 0);
	}

	private boolean delete(TrieNode current, String word, int index) {
	    if (index == word.length()) {
	        if (!current.isEndOfWord) {
	            return false;
	        }
	        current.isEndOfWord = false;
	        return current.children.isEmpty();
	    }
	    char ch = word.charAt(index);
	    TrieNode node = current.children.get(ch);
	    if (node == null) {
	        return false;
	    }
	    boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord;

	    if (shouldDeleteCurrentNode) {
	        current.children.remove(ch);
	        return current.children.isEmpty();
	    }
	    return false;
	}
	
	public void saveToFile(File file) {
		 try {
			 
	            FileOutputStream fileOut = new FileOutputStream(file);
	            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	            objectOut.writeObject(this);
	            objectOut.close();
	            System.out.println("The Tree  was succesfully written to a file");
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		
	}
	
	public static void saveToFile(File file, Trie t) {
		 
        try {
 
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(t);
            objectOut.close();
            System.out.println("The Tree  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	
	public static Trie loadFromFile(File file) {
		Trie t = null;
        try {
 
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            t = (Trie) objectIn.readObject();
            objectIn.close();
            System.out.println("The Tree  was succesfully read from the file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return t;
    }
	
}
