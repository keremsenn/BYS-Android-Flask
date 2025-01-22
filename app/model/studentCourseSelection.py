from .. import db

class StudentCourseSelection(db.Model):
    __tablename__ = "studentcourseselections"

    id = db.Column("SelectionID", db.Integer, primary_key=True, autoincrement=True)
    studentId = db.Column("StudentID", db.ForeignKey("students.StudentID"), nullable=False)
    courseId = db.Column("CourseID", db.ForeignKey("courses.CourseID"), nullable=False)
    selectionDate = db.Column("SelectionDate", db.DateTime, default=db.func.now(), nullable=False)
    isApproved = db.Column("IsApproved", db.Boolean, nullable=False, default=False)