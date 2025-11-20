public class Producto {

    private String name;
    private double precio;
    
    public Producto(String pName, double pPrecio){
        this.name = pName;
        this.precio = pPrecio;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String value){
        this.name = value;
    }

    public double getPrecio(){
        return this.precio;
    }

    public void setPrecio(double value){
        this.precio = value;
    }


}
