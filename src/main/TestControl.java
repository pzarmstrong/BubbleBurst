package main;

import trie.*;
import anagram.Anagram;
import genio.*;
import puzzle.*;
import solver.PuzzleSolver;

public class TestControl
{
    public static void main(String [] args)
    {
        TestControl.testMenu();
    }
    
    public static void testMenu()
    {
        TestControl con = new TestControl();
        Puzzle puz;
        
        boolean exit = false;
        int op;
        while(!exit)
        {
            System.out.print("menu > ");
            op = Genio.getInteger();
            
            if (op == 0)
            {
                System.out.println("exit");
                exit = true;
            }
            else if (op == 1)
            {
                con.anagramSearch();
            }
            else if (op == 2)
            {
                con.wordSearch();
            }
            else if (op == 3)
            {
                con.branchSearch();
            }
            else if (op == 4)
            {
             con.prefixSearch();
            }
        }
    }
    
    public void branchSearch()
    {
        PrefixTree dict = new PrefixTree();
        
        String word;
        boolean exit = false;
        while (!exit)
        {
            System.out.print("menu > branchSearch > ");
            word = Genio.getString();
            word = word.replaceAll("[^a-zA-Z ]", "").replaceAll("\\s+", "").toLowerCase();
            char [] cWord = word.toCharArray();
            
            if (word.equals("0") || word.isEmpty())
            {
                System.out.println("exit");
                exit = true;
            }
            else if (dict.checkIsPrefix(word))
            {
                char [] branch = new char[100];
                dict.printTree(dict.getWordNode(word), 0, branch, word);
                System.out.println("words found: " + dict.wordsFoundCount);
                dict.wordsFoundCount = 0;
            }
            else
            {
                System.out.println("word \"" + word + "\" not found");
            }
        } 
    }
    
    public void prefixSearch()
    {
        PrefixTree dict = new PrefixTree();
        
        String word;
        boolean exit = false;
        while (!exit)
        {
            System.out.print("menu > wordSearch > ");
            word = Genio.getString();
            word = word.replaceAll("[^a-zA-Z ]", "").replaceAll("\\s+", "").toLowerCase();
            char [] cWord = word.toCharArray();
            
            if (word.equals("0") || word.isEmpty())
            {
                System.out.println("exit");
                exit = true;
            }
            else if (dict.checkIsPrefix(word))
            {
                System.out.println("prefix \"" + word + "\" found");
            }
            else
            {
                System.out.println("prefix \"" + word + "\" not found");
            }
        }
    }
    
    public void wordSearch()
    {
        PrefixTree dict = new PrefixTree();
        
        String word;
        boolean exit = false;
        while (!exit)
        {
            System.out.print("menu > wordSearch > ");
            word = Genio.getString();
            word = word.replaceAll("[^a-zA-Z ]", "").replaceAll("\\s+", "").toLowerCase();
            char [] cWord = word.toCharArray();
            
            if (word.equals("0") || word.isEmpty())
            {
                System.out.println("exit");
                exit = true;
            }
            else if (dict.checkIsWord(word))
            {
                System.out.println("word \"" + word + "\" found");
            }
            else
            {
                System.out.println("word \"" + word + "\" not found");
            }
        }
    }
    
    public void anagramSearch()
    {
        PrefixTree dict = new PrefixTree();
        
        boolean exit = false;
        
        while (!exit)
        {
            System.out.print("menu > anagramSearch > ");
            
            String anagram = Genio.getString();
            
            if (anagram.equals("0") || anagram.isEmpty())
            {
                exit = true;
                System.out.println("exit");
            }
            else
            {
                anagram = anagram.replaceAll("[^a-zA-Z ]", "").replaceAll("\\s+", "").toLowerCase();
                char[] cAnagram = anagram.toCharArray();
                Anagram.setDict(dict);
                Anagram.setCharArray(cAnagram);
                Anagram.initAnagram();
            }
        }
    }
}
