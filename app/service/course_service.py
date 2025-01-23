from ..model.course import Course
from .. import db
from werkzeug.exceptions import NotFound

class CourseService:
    @staticmethod
    def get_all():
        courses = Course.query.all()
        return courses

    @staticmethod
    def get_by_id(course_id):
        course = Course.query.filter_by(id = course_id).first()
        if not course:
            raise NotFound(f"Course with id {course_id} not found")
        return course

    @staticmethod
    def get_by_course_code(course_code):
        course = Course.query.filter_by(courseCode = course_code).first()
        if not course:
            raise NotFound(f"Course with code '{course_code}' not found")
        return course

    @staticmethod
    def add(data):
        course = Course(
            courseCode=data.get('CourseCode'),
            courseName=data.get('courseName'),
            isMandatory=data.get('IsMandatory'),
            credit=data.get('Credit'),
            department = data.get('Department')
        )
        db.session.add(course)
        db.session.commit()

        return course

    @staticmethod
    def delete_by_id(course_id):
        course = Course.query.filter_by(id=course_id).first()
        if not course:
            return {"error": f"course not found by id: {course_id}"}

        db.session.delete(course)
        db.session.commit()

        return {"message": "delete successful"}
