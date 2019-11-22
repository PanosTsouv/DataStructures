public class Item{
    //array coords of item
    private int i;
    private int j;

    public Item(){
        
    }

    public Item(int i, int j)
    {
        this.i = i;
        this.j = j;
    }

    public int getI(){return this.i;}
    public int getJ(){return this.j;}

    public void setI(int i){this.i = i;}
    public void setJ(int j){this.j = j;}

}