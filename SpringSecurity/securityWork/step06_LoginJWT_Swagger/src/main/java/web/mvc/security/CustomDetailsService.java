package web.mvc.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.mvc.domain.Member;
import web.mvc.repository.MemberRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username : {}", username); // 아이디

        // username에 해당하는 정보를 조회
        Member member = memberRepository.findById(username);
        if(member != null) {
            log.info("member : {}", member);
            return new CustomMemberDetails(member);
        }

        throw new UsernameNotFoundException("Not found member id: " + username);
    }
}
