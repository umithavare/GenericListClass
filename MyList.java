public class MyList <T> {
    private T[] genericArray;
    private int capacity;
    private int index = 0;

    public MyList() { // Default kapasite olarak 10 yazdik
        setCapacity(10);
        setGenericArray(new Object[getCapacity()]);
    }
    public MyList(int capacity) {
        setCapacity(capacity);
        setGenericArray(new Object[getCapacity()]);
    }

    public int size(){ //dizideki eleman sayisini veriyor.
        int counter = 0 ;
        for (int i = 0 ; i < genericArray.length ; i++){
            if (getGenericArray()[i] != null){
                counter++;
            }
        }
        return counter;
    }

    public int getAvailableCapacity(){  // kullanilabilecek bos kapasiteyi verir
        return getCapacity() - size();
    }
    public void add(T value){ // diziye eleman ekler eger kapasite doluysa dizinin kapasitesini iki katina cikarir
        if (getCapacity() > 0 && getIndex() < getCapacity() ){
           // System.out.println("eklendi");
            getGenericArray()[size()] = value;
        }
        else if (getIndex() >= getCapacity()){
            setCapacity(getCapacity() * 2);
            System.out.println("dizi max kapasiteye ulasti. Dizinin yeni kapasitesi: " + getCapacity());
            Object[] tempArray = (T[]) new Object[getCapacity()];

            for (int i = 0 ; i < genericArray.length ; i++){
                tempArray[i] = genericArray[i];
            }
            genericArray = (T[]) tempArray;
            genericArray[index++] = value;
        }
        else System.out.println("kapasite olmayan bir dizi olusturmussunuz");
    }

    public T get( int index){ // verilen indisteki değeri döndürür. Geçersiz indis girilerse null dondurur
        if (getGenericArray()[index] == null){
            return  null;
        }
        else  return (T) genericArray[index];
    }

    public T remove(int index){ // verilen indisteki veriyi silmeli ve silinen indis sonrasında ki verileri sol doğru kaydırmalı.
        // Geçersiz indis girilerse null döndürür.

        if (index >= 0 && index < size()){ // silme islemi
            T outPut = (T) getGenericArray()[index];
            getGenericArray()[index] = null;
            index--;
            for (int i = 0; i < size()- 1 ; i++){ // sola dogru kaydirma islemi
                getGenericArray()[i] = getGenericArray()[i + 1];
                System.out.println("index numarasi: " + i );
            }
            return outPut;
        }
        else return null; // gecersiz index icin yazildi
    }
    public T set(int index, T value){ // verilen indisteki veriyi yenisi ile değiştirme işlemini yapmalıdır.
        // Geçersiz indis girilerse null döndürür.
        if (index >= 0 && index < size()){
            getGenericArray()[index] = value;
            return value;
        }
        else return null;
    }

    public String toString(){
        if (size() > 0){
            String out = "[";
            for (int i = 0; i < size() - 1; i++){
                out += getGenericArray()[i] + ", ";
            }
            out += getGenericArray()[size() - 1] + "]";
            return  out;
        }
        else return "[]";
    }

    public int indexOf(T data){ //  Parametrede verilen nesnenin listedeki indeksini verir.
        // Nesne listede yoksa -1 değerini verir.

        int indexNum = -1;
       for(T element : getGenericArray()) {
           indexNum++;
           if (element == data) {
               return indexNum;
           }
       }
        return -1;
    }

    public int lastIndexOf(T data){ //  Belirtilen öğenin listedeki son indeksini söyler.
        // Nesne listede yoksa -1 değerini verir.
        int indexNum = -1;
        int lastIndex = -1;
        for(T element : getGenericArray()) {
            indexNum++;
            if (element == data) {
                lastIndex = indexNum;
            }
        }
        return lastIndex;
    }

    boolean isEmpty(){
        boolean isEmpty = true; // varsayilan olarak true donmesini ayarladik.
        for (T element : getGenericArray()){
            if (element != null){
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    public T[] toArray(){ // Listedeki öğeleri, aynı sırayla bir array haline getirir.
        T[] newArray = (T[]) new Object[getCapacity()];
        for (int i = 0 ; i < getCapacity() ; i++){
            newArray[i] = getGenericArray()[i];
        }
        return newArray;
    }

    public T[] clear(){
        for (int i = 0 ; i < genericArray.length ; i++){
            getGenericArray()[i] = null;
        }
        return genericArray;
    }

    public MyList<T> subList(int start,int finish){ // Parametrede verilen indeks aralığına ait bir liste döner.
        if (start >= 0 && finish >= 0){
            if (start <= getCapacity() && finish <= getCapacity()){
                int newSize = finish - start + 1;
                MyList<T> myList = new MyList<>(newSize);
                for (int i = start ; i <= finish ; i++){
                    myList.add(get(i));
                }
                return myList;
            }
        }
        return null;
    }

    public boolean contains(T data){ // Parametrede verilen değerin dizide olup olmadığını söyler.
        for (T element : getGenericArray()){
            if (element == data) {
                return true;
            }
        }
        return false;
    }

    public T[] getGenericArray() {
        return genericArray;
    }

    public void setGenericArray(Object[] array) {
        this.genericArray = (T[]) array;
    }


    public int getCapacity() { // dizinin kapasitesini verir
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
