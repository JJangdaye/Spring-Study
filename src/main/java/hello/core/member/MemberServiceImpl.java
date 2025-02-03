package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl에 MemoyMemberRepository처럼 구현체가 있는 게 X, MemeberRepository라는 인터페이스만 존재!
    // 추상화에만 의존, DIP를 지키게 됨!
    private final MemberRepository memberRepository;

    // 외부에서 보면 의존관계를 주입해주는 것 같다고 해서
    // DI (Dependency Injection) = 의존관계 주입이라고 함
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // "의존관계에 대한 고민은 외부에" 맡기고, "실행에만 집중"하면 됨
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
