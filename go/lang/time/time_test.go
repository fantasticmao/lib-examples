package time

import (
	"github.com/stretchr/testify/assert"
	"testing"
	"time"
)

func TestParseTime(t *testing.T) {
	var (
		datetime time.Time
		err      error
	)

	datetime, err = time.Parse(time.RFC3339, "2021-12-01T00:00:00+08:00")
	assert.Nil(t, err)
	assert.Equal(t, int64(1638288000000), datetime.UnixMilli())

	datetime, err = time.Parse("02/Jan/2006:15:04:05 -0700", "01/Dec/2021:00:00:00 +0800")
	assert.Nil(t, err)
	assert.Equal(t, int64(1638288000000), datetime.UnixMilli())

	datetime, err = time.ParseInLocation("2006-01-02 15:04:05", "2021-12-01 00:00:00", time.Local)
	assert.Nil(t, err)
	assert.Equal(t, int64(1638288000000), datetime.UnixMilli())
}

func TestAfterTime(t *testing.T) {
	ch := time.After(500 * time.Millisecond)
	timePassed := <-ch
	assert.True(t, time.Now().After(timePassed))
}
