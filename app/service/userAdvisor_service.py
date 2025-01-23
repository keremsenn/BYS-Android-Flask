from ..model.UserAdvisor import UserAdvisor
from .. import db
from werkzeug.exceptions import NotFound

class UserAdvisorService:
    @staticmethod
    def get_all():
        userAvisors = UserAdvisor.query.all()
        return userAvisors

    @staticmethod
    def get_by_id(user_id):
        userAdvisor = UserAdvisor.query.filter_by(id = user_id).first()
        if not userAdvisor:
            raise NotFound(f"Student with id {user_id} not found")
        return userAdvisor

    @staticmethod
    def get_by_user_related_id(related_id):
        userAdvisor = UserAdvisor.query.filter_by(relatedId = related_id).first()
        if not userAdvisor:
            raise NotFound(f"Student with id {related_id} not found")
        return userAdvisor

    @staticmethod
    def add(data):
        userAdvisor = UserAdvisor(
            userName=data.get('userName'),
            password=data.get('password'),
            relatedId=data.get('relatedId'),
            email=data.get('email'),
            createdAt = data.get('createdAt')
        )
        db.session.add(userAdvisor)
        db.session.commit()

        return userAdvisor

    @staticmethod
    def delete_by_id(user_id):
        userAdvisor = UserAdvisor.query.filter_by(id=user_id).first()
        if not userAdvisor:
            return {"error": f"student not found by id: {user_id}"}

        db.session.delete(userAdvisor)
        db.session.commit()

        return {"message": "delete successful"}

    def delete_by_related_id(related_id):
        userAdvisor = UserAdvisor.query.filter_by(relatedId = related_id).first()
        if not userAdvisor:
            return {"error": f"student not found by id: {related_id}"}

        db.session.delete(userAdvisor)
        db.session.commit()

        return {"message": "delete successful"}