package trie;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrefixTree 
{
    public TrieNode root;
    Path dictFile = Paths.get("data/eng_dict.txt");
    
    public int wordsFoundCount;
    
    public PrefixTree() 
    {
        this.root = new TrieNode('\0', null);
        this.createTreeFromFile();
    }
    
    public void printTree(TrieNode root, int level, char[] branch, String word)
    {
        if (root == null)
        {
            return;
        }

        for (int i = 0; i < root.links.length; i++)
        {
            branch[level] = root.letter;
            printTree(root.links[i], level + 1, branch, word);
        }

        if (root.fullWord)
        {
            System.out.print(word);
            wordsFoundCount++;
            for (int j = 1; j <= level; j++)
            {
                System.out.print(branch[j]);
            }
            System.out.println();
        }
    }

    
    public boolean checkIsWord(String input)
    {
        char[] letters = input.toCharArray();
        int offset = 97;
        TrieNode currentNode = root;
        
        int i;
        for (i = 0; i < letters.length; i++)
        {
            if (currentNode == null)
            {
                return false;
            }
            currentNode = currentNode.links[letters[i] - offset];
        }
        
        if (i == 1 && currentNode == null)
        {
            return false;
        }
        
        if (currentNode != null && !currentNode.fullWord)
        {
            return false;
        }
        
        if (currentNode == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean checkIsPrefix(String input)
    {
        char[] letters = input.toCharArray();
        int offset = 97;
        TrieNode currentNode = root;
        
        int i;
        for (i = 0; i < letters.length; i++)
        {
            if (currentNode == null)
            {
                return false;
            }
            currentNode = currentNode.links[letters[i] - offset];
        }
        
        if (i == 1 && currentNode == null)
        {
            return false;
        }
        
        if (currentNode != null && !currentNode.fullWord)
        {
            return true;    // returns true for prefix
        }
        
        if (currentNode == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public TrieNode getWordNode(String input)
    {
        char[] word = input.toCharArray();
        int offset = 97;
        TrieNode currentNode = root;
        
        int i;
        for (i = 0; i < word.length; i++)
        {
            currentNode = currentNode.links[word[i] - offset];
        }
        
        return currentNode;
    }
    
    public void insertWord(String word)
    {
        int offset = 97;
        char[] letters = word.toCharArray();
        
        TrieNode currentNode = root;
        TrieNode previousNode;
        
        for (int i = 0; i < letters.length; i++)
        {
            if (currentNode.links[letters[i] - offset] == null)
            {
                previousNode = currentNode;
                currentNode.links[letters[i] - offset] = new TrieNode(letters[i], previousNode);
            }
            currentNode = currentNode.links[letters[i] - offset];
        }
        currentNode.fullWord = true;
    }
    
    public void createTreeFromFile()
    {
        try (BufferedReader reader = Files.newBufferedReader(dictFile, StandardCharsets.UTF_8))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                insertWord(line);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }    
}
