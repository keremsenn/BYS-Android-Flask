from ..model.advisor import Advisors,AdvisorSchema
from .. import db
from werkzeug.exceptions import NotFound

class AdvisorService:
    @staticmethod
    def get_all():
        advisors_query = Advisors.query.all()
        schema = AdvisorSchema(many = True)
        advisor = schema.dump(advisors_query)
        return advisor

    @staticmethod
    def get_by_id(advisor_id):
        advisor_query = Advisors.query.filter_by(id=advisor_id).first()
        schema = AdvisorSchema()
        advisor = schema.dump(advisor_query)
        if not advisor:
            raise NotFound(f"Advisor with id {advisor_id} not found")
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

        return {"message": "added successfully"}

    @staticmethod
    def delete_by_id(advisor_id):
        advisor = Advisors.query.filter_by(id=advisor_id).first()
        if not advisor:
            return {"error": f"advisor not found by id: {advisor_id}"}

        db.session.delete(advisor)
        db.session.commit()

        return {"message": "delete successful"}