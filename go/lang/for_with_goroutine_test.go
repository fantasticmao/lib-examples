package lang

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"sync"
	"testing"
	"time"
)

// see https://go.dev/doc/faq#closures_and_goroutines
func TestForWithGoroutine_mistake(t *testing.T) {
	wait := sync.WaitGroup{}
	for i := 0; i < 10; i++ {
		wait.Add(1)
		go func() {
			defer wait.Done()

			time.Sleep(10 * time.Millisecond)
			fmt.Printf("%v ", i)
			assert.Equal(t, 10, i)
		}()
	}
	wait.Wait()
}

func TestForWithGoroutine_correct(t *testing.T) {
	wait := sync.WaitGroup{}
	for i := 0; i < 10; i++ {
		wait.Add(1)
		go func(i int) {
			defer wait.Done()

			time.Sleep(10 * time.Millisecond)
			fmt.Printf("%v ", i)
		}(i)
	}
	wait.Wait()
}
