/**
 * Spring Web MVC 基于 Servlet Filter 实现的相关功能：
 * <ul>
 *     <li>{@link org.springframework.web.filter.FormContentFilter} 处理 HTTP PUT、PATCH、DELETE 请求参数</li>
 *     <li>{@link org.springframework.web.filter.CorsFilter} 处理 HTTP CORS Header</li>
 *     <li>{@link org.springframework.web.filter.ForwardedHeaderFilter} 处理 HTTP Forwarded Header</li>
 * </ul>
 *
 * <p>{@link org.springframework.web.filter.GenericFilterBean} 抽象类简单实现了 {@link javax.servlet.Filter} 接口，
 * 支持将 {@code init-param} 参数设置成 Spring Bean 的属性。
 *
 * <p>{@link org.springframework.web.filter.OncePerRequestFilter} 抽象类继承了 {@code GenericFilterBean} 抽象类，
 * 支持保证每次 HTTP 请求中只执行一次 {@code Filter}，是通过 {@code request.setAttribute(String, Object)} 方法设置状态实现的。
 *
 * <p>{@link org.springframework.web.filter.DelegatingFilterProxy} 类继承了 {@code GenericFilterBean} 抽象类，
 * 支持将 Servlet Filter 委托给 Spring IoC 容器中的 Bean 来实现。
 *
 * @author maomao
 * @since 2020-06-30
 */
package cn.fantasticmao.demo.java.spring.framework.webmvc.filter;