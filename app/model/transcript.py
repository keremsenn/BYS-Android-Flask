from .. import db

class Transcript(db.Model):
    __tablename__ = "transcripts"

    id = db.Column("TranscriptID", db.Integer, primary_key=True, autoincrement=True)
    studentId = db.Column("StudentID", db.ForeignKey("students.StudentID"), nullable=False)
    courseId = db.Column("CourseID", db.ForeignKey("courses.CourseID"), nullable=False)
    grade = db.Column("Grade", db.String(2), nullable=False)