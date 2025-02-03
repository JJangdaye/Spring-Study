package hello.core.singleton;

public class StatefulService {

    private int price; // stateful 필드이어서 1만원 -< 2만원으로 바뀌어버림

    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }
}
