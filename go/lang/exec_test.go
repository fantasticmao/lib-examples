package lang

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"os"
	"os/exec"
	"runtime"
	"strings"
	"testing"
)

func TestExecExecutable(t *testing.T) {
	platform := runtime.GOOS
	if !strings.Contains(platform, "linux") &&
		!strings.Contains(platform, "darwin") {
		return
	}

	execForm := []string{"uname", "-s", "-n", "-r", "-m", "-p"}
	cmd := exec.Command(execForm[0], execForm[1:]...)
	out, err := cmd.Output()
	assert.Nil(t, err)
	fmt.Print(string(out))
}

func TestExecShell_success(t *testing.T) {
	platform := runtime.GOOS
	if !strings.Contains(platform, "linux") &&
		!strings.Contains(platform, "darwin") {
		return
	}

	shellForm := "echo hello"
	cmd := exec.Command("/bin/sh", "-c", shellForm)
	out, err := cmd.Output()
	assert.Nil(t, err)
	assert.Equal(t, "hello\n", string(out))
}

func TestExecShell_error(t *testing.T) {
	shellForm := "echp 'hello'"
	cmd := exec.Command("/bin/sh", "-c", shellForm)
	_, err := cmd.Output()
	assert.NotNil(t, err)
	if e, ok := err.(*exec.ExitError); ok {
		errMsg := string(e.Stderr)
		_, _ = fmt.Fprint(os.Stderr, errMsg)
	}
}
