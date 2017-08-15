package io.capdevila.poc.msarchitecture.zuulserver.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http
                .csrf().disable()
                .authorizeRequests()
                //FIXME all role user??
                .antMatchers("/**").access("hasRole('USER')")
                .antMatchers("/msdemob/**").access("hasRole('ADMIN')")
                .antMatchers("/adminserver/**").permitAll()
                .and().httpBasic()

    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {

        auth.inMemoryAuthentication().withUser("user1").password("pwd1").roles("USER").accountLocked(false).disabled(false)
        auth.inMemoryAuthentication().withUser("user2").password("pwd2").roles("USER").accountLocked(false).disabled(false)

        auth.inMemoryAuthentication().withUser("user3").password("pwd3").roles("USER").accountLocked(true).disabled(false)

        auth.inMemoryAuthentication().withUser("user4").password("pwd4").roles("USER").accountLocked(false).disabled(true)
        auth.inMemoryAuthentication().withUser("user5").password("pwd5").roles("USER").accountLocked(false).disabled(true)

        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").accountLocked(false).disabled(false)

    }
}