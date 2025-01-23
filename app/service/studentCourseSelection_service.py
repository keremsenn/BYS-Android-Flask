from ..model.studentCourseSelection import StudentCourseSelection
from .. import db
from werkzeug.exceptions import NotFound

class StudentCourseSelectionService:
    @staticmethod
    def get_all():
        studentCourseSelectionList = StudentCourseSelection.query.all()
        return studentCourseSelectionList

    @staticmethod
    def get_by_id(studentCourseSelection_id):
        studentCourseSelection = StudentCourseSelection.query.filter_by(id=studentCourseSelection_id).first()
        if not studentCourseSelection:
            raise NotFound(f"Student with id {studentCourseSelection_id} not found")
        return studentCourseSelection

    @staticmethod
    def get_by_student_id(student_id):
        studentCourses = StudentCourseSelection.query.filter(StudentCourseSelection.studentId == student_id).all()
        if not studentCourses:
            raise NotFound(f"Student with id {student_id} not found")
        return studentCourses

    @staticmethod
    def get_by_course_id(course_id):
        studentCourses = StudentCourseSelection.query.filter(StudentCourseSelection.courseId == course_id).all()
        if not studentCourses:
            raise NotFound(f"Student with id {course_id} not found")
        return studentCourses

    @staticmethod
    def get_courses_by_approval_status(is_approved):
        courses = StudentCourseSelection.query.filter(StudentCourseSelection.isApproved == is_approved).all()
        if not courses:
            status = "approved" if is_approved else "not approved"
            raise NotFound(f"No {status} courses found.")
        return courses