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
                .antMatchers("/eureka/**").hasRole("ADMIN")
                .antMatchers("/adminserver/**").hasRole("SUPER_ADMIN")
                .antMatchers("/msdemob/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and().formLogin().and().logout().permitAll()
                .logoutSuccessUrl("/logged_out").permitAll()
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
        auth.inMemoryAuthentication().withUser("superadmin").password("admin").roles("SUPER_ADMIN").accountLocked(false).disabled(false)

    }
}