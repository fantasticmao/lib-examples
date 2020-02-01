package concurrency

type WebsiteChecker func(string) bool

type result struct {
	string
	bool
}

func CheckWebsites(checker WebsiteChecker, urls []string) map[string]bool {
	results := make(map[string]bool)
	resultChannel := make(chan result)
	for _, url := range urls {
		go func(u string) { // 使用 func(){}() 匿名函数，开启一个新的 goroutine
			resultChannel <- result{u, checker(u)} // send statement
		}(url) // 设置匿名函数参数
	}
	for i := 0; i < len(urls); i++ {
		result := <-resultChannel // receive expression
		results[result.string] = result.bool
	}
	return results
}
