import json


def test_json_deserialize():
    data = b'{"username": "Sam"}'
    user = json.loads(data)
    assert user.get("username") == "Sam"

    data = '{"username": "Tom"}'
    user = json.loads(data)
    assert user.get("username") == "Tom"

    data = "[0,1,2]"
    nums = json.loads(data)
    assert len(nums) == 3
    assert nums[0] == 0


def test_json():
    data = {"id": 1, "username": "Tom"}
    text = json.dumps(data)
    assert text == '{"id": 1, "username": "Tom"}'

    text = json.dumps(data, indent=2, ensure_ascii=False)
    assert text == '{\n  "id": 1,\n  "username": "Tom"\n}'
