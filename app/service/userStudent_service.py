from ..model.UserStudent import UserStudent
from .. import db
from werkzeug.exceptions import NotFound
from passlib.hash import argon2


class UserStudentService:
    @staticmethod
    def register(data):
        password = data.get('password')
        if len(password) < 7:
            return {"error": "Password must be least 7 characters"}
        hashed_password = argon2.hash(password)

        user_student = UserStudent(
            userName=data.get('username'),
            password=hashed_password,
            relatedId=data.get('relatedId'),
            email=data.get('email')
        )

        db.session.add(user_student)
        db.session.commit()

        return {"message": "Register successful"}

    @staticmethod
    def login(data):
        email = data.get('email')
        password = data.get('password')
        user = UserStudent.query.filter_by(email=email).first()
        if not user:
            return {"error": "User not found by email"}

        if argon2.verify(password, user.password):
            return {"user_id": user.id, "email": user.email}
        else:
            return {"error": "Invalid email or password"}