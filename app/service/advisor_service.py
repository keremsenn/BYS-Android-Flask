from ..model.advisor import Advisors
from .. import db
from werkzeug.exceptions import NotFound

class AdvisorService:
    @staticmethod
    def get_all():
        advisors = Advisors.query.all()
        return advisors

    @staticmethod
    def get_by_id(advisor_id):
        advisor = Advisors.query.filter_by(id=advisor_id).first()
        if not advisor:
            raise NotFound(f"Student with id {advisor_id} not found")
        return advisor

    @staticmethod
    def add(data):
        advisor = Advisors(
            fullName=data.get('fullName'),
            title=data.get('title'),
            department=data.get('department'),
            email=data.get('email')
        )
        db.session.add(advisor)
        db.session.commit()

        return advisor

    @staticmethod
    def delete_by_id(advisor_id):
        advisor = Advisors.query.filter_by(id=advisor_id).first()
        if not advisor:
            return {"error": f"student not found by id: {advisor_id}"}

        db.session.delete(advisor)
        db.session.commit()

        return {"message": "delete successful"}