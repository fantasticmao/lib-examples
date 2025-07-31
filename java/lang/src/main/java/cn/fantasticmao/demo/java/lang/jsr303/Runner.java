package cn.fantasticmao.demo.java.lang.jsr303;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Runner
 *
 * @author fantasticmao
 * @since 2025-07-31
 */
public class Runner {

    public void run(@Positive @NotNull Long count, @NotBlank @UppercaseOnly String message) {

    }
}
