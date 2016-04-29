package anagram;

import trie.PrefixTree;

public class Anagram 
{
    static int size;
    static int count;
    private static char[] charArray;
    private static PrefixTree dict;

    public static void initAnagram()
    {
        size = getCharArray().length;
        count = 0;
        doAnagram(size);
    }

    public static void doAnagram(int newSize)
    {
        // if too small, return;
        if (newSize == 1)
        {
            return;
        }
        
        // for each position,
        for (int i = 0; i < newSize; i++)
        {
            doAnagram(newSize - 1); // anagram remaining
            if (newSize == 2) // if innermost,
            {
                display();
            }
            rotate(newSize); // rotate word
        }
    }

    // rotate left all chars from position to end
    public static void rotate(int newSize)
    {
        int i;
        int position = size - newSize;
        
        // save first letter
        char temp = getCharArray()[position];
        
        // shift others left
        for (i = position + 1; i < size; i++)
        {
            getCharArray()[i - 1] = getCharArray()[i];
        }
        // put first on right
        getCharArray()[i - 1] = temp;
    }

    public static void display()
    {
        String tmp = new String(getCharArray());
        if (getDict().checkIsWord(tmp))
        {
            System.out.print(++count + " ");
        
            for (int i = 0; i < size; i++)
            {
                System.out.print(getCharArray()[i]);
            }
            
            System.out.println();
        }
        else
        {
            return;
        }
    }

    public static PrefixTree getDict()
    {
        return dict;
    }

    public static void setDict(PrefixTree dict)
    {
        Anagram.dict = dict;
    }

    public static char[] getCharArray()
    {
        return charArray;
    }

    public static void setCharArray(char[] charArray)
    {
        Anagram.charArray = charArray;
    }
}
