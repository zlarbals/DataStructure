public class Hash {
    private int number;
    private Table[] hashTable;

    private class HashSlot{
        int key;
        int value;
        HashSlot nextSlot;

        public HashSlot(int key,int value){
            this.key=key;
            this.value=value;
            this.nextSlot = null;
        }
    }

    private class Table{
        HashSlot slot;
        public Table(){
            slot=null;
        }
    }

    public Hash(){
        this.number = 31;
        hashTable = new Table[this.number];
        for(int i=0;i<this.number;i++){
            hashTable[i]=new Table();
        }
    }

    public int HashFunction(int key){
        return key%this.number;
    }

    public void Insert(int key,int value){
        HashSlot temp;
        HashSlot newSlot = new HashSlot(key,value);
        int hashKey=this.HashFunction(key);
        if(hashTable[hashKey].slot==null)
            hashTable[hashKey].slot=newSlot;
        else{
            temp=hashTable[hashKey].slot;
            while(temp.nextSlot !=null){
                temp=temp.nextSlot;
            }
            temp.nextSlot=newSlot;
        }
    }

    public void Delete(int key){
        HashSlot temp;
        int hashKey=this.HashFunction(key);
        if(hashTable[hashKey].slot.key==key){
            hashTable[hashKey].slot=hashTable[hashKey].slot.nextSlot;
        }
        else{
            temp=hashTable[hashKey].slot;
            while(temp.nextSlot.key!=key){
                temp=temp.nextSlot;
            }
            temp.nextSlot=temp.nextSlot.nextSlot;
        }

    }
}
