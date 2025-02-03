package hello.core.singleton;

public class StatefulService {

    // stateful 해결 : void 말고 int로 쓰고 등등
    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        return price;
    }
}
