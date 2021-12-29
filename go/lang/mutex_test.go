package lang

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"sync"
	"testing"
)

func TestMutexExclusion(t *testing.T) {
	wait := sync.WaitGroup{}
	lock := sync.Mutex{}
	count := 0
	for i := 0; i < 10; i++ {
		wait.Add(1)
		go func(i int) {
			defer wait.Done()

			lock.Lock()
			for j := 0; j < 10; j++ {
				count++
				fmt.Printf("count: %v\n", count)
			}
			fmt.Println()
			lock.Unlock()
		}(i)
	}
	wait.Wait()
	assert.Equal(t, 100, count)
}
