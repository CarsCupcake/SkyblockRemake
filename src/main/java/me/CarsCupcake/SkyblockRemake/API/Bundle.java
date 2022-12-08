package me.CarsCupcake.SkyblockRemake.API;

public class  Bundle <T,K>{
    private T t;
    private K k;
    public  Bundle(T t, K k) {
        this.t = t;
        this.k = k;
    }
    public T getFirst() {
        return t;
    }
    public K getLast() {
        return k;
    }
    public void setFirst(T nt){
        t = nt;
    }
    public void setLast(K nk){
        k = nk;
    }
    @Override
    public String toString(){
        return "Bundle[" + t.toString() + "; " + k.toString() + "]";
    }
}
