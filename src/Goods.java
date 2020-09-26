public class Goods {
    private int id;
    private String name;
    private double price;
    private String intrduction;

    public Goods() {
    }

    public Goods(int id, String name, double price, String intrduction) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.intrduction = intrduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIntrduction() {
        return intrduction;
    }

    public void setIntrduction(String intrduction) {
        this.intrduction = intrduction;
    }
}
