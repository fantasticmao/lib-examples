package lang

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

type Number interface {
	int | float64
}

func addNum[N Number](n []N) N {
	var sum N
	for _, v := range n {
		sum += v
	}
	return sum
}

func TestAddNum(t *testing.T) {
	n1 := []int{1, 2, 3}
	sum1 := addNum(n1)
	assert.Equal(t, 6, sum1)

	n2 := []float64{1, 2, 3}
	sum2 := addNum(n2)
	assert.Equal(t, 6.0, sum2)
}
