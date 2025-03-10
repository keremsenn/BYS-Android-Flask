from ..model.course import Course,CourseSchema
from .. import db
from werkzeug.exceptions import NotFound

class CourseService:
    @staticmethod
    def get_all():
        courses_query = Course.query.all()
        schema = CourseSchema(many=True)
        courses = schema.dump(courses_query)
        return courses

    @staticmethod
    def get_by_id(course_id):
        course_query = Course.query.filter_by(id = course_id).first()
        schema = CourseSchema()
        course = schema.dump(course_query)
        if not course:
            raise NotFound(f"Course with id {course_id} not found")
        return course

    @staticmethod
    def get_by_course_code(course_code):
        course_query = Course.query.filter_by(courseCode = course_code).first()
        schema = CourseSchema()
        course = schema.dump(course_query)
        if not course:
            raise NotFound(f"Course with code '{course_code}' not found")
        return course

    @staticmethod
    def add(data):
        course = Course(
            courseCode=data.get('courseCode'),
            courseName=data.get('courseName'),
            isMandatory=data.get('isMandatory'),
            credit=data.get('credit'),
            department = data.get('department')
        )
        db.session.add(course)
        db.session.commit()

        return {"message": "added successfully"}

    @staticmethod
    def delete_by_id(course_id):
        course = Course.query.filter_by(id=course_id).first()
        if not course:
            return {"error": f"course not found by id: {course_id}"}

        db.session.delete(course)
        db.session.commit()

        return {"message": "delete successful"}
