package hello.core.order;

import com.sun.source.tree.UsesTree;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // 인터페이스에만 의존하는 중! = 철저하게 DIP를 지키고 있음 good
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // final이 있으면 무조건 기본으로 생성자를 할당해야 함
    // 생성자를 통해 어떤 구현 객체가 주입될지는 전혀 알 수 없는 상태
    @Autowired // 자동으로 의존관계 주입해준다!
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        // 생성자를 통해 어떤 구현객체가 주입될지는 오직 외부 (AppConfig)에 의해 정해짐
    }

    // 이렇게 하면 구체화에도 의존 O, 추상화에도 의존 O -> DIP에 위배 됨 bad
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 실행하는 건 인터페이스만 보고 개발하면 되는 거임 (구체 클래스 볼 필요 X)

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
