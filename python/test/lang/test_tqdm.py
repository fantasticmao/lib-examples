import time

from tqdm import tqdm


def test_tqdm():
    my_list = ["苹果", "香蕉", "樱桃", "橙子"]
    for i in tqdm(my_list, desc="Processing"):
        print(i)
        time.sleep(0.5)
