package com.tistory.jaimemin.aop.proxyvs;

import com.tistory.jaimemin.aop.member.MemberService;
import com.tistory.jaimemin.aop.member.MemberServiceImpl;
import com.tistory.jaimemin.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
// @SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK Proxy
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService; // CGLIB OK, JDK Proxy OK

    @Autowired
    MemberServiceImpl memberServiceImpl; // CGLIB OK, JDK Proxy X

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());

        memberServiceImpl.hello("hello");
    }
}
