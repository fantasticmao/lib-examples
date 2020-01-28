package main

import (
	"fmt"
	"io"
	"os"
	"time"
)

type Sleeper interface {
	Sleep()
}

type ConfigurableSleeper struct {
	duration time.Duration
}

func (c *ConfigurableSleeper) Sleep() {
	time.Sleep(c.duration)
}

// 使用 Sleeper 接口
func CountDown(w io.Writer, s Sleeper) {
	for i := 3; i > 0; i-- {
		s.Sleep()
		_, _ = fmt.Fprintln(w, i)
	}
	s.Sleep()
	_, _ = fmt.Fprint(w, "Go!")
}

func main() {
	sleeper := &ConfigurableSleeper{duration: 1 * time.Second}
	CountDown(os.Stdout, sleeper)
}
