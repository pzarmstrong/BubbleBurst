package trie;

public class TrieNode 
{
    char letter;
    TrieNode [] links;
    boolean fullWord;
    TrieNode parent;

    public TrieNode(char letter, TrieNode parent)
    {
        this.letter = letter;
        links = new TrieNode[26];
        this.fullWord = false;
    }
}
