package arrays

import (
	"reflect"
	"testing"
)

func TestSum(t *testing.T) {
	assertCorrectMessage := func(t *testing.T, got, want []int) {
		t.Helper()
		if !reflect.DeepEqual(got, want) {
			t.Errorf("got '%v' want '%v'", got, want)
		}
	}

	t.Run("collection of 5 numbers", func(t *testing.T) {
		numbers := [5]int{1, 2, 3, 4, 5}
		got := Sum1(numbers)
		want := 15
		if got != want {
			t.Errorf("got %d want %d given, %v", got, want, numbers)
		}
	})

	t.Run("collection of any size", func(t *testing.T) {
		numbers := []int{1, 2, 3, 4}
		got := Sum2(numbers)
		want := 10
		if got != want {
			t.Errorf("got %d want %d given, %v", got, want, numbers)
		}
	})

	t.Run("collection of variadic functions use make", func(t *testing.T) {
		got := SumAll1([]int{1, 2, 3, 4}, []int{0, 9})
		want := []int{10, 9}
		assertCorrectMessage(t, got, want)
	})

	t.Run("collection of variadic functions use append", func(t *testing.T) {
		got := SumAll2([]int{1, 2, 3, 4}, []int{0, 9})
		want := []int{10, 9}
		assertCorrectMessage(t, got, want)
	})

	t.Run("sum all tails use slice[low:high]", func(t *testing.T) {
		got := SumAllTails([]int{1, 2, 3, 4}, []int{0, 9})
		want := []int{9, 9}
		assertCorrectMessage(t, got, want)
	})

}
