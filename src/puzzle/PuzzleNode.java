package puzzle;

public final class PuzzleNode
{
    private boolean isUsed;
    private char letter;
    private int y;
    private int x;
    
    public PuzzleNode(int y, int x, char c)
    {
        this.letter = c;
        this.isUsed = false;
        this.y = y;
        this.x = x;
    }

    public boolean isUsed()
    {
        return isUsed;
    }

    public void setUsed(boolean isUsed)
    {
        this.isUsed = isUsed;
    }

    public char getLetter()
    {
        return letter;
    }
    
    public boolean isLetter(char isC)
    {
        if (this.letter == isC)
        {
            return true;
        }
        else return false;
    }

    public int getY()
    {
        return y;
    }

    public int getX()
    {
        return x;
    }
    
    
}
