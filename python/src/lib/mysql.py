# Python 练习 - MySQL

# doc https://dev.mysql.com/doc/connector-python/en/
import random

import mysql.connector


def select(conn):
    sql = "SELECT id, name FROM user ORDER BY id DESC LIMIT %s"
    try:
        cursor = conn.cursor()
        cursor.execute(sql, (10,))
        for id, name in cursor:
            print("select user id: {} name: {}".format(id, name))
    finally:
        cursor.close()


def insert(conn):
    sql = "INSERT INTO user (name) VALUES (%(name)s)"
    try:
        cursor = conn.cursor()
        user_name = "Python{}".format(random.randint(0, 9))
        cursor.execute(sql, {"name": user_name})
        conn.commit()
        user_id = cursor.lastrowid
        print("insert user id: {} name: {}".format(user_id, user_name))
        return user_id
    finally:
        cursor.close()


def update(conn, user_id):
    sql = "UPDATE user SET name = %(name)s WHERE id = %(id)s"
    try:
        cursor = conn.cursor()
        user_name = "Python{}".format(random.randint(0, 9))
        cursor.execute(sql, {"name": user_name, "id": user_id})
        conn.commit()
        print("update user id: {} name: {}".format(user_id, user_name))
        return cursor.rowcount
    finally:
        cursor.close()


def delete(conn, user_id):
    sql = "DELETE FROM user WHERE id = %s"
    try:
        cursor = conn.cursor()
        cursor.execute(sql, (user_id,))
        conn.commit()
        print("delete user id: {}".format(user_id))
        return cursor.rowcount
    finally:
        cursor.close()


def main():
    try:
        connection = mysql.connector.connect(
            user="root",
            password="",
            host="localhost",
            port=3306,
            database="lib_examples",
        )
        select(connection)
        # user_id = insert(connection)
        # update(connection, user_id)
        # delete(connection, user_id)
    finally:
        connection.close()


if __name__ == "__main__":
    main()
