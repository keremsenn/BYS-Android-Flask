from ..model.student import Student, StudentSchema
from .. import db
from werkzeug.exceptions import NotFound


class StudentService:
    @staticmethod
    def get_all():
        students_query = Student.query.all()
        schema = StudentSchema(many=True)
        students = schema.dump(students_query)
        return students

    @staticmethod
    def get_by_id(student_id):
        students_query = Student.query.filter_by(id=student_id).first()
        schema = StudentSchema()
        if not students_query:
            raise NotFound(f"Student with id {student_id} not found")
        student = schema.dump(students_query)
        return student

    @staticmethod
    def get_by_advisor_id(advisor_id):
        students_query = Student.query.filter_by(advisorId=advisor_id).all()
        schema = StudentSchema(many=True)
        students = schema.dump(students_query)
        return students

    @staticmethod
    def add(data):
        student = Student(
            firstName=data.get('firstName'),
            lastName=data.get('lastName'),
            email=data.get('email'),
            advisorId=data.get('advisorId')
        )

        db.session.add(student)
        db.session.commit()

        return {"message": "add successful"}

    @staticmethod
    def delete_by_id(student_id):
        student = Student.query.filter_by(id=student_id).first()
        if not student:
            return {"error": f"student not found by id: {student_id}"}

        db.session.delete(student)
        db.session.commit()

        return {"message": "delete successful"}

