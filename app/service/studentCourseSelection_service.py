from ..model.studentCourseSelection import StudentCourseSelection
from .. import db
from werkzeug.exceptions import NotFound

class StudentCourseSelectionService:
    @staticmethod
    def get_by_id(selection_id):
        selection = StudentCourseSelection.query.filter_by(id=selection_id).first()
        if not selection:
            raise NotFound(f"Selection with id {selection_id} not found")
        return selection

    @staticmethod
    def get_by_student_id(student_id):
        studentCourses = StudentCourseSelection.query.filter(StudentCourseSelection.studentId == student_id).all()
        if not studentCourses:
            raise NotFound(f"Student with id {student_id} not found")
        return studentCourses

    @staticmethod
    def get_by_course_id(course_id):
        courseStudents = StudentCourseSelection.query.filter(StudentCourseSelection.courseId == course_id).all()
        if not courseStudents:
            raise NotFound(f"Course with id {course_id} not found")
        return courseStudents

    @staticmethod
    def add_selection(data):
        selection = StudentCourseSelection(
            studentId=data.get('StudentID'),
            courseId=data.get('CourseID')
        )

        db.session.add(selection)
        db.session.commit()

        return selection

    @staticmethod
    def delete_by_selection_id(selection_id):
        selection = StudentCourseSelection.query.filter_by(id=selection_id).first()

        if not selection:
            return {"error": f"Selection not found by id: {selection_id}"}

        db.session.delete(selection)
        db.session.commit()

        return {"message": "delete successful"}

    @staticmethod
    def update_approve(data):
        selection = StudentCourseSelection.query.filter_by(
            studentId=data.get('StudentId'),
            courserId=data.get('CourseId')
        )

        if not selection:
            return {"error": f"Selection not found"}

        new_approve = data.get('isApproved')
        selection.isApproved = new_approve

        db.session.commit()

        return {"message": f"Course approved has been set. New value: {new_approve}"}