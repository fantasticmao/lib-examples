package cn.fantasticmao.demo.java.lang.jsr303;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Param
 *
 * @author fantasticmao
 * @since 2025-07-18
 */
public class Param {
    @Positive
    @NotNull
    private Long count;

    @DecimalMax(value = "100")
    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String message;

    @PastOrPresent
    @NotNull
    private LocalDate birthday;

    // Getters and Setters

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
