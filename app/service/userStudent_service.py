from ..model.UserStudent import UserStudent
from .. import db
from werkzeug.exceptions import NotFound

class UserStudentService:
    @staticmethod
    def get_all():
        userStudent = UserStudent.query.all()
        return userStudent

    @staticmethod
    def get_by_id(user_id):
        userStudent = UserStudent.query.filter_by(id = user_id).first()
        if not userStudent:
            raise NotFound(f"Student with id {user_id} not found")
        return userStudent

    @staticmethod
    def get_by_user_related_id(related_id):
        userStudent = UserStudent.query.filter_by(relatedId = related_id).first()
        if not userStudent:
            raise NotFound(f"Student with id {related_id} not found")
        return userStudent

    @staticmethod
    def add(data):
        userStudent = UserStudent(
            userName=data.get('userName'),
            password=data.get('password'),
            relatedId=data.get('relatedId'),
            email=data.get('email'),
            createdAt = data.get('createdAt')
        )
        db.session.add(userStudent)
        db.session.commit()

        return userStudent

    @staticmethod
    def delete_by_id(user_id):
        userStudent = UserStudent.query.filter_by(id = user_id).first()
        if not userStudent:
            return {"error": f"student not found by id: {user_id}"}

        db.session.delete(userStudent)
        db.session.commit()

        return {"message": "delete successful"}

    def delete_by_related_id(related_id):
        userStudent = UserStudent.query.filter_by(relatedId = related_id).first()
        if not userStudent:
            return {"error": f"student not found by id: {related_id}"}

        db.session.delete(userStudent)
        db.session.commit()

        return {"message": "delete successful"}