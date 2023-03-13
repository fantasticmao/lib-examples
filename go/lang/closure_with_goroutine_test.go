package lang

import (
	"fmt"
	"testing"
)

// see https://go.dev/doc/faq#closures_and_goroutines

func TestClosureWithGoroutine(t *testing.T) {
	done := make(chan bool)
	values := []string{"a", "b", "c"}

	for _, v := range values {
		go func(u string) {
			fmt.Printf("%v ", u)
			done <- true
		}(v)
	}

	for range values {
		<-done
	}
}
