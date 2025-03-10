from ..model.studentCourseSelection import StudentCourseSelection,StudentCourseSelectionSchema
from .. import db
from werkzeug.exceptions import NotFound

class StudentCourseSelectionService:
    @staticmethod
    def get_by_id(selection_id):
        selection_query = StudentCourseSelection.query.filter_by(id=selection_id).first()
        schema = StudentCourseSelectionSchema()
        selection = schema.dump(selection_query)
        if not selection:
            raise NotFound(f"Selection with id {selection_id} not found")
        return selection

    @staticmethod
    def get_by_student_id(student_id):
        student_courses_query = StudentCourseSelection.query.filter(StudentCourseSelection.studentId == student_id).all()
        schema = StudentCourseSelectionSchema(many = True)
        student_courses = schema.dump(student_courses_query)
        if not student_courses:
            raise NotFound(f"Student with id {student_id} not found")
        return student_courses

    @staticmethod
    def get_by_course_id(course_id):
        course_students_query = StudentCourseSelection.query.filter(StudentCourseSelection.courseId == course_id).all()
        schema = StudentCourseSelectionSchema(many=True)
        course_students = schema.dump(course_students_query)
        if not course_students:
            raise NotFound(f"Course with id {course_id} not found")
        return course_students

    @staticmethod
    def add_selection(data):
        selection = StudentCourseSelection(
            studentId=data.get('studentId'),
            courseId=data.get('courseId')
        )

        db.session.add(selection)
        db.session.commit()

        return {"message": "added successfully"}

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
            studentId=data.get('studentId'),
            courseId=data.get('courseId')
        ).first()
        if not selection:
            return {"error": f"Selection not found"}
        new_approve = data.get('isApproved')
        selection.isApproved = new_approve
        db.session.commit()

        return {"message": f"Course approved has been set. New value: {new_approve}"}