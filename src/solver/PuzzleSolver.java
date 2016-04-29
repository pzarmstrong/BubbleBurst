package solver;

import java.util.ArrayList;
import java.util.Hashtable;

import puzzle.*;
import trie.PrefixTree;


public class PuzzleSolver
{
    Puzzle currentPuzzle;
    PrefixTree dict = new PrefixTree();
    ArrayList<String> wordsFound = new ArrayList<String>();
    String currentWord;
    
    public PuzzleSolver(String puzzleName)
    {
        this.currentPuzzle = new Puzzle(puzzleName);
        currentPuzzle.run();
    }
    
    public Puzzle getCurrentPuzzle()
    {
        return currentPuzzle;
    }
    
    public static void main(String[] args)
    {
        PuzzleSolver pSolv = new PuzzleSolver("data/19_357.txt");
    }
}
