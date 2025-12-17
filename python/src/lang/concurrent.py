import threading


class SafeCounter:
    count: int
    lock: threading.Lock

    def __init__(self):
        self.count = 0
        self.lock = threading.Lock()

    def increment(self):
        with self.lock:
            self.count = self.count + 1

    def decrement(self):
        with self.lock:
            self.count = self.count - 1
