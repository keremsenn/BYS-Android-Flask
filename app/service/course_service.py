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
            raise NotFound(f"Student with id {course_id} not found")
        return course

    @staticmethod
    def get_by_credit(course_credit):
        courses = Course.query.filter(Course.credit == course_credit).all()
        if not courses:
            raise NotFound(f"No courses found with credit {course_credit}")
        return courses

    @staticmethod
    def get_by_course_code(course_code):
        course = Course.query.filter_by(courseCode = course_code).first()
        if not course:
            raise NotFound(f"Course with code '{course_code}' not found")
        return course
