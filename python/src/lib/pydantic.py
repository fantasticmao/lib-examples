from datetime import datetime

from pydantic import BaseModel, Field, PositiveInt, ConfigDict


class User(BaseModel):
    id: int
    name: str = Field(alias="user_name")
    signup_ts: datetime | None
    tastes: dict[str, PositiveInt]

    model_config = ConfigDict(extra="allow")
