package lang

import (
	"bytes"
	"github.com/stretchr/testify/assert"
	"testing"
	"time"
)

// see https://go.dev/ref/spec#The_zero_value

var zeroValueBool bool
var zeroValueNumber int
var zeroValueString string
var zeroValuePointer *bytes.Buffer
var zeroValueFunction func()
var zeroValueInterface interface{}
var zeroValueSlice []int
var zeroValueChannel <-chan time.Time
var zeroValueMap map[int]string

func TestZeroValue(t *testing.T) {
	assert.False(t, zeroValueBool)
	assert.Equal(t, 0, zeroValueNumber)
	assert.Equal(t, "", zeroValueString)
	assert.Nil(t, zeroValuePointer)
	assert.Nil(t, zeroValueFunction)
	assert.Nil(t, zeroValueInterface)
	assert.Nil(t, zeroValueSlice)
	assert.Nil(t, zeroValueChannel)
	assert.Nil(t, zeroValueMap)
}
