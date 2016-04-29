package puzzle;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import trie.PrefixTree;
import puzzle.Compass;

public class Puzzle
{
    private Path puzzleFile;
    PrefixTree dict = new PrefixTree();
    private ArrayList<Character> puzzleBase = new ArrayList<Character>();
    
    private PuzzleNode[][] puzzleArray;
    
    private int width;
    private int height;
    
    public Puzzle(String puzzleFileName)
    {
        this.puzzleFile = Paths.get(puzzleFileName);
        createPuzzleFromFile();
    }
    
    public void run()
    {
        PuzzleNode node = getFirstLetterNode('i');
        this.startNode = node;
        solveForNode(node, new String());
        
        printPuzzle();
        System.out.println(results);
    }
    
    ArrayList<String> results = new ArrayList<String>();
    PuzzleNode startNode;
    int solvecount = 0;
    
    /**
     * TODO SOLVE currently jumps nodes, need to catch where and when the program is trying to do
     * this. So/too much nesting! 
     * @param node
     * @param currentWord
     */
    public void solveForNode(PuzzleNode node, String currentWord)
    {
        solvecount++;
        if (currentWord.equals("")) 
        {
            currentWord = Character.toString(node.getLetter());
        }
        int y = node.getY(); 
        int x = node.getX();
        
        node.setUsed(true);
        
        String tempString;
        PuzzleNode tempNode;
        
        for (Compass d : Compass.values())
        {
            switch (d)
            {
                case NORTH:
                {
                    tempNode = getArrayNode(y-1, x);
                    if (tempNode != null && !tempNode.isUsed())
                    {
                        currentWord += tempNode.getLetter();
                        if (dict.checkIsPrefix(currentWord))
                        {
                            if (dict.checkIsWord(currentWord))
                            {
                                results.add(currentWord);
                            }
                            solveForNode(tempNode, currentWord);
                        }
                        else 
                        {
                            currentWord = "";
                        }
                        continue;
                    }
                    else
                    {
                        break;
                    }
                }
                case SOUTH:
                {
                    tempNode = getArrayNode(y+1, x);
                    if (tempNode != null && !tempNode.isUsed())
                    {
                        currentWord += tempNode.getLetter();
                        if (dict.checkIsPrefix(currentWord))
                        {
                            if (dict.checkIsWord(currentWord))
                            {
                                results.add(currentWord);
                            }
                            solveForNode(tempNode, currentWord);
                        }
                        else 
                        {
                            currentWord = "";
                        }
                        continue;
                    }
                    else
                    {
                        break;
                    }
                }
                default:
                {
                    break;
                }
            }
        }
    }
    
    public PuzzleNode getFirstLetterNode(char letter)
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (puzzleArray[y][x].getLetter() == letter && !puzzleArray[y][x].isUsed())
                {
                    return puzzleArray[y][x];
                }
                else
                {
                    continue;
                }
            }
        }
        return null;
    }
    
    private void createPuzzleFromFile()
    {
        try (BufferedReader reader = Files.newBufferedReader(puzzleFile, StandardCharsets.UTF_8))
        {
            int r;
            width = Integer.parseInt(reader.readLine());
            height = Integer.parseInt(reader.readLine());
            
            while ((r = reader.read()) != -1)
            {
                if (r == '\r' || r == '\n')
                {
                    continue;
                }
                else
                {
                    puzzleBase.add((char) r);
                }
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        puzzleArray = new PuzzleNode[height][width];
        //letterUsed = new boolean[height][width];
        int count = 0;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                puzzleArray[y][x] = new PuzzleNode(y, x, puzzleBase.get(count));
                if (puzzleBase.get(count) == '_')
                {
                    puzzleArray[y][x].setUsed(true);
                }
                //letterUsed[y][x] = false;
                count++;
            }
        }
    }
    
    public PuzzleNode getArrayNode(int y, int x)
    {
        if (x >= width || x < 0 || y >= height || y < 0)
        {
            return null;
        }
        else
        {
            return puzzleArray[y][x];
        }
    }
    
    public void printPuzzle()
    {
        System.out.println(this.width + " " + this.height);
        
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                System.out.print(puzzleArray[i][j].getLetter() + " ");
            }
            System.out.println();
        }
        
        System.out.println();

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (puzzleArray[i][j].isUsed())
                {
                    System.out.print("X ");
                }
                else
                {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    
    public PuzzleNode[][] getPuzzleArray()
    {
        return puzzleArray;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
    
}