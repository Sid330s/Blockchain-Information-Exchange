import java.text.SimpleDateFormat;
import java.util.Date;



public class Blockchain
{
    private final int difficulty=2;
    int temp=difficulty;
    String difficultyString="";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private int i=0;
    private int j=0;
    int unconfirmedTransactions[]=new int[1000];
    Block Chain[]=new Block[1000];
    String previousHash;

    public String computedifficultyString()
    {
        int temp=difficulty;
        while (temp>=0)
        {
            difficultyString = difficultyString + "0";
        }
        return difficultyString;
    }



    public void createGenesisBlock()
    {
        Block GenesisBlock = new Block(0,dateFormat.format(new Date()),"Genesis",0, 100);
        GenesisBlock.hash=Block.computeHash(GenesisBlock);
        Chain[i]=GenesisBlock;
        i++;
    }

    public Block getLastBlock()
    {
        return Chain[i];
    }
    public void addNewTransaction(int Transaction)
    {
        unconfirmedTransactions[j]=Transaction;
        j++;
    }

    String proofOfWork(Block block)
    {
        String difficultyString=computedifficultyString();
        block.nonce=0;
        String computedHash=Block.computeHash(block);
        while(difficultyString!=computedHash.substring(0,difficulty-1));
        {
            block.nonce++;
            computedHash=Block.computeHash(block);
        }
        return computedHash;
    }

    public boolean addBlock(Block block,String proof)
    {
        previousHash=Block.computeHash(getLastBlock());
        if (this.previousHash!=block.previousHash)
            return false;
        block.hash=proof;
        Chain[i]=block;
        return true;
    }

    public Boolean Mine(int nonce)
    {
        Block lastBlock=Chain[i];
        Block newBlock=new Block(lastBlock.index+1,dateFormat.format(new Date()),Block.computeHash(lastBlock),0,unconfirmedTransactions[j]);
        String proof=proofOfWork(newBlock);
        addBlock(newBlock,proof);
        int unconfirmedTransactions[]=new int[1000];
        return true;
    }











}
