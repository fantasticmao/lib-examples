# https://docs.pydantic.dev/latest/concepts/models/

from datetime import datetime

from lib import pydantic


def test_user_serialize():
    user = pydantic.User(
        id=20, user_name="Tom", signup_ts=datetime(2026, 2, 1, 12, 00), tastes={"banana": 10}
    )
    json = user.model_dump_json(by_alias=False, exclude_none=True)
    assert json == '{"id":20,"name":"Tom","signup_ts":"2026-02-01T12:00:00","tastes":{"banana":10}}'


def test_user_deserialize():
    json = '{"id":123,"user_name":"John Doe","signup_ts":"2019-06-01 12:22","tastes":{"wine":9,"cheese":7,"cabbage":1}}'
    user = pydantic.User.model_validate_json(json)
    assert user.id == 123
    assert user.name == "John Doe"
    assert user.signup_ts.year == 2019
    assert user.signup_ts.month == 6
    assert user.signup_ts.day == 1
    assert user.signup_ts.hour == 12
    assert user.signup_ts.minute == 22
    assert user.tastes["wine"] == 9
    assert user.tastes["cheese"] == 7
    assert user.tastes["cabbage"] == 1
